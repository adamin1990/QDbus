package com.adamin.android.qdbus.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.adamin.android.qdbus.api.service.RealtimeService;
import com.adamin.android.qdbus.domain.BusLineDomain;
import com.adamin.android.qdbus.domain.linedetail.LineDetailDomain;
import com.adamin.android.qdbus.domain.linedetail.LineDetailRealWrapper;
import com.adamin.android.qdbus.domain.realtime.RealTimeDataWrapper;
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
    private RealtimeService realtimeService;

    private LineDetailAdapter lineDetailAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<LineDetailDomain>  lineDetailDomains2;
    private List<RealtimeData>  realtimeDatasout; //某站点的公交车数量
    private int lastposition=-1;
    private int lastchildsize=-1;

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
        direction=busLineDomain.getDirect();
        lineDetailService= ServiceGenerator.createService(LineDetailService.class);
        realtimeService=ServiceGenerator.createService(RealtimeService.class);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        lineDetailDomains2=new ArrayList<>();
        realtimeDatasout=new ArrayList<>();
//        lineDetailAdapter=new LineDetailAdapter(getApplicationContext(),lineDetailDomains2);
//        recyclerView.setAdapter(lineDetailAdapter);


    }

    private void getData() {

        lineDetailService.getLineDetail(busLineDomain.getGuid(),direction)
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
                                getRealtime(position);
                                Log.e("展开","展开"+position);
                            }

                            @Override
                            public void onListItemCollapsed(int position) {
                                Log.e("闭合","闭合"+position);
                                lineDetailDomains2.get(position).setStrings(new ArrayList<RealtimeData>());
                                lineDetailAdapter.notifyChildItemRangeRemoved(position,0,realtimeDatasout.size());
                                realtimeDatasout.clear();


                            }
                        });
                    }
                });

    }

    public void getRealtime(final int position){
        realtimeService.getRealtimeData(String.valueOf(lineDetailDomains2.get(position).getBusLineId())
        ,String.valueOf(lineDetailDomains2.get(position).getBusOnewayId()),
                String.valueOf(lineDetailDomains2.get(position).getBusStopNumber()))
                .map(new Func1<RealTimeDataWrapper, List<RealtimeData>>() {
                    @Override
                    public List<RealtimeData> call(RealTimeDataWrapper realTimeDataWrapper) {
                        return realTimeDataWrapper.getData();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<RealtimeData>>() {
                    @Override
                    public void call(List<RealtimeData> realtimeDatas) {
                        realtimeDatasout.addAll(realtimeDatas);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        if(realtimeDatasout.size()==0){
                            Snackbar.make(recyclerView,"总站没有发车",Snackbar.LENGTH_LONG).show();
                        }else {
                            lineDetailDomains2.get(position).setStrings(realtimeDatasout);
                            lineDetailAdapter.notifyChildItemRangeInserted(position,0,realtimeDatasout.size());
                        }

                        lastposition=position;
                        lastchildsize=lineDetailDomains2.size();

                    }
                });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        lineDetailAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lineDetailAdapter.onRestoreInstanceState(savedInstanceState);
    }
}
