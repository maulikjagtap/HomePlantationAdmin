package com.example.design.homeplantationadmin;

import android.widget.EditText;

public class FlowerModel {
    String flower_name;
    String flower_decription;
    String flower_url;
    String nursury_name;
    String plant_price;

    public FlowerModel(String flower_name, String flower_decription, String flower_url, String nursury_name, String plant_price) {
        this.flower_name = flower_name;
        this.flower_decription = flower_decription;
        this.flower_url = flower_url;
        this.nursury_name = nursury_name;
        this.plant_price = plant_price;
    }

    public FlowerModel() {
    }

    public String getFlower_name() {
        return flower_name;
    }

    public void setFlower_name(String flower_name) {
        this.flower_name = flower_name;
    }

    public String getFlower_decription() {
        return flower_decription;
    }

    public void setFlower_decription(String flower_decription) {
        this.flower_decription = flower_decription;
    }

    public String getFlower_url() {
        return flower_url;
    }

    public void setFlower_url(String flower_url) {
        this.flower_url = flower_url;
    }

    public String getNursury_name() {
        return nursury_name;
    }

    public void setNursury_name(String nursury_name) {
        this.nursury_name = nursury_name;
    }

    public String getPlant_price() {
        return plant_price;
    }

    public void setPlant_price(String plant_price) {
        this.plant_price = plant_price;
    }
}
