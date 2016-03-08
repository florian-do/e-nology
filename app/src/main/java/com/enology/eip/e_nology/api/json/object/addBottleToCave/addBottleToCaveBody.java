package com.enology.eip.e_nology.api.json.object.addBottleToCave;

import com.google.gson.annotations.Expose;

/**
 * Created by do_f on 26/11/15.
 */
public class addBottleToCaveBody
{
    @Expose
    private String  domain;

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

    public addBottleToCaveBody(String domain, String grade, String price, String grapetype, String cru, String year, String desc, String name) {
        this.domain = domain;
        this.grade = grade;
        this.price = price;
        this.grapetype = grapetype;
        this.cru = cru;
        this.year = year;
        this.desc = desc;
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
