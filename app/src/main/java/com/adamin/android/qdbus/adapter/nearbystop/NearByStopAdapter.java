package com.adamin.android.qdbus.adapter.nearbystop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.domain.nearbystop.NearbyStopDomain;
import com.adamin.android.qdbus.domain.stationsearch.SearchBusDomain;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.POST;

/**
 * Created by Adam on 2016/6/3.
 */
public class NearByStopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NearbyStopDomain> nearbyStopDomains;

    private SearchBusDomain searchBusDomain;

    private static final int TYPE_HEADER=0;
    private static final int TYPE_ITEM=1;

    public NearByStopAdapter(List<NearbyStopDomain> nearbyStopDomains, SearchBusDomain searchBusDomain) {
        this.nearbyStopDomains = nearbyStopDomains;
        this.searchBusDomain = searchBusDomain;
    }


    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER){
            View view= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_nearby_stop_header,parent,false);
            return new HeadHoder(view);
        }else if(viewType==TYPE_ITEM){
         View view=LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_nearby_stop_item,parent,false);
            return new NeayByHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==TYPE_ITEM){
            ((NeayByHolder)holder).tv_road.setText(nearbyStopDomains.get(position-1).getLName()+"("+nearbyStopDomains.get(position-1).getLDirection()+")");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }else if(getItemViewType(position)==TYPE_HEADER){
            ((HeadHoder)holder).tv_hroad.setText(searchBusDomain.getName()+"");
            ((HeadHoder)holder).tv_hrwop.setText("位於"+searchBusDomain.getRoad());
        }

    }

    @Override
    public int getItemCount() {
        return nearbyStopDomains.size()+1;
    }


    class NeayByHolder extends RecyclerView.ViewHolder{
          @BindView(R.id.tv_nearby_stop_road)
        TextView tv_road;
        @BindView(R.id.tv_nearby_stop_wop)
        TextView tv_rwop;
        public NeayByHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class HeadHoder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_nearby_stop_header_road)
        TextView tv_hroad;
        @BindView(R.id.tv_nearby_stop_header_wop)
        TextView tv_hrwop;
        public HeadHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
