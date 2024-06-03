package com.example.test3.repostory;

public class FurnitureModel {

    private String PhoneUser;
    private String emailUser;
    private String name;
    private String price;
    private String length;
    private String width;
    private String height;
    private String color;
    private String type;
    private String  picPath;



    public FurnitureModel(String name, String price, String length, String width, String height, String color, String type,String currentEmail,String picPath,String Phone) {
        this.name = name;
        this.price = price;
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
        this.type = type;
        this.emailUser = currentEmail;
        this.picPath = picPath;
        this.PhoneUser = Phone;
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

    public String getEmailUser() {
        return this.emailUser;
    }

    public void setEmailUser(String currentEmail) {
        this.emailUser = currentEmail;
    }
    public String getPicPath() {
        return this.picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPhoneUser() {
        return this.PhoneUser;
    }

    public void setPhoneUser(String Phone) {
        this.PhoneUser = Phone;
    }
}
