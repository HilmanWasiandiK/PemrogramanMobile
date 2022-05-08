package com.example.listofduty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskDetailActivity extends AppCompatActivity {
    TextView text_ShowTitle, text_ShowDeadline, text_ShowStatus, text_ShowDescription;

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

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, EEEE");
        String dlDate = model.getDeadline();
        String curDate = format.format(new Date());
        if(model.isCheckbox()) {
            text_ShowStatus.setText("Completed");
            text_ShowStatus.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.greenOn));
        } else if((!model.isCheckbox()) && (dlDate.isEmpty()) || (curDate.compareTo(dlDate)<0)) {
            text_ShowStatus.setText("In Progress");
            text_ShowStatus.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.yellowOn));
        } else {
            text_ShowStatus.setText("Overdue");
            text_ShowStatus.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.redOn));
        }

        if (model.getDescription().equals("")) {
            text_ShowDescription.setText("No Description");
        } else {
            text_ShowDescription.setText(model.getDescription());
        }
        text_ShowDescription.setMovementMethod(new ScrollingMovementMethod());
    }
}