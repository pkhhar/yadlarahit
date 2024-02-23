package com.example.test3.ui.logout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test3.R;
import com.example.test3.repostory.UserModel;

public class LogoutFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        UserModel userModel = new UserModel();
        if(userModel.getIsLogin()) {
            //log out


            //move to home fragment

        }


        return view;
    }


}