package com.enology.eip.e_nology.api.json.object.getBottles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Lolo on 26/05/2015.
 */
public class Domain implements Serializable {
    @SerializedName("_id")
    @Expose
    private String Id;
    @Expose
    private String appellation;
    @Expose
    private String city;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
