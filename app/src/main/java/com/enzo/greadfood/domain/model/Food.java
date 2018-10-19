package com.enzo.greadfood.domain.model;

public class Food {
    private String descrip, discount,  image,  menu_id, name, price;

    public Food() {
    }

    public Food(String descrip, String discount, String image, String menu_id, String name, String price) {
        this.descrip = descrip;
        this.discount = discount;
        this.image = image;
        this.menu_id = menu_id;
        this.name = name;
        this.price = price;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
