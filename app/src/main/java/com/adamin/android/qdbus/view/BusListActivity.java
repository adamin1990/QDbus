package com.adamin.android.qdbus.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
import com.adamin.android.qdbus.thirdparty.avloading.AvloadingDialog;
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
    @BindView(R.id.tv_linename)
    TextView tv_linename;
    @BindView(R.id.tv_from_end)
    TextView tv_from_end;
    @BindView(R.id.tv_time_in)
    TextView tv_time_in;


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

    private String lname,ldirection,begintime,endtime;

    private AvloadingDialog avloadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);
        ButterKnife.bind(this);
        init();
        getData();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle("606路-上行");
        avloadingDialog = new AvloadingDialog(this);
        avloadingDialog.setMessage("数据加载中..");
        avloadingDialog.diaogshow();
        busLineDomain= (BusLineDomain) getIntent().getSerializableExtra("busdomain");
        direction=busLineDomain.getDirect();
        lineDetailService= ServiceGenerator.createService(LineDetailService.class);
        realtimeService=ServiceGenerator.createService(RealtimeService.class);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        lineDetailDomains2=new ArrayList<>();
        realtimeDatasout=new ArrayList<>();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(direction==0){
                    direction=1;
                }else {
                    direction=0;
                }
                getData();
            }
        });


    }

    private void getData() {

        lineDetailService.getLineDetail(busLineDomain.getGuid(),direction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LineDetailRealWrapper>() {
                    @Override
                    public void call(LineDetailRealWrapper lineDetailRealWrapper) {
                        lineDetailDomains2.clear();
                      lineDetailDomains2.addAll(lineDetailRealWrapper.getData().getStandInfo());
                        lname=lineDetailRealWrapper.getData().getLName()+"";
                        ldirection=lineDetailRealWrapper.getData().getLDirection()+"";
                        begintime=lineDetailRealWrapper.getData().getLFStdFTime();
                        endtime=lineDetailRealWrapper.getData().getLFStdETime();
                        lineDetailAdapter=new LineDetailAdapter(getApplicationContext(),lineDetailDomains2);
                        recyclerView.setAdapter(lineDetailAdapter);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        avloadingDialog.dismiss();
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        avloadingDialog.dismiss();
                        if(TextUtils.isEmpty(busLineDomain.getBStation())){
                            tv_from_end.setText("");
                        }else {
                            tv_from_end.setText(busLineDomain.getDirect()==direction?busLineDomain.getBStation()+" 至 "
                                    +busLineDomain.getEStation():busLineDomain.getEStation()+" 至 "
                                    +busLineDomain.getBStation());
                        }

                        tv_linename.setText(lname+"");
                        tv_time_in.setText(begintime+"-"+endtime);
                        collapsingToolbarLayout.setTitle(lname+"-"+ldirection);
                        lineDetailAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
                            @Override
                            public void onListItemExpanded(int position) {
                                if(lastposition!=-1&&lastposition!=position){
                                    lineDetailAdapter.collapseParent(lastposition);
                                    realtimeDatasout.clear();
                                }
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
        avloadingDialog.diaogshow();
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
                        avloadingDialog.dismiss();
                        if(realtimeDatasout.size()==0){
                            Snackbar.make(recyclerView,"总站没有发车",Snackbar.LENGTH_LONG).show();
                            lineDetailAdapter.collapseParent(position);
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
