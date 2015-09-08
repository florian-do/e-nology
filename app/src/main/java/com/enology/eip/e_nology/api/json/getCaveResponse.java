package com.enology.eip.e_nology.api.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Lolo on 08/09/2015.
 */
public class getCaveResponse implements Serializable {
    @SerializedName("_id")
    @Expose
    private String  id;
    @Expose
    private String  cave;
    @Expose
    private String  domain;
    @SerializedName("__v")
    @Expose
    private int     v;
    @Expose
    private boolean consumed;
    @Expose
    private String  optimaldate;
    @Expose
    private String  created;
    @Expose
    private double  grade;
    @Expose
    private double  price;
    @Expose
    private String  grapetype;
    @Expose
    private String  cru;
    @Expose
    private String  year;
    @Expose
    private String  desc;
    @Expose
    private String  name;


    public String getId() {
        return id;
    }

    public String getCave() {
        return cave;
    }

    public String getDomain() {
        return domain;
    }

    public int getV() {
        return v;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public String getOptimaldate() {
        return optimaldate;
    }

    public String getCreated() {
        return created;
    }

    public double getGrade() {
        return grade;
    }

    public double getPrice() {
        return price;
    }

    public String getGrapetype() {
        return grapetype;
    }

    public String getCru() {
        return cru;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCave(String cave) {
        this.cave = cave;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public void setOptimaldate(String optimaldate) {
        this.optimaldate = optimaldate;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setGrapetype(String grapetype) {
        this.grapetype = grapetype;
    }

    public void setCru(String cru) {
        this.cru = cru;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
