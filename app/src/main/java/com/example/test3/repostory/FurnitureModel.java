package com.example.test3.repostory;

public class FurnitureModel {

    private String name;
    private String price;
    private String length;
    private String width;
    private String height;
    private String color;
    private String type;
    private byte[] image;



    public FurnitureModel(String name, String price, String length, String width, String height, String color, String type) {
        this.name = name;
        this.price = price;
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
        this.type = type;
    }
    public  FurnitureModel() {}

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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
