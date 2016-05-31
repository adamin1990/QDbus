package com.adamin.android.qdbus.domain.realtime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Adam on 2016/5/31.
 */
public class RealtimeData {

    @SerializedName("busStopName")
    @Expose
    private String busStopName;
    @SerializedName("actDatetime")
    @Expose
    private String actDatetime;
    @SerializedName("stationNum")
    @Expose
    private Integer stationNum;
    @SerializedName("lastBus")
    @Expose
    private Integer lastBus;
    @SerializedName("busId")
    @Expose
    private String busId;

    /**
     *
     * @return
     * The busStopName
     */
    public String getBusStopName() {
        return busStopName;
    }

    /**
     *
     * @param busStopName
     * The busStopName
     */
    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }

    /**
     *
     * @return
     * The actDatetime
     */
    public String getActDatetime() {
        return actDatetime;
    }

    /**
     *
     * @param actDatetime
     * The actDatetime
     */
    public void setActDatetime(String actDatetime) {
        this.actDatetime = actDatetime;
    }

    /**
     *
     * @return
     * The stationNum
     */
    public Integer getStationNum() {
        return stationNum;
    }

    /**
     *
     * @param stationNum
     * The stationNum
     */
    public void setStationNum(Integer stationNum) {
        this.stationNum = stationNum;
    }

    /**
     *
     * @return
     * The lastBus
     */
    public Integer getLastBus() {
        return lastBus;
    }

    /**
     *
     * @param lastBus
     * The lastBus
     */
    public void setLastBus(Integer lastBus) {
        this.lastBus = lastBus;
    }

    /**
     *
     * @return
     * The busId
     */
    public String getBusId() {
        return busId;
    }

    /**
     *
     * @param busId
     * The busId
     */
    public void setBusId(String busId) {
        this.busId = busId;
    }
}
