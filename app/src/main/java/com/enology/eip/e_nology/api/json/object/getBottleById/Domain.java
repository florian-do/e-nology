package com.enology.eip.e_nology.api.json.object.getBottleById;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by do_f on 19/11/15.
 */
public class Domain {
    @Expose
    @SerializedName("_id")
    private String  id;

    @Expose
    private String  user;

    @Expose
    private String  appellation;

    @SerializedName("__v")
    @Expose
    private String  v;

    @Expose
    private String  created;

    @Expose
    private List<String>    bottles;

    @Expose
    private String  zipcode;

    @Expose
    private String  city;

    @Expose
    private String  address;

    @Expose
    private String  owner;

    @Expose
    private String  desc;

    @Expose
    private String  name;

    public Domain(String id, String user, String appellation, String v, String created, List<String> bottles, String zipcode, String city, String address, String owner, String desc, String name) {
        this.id = id;
        this.user = user;
        this.appellation = appellation;
        this.v = v;
        this.created = created;
        this.bottles = bottles;
        this.zipcode = zipcode;
        this.city = city;
        this.address = address;
        this.owner = owner;
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

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<String> getBottles() {
        return bottles;
    }

    public void setBottles(List<String> bottles) {
        this.bottles = bottles;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
