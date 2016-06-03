package com.adamin.android.qdbus.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.adapter.nearbystop.NearByStopAdapter;
import com.adamin.android.qdbus.api.ServiceGenerator;
import com.adamin.android.qdbus.api.service.NearbyStopService;
import com.adamin.android.qdbus.domain.nearbystop.NearByStopWrapper;
import com.adamin.android.qdbus.domain.nearbystop.NearbyStopDomain;
import com.adamin.android.qdbus.domain.stationsearch.SearchBusDomain;
import com.adamin.android.qdbus.thirdparty.avloading.AvloadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class NearByStopActivity extends AppCompatActivity {
        @BindView(R.id.toolbar)
        Toolbar toolbar;
    @BindView(R.id.recycyerview_nearby_stop)
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SearchBusDomain searchBusDomain;
    private List<NearbyStopDomain>  nearbyStopDomains;
     private NearByStopAdapter nearByStopAdapter;
    private AvloadingDialog avloadingDialog;

    private NearbyStopService nearbyStopService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_stop);
        ButterKnife.bind(this);

        init();
        getData();
    }


    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchBusDomain= (SearchBusDomain) getIntent().getSerializableExtra("searchbusdomain");
        nearbyStopDomains=new ArrayList<>();
        nearByStopAdapter=new NearByStopAdapter(nearbyStopDomains,searchBusDomain);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(nearByStopAdapter);
        nearbyStopService = ServiceGenerator.createService(NearbyStopService.class);
        avloadingDialog=new AvloadingDialog(this);
        avloadingDialog.setMessage("正在查询..");


    }

    private void getData() {
        avloadingDialog.diaogshow();
        nearbyStopService.getNearbyStops(searchBusDomain.getNoteGuid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NearByStopWrapper>() {
                    @Override
                    public void call(NearByStopWrapper nearByStopWrapper) {
                        if(nearByStopWrapper.getData()!=null){
                            nearbyStopDomains.addAll(nearByStopWrapper.getData());
                            nearByStopAdapter.notifyDataSetChanged();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Snackbar.make(recyclerView,"查询出错",Snackbar.LENGTH_SHORT).show();
                 avloadingDialog.dismiss();
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        avloadingDialog.dismiss();
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
