package com.adamin.android.qdbus.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Adam on 2016/5/30.
 *   {
 "lineName": "隧道7路",
 "bStation": "利津路客运站",
 "eStation": "灵山卫公交枢纽站",
 "Guid": "300007",
 "Direct": 0
 }
 */
public class BusLineDomain implements Serializable{

    @SerializedName("lineName")
    @Expose
    private String lineName;
    @SerializedName("bStation")
    @Expose
    private String bStation;
    @SerializedName("eStation")
    @Expose
    private String eStation;
    @SerializedName("Guid")
    @Expose
    private String guid;
    @SerializedName("Direct")
    @Expose
    private Integer direct;

    /**
     *
     * @return
     * The lineName
     */
    public String getLineName() {
        return lineName;
    }

    /**
     *
     * @param lineName
     * The lineName
     */
    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    /**
     *
     * @return
     * The bStation
     */
    public String getBStation() {
        return bStation;
    }

    /**
     *
     * @param bStation
     * The bStation
     */
    public void setBStation(String bStation) {
        this.bStation = bStation;
    }

    /**
     *
     * @return
     * The eStation
     */
    public String getEStation() {
        return eStation;
    }

    /**
     *
     * @param eStation
     * The eStation
     */
    public void setEStation(String eStation) {
        this.eStation = eStation;
    }

    /**
     *
     * @return
     * The guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     *
     * @param guid
     * The Guid
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     *
     * @return
     * The direct
     */
    public Integer getDirect() {
        return direct;
    }

    /**
     *
     * @param direct
     * The Direct
     */
    public void setDirect(Integer direct) {
        this.direct = direct;
    }
}
