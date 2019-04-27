package com.example.design.homeplantationadmin;

public class IndoorModel {
    String indoorplant_name;
    String indoorplant_decription;
    String indoorplant_url;
    String nursury_name;
    String indoorplan_price;

    public IndoorModel(String indoorplant_name, String indoorplant_decription, String indoorplant_url, String nursury_name, String indoorplan_price) {
        this.indoorplant_name = indoorplant_name;
        this.indoorplant_decription = indoorplant_decription;
        this.indoorplant_url = indoorplant_url;
        this.nursury_name = nursury_name;
        this.indoorplan_price = indoorplan_price;
    }

    public IndoorModel() {
    }

    public String getIndoorplant_name() {
        return indoorplant_name;
    }

    public void setIndoorplant_name(String indoorplant_name) {
        this.indoorplant_name = indoorplant_name;
    }

    public String getIndoorplant_decription() {
        return indoorplant_decription;
    }

    public void setIndoorplant_decription(String indoorplant_decription) {
        this.indoorplant_decription = indoorplant_decription;
    }

    public String getIndoorplant_url() {
        return indoorplant_url;
    }

    public void setIndoorplant_url(String indoorplant_url) {
        this.indoorplant_url = indoorplant_url;
    }

    public String getNursury_name() {
        return nursury_name;
    }

    public void setNursury_name(String nursury_name) {
        this.nursury_name = nursury_name;
    }

    public String getIndoorplan_price() {
        return indoorplan_price;
    }

    public void setIndoorplan_price(String indoorplan_price) {
        this.indoorplan_price = indoorplan_price;
    }
}
