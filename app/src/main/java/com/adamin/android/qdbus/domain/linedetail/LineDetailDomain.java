package com.adamin.android.qdbus.domain.linedetail;

import com.adamin.android.qdbus.domain.realtime.RealtimeData;
import com.adamin.android.qdbus.thirdparty.expandablerecyclerview.Model.ParentListItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2016/5/31.
 */
public class LineDetailDomain implements ParentListItem{

    @SerializedName("SGuid")
    @Expose
    private String sGuid;
    @SerializedName("SName")
    @Expose
    private String sName;
    @SerializedName("busLineId")
    @Expose
    private Integer busLineId;
    @SerializedName("busOnewayId")
    @Expose
    private Integer busOnewayId;
    @SerializedName("busStopNumber")
    @Expose
    private Integer busStopNumber;


    private List<RealtimeData> strings=new ArrayList<>();

    public List<RealtimeData> getStrings() {
        return strings;
    }

    public void setStrings(List<RealtimeData> strings) {
        this.strings = strings;
    }

    /**
     *
     * @return
     * The sGuid
     */
    public String getSGuid() {
        return sGuid;
    }

    /**
     *
     * @param sGuid
     * The SGuid
     */
    public void setSGuid(String sGuid) {
        this.sGuid = sGuid;
    }

    /**
     *
     * @return
     * The sName
     */
    public String getSName() {
        return sName;
    }

    /**
     *
     * @param sName
     * The SName
     */
    public void setSName(String sName) {
        this.sName = sName;
    }

    /**
     *
     * @return
     * The busLineId
     */
    public Integer getBusLineId() {
        return busLineId;
    }

    /**
     *
     * @param busLineId
     * The busLineId
     */
    public void setBusLineId(Integer busLineId) {
        this.busLineId = busLineId;
    }

    /**
     *
     * @return
     * The busOnewayId
     */
    public Integer getBusOnewayId() {
        return busOnewayId;
    }

    /**
     *
     * @param busOnewayId
     * The busOnewayId
     */
    public void setBusOnewayId(Integer busOnewayId) {
        this.busOnewayId = busOnewayId;
    }

    /**
     *
     * @return
     * The busStopNumber
     */
    public Integer getBusStopNumber() {
        return busStopNumber;
    }

    /**
     *
     * @param busStopNumber
     * The busStopNumber
     */
    public void setBusStopNumber(Integer busStopNumber) {
        this.busStopNumber = busStopNumber;
    }

    @Override
    public List<?> getChildItemList() {
        return strings;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
