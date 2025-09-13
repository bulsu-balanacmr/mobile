package com.example.riderapp;

public class Product {
    private String name;
    private int imageResId;
    private double price;
    private String category;

    public Product(String name, int imageResId, double price, String category) {
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public int getImageResId() { return imageResId; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
}
