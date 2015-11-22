package com.enology.eip.e_nology.api.json;

import com.enology.eip.e_nology.api.json.object.Sommelier.SommelierBottles;
import com.enology.eip.e_nology.api.json.object.Sommelier.SommelierParam;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by do_f on 19/11/15.
 */
public class SommelierResponse
{
    @SerializedName("__v")
    @Expose
    private int     v;
    @Expose
    private String  reqType;
    @SerializedName("_id")
    @Expose
    private String  id;
    @Expose
    private String  created;
    @SerializedName("results")
    @Expose
    private SommelierBottles bottles;
    @SerializedName("param")
    @Expose
    private SommelierParam param;
    @Expose
    private boolean  extended;

    public SommelierResponse(int v, String reqType, String id, String created, SommelierBottles bottles, SommelierParam param, boolean extended) {
        this.v = v;
        this.reqType = reqType;
        this.id = id;
        this.created = created;
        this.bottles = bottles;
        this.param = param;
        this.extended = extended;
    }

    public boolean isExtended() {
        return extended;
    }

    public int getV() {
        return v;
    }

    public String getReqType() {
        return reqType;
    }

    public String getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public SommelierBottles getBottles() {
        return bottles;
    }

    public SommelierParam getParam() {
        return param;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setBottles(SommelierBottles bottles) {
        this.bottles = bottles;
    }

    public void setParam(SommelierParam param) {
        this.param = param;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }
}