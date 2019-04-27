package com.example.design.homeplantationadmin;

public class SeedModel {
    String seed_name;
    String seed_decription;
    String seed_url;
    String nursury_name;
    String seed_price;

    public SeedModel(String seed_name, String seed_decription, String seed_url, String nursury_name, String seed_price) {
        this.seed_name = seed_name;
        this.seed_decription = seed_decription;
        this.seed_url = seed_url;
        this.nursury_name = nursury_name;
        this.seed_price = seed_price;
    }

    public SeedModel() {
    }

    public String getSeed_name() {
        return seed_name;
    }

    public void setSeed_name(String seed_name) {
        this.seed_name = seed_name;
    }

    public String getSeed_decription() {
        return seed_decription;
    }

    public void setSeed_decription(String seed_decription) {
        this.seed_decription = seed_decription;
    }

    public String getSeed_url() {
        return seed_url;
    }

    public void setSeed_url(String seed_url) {
        this.seed_url = seed_url;
    }

    public String getNursury_name() {
        return nursury_name;
    }

    public void setNursury_name(String nursury_name) {
        this.nursury_name = nursury_name;
    }

    public String getSeed_price() {
        return seed_price;
    }

    public void setSeed_price(String seed_price) {
        this.seed_price = seed_price;
    }
}
