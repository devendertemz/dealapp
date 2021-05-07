package com.deals.dealapp.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Get_trending_categories_Response {
    @Override
    public String toString() {
        return "Get_trending_categories_Response{" +
                "id='" + id + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categorydesc='" + categorydesc + '\'' +
                ", image='" + image + '\'' +
                ", trending='" + trending + '\'' +
                ", singleTrending='" + singleTrending + '\'' +
                ", upgradeYourHome='" + upgradeYourHome + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("categorydesc")
    @Expose
    private String categorydesc;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("trending")
    @Expose
    private String trending;
    @SerializedName("single_trending")
    @Expose
    private String singleTrending;
    @SerializedName("upgrade_your_home")
    @Expose
    private String upgradeYourHome;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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

    public String getSingleTrending() {
        return singleTrending;
    }

    public void setSingleTrending(String singleTrending) {
        this.singleTrending = singleTrending;
    }

    public String getUpgradeYourHome() {
        return upgradeYourHome;
    }

    public void setUpgradeYourHome(String upgradeYourHome) {
        this.upgradeYourHome = upgradeYourHome;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
