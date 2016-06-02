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
    private TextView shijian,haiyou,chepai;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public DetailChildHolder(View itemView) {
        super(itemView);
        shijian= (TextView) itemView.findViewById(R.id.tv_child1);
        haiyou= (TextView) itemView.findViewById(R.id.tv_haiyou);
        chepai= (TextView) itemView.findViewById(R.id.tv_chepai);
    }
    public void bind(RealtimeData realtimeData){
        shijian.setText(realtimeData.getBusStopName()+" ("+realtimeData.getActDatetime()+"到达"+")");
        haiyou.setText("还有"+realtimeData.getStationNum()+"站");
        chepai.setText(realtimeData.getBusId()+"");
//        zaina.setText(realtimeData.getBusStopName()+"("+realtimeData.getActDatetime()+")");
//        haiyou.setText("還有"+realtimeData.getStationNum()+"站");

    }
}
