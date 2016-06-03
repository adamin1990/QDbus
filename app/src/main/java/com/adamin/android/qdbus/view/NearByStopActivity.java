package com.adamin.android.qdbus.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adamin.android.qdbus.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NearByStopActivity extends AppCompatActivity {
        @BindView(R.id.toolbar)
        Toolbar toolbar;
    @BindView(R.id.recycyerview_nearby_stop)
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_stop);
        ButterKnife.bind(this);
    }

}
