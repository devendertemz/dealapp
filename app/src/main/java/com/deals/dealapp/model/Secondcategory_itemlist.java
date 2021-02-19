package com.deals.dealapp.model;

public class Secondcategory_itemlist {
    public String text;
    public String color;
    public int drawable;

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public Secondcategory_itemlist(String text, String color, int drawable) {
        this.text = text;
        this.color = color;
        this.drawable = drawable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Secondcategory_itemlist(String text, String color) {
        this.text = text;
        this.color = color;
    }
}
