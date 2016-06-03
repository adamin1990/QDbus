package com.adamin.android.qdbus.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.adamin.android.qdbus.fragment.BusNameFragment;
import com.adamin.android.qdbus.fragment.BusNumFragment;

/**
 * Created by Adam on 2016/5/28.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return BusNumFragment.newInstance();
            case 1:
                return BusNameFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
