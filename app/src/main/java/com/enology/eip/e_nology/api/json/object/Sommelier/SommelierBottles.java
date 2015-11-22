package com.enology.eip.e_nology.api.json.object.Sommelier;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by do_f on 19/11/15.
 */
public class SommelierBottles {
    @SerializedName("myBottles")
    @Expose
    private List<String> bottles;

    @SerializedName("bottles")
    @Expose
    private List<String> tmp;

    @SerializedName("recipes")
    @Expose
    private List<String> tmp1;

    public SommelierBottles(List<String> bottles, List<String> tmp, List<String> tmp1) {
        this.bottles = bottles;
        this.tmp = tmp;
        this.tmp1 = tmp1;
    }

    public List<String> getBottles() {
        return bottles;
    }

    public List<String> getTmp() {
        return tmp;
    }

    public List<String> getTmp1() {
        return tmp1;
    }

    public void setBottles(List<String> bottles) {
        this.bottles = bottles;
    }

    public void setTmp(List<String> tmp) {
        this.tmp = tmp;
    }

    public void setTmp1(List<String> tmp1) {
        this.tmp1 = tmp1;
    }
}
