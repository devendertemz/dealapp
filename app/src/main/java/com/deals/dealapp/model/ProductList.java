package com.deals.dealapp.model;

public class ProductList {

    public String P_Name,P_Finalprice,P_OfferPrice,P_Taxdeatils;

    public int P_Iamge;

    public ProductList(String p_Name, String p_Finalprice, String p_OfferPrice, String p_Taxdeatils, int p_Iamge) {
        P_Name = p_Name;
        P_Finalprice = p_Finalprice;
        P_OfferPrice = p_OfferPrice;
        P_Taxdeatils = p_Taxdeatils;
        P_Iamge = p_Iamge;
    }

    public String getP_Name() {
        return P_Name;
    }

    public void setP_Name(String p_Name) {
        P_Name = p_Name;
    }

    public String getP_Finalprice() {
        return P_Finalprice;
    }

    public void setP_Finalprice(String p_Finalprice) {
        P_Finalprice = p_Finalprice;
    }

    public String getP_OfferPrice() {
        return P_OfferPrice;
    }

    public void setP_OfferPrice(String p_OfferPrice) {
        P_OfferPrice = p_OfferPrice;
    }

    public String getP_Taxdeatils() {
        return P_Taxdeatils;
    }

    public void setP_Taxdeatils(String p_Taxdeatils) {
        P_Taxdeatils = p_Taxdeatils;
    }

    public int getP_Iamge() {
        return P_Iamge;
    }

    public void setP_Iamge(int p_Iamge) {
        P_Iamge = p_Iamge;
    }
}
