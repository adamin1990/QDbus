package com.adamin.android.qdbus.domain.nearbystop;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2016/6/3.
 */
public class NearByStopWrapper {


    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("errorMsg")
    @Expose
    private String errorMsg;
    @Nullable
    @SerializedName("data")
    @Expose
    private List<NearbyStopDomain> data = new ArrayList<NearbyStopDomain>();

    /**
     *
     * @return
     * The errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     *
     * @param errorCode
     * The errorCode
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     *
     * @return
     * The errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     *
     * @param errorMsg
     * The errorMsg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     *
     * @return
     * The data
     */
    public List<NearbyStopDomain> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<NearbyStopDomain> data) {
        this.data = data;
    }
}
