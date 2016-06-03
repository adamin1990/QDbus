package com.adamin.android.qdbus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.adapter.searchbyname.SearchByNameAdapter;
import com.adamin.android.qdbus.api.ServiceGenerator;
import com.adamin.android.qdbus.api.service.SearchByNameService;
import com.adamin.android.qdbus.domain.stationsearch.SearchBusDomain;
import com.adamin.android.qdbus.domain.stationsearch.SearchBusWrapper;
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
 * Created by Adam on 2016/6/3.
 */
public class BusNameFragment extends Fragment {
    @BindView(R.id.recyclerview_busname)
    RecyclerView recyclerView;
    @BindView(R.id.eidt_text_busname)
    EditText editText;
    private LinearLayoutManager linearLayoutManager;
    private SearchByNameService searchByNameService;
    private AvloadingDialog avloadingDialog;
    private List<SearchBusDomain> searchBusDomainsout;
    private SearchByNameAdapter searchByNameAdapter;
    private boolean havesearchContent=false;



    public static Fragment newInstance() {
        BusNameFragment busNameFragment = new BusNameFragment();
        return busNameFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_bus_name,container,false);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {
        searchByNameService= ServiceGenerator.createService(SearchByNameService.class);
        avloadingDialog=new AvloadingDialog(getActivity());
        avloadingDialog.setMessage("正在查询..");
        linearLayoutManager=new LinearLayoutManager(getContext());
        searchBusDomainsout=new ArrayList<>();
        searchByNameAdapter=new SearchByNameAdapter(searchBusDomainsout);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(searchByNameAdapter);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    if(TextUtils.isEmpty(v.getText().toString())){
                        Snackbar.make(recyclerView,"请输入文字后搜索",Snackbar.LENGTH_SHORT).show();
                        return true;
                    }
                    searchData(v.getText().toString());

                    //
                    return  true;
                }
                return false;
            }
        });

    }

    private void searchData(String keyword) {
        avloadingDialog.diaogshow();
        searchBusDomainsout.clear();
        searchByNameAdapter.notifyDataSetChanged();
        searchByNameService.searchByName(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SearchBusWrapper>() {
                    @Override
                    public void call(SearchBusWrapper searchBusWrapper) {
                          if(null==searchBusWrapper.getData()){
                              Log.e("查询结果","没有查询到内容");
                          }else {
                              Snackbar.make(recyclerView,"查询数据成功",Snackbar.LENGTH_SHORT).show();
                              searchBusDomainsout.addAll(searchBusWrapper.getData());
                              searchByNameAdapter.notifyDataSetChanged();
                          }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        avloadingDialog.dismiss();
                   Snackbar.make(recyclerView,"没有查询到结果",Snackbar.LENGTH_SHORT).show();
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                            avloadingDialog.dismiss();
                    }
                });

    }
}
