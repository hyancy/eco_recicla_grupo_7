package com.hyancy.eco_recicla_reto_1_grupo_7.models;

import java.util.Date;

public class Product {
    private final String ID;
    private String description;
    private String photoUrl;
    private Date registerDate;
    private String location;
    private String category;
    private double quantity;
    private int points;

    public Product(String ID, String description, String photoUrl, Date registerDate, String location, String category, double quantity, int points) {
        this.ID = ID;
        this.description = description;
        this.photoUrl = photoUrl;
        this.registerDate = registerDate;
        this.location = location;
        this.category = category;
        this.quantity = quantity;
        this.points = points;
    }

    public String getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
