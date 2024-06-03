package com.example.test3.ui.login;

import android.content.Context;
import android.database.Cursor;

import com.example.test3.DB.FireBaseDataBase;
import com.example.test3.DB.MyDatabaseHelper;
import com.example.test3.repostory.Repository;
import com.example.test3.repostory.UserModel;

public class LoginModel
{

    Context context;

    Repository repository;
    public LoginModel(Context context)
    {

        this.context = context;
        repository = new Repository(this.context);
    }

//



    public void userExist(String email,String password,FireBaseDataBase.FoundUser callback) {
        repository.userExist(email,password,callback);
    }

}
