package com.adamin.android.qdbus.domain.stationsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Adam on 2016/6/3.
 */
public class SearchBusDomain {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("NoteGuid")
    @Expose
    private String noteGuid;
    @SerializedName("Canton")
    @Expose
    private String canton;
    @SerializedName("Road")
    @Expose
    private String road;
    @SerializedName("Direct")
    @Expose
    private String direct;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The noteGuid
     */
    public String getNoteGuid() {
        return noteGuid;
    }

    /**
     *
     * @param noteGuid
     * The NoteGuid
     */
    public void setNoteGuid(String noteGuid) {
        this.noteGuid = noteGuid;
    }

    /**
     *
     * @return
     * The canton
     */
    public String getCanton() {
        return canton;
    }

    /**
     *
     * @param canton
     * The Canton
     */
    public void setCanton(String canton) {
        this.canton = canton;
    }

    /**
     *
     * @return
     * The road
     */
    public String getRoad() {
        return road;
    }

    /**
     *
     * @param road
     * The Road
     */
    public void setRoad(String road) {
        this.road = road;
    }

    /**
     *
     * @return
     * The direct
     */
    public String getDirect() {
        return direct;
    }

    /**
     *
     * @param direct
     * The Direct
     */
    public void setDirect(String direct) {
        this.direct = direct;
    }

}
