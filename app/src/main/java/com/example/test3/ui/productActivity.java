package com.example.test3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test3.R;

public class productActivity extends AppCompatActivity
{
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        linearLayout = findViewById(R.id.linearProductPage);
        TextView nameProduct = new TextView(this);
        TextView priceProduct = new TextView(this);
        TextView materialProduct = new TextView(this);
        TextView lengthProduct = new TextView(this);
        TextView widthProduct = new TextView(this);
        ImageView imageView = new ImageView(this);
    }

    public void buildPage()
    {





    }
}