package com.adamin.android.qdbus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.adapter.BusLineFilterAdapter;
import com.adamin.android.qdbus.api.ServiceGenerator;
import com.adamin.android.qdbus.api.service.BusLineService;
import com.adamin.android.qdbus.db.DBManager;
import com.adamin.android.qdbus.domain.BusLineDomain;
import com.adamin.android.qdbus.thirdparty.avloading.AvloadingDialog;
import com.adamin.android.qdbus.thirdparty.tagview.OnTagClickListener;
import com.adamin.android.qdbus.thirdparty.tagview.OnTagDeleteListener;
import com.adamin.android.qdbus.thirdparty.tagview.Tag;
import com.adamin.android.qdbus.thirdparty.tagview.TagView;
import com.adamin.android.qdbus.view.BusListActivity;

import java.util.ArrayList;
import java.util.Arrays;
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
    private static final int MESSAGE_TAG = 0x1;
    private AvloadingDialog avloadingDialog;
    private List<BusLineDomain> busLineDomain;
    private List<BusLineDomain> history;
    @BindView(R.id.auto_text)
    AppCompatAutoCompleteTextView autoCompleteTextView;
    @BindView(R.id.tagview)
    TagView tagView;
    private BusLineFilterAdapter busLineFilterAdapter;
    private DBManager dbManager;

    public static Fragment newInstance() {
        BusNumFragment busNumFragment = new BusNumFragment();
        return busNumFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busnum, container, false);
        ButterKnife.bind(this, view);
        init();
        getData();

        return view;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_TAG) {
                tagView.removeAllTags();
                history.clear();
                history = dbManager.query();
                for (int i = 0; i < history.size(); i++) {
                    Tag tag = new Tag(history.get(i).getLineName());
                    tag.tagTextSize = 16;
                    tag.isDeletable = true;
                    tag.layoutColor = R.color.md_brown_500;
                    tagView.addTag(tag);
                }
            }
            super.handleMessage(msg);
        }
    };

    private void init() {
        dbManager = DBManager.getInstance(getActivity());
        history = new ArrayList<>();
        busLineDomain = new ArrayList<>();
        avloadingDialog = new AvloadingDialog(getActivity());
        avloadingDialog.setMessage("正在查询..");
        avloadingDialog.diaogshow();
        autoCompleteTextView.setDropDownAnchor(R.id.auto_text);
        busLineFilterAdapter = new BusLineFilterAdapter(getActivity(), busLineDomain);
        autoCompleteTextView.setAdapter(busLineFilterAdapter);
        busLineFilterAdapter.setOnBusItemClickListener(new BusLineFilterAdapter.OnBusItemClickListener() {
            @Override
            public void onBusItemClick(BusLineDomain busLineDomain) {
                if (history.size() == 15) {
                    BusLineDomain busLineDomain1 = history.get(14);
                    history.remove(14);
                    dbManager.delete(busLineDomain1);
                }
                dbManager.add(busLineDomain);
                handler.sendEmptyMessage(MESSAGE_TAG);
                Intent intent = new Intent(getActivity(), BusListActivity.class);
                intent.putExtra("busdomain", busLineDomain);
                startActivity(intent);

            }
        });
        tagView.setOnTagDeleteListener(new OnTagDeleteListener() {
            @Override
            public void onTagDeleted(Tag tag, int position) {
                dbManager.delete(history.get(position));
                history.remove(position);
                handler.sendEmptyMessage(MESSAGE_TAG);
            }
        });
        tagView.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int position) {
                Intent intent = new Intent(getActivity(), BusListActivity.class);
                intent.putExtra("busdomain", history.get(position));
                startActivity(intent);
            }
        });
        history = dbManager.query();
        for (int i = 0; i < history.size(); i++) {
            Tag tag = new Tag(history.get(i).getLineName());
            tag.tagTextSize = 16;
            tag.isDeletable = true;
            tag.layoutColor = R.color.md_green_500;
            tagView.addTag(tag);
        }
    }

    private void getData() {
        BusLineService busLineService = ServiceGenerator.createService(BusLineService.class);
        busLineService.getBusLines()
                .
                        subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<BusLineDomain>>() {
                    @Override
                    public void call(List<BusLineDomain> busLineDomains) {
                        busLineDomain.addAll(busLineDomains);
                        busLineFilterAdapter.notifyDataSetChanged();
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
