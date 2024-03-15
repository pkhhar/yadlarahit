package com.example.test3.ui.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3.DB.FireBaseDataBase;
import com.example.test3.DB.MyDatabaseHelper;
import com.example.test3.R;
import com.example.test3.repostory.UserModel;
import com.example.test3.ui.login.LoginActivity;
import com.example.test3.ui.start.StartActivity;

public class signupActivity extends AppCompatActivity implements View.OnClickListener
{

  private   EditText Fname,Lname,Email,Password,Phone;
   private MyDatabaseHelper myDatabaseHelper;

   private  UserModel userModel;
   private TextView MoveLogin;
   private  signUpModel upModel;
   private Button btnSignUp;

   @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        if (!sharedPreferences.getString("email","").equals("") )
        {
            Intent intent = new Intent(signupActivity.this, StartActivity.class);
            startActivity(intent);
        }

        MoveLogin = findViewById(R.id.moveToLogin);
        btnSignUp = findViewById(R.id.signUpButton);
        Fname = findViewById(R.id.firstNameEditText);
        Lname = findViewById(R.id.lastNameEditText);
        Email = findViewById(R.id.emailEditText);
        Password = findViewById(R.id.passwordEditText);
        Phone = findViewById(R.id.phoneEditText);
        myDatabaseHelper = new MyDatabaseHelper(this);
        btnSignUp.setOnClickListener(this);
        MoveLogin.setOnClickListener(this);
        upModel = new signUpModel();
    }


    @Override
    public void onClick(View view)
    {
        if(MoveLogin == view)
        {
            Intent intent = new Intent(signupActivity.this,LoginActivity.class);
            startActivity(intent);

        }


        if(Fname.getText().toString().trim().length() ==0 )
        {
            Toast.makeText(this, "write first name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(Lname.getText().toString().length() ==0)
        {
            Toast.makeText(this, "write last name", Toast.LENGTH_SHORT).show();
            return;

        }
        if(upModel.EmailCheck(Email.getText().toString().trim()) == false)
        {
            Toast.makeText(this, "write email correctly please", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Password.getText().toString().trim().length() == 0)
        {
            Toast.makeText(this, "write password", Toast.LENGTH_SHORT).show();
            return;

        }
        if(Phone.getText().toString().length() ==0)
        {
            Toast.makeText(this, "write phone", Toast.LENGTH_SHORT).show();
            return;

        }
        if(Phone.getText().toString().trim().length() != 10)
        {
            Toast.makeText(this, "phone must 10 digits", Toast.LENGTH_SHORT).show();
            return;
        }
        if(btnSignUp == view)
        {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            userModel = new UserModel(Fname.getText().toString().trim(),Lname.getText().toString().trim(),Email.getText().toString().trim(),Password.getText().toString().trim(),(Phone.getText().toString().trim()));
            FireBaseDataBase fireBaseDataBase = new FireBaseDataBase();
            fireBaseDataBase.AddUser(Fname.getText().toString(),Lname.getText().toString(),Password.getText().toString(),Email.getText().toString(),Phone.getText().toString());
            myDatabaseHelper.addUser(Fname.getText().toString().trim(),Lname.getText().toString().trim(),Email.getText().toString().trim(),Password.getText().toString().trim(),(Phone.getText().toString().trim()));

            Intent intent = new Intent(signupActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

}
