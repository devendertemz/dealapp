package com.deals.dealapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JewalleryModelResponse {
    @SerializedName("heading")
    @Expose
    private String heading;
    @SerializedName("cate_desc")
    @Expose
    private String cateDesc;
    @SerializedName("slider")
    @Expose
    private List<Slider> slider = null;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getCateDesc() {
        return cateDesc;
    }

    public void setCateDesc(String cateDesc) {
        this.cateDesc = cateDesc;
    }

    public List<Slider> getSlider() {
        return slider;
    }

    public void setSlider(List<Slider> slider) {
        this.slider = slider;
    }
        public class Slider {

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
    }
