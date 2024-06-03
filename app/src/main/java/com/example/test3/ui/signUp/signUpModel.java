package com.example.test3.ui.signUp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.test3.repostory.Repository;
import com.example.test3.repostory.UserModel;
import com.example.test3.ui.start.StartActivity;

public class signUpModel
{
    Context context;

    Repository repository;


    UserModel userModel;

    public signUpModel(Context c) {
        this.context = c;
        repository = new Repository(c);
        userModel = new UserModel();
    }
    public void AddUserToDataBases(String Fname,String Lname,String Email,String Password,String Phone) {

        repository.addUser(Fname,Lname,Email,Password,Phone);
    }


    public boolean EmailCheck(String semail) {
        if (semail.length() == 0)
        {

            return false;
        }
        //check if . and @ is not the first char or the last char
        if (semail.charAt(semail.length() - 1) == '.' || semail.charAt(semail.length() - 1) == '@' || semail.charAt(0) == '.' || semail.charAt(0) == '@') {
            return false;
        }
        int counter = 0;
        for (int i = 0; i < semail.length() - 2; i++) {
            //checks if . come after @
            if (semail.charAt(i) == '.' && semail.charAt(i + 1) == '@') {
                return false;
            }
            //count every @ symbol
            if (semail.charAt(i) == '@')
                counter++;
            //check if exist .com or .co.
            if (!semail.contains(".com") && !semail.contains(".co.")) {
                return false;
            }
            //check if the distance between . and @ is less than 3
            if (semail.indexOf(".") - semail.indexOf("@") <= 3) {
                return false;
            }


        }

        if (counter != 1) {
            return false;
        }
        return true;
    }

    public boolean emailExists(String semail){return repository.emailExists(semail);}

    public void createUser(String firstName, String lastName,String email,  String password, String phone) {
        userModel = new UserModel(firstName,lastName,email,password,phone);
    }


}
