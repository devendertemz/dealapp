package com.deals.dealapp.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Getproducts_bybrandResp {

    private String status,id,catId,subcatId,productName;


    private String productType;

        private String images;
        private String productDesc;
        private String price;
        private String discount;
        private String actualAmount;
        private String quantity;
        private String stock;
        private String createdAt;
        private String updatedAt;

        private String subcatename;

    private String categoryname;
    private String categorydesc;
    private String icon;

        private String subcatedesc;
        private String image;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getSubcatId() {
        return subcatId;
    }

    public void setSubcatId(String subcatId) {
        this.subcatId = subcatId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
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

    public String getSubcatename() {
        return subcatename;
    }

    public void setSubcatename(String subcatename) {
        this.subcatename = subcatename;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategorydesc() {
        return categorydesc;
    }

    public void setCategorydesc(String categorydesc) {
        this.categorydesc = categorydesc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSubcatedesc() {
        return subcatedesc;
    }

    public void setSubcatedesc(String subcatedesc) {
        this.subcatedesc = subcatedesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Getproducts_bybrandResp() {
    }
}