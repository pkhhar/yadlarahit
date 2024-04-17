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
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.test3.ui.login.LoginActivity;
import com.example.test3.R;
import com.example.test3.ui.signUp.signupActivity;

public class profileFragment extends Fragment implements View.OnClickListener
{
private  View v;
private Intent intent;
private Button signInOrOutButton;

private RelativeLayout relativeLayout;
private SharedPreferences sharedPreferences;
private TextView nameoftheAccount;
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
         relativeLayout = v.findViewById(R.id.bla);
         nameoftheAccount = v.findViewById(R.id.textViewUsername);
         sharedPreferences = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        nameoftheAccount.setText(sharedPreferences.getString("fname",""));








        return v;

    }


    @Override
    public void onClick(View view)
    {

        if(signInOrOutButton == view)
    {
            intent = new Intent(requireActivity(), LoginActivity.class);
            startActivity(intent);
        }




    }
}