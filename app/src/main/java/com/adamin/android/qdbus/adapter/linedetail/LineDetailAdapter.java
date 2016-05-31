package com.adamin.android.qdbus.adapter.linedetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.domain.linedetail.LineDetailDomain;
import com.adamin.android.qdbus.domain.realtime.RealtimeData;
import com.adamin.android.qdbus.thirdparty.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.adamin.android.qdbus.thirdparty.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by Adam on 2016/5/31.
 */
public class LineDetailAdapter extends ExpandableRecyclerAdapter<DetailParentHolder,DetailChildHolder> {
    private LayoutInflater mInflater;
    public LineDetailAdapter(Context cotext,@NonNull List<? extends ParentListItem> parentItemList)
    {
        super(parentItemList);
        mInflater=LayoutInflater.from(cotext);
    }


    @Override
    public DetailParentHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View parentview = mInflater.inflate(R.layout.item_expand_parent, parentViewGroup, false);
        return new DetailParentHolder(parentview);
    }

    @Override
    public DetailChildHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View childview = mInflater.inflate(R.layout.item_expand_child, childViewGroup, false);
        return new DetailChildHolder(childview);
    }

    @Override
    public void onBindParentViewHolder(DetailParentHolder parentViewHolder, int position, ParentListItem parentListItem) {
        LineDetailDomain lineDetailDomain= (LineDetailDomain) parentListItem;
        parentViewHolder.bind(lineDetailDomain);

    }

    @Override
    public void onBindChildViewHolder(DetailChildHolder childViewHolder, int position, Object childListItem) {
        RealtimeData realtimeData= (RealtimeData) childListItem;
        childViewHolder.bind(realtimeData);

    }
}
