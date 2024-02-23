package com.example.test3.ui.profileFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3.LoginActivity;
import com.example.test3.R;
import com.example.test3.repostory.UserModel;
import com.example.test3.ui.signUp.signupActivity;

public class profileFragment extends Fragment implements View.OnClickListener
{
private  View v;
private Intent intent;
UserModel userModel;
private  String Email,Password;
private Button signInButton,logOutButton;
private  SharedPreferences sharedPreferences;

private TextView createAccountButton;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


         v = inflater.inflate(R.layout.fragment_profile, container, false);
        signInButton = v.findViewById(R.id.signInAccountButton);
        logOutButton = v.findViewById(R.id.btnlogOut);
            createAccountButton = v.findViewById(R.id.createAccountButton);
        signInButton.setOnClickListener(this);
           logOutButton.setOnClickListener(this);
            createAccountButton.setOnClickListener(this);
        sharedPreferences = getContext().getSharedPreferences("checkbox",Context.MODE_PRIVATE);
        if(sharedPreferences != null)
        {
            Email = sharedPreferences.getString("email","");
            Password = sharedPreferences.getString("password","");
            Toast.makeText(requireActivity(), ""+Email + '\n' + Password, Toast.LENGTH_SHORT).show();
        }

        return v;

    }


    @Override
    public void onClick(View view)
    {
        if(signInButton == view)
    {

            intent = new Intent(requireActivity(), LoginActivity.class);
            startActivity(intent);

    }
        if (createAccountButton == view)
        {
           intent = new Intent(requireActivity(), signupActivity.class);
        startActivity(intent);
        }



    }
}