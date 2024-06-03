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
    List<FurnitureModel> furnitures;
    FireBaseDataBase fireBaseDataBase = new FireBaseDataBase();

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.furniture = furnitures.get(position);
        holder.tvName.setText(furnitures.get(position).getName());
        holder.tvPrice.setText("מכיר  "+furnitures.get(position).getPrice());
        holder.tvLength.setText("אורך  "+furnitures.get(position).getLength());
        holder.tvWidth.setText("רוחב  "+furnitures.get(position).getWidth());
        holder.tvHeight.setText("גובה  "+furnitures.get(position).getHeight());
        holder.tvColor.setText("צבע  "+furnitures.get(position).getColor());
        holder.tvPhone.setText(furnitures.get(position).getPhoneUser());
        fireBaseDataBase.getInfo(furnitures.get(position).getEmailUser(), furnitures.get(position).getPicPath().toString(), new FireBaseDataBase.Got() {
            @Override
            public void onInfoGot(Uri photo)
            {

                holder.imageView.setImageURI(photo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return furnitures.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvName, tvPrice,tvLength,tvWidth,tvHeight,tvColor,tvPhone;
        FurnitureModel furniture;


        public ViewHolder(View root)
        {
            super(root);
            imageView = root.findViewById(R.id.imageDisplay);
            tvName = root.findViewById(R.id.nameDisplay);
            tvPrice = root.findViewById(R.id.priceDisplay);
            tvLength =root.findViewById(R.id.lengthDisplay);
            tvWidth =root.findViewById(R.id.widthDisplay);
            tvHeight =root.findViewById(R.id.heightDisplay);
            tvColor =root.findViewById(R.id.colorDisplay);
            tvPhone =root.findViewById(R.id.phoneDisplay);
            furniture = new FurnitureModel();


        }
    }
}
