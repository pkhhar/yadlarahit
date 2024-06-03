package com.example.test3.ui.profileFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.test3.DB.FireBaseDataBase;
import com.example.test3.DB.MyDatabaseHelper;
import com.example.test3.R;
import com.example.test3.repostory.Repository;
import com.example.test3.ui.start.StartActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class profileFragModel
{
    private Context context;
    private Repository repository;


    public profileFragModel(Context context)
    {
        this.context = context;
        repository = new Repository(context);
    }

        public void GetDataToUpdate(String fname,String lname,String email,String phone,FireBaseDataBase.WhenDone callBack)
        {
            repository.GetDataToUpdate(fname,lname,email,phone,callBack);
        }

    public void removeUser(String email)
    {
        repository.removeUser(email);
    }






}