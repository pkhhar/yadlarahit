package com.example.test3.repostory;

import java.util.List;
 public  class   UserModel {
    private static String firstName;
    private static Boolean IsLogin;
    private  static String lastName;
    private static String password;
    private static String email;
    private static String id;
    private static List<FurnitureModel> productList;

    public  UserModel()
    {


    }
    public UserModel(String firstName, String lastName, String password, String email, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.id = id;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public List<FurnitureModel> getProductList() {
        return productList;
    }

    public void setProductList(List<FurnitureModel> productList) {
        this.productList = productList;
    }

     public Boolean getLogin() {
         return IsLogin;
     }

     public void setLogin(Boolean login) {
         IsLogin = login;
     }
 }
