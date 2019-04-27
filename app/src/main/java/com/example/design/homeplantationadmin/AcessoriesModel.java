package com.example.design.homeplantationadmin;

class AcessoriesModel {
    String name;
    String description;
    String url;
    String prcie;

    public AcessoriesModel(String name, String description, String url, String prcie) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.prcie = prcie;
    }

    public AcessoriesModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPrcie() {
        return prcie;
    }

    public void setPrcie(String prcie) {
        this.prcie = prcie;
    }
}
