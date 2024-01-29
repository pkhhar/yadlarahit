package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.test3.DB.MyDatabaseHelper;
import com.example.test3.repostory.UserModel;

public class signupActivity extends AppCompatActivity
{

  private   EditText Fname,Lname,Email,Password,Id;
   private MyDatabaseHelper myDatabaseHelper;
   private  UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Fname = findViewById(R.id.firstNameEditText);
        Lname = findViewById(R.id.lastNameEditText);
        Email = findViewById(R.id.emailEditText);
        Password = findViewById(R.id.passwordEditText);
        Id = findViewById(R.id.idEditText);
        userModel = new UserModel(Fname.getText().toString().trim(),Lname.getText().toString().trim(),Email.getText().toString().trim(),Password.getText().toString().trim(),(Id.getText().toString().trim()));

        myDatabaseHelper = new MyDatabaseHelper(this);
        myDatabaseHelper.addUser(Fname.getText().toString().trim(),Lname.getText().toString().trim(),Email.getText().toString().trim(),Password.getText().toString().trim(),(Id.getText().toString().trim()));
    }
}