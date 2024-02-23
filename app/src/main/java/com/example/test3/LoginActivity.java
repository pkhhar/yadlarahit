package com.example.test3;// LoginActivity.java

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.repostory.UserModel;
import com.example.test3.ui.home.HomeFragment;
import com.example.test3.ui.signUp.signUpModel;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextPassword,editextEmail;
    private Button buttonLogin;
    private CheckBox checkBoxRemember;

    private  SharedPreferences preferences;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       editextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        checkBoxRemember = findViewById(R.id.checkboxRememberMe);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
         preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
       String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true"))
        {
            Intent intent = new Intent(LoginActivity.this,StartActivity.class);
            startActivity(intent);

        }
        else if (checkbox.equals("false"))
        {
            Toast.makeText(this, "Please Sign In.", Toast.LENGTH_SHORT).show();
        }

        checkBoxRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked())
                {
                     preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                   userModel.setIsLogin(true);
                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }else if(!compoundButton.isChecked())
                {
                    userModel.setIsLogin(false);
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    @Override
    public void onClick(View view)
    {
      //if(){} ask if the email exist and check if the password is correctly to the user
        //if not type toast with and error and do  "return;"


    if(buttonLogin == view)
    {
        if(editextEmail.getText().toString().trim().equals("")||editTextPassword.getText().toString().trim().equals(""))
        {
            Toast.makeText(this, "invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        setSherpreferences();

        Intent intent = new Intent(LoginActivity.this,StartActivity.class);
        startActivity(intent);
    }
    }

    public void setSherpreferences()
    {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remember","true");
        editor.putString("email",editextEmail.getText().toString().trim());
        editor.putString("password",editTextPassword.getText().toString().trim());
        editor.apply();


    }
}