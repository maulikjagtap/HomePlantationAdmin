package com.example.design.homeplantationadmin;

public  class VegetableModel {
    String vegetable_name;
    String vegetable_decription;
    String vegetable_url;
    String nursury_name;
    String vegetable_price;

    public VegetableModel(String vegetable_name, String vegetable_decription, String vegetable_url, String nursury_name, String vegetable_price) {
        this.vegetable_name = vegetable_name;
        this.vegetable_decription = vegetable_decription;
        this.vegetable_url = vegetable_url;
        this.nursury_name = nursury_name;
        this.vegetable_price = vegetable_price;
    }

    public VegetableModel() {
    }

    public String getVegetable_name() {
        return vegetable_name;
    }

    public void setVegetable_name(String vegetable_name) {
        this.vegetable_name = vegetable_name;
    }

    public String getVegetable_decription() {
        return vegetable_decription;
    }

    public void setVegetable_decription(String vegetable_decription) {
        this.vegetable_decription = vegetable_decription;
    }

    public String getVegetable_url() {
        return vegetable_url;
    }

    public void setVegetable_url(String vegetable_url) {
        this.vegetable_url = vegetable_url;
    }

    public String getNursury_name() {
        return nursury_name;
    }

    public void setNursury_name(String nursury_name) {
        this.nursury_name = nursury_name;
    }

    public String getVegetable_price() {
        return vegetable_price;
    }

    public void setVegetable_price(String vegetable_price) {
        this.vegetable_price = vegetable_price;
    }
}
