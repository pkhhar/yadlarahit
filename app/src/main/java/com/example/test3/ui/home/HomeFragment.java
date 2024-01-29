package com.example.test3.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test3.LoginActivity;
import com.example.test3.StartActivity;
import com.example.test3.R;
import com.example.test3.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnSearch, imagebedRoomButton,imagebathRoomButton,imagekitchenRoomButton,imagelivingRoomButton,imageoutsideButton;

    private EditText searchView;
    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        v = inflater.inflate(R.layout.fragment_home, container, false);
        imagebedRoomButton = v.findViewById(R.id.bedRoomPic);
        imagebathRoomButton = v.findViewById(R.id.bathRoomPic);
        imagekitchenRoomButton = v.findViewById(R.id.kitchemRoomPic);
        imagelivingRoomButton = v.findViewById(R.id.livingRoomPic);
        imageoutsideButton = v.findViewById(R.id.outsidePic);
        btnSearch = v.findViewById(R.id.btnSearching);
        searchView = v.findViewById(R.id.searchView);
        imagebedRoomButton.setOnClickListener(this);
        imagebathRoomButton.setOnClickListener(this);
        imagekitchenRoomButton.setOnClickListener(this);
        imagelivingRoomButton.setOnClickListener(this);
        imageoutsideButton.setOnClickListener(this);
        btnSearch.setOnClickListener(this);


        return  v;
    }




    @Override
    public void onClick(View view) {
        if(imagebedRoomButton == view)
        {
            Toast.makeText(requireActivity(), "bedroom", Toast.LENGTH_SHORT).show();

        }
        if(imagebathRoomButton == view)
        {
            Toast.makeText(requireActivity(), "bathroom", Toast.LENGTH_SHORT).show();

        }  if(imagekitchenRoomButton == view)
        {
            Toast.makeText(requireActivity(), "kitchen room", Toast.LENGTH_SHORT).show();

        }  if(imagelivingRoomButton == view)
        {
            Toast.makeText(requireActivity(), "livingroom", Toast.LENGTH_SHORT).show();

        }  if(imageoutsideButton == view)
        {
            Toast.makeText(requireActivity(), "outside", Toast.LENGTH_SHORT).show();

        }


    }

}