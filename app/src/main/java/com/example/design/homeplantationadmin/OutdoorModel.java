package com.example.design.homeplantationadmin;

public class OutdoorModel {
    String outdoorplant_name;
    String outdoorplant_decription;
    String outdoorplant_url;
    String nursury_name;
    String outdoorplant_price;

    public OutdoorModel(String outdoorplant_name, String outdoorplant_decription, String outdoorplant_url, String nursury_name, String outdoorplant_price) {
        this.outdoorplant_name = outdoorplant_name;
        this.outdoorplant_decription = outdoorplant_decription;
        this.outdoorplant_url = outdoorplant_url;
        this.nursury_name = nursury_name;
        this.outdoorplant_price = outdoorplant_price;
    }

    public OutdoorModel() {
    }

    public String getOutdoorplant_name() {
        return outdoorplant_name;
    }

    public void setOutdoorplant_name(String outdoorplant_name) {
        this.outdoorplant_name = outdoorplant_name;
    }

    public String getOutdoorplant_decription() {
        return outdoorplant_decription;
    }

    public void setOutdoorplant_decription(String outdoorplant_decription) {
        this.outdoorplant_decription = outdoorplant_decription;
    }

    public String getOutdoorplant_url() {
        return outdoorplant_url;
    }

    public void setOutdoorplant_url(String outdoorplant_url) {
        this.outdoorplant_url = outdoorplant_url;
    }

    public String getNursury_name() {
        return nursury_name;
    }

    public void setNursury_name(String nursury_name) {
        this.nursury_name = nursury_name;
    }

    public String getOutdoorplant_price() {
        return outdoorplant_price;
    }

    public void setOutdoorplant_price(String outdoorplant_price) {
        this.outdoorplant_price = outdoorplant_price;
    }
}

