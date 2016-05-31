package com.adamin.android.qdbus.domain.realtime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2016/5/31.
 */
public class RealTimeDataWrapper {

    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("errorMsg")
    @Expose
    private String errorMsg;
    @SerializedName("data")
    @Expose
    private List<RealtimeData> data = new ArrayList<RealtimeData>();

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
    public List<RealtimeData> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<RealtimeData> data) {
        this.data = data;
    }
}
