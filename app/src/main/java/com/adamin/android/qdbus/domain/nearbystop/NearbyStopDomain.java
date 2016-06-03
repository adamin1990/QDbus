package com.adamin.android.qdbus.domain.nearbystop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Adam on 2016/6/3.
 */
public class NearbyStopDomain {

    @SerializedName("Guid")
    @Expose
    private Integer guid;
    @SerializedName("LName")
    @Expose
    private String lName;
    @SerializedName("LDirection")
    @Expose
    private String lDirection;
    @SerializedName("DBusCard")
    @Expose
    private String dBusCard;
    @SerializedName("InTime")
    @Expose
    private String inTime;
    @SerializedName("SName")
    @Expose
    private String sName;
    @SerializedName("Distince")
    @Expose
    private String distince;
    @SerializedName("Direct")
    @Expose
    private Integer direct;

    /**
     *
     * @return
     * The guid
     */
    public Integer getGuid() {
        return guid;
    }

    /**
     *
     * @param guid
     * The Guid
     */
    public void setGuid(Integer guid) {
        this.guid = guid;
    }

    /**
     *
     * @return
     * The lName
     */
    public String getLName() {
        return lName;
    }

    /**
     *
     * @param lName
     * The LName
     */
    public void setLName(String lName) {
        this.lName = lName;
    }

    /**
     *
     * @return
     * The lDirection
     */
    public String getLDirection() {
        return lDirection;
    }

    /**
     *
     * @param lDirection
     * The LDirection
     */
    public void setLDirection(String lDirection) {
        this.lDirection = lDirection;
    }

    /**
     *
     * @return
     * The dBusCard
     */
    public String getDBusCard() {
        return dBusCard;
    }

    /**
     *
     * @param dBusCard
     * The DBusCard
     */
    public void setDBusCard(String dBusCard) {
        this.dBusCard = dBusCard;
    }

    /**
     *
     * @return
     * The inTime
     */
    public String getInTime() {
        return inTime;
    }

    /**
     *
     * @param inTime
     * The InTime
     */
    public void setInTime(String inTime) {
        this.inTime = inTime;
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
     * The distince
     */
    public String getDistince() {
        return distince;
    }

    /**
     *
     * @param distince
     * The Distince
     */
    public void setDistince(String distince) {
        this.distince = distince;
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
