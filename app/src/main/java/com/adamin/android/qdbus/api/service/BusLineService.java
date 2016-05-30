package com.adamin.android.qdbus.api.service;

import com.adamin.android.qdbus.domain.BusLineDomain;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Adam on 2016/5/30.
 */
public interface BusLineService {
    @GET("download/busLine.json")
    Observable<List<BusLineDomain>> getBusLines();
}
