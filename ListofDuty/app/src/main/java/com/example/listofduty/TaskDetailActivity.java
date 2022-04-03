package com.example.listofduty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TaskDetailActivity extends AppCompatActivity {
    TextView text_ShowTitle, text_ShowDeadline, text_ShowStatus, text_ShowDescription;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Model model = getIntent().getExtras().getParcelable("task details");

        text_ShowTitle = findViewById(R.id.textShowTitleTask);
        text_ShowDeadline = findViewById(R.id.textShowDeadlineTask);
        text_ShowStatus = findViewById(R.id.textShowStatusTask);
        text_ShowDescription = findViewById(R.id.textShowDescriptionTask);

        text_ShowTitle.setText(model.getTitle());
        if(model.getDeadline().equals("")) {
            text_ShowDeadline.setText("No Deadline");
        } else {
            text_ShowDeadline.setText(model.getDeadline());
        }

        if(model.isCheckbox()) {
            text_ShowStatus.setText("Done");
        } else {
            text_ShowStatus.setText("Not Yet Done");
        }

        if (model.getDescription().equals("")) {
            text_ShowDescription.setText("No Description");
        } else {
            text_ShowDescription.setText(model.getDescription());
        }
        text_ShowDescription.setMovementMethod(new ScrollingMovementMethod());
    }
}