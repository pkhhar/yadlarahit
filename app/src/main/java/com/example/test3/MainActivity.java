package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button btnSearch, imagebedRoomButton,imagebathRoomButton,imagekitchenRoomButton,imagelivingRoomButton,imageoutsideButton;

    private EditText searchView;
    FragmentManager manager;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagebedRoomButton = findViewById(R.id.bedRoomPic);
        imagebathRoomButton = findViewById(R.id.bathRoomPic);
        imagekitchenRoomButton = findViewById(R.id.kitchemRoomPic);
        imagelivingRoomButton = findViewById(R.id.livingRoomPic);
        imageoutsideButton = findViewById(R.id.outsidePic);
      btnSearch = findViewById(R.id.btnSearching);
        searchView = findViewById(R.id.searchView);
        imagebedRoomButton.setOnClickListener(this);
        imagebathRoomButton.setOnClickListener(this);
        imagekitchenRoomButton.setOnClickListener(this);
        imagelivingRoomButton.setOnClickListener(this);
        imageoutsideButton.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {

        if(imagebedRoomButton == v)
        {
            Toast.makeText(this, "bedroom", Toast.LENGTH_SHORT).show();

        }
        if(imagebathRoomButton == v)
        {
            Toast.makeText(this, "bathroom", Toast.LENGTH_SHORT).show();

        }  if(imagekitchenRoomButton == v)
    {
        Toast.makeText(this, "kitchen room", Toast.LENGTH_SHORT).show();

    }  if(imagelivingRoomButton == v)
    {
        Toast.makeText(this, "livingroom", Toast.LENGTH_SHORT).show();

    }  if(imageoutsideButton == v)
    {
        Toast.makeText(this, "outside", Toast.LENGTH_SHORT).show();

    }
        if(btnSearch == v)
        {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}