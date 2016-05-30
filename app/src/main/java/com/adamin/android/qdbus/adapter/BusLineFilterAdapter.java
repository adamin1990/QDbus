package com.adamin.android.qdbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.domain.BusLineDomain;
import com.adamin.android.qdbus.filter.BusLineFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2016/5/30.
 */
public class BusLineFilterAdapter extends ArrayAdapter<BusLineDomain> {

    private final List<BusLineDomain> dogs;
    public List<BusLineDomain> filteredDogs = new ArrayList<>();
    public BusLineFilterAdapter(Context context, List<BusLineDomain> dogs) {
        super(context, 0, dogs);
        this.dogs = dogs;
    }

    @Override
    public int getCount() {
        return filteredDogs.size();
    }

    @Override
    public Filter getFilter() {
        return new BusLineFilter(this, dogs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item from filtered list.
        BusLineDomain busLineDomain = filteredDogs.get(position);

        // Inflate your custom row layout as usual.
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.item_filter_list, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvLine = (TextView) convertView.findViewById(R.id.tv_line);
        tvName.setText(busLineDomain.getLineName()+"");
        tvLine.setText(busLineDomain.getBStation()+"-"+busLineDomain.getEStation());

        return convertView;
    }
}
