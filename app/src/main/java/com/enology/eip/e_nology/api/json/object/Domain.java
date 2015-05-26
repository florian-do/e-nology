package com.enology.eip.e_nology.api.json.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lolo on 26/05/2015.
 */
public class Domain {
    @SerializedName("_id")
    @Expose
    private String Id;
    @Expose
    private String appellation;
    @Expose
    private String city;

    /**
     *
     * @return
     * The Id
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The appellation
     */
    public String getAppellation() {
        return appellation;
    }

    /**
     *
     * @param appellation
     * The appellation
     */
    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }
}
