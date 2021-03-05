package com.deals.dealapp.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Devender Singh on 21-feb-2021.
 *
 * @Email :  ds8287018255@gmail.com
 */


public class CategoryListModel {




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
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
