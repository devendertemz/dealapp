package com.deals.dealapp.model;

import com.google.android.material.slider.Slider;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JewalleryModel {

    private String id;

    private String categoryName;
    private String categorydesc;
    private String image;
    private String trending;

    public JewalleryModel(String id, String categoryName, String categorydesc, String image, String trending) {
        this.id = id;
        this.categoryName = categoryName;
        this.categorydesc = categorydesc;
        this.image = image;
        this.trending = trending;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategorydesc() {
        return categorydesc;
    }

    public void setCategorydesc(String categorydesc) {
        this.categorydesc = categorydesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrending() {
        return trending;
    }

    public void setTrending(String trending) {
        this.trending = trending;
    }
}