package com.example.test3.repostory;

import android.content.Context;

import com.example.test3.DB.FireBaseDataBase;
import com.example.test3.DB.MyDatabaseHelper;

public class Repository {

    FireBaseDataBase fireBaseDataBase;
    MyDatabaseHelper myDatabaseHelper;

    public Repository(Context context) {
        fireBaseDataBase = new FireBaseDataBase();
        myDatabaseHelper = new MyDatabaseHelper(context);

    }

    public void addUser(String Fname, String Lname, String Email, String Password, String Phone) {
        fireBaseDataBase.AddUser(Fname, Lname, Email, Password, Phone);
        myDatabaseHelper.addUser(Fname, Lname, Email, Password, Phone);

    }

    public boolean emailExists(String semail)
    {
        return myDatabaseHelper.emailExists(semail);
    }



    public void userExist(String email, String password, FireBaseDataBase.FoundUser callback)
    {
      fireBaseDataBase.FindUser(email,password,callback);
    }

    public  void removeUser(String email) {
        fireBaseDataBase.deleteUser(email);
    }

    public void GetDataToUpdate(String fname,String lname,String email,String phone,FireBaseDataBase.WhenDone callBack)
    {
        fireBaseDataBase.GetDataToUpdate(fname,lname,email,phone,callBack);

    }

}