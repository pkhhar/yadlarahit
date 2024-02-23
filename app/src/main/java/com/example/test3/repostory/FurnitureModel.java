package com.example.test3.repostory;

public class FurnitureModel {

    private String name;
    private double price;
    private String material;
    private double length;
    private double width;
    private double height;
    private String color;
    private String picture;
    private String catalogNumber;



    public FurnitureModel(String name, double price, String material, double length, double width, double height, String color, String picture, String catalogNumber) {
        this.name = name;
        this.price = price;
        this.material = material;
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
        this.picture = picture;
        this.catalogNumber = catalogNumber;
    }
}
