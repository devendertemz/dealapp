package com.deals.dealapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpgradeHomeModel {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("categoryname")
    @Expose
    private String categoryname;
    @SerializedName("categorydesc")
    @Expose
    private Object categorydesc;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Object getCategorydesc() {
        return categorydesc;
    }

    public void setCategorydesc(Object categorydesc) {
        this.categorydesc = categorydesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "CategoryListModel{" +
                "id=" + id +
                ", categoryname='" + categoryname + '\'' +
                ", categorydesc=" + categorydesc +
                ", image='" + image + '\'' +
                ", icon='" + icon + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

}
