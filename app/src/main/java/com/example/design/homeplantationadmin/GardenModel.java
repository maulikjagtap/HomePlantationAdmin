package com.example.design.homeplantationadmin;

class GardenModel {
    String description;
    String url;

    public GardenModel(String description, String url) {
        this.description = description;
        this.url = url;
    }

    public GardenModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
