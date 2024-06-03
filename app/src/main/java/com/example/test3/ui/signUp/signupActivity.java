package com.example.test3.ui.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

   private TextView MoveLogin;
   private  signUpModel signUpModel;
   private Button btnSignUp;

   @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
//        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
//        myDatabaseHelper.deleteAllUsers();
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
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
        btnSignUp.setOnClickListener(this);
        MoveLogin.setOnClickListener(this);
        signUpModel = new signUpModel(this);

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
        if(signUpModel.EmailCheck(Email.getText().toString().trim()) == false)
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
           if(signUpModel.emailExists(Email.getText().toString().trim()) == true)
           {
               Toast.makeText(this, "this email is already exist change email", Toast.LENGTH_SHORT).show();
               return;
           }

            signUpModel.createUser(Fname.getText().toString().trim(),Lname.getText().toString().trim(),Email.getText().toString().trim(),Password.getText().toString().trim(),(Phone.getText().toString().trim()));
            signUpModel.AddUserToDataBases(Fname.getText().toString().trim(),Lname.getText().toString().trim(),Email.getText().toString().trim(),Password.getText().toString().trim(),(Phone.getText().toString().trim()));
            Intent intent = new Intent(signupActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
