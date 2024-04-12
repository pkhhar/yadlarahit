package com.example.test3.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test3.DB.FireBaseDataBase;
import com.example.test3.R;
import com.example.test3.displayFurniture.displayFurniture;
import com.example.test3.repostory.FurnitureModel;
import com.example.test3.ui.signUp.signupActivity;

import java.util.LinkedList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnSearch, imagebedRoomButton,imagebathRoomButton,imagekitchenRoomButton,imagelivingRoomButton,imageoutsideButton;

    private EditText searchView;

    FireBaseDataBase firebaseHelper = new FireBaseDataBase();
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
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        if(sharedPreferences == null)
        {
            Intent intent = new Intent(requireActivity(), signupActivity.class);
            startActivity(intent);

        }

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
        if(btnSearch == view)
        {
            Intent intent = new Intent(requireActivity(), displayFurniture.class);
            intent.putExtra("Furniture", searchView.getText().toString());
            startActivity(intent);
        }


    }

}