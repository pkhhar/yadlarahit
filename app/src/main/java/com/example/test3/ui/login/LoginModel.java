package com.example.test3.ui.login;

import android.content.Context;
import android.database.Cursor;

import com.example.test3.DB.MyDatabaseHelper;
import com.example.test3.repostory.UserModel;

public class LoginModel
{
    private MyDatabaseHelper DataBaseHelper;

    private UserModel currentUser;
    public LoginModel(Context context)
    {
        DataBaseHelper = new MyDatabaseHelper(context);

    }

    public  boolean LogIn(String emailLogin, String passwordLogin)
    {

        Cursor cursor = DataBaseHelper.readAllData();

        int n = cursor.getCount();
        cursor.moveToFirst();

        String fname = null;
        String lname =null;
        String email =null;
        String password =null;
        String id =null;


        for (int i = 0; i < n ; i++)
        {
        email = cursor.getString(3);

        if(email.equals(emailLogin))
        {
            fname = cursor.getString(1);
            lname = cursor.getString(2);
            password = cursor.getString(4);
            id = cursor.getString(5);

            currentUser = new UserModel(fname,lname,password,emailLogin,id);

            return  true;
        }

        cursor.moveToNext();

        }
        return  false;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }
}
