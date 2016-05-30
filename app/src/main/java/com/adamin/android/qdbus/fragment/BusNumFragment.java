package com.adamin.android.qdbus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.adapter.BusLineFilterAdapter;
import com.adamin.android.qdbus.api.ServiceGenerator;
import com.adamin.android.qdbus.api.service.BusLineService;
import com.adamin.android.qdbus.domain.BusLineDomain;
import com.adamin.android.qdbus.thirdparty.avloading.AvloadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Adam on 2016/5/28.
 */
public class BusNumFragment extends Fragment {
    private AvloadingDialog avloadingDialog;
    private List<BusLineDomain> busLineDomain;
    @BindView(R.id.auto_text)
    AppCompatAutoCompleteTextView autoCompleteTextView;
    private BusLineFilterAdapter busLineFilterAdapter;
    public static Fragment newInstance(){
        BusNumFragment busNumFragment=new BusNumFragment();
        return busNumFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_busnum,container,false);
        ButterKnife.bind(this,view);
        init();
        getData();

        return view;
    }

    private void init() {
        busLineDomain=new ArrayList<>();
        avloadingDialog=new AvloadingDialog(getActivity());
        avloadingDialog.setMessage("数据加载中..");
        avloadingDialog.diaogshow();
    }

    private void getData() {
        BusLineService busLineService=   ServiceGenerator.createService(BusLineService.class);
        busLineService.getBusLines()
                .
                        subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<BusLineDomain>>() {
                    @Override
                    public void call(List<BusLineDomain> busLineDomains) {
                        busLineDomain.addAll(busLineDomains);
                        busLineFilterAdapter=new BusLineFilterAdapter(getActivity(),busLineDomains);
                        autoCompleteTextView.setAdapter(busLineFilterAdapter);

                        for (int i = 0; i < busLineDomains.size(); i++) {
                            Log.e("线路", "线路" + busLineDomains.get(i).getLineName());
                        }
                        avloadingDialog.dismiss();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("错误", "错误" + throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                });
    }
}
