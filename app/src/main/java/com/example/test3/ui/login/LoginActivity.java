package com.example.test3.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.DB.FireBaseDataBase;
import com.example.test3.DB.MyDatabaseHelper;
import com.example.test3.R;
import com.example.test3.repostory.FurnitureModel;
import com.example.test3.repostory.UserModel;
import com.example.test3.ui.signUp.signupActivity;
import com.example.test3.ui.start.StartActivity;
import com.google.firebase.Firebase;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {



    private TextView movebacktosignup;
    private EditText editTextPassword,editextEmail;
    private Button buttonLogin;
    private LoginModel loginModel;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         loginModel = new LoginModel(this);
        editextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        movebacktosignup = findViewById(R.id.MoveToSignupPage);
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        movebacktosignup.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View view)
    {

        if(movebacktosignup == view)
        {
            Intent intent = new Intent(LoginActivity.this, signupActivity.class);
            startActivity(intent);
        }

         if(buttonLogin == view)
        {
            if(editextEmail.getText().toString().trim().equals("")||editTextPassword.getText().toString().trim().equals(""))
            {
               Toast.makeText(this, "invalid", Toast.LENGTH_SHORT).show();
              return;
            }

                loginModel.userExist(editextEmail.getText().toString().trim(), editTextPassword.getText().toString().trim(), new FireBaseDataBase.FoundUser() {
                    @Override
                    public void onFoundUser(boolean flag,String Fname,String Lname, String Password ,String Email,String Phone)
                    {

                        if(flag)
                        {
                        UserModel userModel = new UserModel(Fname,Lname,Password,Email,Phone);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", editextEmail.getText().toString().trim());
                        editor.putString("password", editTextPassword.getText().toString().trim());
                        editor.putString("fname", userModel.getFirstName());
                        editor.putString("lname", userModel.getLastName());
                        editor.putString("phone", userModel.getPhone());
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                        startActivity(intent);
                        }
                        if(flag == false)
                        {
                            Toast.makeText(getApplicationContext(), "invalid", Toast.LENGTH_SHORT).show();
                            return;

                        }
                    }
                });





        }








    }
}