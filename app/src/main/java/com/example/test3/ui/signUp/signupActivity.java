package com.example.test3.ui.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test3.DB.MyDatabaseHelper;
import com.example.test3.R;
import com.example.test3.repostory.UserModel;

public class signupActivity extends AppCompatActivity implements View.OnClickListener
{

  private   EditText Fname,Lname,Email,Password,Id;
   private MyDatabaseHelper myDatabaseHelper;
   private  UserModel userModel;

   private  signUpModel upModel;
   private Button btnSignUp
;    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSignUp = findViewById(R.id.signUpButton);
        Fname = findViewById(R.id.firstNameEditText);
        Lname = findViewById(R.id.lastNameEditText);
        Email = findViewById(R.id.emailEditText);
        Password = findViewById(R.id.passwordEditText);
        Id = findViewById(R.id.idEditText);
        myDatabaseHelper = new MyDatabaseHelper(this);
        btnSignUp.setOnClickListener(this);

        upModel = new signUpModel();
    }

    @Override
    public void onClick(View view)
    {



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
        if(Id.getText().toString().length() ==0)
        {
            Toast.makeText(this, "write id", Toast.LENGTH_SHORT).show();
            return;

        }
        if(btnSignUp == view)
        {
            userModel = new UserModel(Fname.getText().toString().trim(),Lname.getText().toString().trim(),Email.getText().toString().trim(),Password.getText().toString().trim(),(Id.getText().toString().trim()));

            myDatabaseHelper.addUser(Fname.getText().toString().trim(),Lname.getText().toString().trim(),Email.getText().toString().trim(),Password.getText().toString().trim(),(Id.getText().toString().trim()));

        }
    }

}
