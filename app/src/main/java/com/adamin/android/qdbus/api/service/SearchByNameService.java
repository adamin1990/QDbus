package com.adamin.android.qdbus.api.service;

import com.adamin.android.qdbus.api.QdBusApi;
import com.adamin.android.qdbus.domain.stationsearch.SearchBusWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Adam on 2016/6/3.
 */
public interface SearchByNameService {
    @GET(QdBusApi.API_SEARCH_WITHNAME)
    Observable<SearchBusWrapper> searchByName(@Query("standName") String keyword);
}
