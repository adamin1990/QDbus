package com.adamin.android.qdbus.adapter.linedetail;

import android.view.View;
import android.widget.TextView;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.domain.linedetail.LineDetailDomain;
import com.adamin.android.qdbus.thirdparty.expandablerecyclerview.ViewHolder.ParentViewHolder;

/**
 * Created by Adam on 2016/5/31.
 */
public class DetailParentHolder extends ParentViewHolder {
    private TextView tvbusstationname;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public DetailParentHolder(View itemView) {
        super(itemView);
        tvbusstationname= (TextView) itemView.findViewById(R.id.tv_parent);
    }

    public void bind(LineDetailDomain lineDetailDomain){
        tvbusstationname.setText(lineDetailDomain.getSName()+"");
    }

}
