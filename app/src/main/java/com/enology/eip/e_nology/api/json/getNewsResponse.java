package com.enology.eip.e_nology.api.json;

import com.enology.eip.e_nology.api.json.object.Sommelier.SommelierBottles;
import com.enology.eip.e_nology.api.json.object.Sommelier.SommelierParam;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by do_f on 19/01/16.
 */
public class getNewsResponse implements Serializable {
    @SerializedName("_id")
    @Expose
    private String  id;
    @SerializedName("newsType")
    @Expose
    private String  type;
    @Expose
    private String  title;
    @SerializedName("__v")
    @Expose
    private int     v;
    @Expose
    private String  imgurl;
    @Expose
    private String  body;
    @Expose
    private String  created;

    public getNewsResponse(String id, String type, String title, int v, String imgurl, String body, String created) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.v = v;
        this.imgurl = imgurl;
        this.body = body;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getV() {
        return v;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getBody() {
        return body;
    }

    public String getCreated() {
        return created;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}

