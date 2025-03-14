package com.example.retrofix2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<Category> arrayCate;

    public CategoryAdapter(Context context, List<Category> arrayCate) {
        this.context = context;
        this.arrayCate = arrayCate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category cate = arrayCate.get(position);
        holder.textV.setText(cate.getName());
        /*
        String urlImage = "http://10.0.2.2:8081/uploads/" + cate.getImage();
        Glide.with(context).load(urlImage).into(holder.imgV);
        */
        Glide.with(context)
                .load(cate.getImage())
                .into(holder.imgV);
    }

    @Override
    public int getItemCount() {
        return arrayCate == null ? 0 : arrayCate.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgV;
        public TextView textV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgV = (ImageView) itemView.findViewById(R.id.image_cate);
            textV = (TextView) itemView.findViewById(R.id.tvNameCategory);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Ban da chon category: "+ textV.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}