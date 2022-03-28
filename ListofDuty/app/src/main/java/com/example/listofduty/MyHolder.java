package com.example.listofduty;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {
    TextView mTitle, mDeadline;
    CheckBox checkBox;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mTitle = itemView.findViewById(R.id.textTitleTask);
        this.mDeadline = itemView.findViewById(R.id.textDeadlineTask);
        this.checkBox = itemView.findViewById(R.id.cboxtask);
    }
}
