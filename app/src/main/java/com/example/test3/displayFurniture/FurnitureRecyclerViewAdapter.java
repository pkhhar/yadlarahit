package com.example.test3.displayFurniture;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test3.DB.FireBaseDataBase;
import com.example.test3.R;
import com.example.test3.repostory.FurnitureModel;

import java.util.List;

public class FurnitureRecyclerViewAdapter extends RecyclerView.Adapter<FurnitureRecyclerViewAdapter.ViewHolder> {
    // List to hold the furniture items
    List<FurnitureModel> furnitures;
    // Firebase database instance
    FireBaseDataBase fireBaseDataBase = new FireBaseDataBase();

    // Constructor to initialize the adapter with the furniture list
    public FurnitureRecyclerViewAdapter(List<FurnitureModel> furnitures) {
        this.furnitures = furnitures;
    }

    // Method to create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the list item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_furniture, parent, false);
        return new ViewHolder(view);
    }

    // Method to bind the data to the views (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the furniture item at the current position
        holder.furniture = furnitures.get(position);

        // Set the furniture details in the respective TextViews
        holder.tvName.setText(furnitures.get(position).getName());
        holder.tvPrice.setText("מכיר  " + furnitures.get(position).getPrice());
        holder.tvLength.setText("אורך  " + furnitures.get(position).getLength());
        holder.tvWidth.setText("רוחב  " + furnitures.get(position).getWidth());
        holder.tvHeight.setText("גובה  " + furnitures.get(position).getHeight());
        holder.tvColor.setText("צבע  " + furnitures.get(position).getColor());
        holder.tvPhone.setText(furnitures.get(position).getPhoneUser());

        // Retrieve the furniture image from Firebase Storage
        fireBaseDataBase.getInfo(furnitures.get(position).getEmailUser(), furnitures.get(position).getPicPath().toString(), new FireBaseDataBase.Got() {
            @Override
            public void onInfoGot(Uri photo) {
                // Set the ImageView with the retrieved image URI
                holder.imageView.setImageURI(photo);
            }
        });
    }

    // Method to return the total number of items
    @Override
    public int getItemCount() {
        return furnitures.size();
    }

    // ViewHolder class to represent the furniture item views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName, tvPrice, tvLength, tvWidth, tvHeight, tvColor, tvPhone;
        FurnitureModel furniture;

        // Constructor to initialize the views
        public ViewHolder(View root) {
            super(root);
            // Find the views by their IDs
            imageView = root.findViewById(R.id.imageDisplay);
            tvName = root.findViewById(R.id.nameDisplay);
            tvPrice = root.findViewById(R.id.priceDisplay);
            tvLength = root.findViewById(R.id.lengthDisplay);
            tvWidth = root.findViewById(R.id.widthDisplay);
            tvHeight = root.findViewById(R.id.heightDisplay);
            tvColor = root.findViewById(R.id.colorDisplay);
            tvPhone = root.findViewById(R.id.phoneDisplay);
            furniture = new FurnitureModel();
        }
    }
}
