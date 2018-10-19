package com.enzo.greadfood.domain.model;

public class Category {
    private String id, name, url;

    public Category() {
    }

    public Category(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Category(String id, String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
