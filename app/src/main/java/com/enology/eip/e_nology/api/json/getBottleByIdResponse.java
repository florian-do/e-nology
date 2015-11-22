package com.enology.eip.e_nology.api.json;

import com.enology.eip.e_nology.api.json.object.getBottleById.Domain;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by do_f on 19/11/15.
 */
public class getBottleByIdResponse implements Serializable {
    @Expose
    @SerializedName("_id")
    private String  id;

    @Expose
    private String  user;

    @Expose
    private Domain  domain;

    @Expose
    private String  created;

    @Expose
    private String  grade;

    @Expose
    private String  price;

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

    public getBottleByIdResponse(String id, String user, Domain domain, String created, String grade, String price, String grapetype, String cru, String year, String desc, String name) {
        this.id = id;
        this.user = user;
        this.domain = domain;
        this.created = created;
        this.grade = grade;
        this.price = price;
        this.grapetype = grapetype;
        this.cru = cru;
        this.year = year;
        this.desc = desc;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGrapetype() {
        return grapetype;
    }

    public void setGrapetype(String grapetype) {
        this.grapetype = grapetype;
    }

    public String getCru() {
        return cru;
    }

    public void setCru(String cru) {
        this.cru = cru;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
