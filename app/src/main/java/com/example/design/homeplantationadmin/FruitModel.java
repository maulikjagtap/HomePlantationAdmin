package com.example.design.homeplantationadmin;

public class FruitModel {
    String fruit_name;
    String fruit_decription;
    String fruit_url;
    String nursury_name;
    String fruit_price;

    public FruitModel(String fruit_name, String fruit_decription, String fruit_url, String nursury_name, String fruit_price) {
        this.fruit_name = fruit_name;
        this.fruit_decription = fruit_decription;
        this.fruit_url = fruit_url;
        this.nursury_name = nursury_name;
        this.fruit_price = fruit_price;
    }

    public FruitModel() {
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }

    public String getFruit_decription() {
        return fruit_decription;
    }

    public void setFruit_decription(String fruit_decription) {
        this.fruit_decription = fruit_decription;
    }

    public String getFruit_url() {
        return fruit_url;
    }

    public void setFruit_url(String fruit_url) {
        this.fruit_url = fruit_url;
    }

    public String getNursury_name() {
        return nursury_name;
    }

    public void setNursury_name(String nursury_name) {
        this.nursury_name = nursury_name;
    }

    public String getFruit_price() {
        return fruit_price;
    }

    public void setFruit_price(String fruit_price) {
        this.fruit_price = fruit_price;
    }
}
