package com.adamin.android.qdbus.adapter.searchbyname;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.domain.stationsearch.SearchBusDomain;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adam on 2016/6/3.
 */
public class SearchByNameAdapter extends RecyclerView.Adapter<SearchByNameAdapter.MyHolder> {

    private List<SearchBusDomain> searchBusDomains;

    public SearchByNameAdapter(List<SearchBusDomain> searchBusDomains) {
        this.searchBusDomains = searchBusDomains;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_bus,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tv_station.setText(searchBusDomains.get(position).getName()+"("+searchBusDomains.get(position).getRoad()+")");
        holder.tv_place.setText(searchBusDomains.get(position).getCanton()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return searchBusDomains.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
  @BindView(R.id.tv_search_station)
        TextView tv_station;
        @BindView(R.id.tv_search_place)
        TextView tv_place;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
