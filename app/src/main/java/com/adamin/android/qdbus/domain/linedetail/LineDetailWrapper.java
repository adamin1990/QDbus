package com.adamin.android.qdbus.domain.linedetail;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2016/5/31.
 */
public class LineDetailWrapper {

    @SerializedName("LGUID")
    @Expose
    private Integer lGUID;
    @SerializedName("LName")
    @Expose
    private String lName;
    @SerializedName("LDirection")
    @Expose
    private String lDirection;
    @SerializedName("LFStdFTime")
    @Expose
    private String lFStdFTime;
    @SerializedName("LFStdETime")
    @Expose
    private String lFStdETime;
    @SerializedName("Direct")
    @Expose
    private Integer direct;
    @Nullable
    @SerializedName("StandInfo")
    @Expose
    private List<LineDetailDomain> standInfo = new ArrayList<LineDetailDomain>();

    /**
     *
     * @return
     * The lGUID
     */
    public Integer getLGUID() {
        return lGUID;
    }

    /**
     *
     * @param lGUID
     * The LGUID
     */
    public void setLGUID(Integer lGUID) {
        this.lGUID = lGUID;
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
     * The lFStdFTime
     */
    public String getLFStdFTime() {
        return lFStdFTime;
    }

    /**
     *
     * @param lFStdFTime
     * The LFStdFTime
     */
    public void setLFStdFTime(String lFStdFTime) {
        this.lFStdFTime = lFStdFTime;
    }

    /**
     *
     * @return
     * The lFStdETime
     */
    public String getLFStdETime() {
        return lFStdETime;
    }

    /**
     *
     * @param lFStdETime
     * The LFStdETime
     */
    public void setLFStdETime(String lFStdETime) {
        this.lFStdETime = lFStdETime;
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

    /**
     *
     * @return
     * The standInfo
     */
    public List<LineDetailDomain> getStandInfo() {
        return standInfo;
    }

    /**
     *
     * @param standInfo
     * The StandInfo
     */
    public void setStandInfo(List<LineDetailDomain> standInfo) {
        this.standInfo = standInfo;
    }

}
