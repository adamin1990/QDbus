package com.adamin.android.qdbus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adamin.android.qdbus.R;

/**
 * Created by Adam on 2016/5/28.
 */
public class BusNumFragment extends Fragment {

    public static Fragment newInstance(){
        BusNumFragment busNumFragment=new BusNumFragment();
        return busNumFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_busnum,container,false);
        return view;
    }
}
