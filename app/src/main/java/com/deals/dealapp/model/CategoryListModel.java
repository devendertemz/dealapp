package com.deals.dealapp.model;

/**
 * Created by Devender Singh on 21-feb-2021.
 *
 * @Email :  ds8287018255@gmail.com
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CategoryListModel {


    public String id,categoryname,image,categorydesc;

    public CategoryListModel(String id, String categoryname, String image, String categorydesc) {
        this.id = id;
        this.categoryname = categoryname;
        this.image = image;
        this.categorydesc = categorydesc;
    }

    public CategoryListModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategorydesc() {
        return categorydesc;
    }

    public void setCategorydesc(String categorydesc) {
        this.categorydesc = categorydesc;
    }
}
