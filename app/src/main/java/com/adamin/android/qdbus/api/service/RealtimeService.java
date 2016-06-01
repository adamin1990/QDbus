package com.adamin.android.qdbus.api.service;

import com.adamin.android.qdbus.api.QdBusApi;
import com.adamin.android.qdbus.domain.realtime.RealTimeDataWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Adam on 2016/6/1.
 */
public interface RealtimeService {
    /*busLineId=1&busOnewayId=8525637&busStopNumber=3*/
    @GET(QdBusApi.API_GET_BUS_DETAIL)
    Observable<RealTimeDataWrapper> getRealtimeData(@Query("busLineId") String buslineid,@Query("busOnewayId") String busonewayid
    ,@Query("busStopNumber") String bustopnumber);
}
