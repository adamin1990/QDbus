package com.adamin.android.qdbus.filter;

import android.widget.Filter;

import com.adamin.android.qdbus.adapter.BusLineFilterAdapter;
import com.adamin.android.qdbus.domain.BusLineDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2016/5/30.
 */
public class BusLineFilter extends Filter {

    BusLineFilterAdapter adapter;
    List<BusLineDomain> originalList;
    List<BusLineDomain> filteredList;

    public BusLineFilter(BusLineFilterAdapter adapter, List<BusLineDomain> originalList) {
        this.adapter = adapter;
        this.originalList = originalList;
        this.filteredList=new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        final FilterResults results = new FilterResults();

        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            final String filterPattern = constraint.toString().toLowerCase().trim();

            // Your filtering logic goes in here
            for (final BusLineDomain dog : originalList) {
                if (dog.getLineName().contains(filterPattern)) {
                    filteredList.add(dog);
                }
            }
        }
        results.values = filteredList;
        results.count = filteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.filteredDogs.clear();
        adapter.filteredDogs.addAll((List) results.values);
        adapter.notifyDataSetChanged();
    }
}
