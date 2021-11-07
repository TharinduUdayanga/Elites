package com.example.food_app;

public class MainModel {
    String name,type,purl,price;

    MainModel()
    {

    }


    public MainModel(String name, String type, String purl, String price) {
        this.name = name;
        this.type = type;
        this.purl = purl;
        this.price = price;



    }





    public String getPrice() {
        return price;
    }

    public void getPrice(String price) {
        this.price = price;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
