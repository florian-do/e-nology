package com.enology.eip.e_nology.api.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by do_f on 26/11/15.
 */
public class addBottleToCaveResponse
{
    @Expose
    @SerializedName("__v")
    private int     v;

    @Expose
    private String  cave;

    @Expose
    private String  user;

    @Expose
    private String  domain;

    @Expose
    @SerializedName("_id")
    private String  id;

    @Expose
    private boolean consumed;

    @Expose
    private String  optimaldate;

    @Expose
    private String  created;

    @Expose
    private int     grade;

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

    public addBottleToCaveResponse(int v, String cave, String user, String domain, String id,
                                   boolean consumed, String optimaldate, String created, int grade,
                                   String price, String grapetype, String cru, String year,
                                   String desc, String name)
    {
        this.v = v;
        this.cave = cave;
        this.user = user;
        this.domain = domain;
        this.id = id;
        this.consumed = consumed;
        this.optimaldate = optimaldate;
        this.created = created;
        this.grade = grade;
        this.price = price;
        this.grapetype = grapetype;
        this.cru = cru;
        this.year = year;
        this.desc = desc;
        this.name = name;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public String getCave() {
        return cave;
    }

    public void setCave(String cave) {
        this.cave = cave;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public String getOptimaldate() {
        return optimaldate;
    }

    public void setOptimaldate(String optimaldate) {
        this.optimaldate = optimaldate;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
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


/*
    {
      "__v": 0,
      "cave": "55eaf0eab79fae0b00eee97f",
      "user": "56261e9c8b42f10b00cdbbad",
      "domain": "5563ace6e7147d0b00c9c152",
      "_id": "56575c02c818580b0011de23",
      "consumed": false,
      "optimaldate": "2015-11-26T19:22:42.085Z",
      "created": "2015-11-26T19:22:42.085Z",
      "grade": 3,
      "price": "55.30",
      "grapetype": "",
      "cru": "Côte de Nuits",
      "year": "2013",
      "desc": "[NFC]Côte de Nuits,Vosne Romanée,Village,Rouge,2013",
      "name": "[NFC]Vosne Romanée Alliance des Terroirs"
    }
 */