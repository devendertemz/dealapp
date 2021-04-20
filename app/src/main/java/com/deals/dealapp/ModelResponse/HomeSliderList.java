package com.deals.dealapp.ModelResponse;

public class HomeSliderList {
    public String Name,Offer,prodcut,Image;

    public HomeSliderList(String name, String offer, String prodcut, String image) {
        Name = name;
        Offer = offer;
        this.prodcut = prodcut;
        Image = image;
    }


    public String getProdcut() {
        return prodcut;
    }

    public void setProdcut(String prodcut) {
        this.prodcut = prodcut;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOffer() {
        return Offer;
    }

    public void setOffer(String offer) {
        Offer = offer;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
