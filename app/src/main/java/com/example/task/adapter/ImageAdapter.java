package com.example.task.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.LoginActivity;
import com.example.task.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<Integer> imageList1;
    private List<Integer> imageList2;

    public ImageAdapter(List<Integer> imageList1,List<Integer> imageList2) {
        this.imageList1 = imageList1;
        this.imageList2 = imageList2;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager_layout, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList1.get(position));
        holder.imageView2.setImageResource(imageList2.get(position));
        holder.imgNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                v.getContext().startActivity(intent);
                if (v.getContext() instanceof Activity) {
                    ((Activity) v.getContext()).finish();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList1.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView2;
        ImageButton imgNext;
        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            imageView2 = itemView.findViewById(R.id.img2);
            imgNext = itemView.findViewById(R.id.btnNext);
        }
    }
}

