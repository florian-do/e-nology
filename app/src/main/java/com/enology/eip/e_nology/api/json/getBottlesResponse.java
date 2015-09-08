package com.enology.eip.e_nology.api.json;

import com.enology.eip.e_nology.api.json.object.Domain;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lolo on 17/04/2015.
 */
public class getBottlesResponse implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @Expose
    private Domain domain;
    @Expose
    private String year;
    @Expose
    private String desc;
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public Domain getDomain() {
        return domain;
    }

    public String getYear() {
        return year;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setName(String name) {
        this.name = name;
    }
}
