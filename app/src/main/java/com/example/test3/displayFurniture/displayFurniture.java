package com.example.test3.displayFurniture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.test3.DB.FireBaseDataBase;
import com.example.test3.R;
import com.example.test3.repostory.FurnitureModel;

import java.util.LinkedList;

public class displayFurniture extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_furniture);

        FireBaseDataBase fireBaseDataBase = new FireBaseDataBase();
        recyclerView = findViewById(R.id.furniture_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProgressDialog pd = new ProgressDialog(getBaseContext());
        pd.setTitle("Searching all furniture");
        pd.setCancelable(false);
        pd.show();
        fireBaseDataBase.getSomeFurniture(getIntent().getStringExtra("Furniture"), new FireBaseDataBase.searchDone() {
            @Override
            public void onSearchDone(LinkedList<FurnitureModel> list) {
                pd.dismiss();
                recyclerView.setAdapter(new FurnitureRecyclerViewAdapter(list));

            }
        });
    }
}