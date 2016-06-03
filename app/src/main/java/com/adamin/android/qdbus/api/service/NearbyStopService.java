package com.adamin.android.qdbus.api.service;

import com.adamin.android.qdbus.api.QdBusApi;
import com.adamin.android.qdbus.domain.nearbystop.NearByStopWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Adam on 2016/6/3.
 */
public interface NearbyStopService {

    @GET(QdBusApi.API_GETSTATIONS)
    Observable<NearByStopWrapper> getNearbyStops(@Query("NoteGuid") String noteguid);
}
