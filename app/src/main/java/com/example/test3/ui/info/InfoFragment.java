package com.example.test3.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test3.R;
import com.example.test3.databinding.FragmentInfoBinding;

public class InfoFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v  = inflater.inflate(R.layout.fragment_info, container, false);


        return v;
    }
}