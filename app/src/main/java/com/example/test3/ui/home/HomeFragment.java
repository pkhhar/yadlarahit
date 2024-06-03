package com.example.test3.ui.home;

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

import com.example.test3.R;
import com.example.test3.displayFurniture.displayFurniture;
import com.example.test3.ui.signUp.signupActivity;


public class HomeFragment extends Fragment implements View.OnClickListener {

    // Declaring buttons and search view
    private Button btnSearch, imagebedRoomButton, imagebathRoomButton, imagekitchenRoomButton, imagelivingRoomButton, imageoutsideButton;
    private EditText searchView;


    private View v;

    // This method is called to create and return the view hierarchy associated with the fragment
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize buttons and search view
        imagebedRoomButton = v.findViewById(R.id.bedRoomPic);
        imagebathRoomButton = v.findViewById(R.id.bathRoomPic);
        imagekitchenRoomButton = v.findViewById(R.id.kitchemRoomPic);
        imagelivingRoomButton = v.findViewById(R.id.livingRoomPic);
        imageoutsideButton = v.findViewById(R.id.outsidePic);
        btnSearch = v.findViewById(R.id.btnSearching);
        searchView = v.findViewById(R.id.searchView);

        // Set onClick listeners for buttons
        imagebedRoomButton.setOnClickListener(this);
        imagebathRoomButton.setOnClickListener(this);
        imagekitchenRoomButton.setOnClickListener(this);
        imagelivingRoomButton.setOnClickListener(this);
        imageoutsideButton.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        // Check if shared preferences exist for the user
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        if (sharedPreferences == null) {
//            // If no shared preferences, navigate to the signup activity
//            Intent intent = new Intent(requireActivity(), signupActivity.class);
//            startActivity(intent);
//        }

        // Return the view
        return v;
    }

    // Handle button clicks
    @Override
    public void onClick(View view) {
        if (imagebedRoomButton == view) {
            // Navigate to displayFurniture activity for bedroom
            Intent intent = new Intent(requireActivity(), displayFurniture.class);
            intent.putExtra("Furniture", "חדר שינה");
            startActivity(intent);
        } else if (imagebathRoomButton == view) {
            // Navigate to displayFurniture activity for bathroom
            Intent intent = new Intent(requireActivity(), displayFurniture.class);
            intent.putExtra("Furniture", "שירותים");
            startActivity(intent);
        } else if (imagekitchenRoomButton == view) {
            // Navigate to displayFurniture activity for kitchen
            Intent intent = new Intent(requireActivity(), displayFurniture.class);
            intent.putExtra("Furniture", "מטבח");
            startActivity(intent);
        } else if (imagelivingRoomButton == view) {
            // Navigate to displayFurniture activity for living room
            Intent intent = new Intent(requireActivity(), displayFurniture.class);
            intent.putExtra("Furniture", "סלון");
            startActivity(intent);
        } else if (imageoutsideButton == view) {
            // Navigate to displayFurniture activity for outside
            Intent intent = new Intent(requireActivity(), displayFurniture.class);
            intent.putExtra("Furniture", "חצר");
            startActivity(intent);
        } else if (btnSearch == view) {
            // Handle search button click
            if (searchView.length() == 0) {
                // If search view is empty, show a toast message
                Toast.makeText(requireActivity(), "choose a type or write a furniture name", Toast.LENGTH_SHORT).show();
                return;
            }
            // Navigate to displayFurniture activity with search query
            Intent intent = new Intent(requireActivity(), displayFurniture.class);
            intent.putExtra("Furniture", searchView.getText().toString());
            startActivity(intent);
        }
    }
}
