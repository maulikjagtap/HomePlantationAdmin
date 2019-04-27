package com.example.design.homeplantationadmin;

public class SpecialplantModel {
    String specialplant_name;
    String specialplant_decription;
    String specialplant_url;
    String nursury_name;
    String specialplant_price;

    public SpecialplantModel(String specialplant_name, String specialplant_decription, String specialplant_url, String nursury_name, String specialplant_price) {
        this.specialplant_name = specialplant_name;
        this.specialplant_decription = specialplant_decription;
        this.specialplant_url = specialplant_url;
        this.nursury_name = nursury_name;
        this.specialplant_price = specialplant_price;
    }

    public SpecialplantModel() {
    }

    public String getSpecialplant_name() {
        return specialplant_name;
    }

    public void setSpecialplant_name(String specialplant_name) {
        this.specialplant_name = specialplant_name;
    }

    public String getSpecialplant_decription() {
        return specialplant_decription;
    }

    public void setSpecialplant_decription(String specialplant_decription) {
        this.specialplant_decription = specialplant_decription;
    }

    public String getSpecialplant_url() {
        return specialplant_url;
    }

    public void setSpecialplant_url(String specialplant_url) {
        this.specialplant_url = specialplant_url;
    }

    public String getNursury_name() {
        return nursury_name;
    }

    public void setNursury_name(String nursury_name) {
        this.nursury_name = nursury_name;
    }

    public String getSpecialplant_price() {
        return specialplant_price;
    }

    public void setSpecialplant_price(String specialplant_price) {
        this.specialplant_price = specialplant_price;
    }
}
