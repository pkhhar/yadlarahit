package com.example.test3.ui.profileFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.test3.LoginActivity;
import com.example.test3.R;
import com.example.test3.signupActivity;

public class profileFragment extends Fragment implements View.OnClickListener
{
private  View v;
private Intent intent;
private Button signInButton;

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
            createAccountButton = v.findViewById(R.id.createAccountButton);
            signInButton.setOnClickListener(this);
            createAccountButton.setOnClickListener(this);

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