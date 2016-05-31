package com.adamin.android.qdbus.domain.linedetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Adam on 2016/5/31.
 */
public class LineDetailRealWrapper {
    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("errorMsg")
    @Expose
    private String errorMsg;
    @SerializedName("data")
    @Expose
    private LineDetailWrapper data;

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
    public  LineDetailWrapper getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData( LineDetailWrapper data) {
        this.data = data;
    }
}
