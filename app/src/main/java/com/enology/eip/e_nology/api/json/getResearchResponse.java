package com.enology.eip.e_nology.api.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lolo on 09/09/2015.
 */
public class getResearchResponse implements Serializable {
    @SerializedName("_id")
    @Expose
    private String      id;
    @SerializedName("__v")
    @Expose
    private String      v;
    @Expose
    private String      created;
    @Expose
    private String      imgurl;
    @Expose
    private String      desc;
    @Expose
    private String      main;
    @Expose
    private String      name;
    @SerializedName("tags")
    @Expose
    private List<String> tagsList;

    public String getId() {
        return id;
    }

    public String getV() {
        return v;
    }

    public String getCreated() {
        return created;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getDesc() {
        return desc;
    }

    public String getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public List<String> getTagsList() {
        return tagsList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setV(String v) {
        this.v = v;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTagsList(List<String> tagsList) {
        this.tagsList = tagsList;
    }
}
