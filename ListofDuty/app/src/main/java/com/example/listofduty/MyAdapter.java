package com.example.listofduty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    Context context;
    private List<Model> models = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public MyAdapter(Context context, List<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_data_task, viewGroup, false);
        return new MyHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        Model model = models.get(position);
        myHolder.mTitle.setText(model.getTitle());
        myHolder.mDeadline.setText(model.getDeadline());
        myHolder.mCheck.setChecked(model.isCheckbox());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setModels(List<Model> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    public Model getModelAt(int position) {
        return models.get(position);
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private TextView mTitle, mDescription, mDeadline;
        private CheckBox mCheck;

        public MyHolder(@NonNull View itemView, final MyAdapter.OnItemClickListener onItemClickListener) {
            super(itemView);

            this.mTitle = itemView.findViewById(R.id.textviewTitleTask);
            this.mDeadline = itemView.findViewById(R.id.textviewDeadlineTask);
            this.mCheck = itemView.findViewById(R.id.checkboxTask);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null) {
                        if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(models.get(getAdapterPosition()));
                        }
                    }
                }
            });

            mCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null) {
                        if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                            onItemClickListener.onCboxClick(models.get(getAdapterPosition()));
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Model model);
        void onCboxClick(Model model);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
