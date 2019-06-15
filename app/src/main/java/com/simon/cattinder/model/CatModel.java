package com.simon.cattinder.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


public class CatModel {


    @SerializedName("id")
    public String image_id;

    @SerializedName("url")
    public String image_url;

    public CatModel(String image_id, String image_url) {
        this.image_id = image_id;
        this.image_url = image_url;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
