package com.adamin.android.qdbus.api.service;

import com.adamin.android.qdbus.api.QdBusApi;
import com.adamin.android.qdbus.domain.linedetail.LineDetailRealWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Adam on 2016/5/31.
 */
public interface LineDetailService {

    @GET(QdBusApi.API_GET_BUSBYNUMBER)
    Observable<LineDetailRealWrapper> getLineDetail(@Query("Guid") String guid,@Query("Direct") int direct);
}
