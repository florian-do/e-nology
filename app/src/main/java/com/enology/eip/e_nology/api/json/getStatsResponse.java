package com.enology.eip.e_nology.api.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by do_f on 20/01/16.
 */
public class getStatsResponse {
    @SerializedName("_id")
    @Expose
    private String  id;
    @Expose
    private bottle  user;
    @SerializedName("__v")
    @Expose
    private int     v;
    @Expose
    private String  created;
    @Expose
    private String  bottle_price_average;
    @SerializedName("past_most_expensive_bottle")
    @Expose
    private bottle  past_expensive;
    @SerializedName("past_oldest_bottle")
    @Expose
    private bottle  past_oldest;
    @SerializedName("current_most_expensive_bottle")
    @Expose
    private bottle  current_expensive;
    @SerializedName("current_oldest_bottle")
    @Expose
    private bottle  current_oldest;
    @Expose
    private int     nb_millesime;
    @SerializedName("millesime_per_year")
    @Expose
    private millesime   millesime;
    @Expose
    private String  preferred_type;
    @Expose
    private String  cave_price;
    @Expose
    private String  nbr_bottle_open;
    @Expose
    private String  nbr_type_rose;
    @Expose
    private String  nbr_type_white;
    @Expose
    private String  nbr_type_red;
    @Expose
    private String  nbr_bottle;
    @Expose
    private String  name;

    public getStatsResponse(String id, bottle user, int v, String created, String bottle_price_average, bottle past_expensive, bottle past_oldest, bottle current_expensive, bottle current_oldest, int nb_millesime, getStatsResponse.millesime millesime, String preferred_type, String cave_price, String nbr_bottle_open, String nbr_type_rose, String nbr_type_white, String nbr_type_red, String nbr_bottle, String name) {
        this.id = id;
        this.user = user;
        this.v = v;
        this.created = created;
        this.bottle_price_average = bottle_price_average;
        this.past_expensive = past_expensive;
        this.past_oldest = past_oldest;
        this.current_expensive = current_expensive;
        this.current_oldest = current_oldest;
        this.nb_millesime = nb_millesime;
        this.millesime = millesime;
        this.preferred_type = preferred_type;
        this.cave_price = cave_price;
        this.nbr_bottle_open = nbr_bottle_open;
        this.nbr_type_rose = nbr_type_rose;
        this.nbr_type_white = nbr_type_white;
        this.nbr_type_red = nbr_type_red;
        this.nbr_bottle = nbr_bottle;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public bottle getUser() {
        return user;
    }

    public void setUser(bottle user) {
        this.user = user;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getBottle_price_average() {
        return bottle_price_average;
    }

    public void setBottle_price_average(String bottle_price_average) {
        this.bottle_price_average = bottle_price_average;
    }

    public bottle getPast_expensive() {
        return past_expensive;
    }

    public void setPast_expensive(bottle past_expensive) {
        this.past_expensive = past_expensive;
    }

    public bottle getPast_oldest() {
        return past_oldest;
    }

    public void setPast_oldest(bottle past_oldest) {
        this.past_oldest = past_oldest;
    }

    public bottle getCurrent_expensive() {
        return current_expensive;
    }

    public void setCurrent_expensive(bottle current_expensive) {
        this.current_expensive = current_expensive;
    }

    public bottle getCurrent_oldest() {
        return current_oldest;
    }

    public void setCurrent_oldest(bottle current_oldest) {
        this.current_oldest = current_oldest;
    }

    public int getNb_millesime() {
        return nb_millesime;
    }

    public void setNb_millesime(int nb_millesime) {
        this.nb_millesime = nb_millesime;
    }

    public getStatsResponse.millesime getMillesime() {
        return millesime;
    }

    public void setMillesime(getStatsResponse.millesime millesime) {
        this.millesime = millesime;
    }

    public String getPreferred_type() {
        return preferred_type;
    }

    public void setPreferred_type(String preferred_type) {
        this.preferred_type = preferred_type;
    }

    public String getCave_price() {
        return cave_price;
    }

    public void setCave_price(String cave_price) {
        this.cave_price = cave_price;
    }

    public String getNbr_bottle_open() {
        return nbr_bottle_open;
    }

    public void setNbr_bottle_open(String nbr_bottle_open) {
        this.nbr_bottle_open = nbr_bottle_open;
    }

    public String getNbr_type_rose() {
        return nbr_type_rose;
    }

    public void setNbr_type_rose(String nbr_type_rose) {
        this.nbr_type_rose = nbr_type_rose;
    }

    public String getNbr_type_white() {
        return nbr_type_white;
    }

    public void setNbr_type_white(String nbr_type_white) {
        this.nbr_type_white = nbr_type_white;
    }

    public String getNbr_type_red() {
        return nbr_type_red;
    }

    public void setNbr_type_red(String nbr_type_red) {
        this.nbr_type_red = nbr_type_red;
    }

    public String getNbr_bottle() {
        return nbr_bottle;
    }

    public void setNbr_bottle(String nbr_bottle) {
        this.nbr_bottle = nbr_bottle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class bottle
    {
        @Expose
        private String  id;
        @Expose
        private String  name;

        public bottle(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class millesime
    {
        @Expose
        private List<String>    year;

        @Expose
        private List<Integer>   nb_year;

        public millesime(List<String> year, List<Integer> nb_year) {
            this.year = year;
            this.nb_year = nb_year;
        }

        public List<String> getYear() {
            return year;
        }

        public List<Integer> getNb_year() {
            return nb_year;
        }

        public void setYear(List<String> year) {
            this.year = year;
        }

        public void setNb_year(List<Integer> nb_year) {
            this.nb_year = nb_year;
        }
    }
}
