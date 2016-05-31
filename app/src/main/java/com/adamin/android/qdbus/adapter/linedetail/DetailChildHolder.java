package com.adamin.android.qdbus.adapter.linedetail;

import android.view.View;
import android.widget.TextView;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.domain.realtime.RealtimeData;
import com.adamin.android.qdbus.thirdparty.expandablerecyclerview.ViewHolder.ChildViewHolder;

/**
 * Created by Adam on 2016/5/31.
 */
public class DetailChildHolder extends ChildViewHolder {
    private TextView chepai,haiyou,zaina;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public DetailChildHolder(View itemView) {
        super(itemView);
        chepai= (TextView) itemView.findViewById(R.id.tv_child1);
    }
    public void bind(RealtimeData realtimeData){
        chepai.setText("你大爺的"+"");
//        zaina.setText(realtimeData.getBusStopName()+"("+realtimeData.getActDatetime()+")");
//        haiyou.setText("還有"+realtimeData.getStationNum()+"站");

    }
}
