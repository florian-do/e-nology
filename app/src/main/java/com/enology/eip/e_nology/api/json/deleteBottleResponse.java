package com.enology.eip.e_nology.api.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by do_f on 21/01/16.
 */
public class deleteBottleResponse {
    @SerializedName("_id")
    @Expose
    private String  id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
