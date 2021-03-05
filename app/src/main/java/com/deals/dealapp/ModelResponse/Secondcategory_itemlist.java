package com.deals.dealapp.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Secondcategory_itemlist {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("catid")
    @Expose
    private String catid;
    @SerializedName("subcatename")
    @Expose
    private String subcatename;
    @SerializedName("subcatedesc")
    @Expose
    private Object subcatedesc;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getSubcatename() {
        return subcatename;
    }

    public void setSubcatename(String subcatename) {
        this.subcatename = subcatename;
    }

    public Object getSubcatedesc() {
        return subcatedesc;
    }

    public void setSubcatedesc(Object subcatedesc) {
        this.subcatedesc = subcatedesc;
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

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Secondcategory_itemlist{" +
                "id=" + id +
                ", catid='" + catid + '\'' +
                ", subcatename='" + subcatename + '\'' +
                ", subcatedesc=" + subcatedesc +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
