package com.example.design.homeplantationadmin;

public class HybridModel {
    String hybrid_name;
    String hybrid_decription;
    String hybrid_url;
    String nursury_name;
    String hybrid_price;

    public HybridModel(String hybrid_name, String hybrid_decription, String hybrid_url, String nursury_name, String hybrid_price) {
        this.hybrid_name = hybrid_name;
        this.hybrid_decription = hybrid_decription;
        this.hybrid_url = hybrid_url;
        this.nursury_name = nursury_name;
        this.hybrid_price = hybrid_price;
    }

    public HybridModel() {
    }

    public String getHybrid_name() {
        return hybrid_name;
    }

    public void setHybrid_name(String hybrid_name) {
        this.hybrid_name = hybrid_name;
    }

    public String getHybrid_decription() {
        return hybrid_decription;
    }

    public void setHybrid_decription(String hybrid_decription) {
        this.hybrid_decription = hybrid_decription;
    }

    public String getHybrid_url() {
        return hybrid_url;
    }

    public void setHybrid_url(String hybrid_url) {
        this.hybrid_url = hybrid_url;
    }

    public String getNursury_name() {
        return nursury_name;
    }

    public void setNursury_name(String nursury_name) {
        this.nursury_name = nursury_name;
    }

    public String getHybrid_price() {
        return hybrid_price;
    }

    public void setHybrid_price(String hybrid_price) {
        this.hybrid_price = hybrid_price;
    }
}
