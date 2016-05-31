package com.adamin.android.qdbus.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.adapter.linedetail.LineDetailAdapter;
import com.adamin.android.qdbus.api.ServiceGenerator;
import com.adamin.android.qdbus.api.service.LineDetailService;
import com.adamin.android.qdbus.domain.BusLineDomain;
import com.adamin.android.qdbus.domain.linedetail.LineDetailDomain;
import com.adamin.android.qdbus.domain.linedetail.LineDetailRealWrapper;
import com.adamin.android.qdbus.domain.realtime.RealtimeData;
import com.adamin.android.qdbus.thirdparty.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class BusListActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingtoobar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private int direction=0;
    private BusLineDomain busLineDomain;
   private LineDetailService lineDetailService;

    private LineDetailAdapter lineDetailAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<LineDetailDomain>  lineDetailDomains2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);
        ButterKnife.bind(this);
        init();
        getData();
//        setNotRealData();

    }

//    private void setNotRealData() {
//        RealtimeData realtimeData=new RealtimeData();
//        realtimeData.setBusStopName("青島大劇院1");
//        RealtimeData realtimeData2=new RealtimeData();
//        realtimeData2.setBusStopName("青島大劇院2");
//        List<RealtimeData> realtimeDatas= Arrays.asList(realtimeData,realtimeData2);
//        LineDetailDomain lineDetailDomain=new LineDetailDomain();
//        lineDetailDomain.setStrings(realtimeDatas);
//        LineDetailDomain lineDetailDomain2=new LineDetailDomain();
//        lineDetailDomain2.setStrings(realtimeDatas);
//        List<LineDetailDomain> lineDetailDomains222=Arrays.asList(lineDetailDomain,lineDetailDomain2);
//        lineDetailAdapter=new LineDetailAdapter(this,lineDetailDomains222);
//        recyclerView.setAdapter(lineDetailAdapter);
//
//    }


    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle("606路-上行");
        busLineDomain= (BusLineDomain) getIntent().getSerializableExtra("busdomain");
        lineDetailService= ServiceGenerator.createService(LineDetailService.class);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        lineDetailDomains2=new ArrayList<>();
//        lineDetailAdapter=new LineDetailAdapter(getApplicationContext(),lineDetailDomains2);
//        recyclerView.setAdapter(lineDetailAdapter);


    }

    private void getData() {

        lineDetailService.getLineDetail("606",0)
                .map(new Func1<LineDetailRealWrapper, List<LineDetailDomain>>() {
                    @Override
                    public List<LineDetailDomain> call(LineDetailRealWrapper lineDetailRealWrapper) {

                        return lineDetailRealWrapper.getData().getStandInfo();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<LineDetailDomain>>() {
                    @Override
                    public void call(List<LineDetailDomain> lineDetailDomains) {
                      lineDetailDomains2.addAll(lineDetailDomains);
                        lineDetailAdapter=new LineDetailAdapter(getApplicationContext(),lineDetailDomains2);
                        recyclerView.setAdapter(lineDetailAdapter);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("錯誤","錯誤"+throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        lineDetailAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
                            @Override
                            public void onListItemExpanded(int position) {
                                RealtimeData realtimeData=new RealtimeData();
                                realtimeData.setBusStopName("青島大劇院1");
                                RealtimeData realtimeData2=new RealtimeData();
                                realtimeData2.setBusStopName("青島大劇院2");
                                List<RealtimeData> realtimeDatas= Arrays.asList(realtimeData,realtimeData2);
                                lineDetailDomains2.get(position).setStrings(realtimeDatas);
                                lineDetailAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onListItemCollapsed(int position) {

                            }
                        });
                    }
                });

    }
}
