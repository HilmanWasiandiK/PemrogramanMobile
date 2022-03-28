package com.example.listofduty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context context;
    ArrayList<Model> models;

    public MyAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_data_task, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        Model model = models.get(position);
        myHolder.mTitle.setText(models.get(position).getTitle());
        myHolder.mDeadline.setText(models.get(position).getDeadline());
        myHolder.checkBox.setChecked(models.get(position).isCheckbox());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
