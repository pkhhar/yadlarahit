package com.example.test3.displayFurniture;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test3.R;
import com.example.test3.repostory.FurnitureModel;

import java.util.List;

public class FurnitureRecyclerViewAdapter extends RecyclerView.Adapter<FurnitureRecyclerViewAdapter.ViewHolder> {
    List<FurnitureModel> furnitures;

    public FurnitureRecyclerViewAdapter(List<FurnitureModel> furnitures) {
        this.furnitures = furnitures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_furniture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.furniture = furnitures.get(position);
        holder.tvPrice.setText(furnitures.get(position).getPrice());
        holder.tvName.setText(furnitures.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return furnitures.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvName, tvPrice;
        FurnitureModel furniture;

        public ViewHolder(View root)
        {
            super(root);
            imageView = root.findViewById(R.id.imageDisplay);
            tvName = root.findViewById(R.id.nameDisplay);
            tvPrice = root.findViewById(R.id.priceDisplay);
            furniture = new FurnitureModel();
        }
    }
}
