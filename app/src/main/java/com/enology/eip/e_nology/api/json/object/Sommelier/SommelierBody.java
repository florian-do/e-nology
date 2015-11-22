package com.enology.eip.e_nology.api.json.object.Sommelier;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by do_f on 19/11/15.
 */
public class SommelierBody {
    @Expose
    private String  reqType;
    @Expose
    private boolean extended;
    @SerializedName("param")
    @Expose
    private SommelierBodyParam  param;

    public SommelierBody(String reqType, boolean extendted, SommelierBodyParam param) {
        this.reqType = reqType;
        this.extended = extendted;
        this.param = param;
    }

    public String getReqType() {
        return reqType;
    }

    public boolean isExtendted() {
        return extended;
    }

    public SommelierBodyParam getParam() {
        return param;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public void setExtendted(boolean extendted) {
        this.extended = extendted;
    }

    public void setParam(SommelierBodyParam param) {
        this.param = param;
    }
}
