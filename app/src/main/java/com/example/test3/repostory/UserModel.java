package com.example.test3.repostory;


import com.example.test3.DB.FireBaseDataBase;

import java.util.LinkedList;
import java.util.List;
 public  class   UserModel {
    private static String firstName;
    private static Boolean IsLogin;
    private  static String lastName;
    private static String password;
    private static String email;
    private static String phone;
    FireBaseDataBase firebaseHelper = new FireBaseDataBase();

    public  UserModel()
    {


    }
    public UserModel(String firstName, String lastName, String password, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phone;

    }

    public void SearchFurniture(String Furniture, FireBaseDataBase.searchDone callback)
    {
        firebaseHelper.getSomeFurniture(Furniture, callback);
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

    public String getPhone() {
        return phone;
    }

    public void setphone(String phone) {this.phone = phone;}


     public Boolean getLogin() {
         return IsLogin;
     }

     public void setLogin(Boolean login) {
         IsLogin = login;
     }
 }
