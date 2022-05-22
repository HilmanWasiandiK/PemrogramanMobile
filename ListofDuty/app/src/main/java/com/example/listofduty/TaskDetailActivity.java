package com.example.listofduty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskDetailActivity extends AppCompatActivity {
    TextView text_ShowDeadline, text_ShowStatus, textview_PageTitle;
    EditText text_ShowTitle, text_ShowDescription;
    Button button;
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        textview_PageTitle = findViewById(R.id.textviewPageTitle);
        text_ShowTitle = findViewById(R.id.textShowTitleTask);
        text_ShowDeadline = findViewById(R.id.textShowDeadlineTask);
        text_ShowStatus = findViewById(R.id.textShowStatusTask);
        text_ShowDescription = findViewById(R.id.textShowDescriptionTask);
        button = findViewById(R.id.buttonEdit);

        model = getIntent().getExtras().getParcelable("task details");

        showTitleTask();
        showDeadlineTask();
        showStatusTask();
        showDescriptionTask();

        setDisableEdit();

        button.setBackgroundTintList(null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button.getText().equals("Edit")) {
                    setEnableEdit();

                    text_ShowTitle.addTextChangedListener(addTaskTextWatcher);
                } else {
                    Intent data = new Intent();
                    Model modelelel = new Model(text_ShowTitle.getText().toString(), text_ShowDescription.getText().toString(), text_ShowDeadline.getText().toString(), false);
                    data.putExtra("apa", modelelel);

                    long id = getIntent().getLongExtra("task id", -1);
                    if(id != -1) {
                        data.putExtra("aja", id);
                        setResult(Activity.RESULT_OK, data);
                        finish();
                    } else {
                        setDisableEdit();
                    }
                }
            }
        });
    }

    private void setEnableEdit() {
        textview_PageTitle.setText("Edit Task");
        text_ShowTitle.setEnabled(true);
        text_ShowDescription.setEnabled(true);
        button.setText("Save");
        button.setBackgroundColor(getResources().getColor(R.color.greenOn));
    }


    private void setDisableEdit() {
        textview_PageTitle.setText("Task Details");
        text_ShowTitle.setEnabled(false);
        text_ShowDescription.setEnabled(false);
        button.setText("Edit");
        button.setBackgroundColor(getResources().getColor(R.color.blueOn));
    }

    private void showDescriptionTask() {
        if (model.getDescription().equals("")) {
            text_ShowDescription.setText("");
        } else {
            text_ShowDescription.setText(model.getDescription());
        }
        text_ShowDescription.setMovementMethod(new ScrollingMovementMethod());
    }

    private void showStatusTask() {
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
    }

    private void showDeadlineTask() {
        if(model.getDeadline().equals("")) {
            text_ShowDeadline.setText("No Deadline");
        } else {
            text_ShowDeadline.setText(model.getDeadline());
        }
    }

    private void showTitleTask() {

        text_ShowTitle.setText(model.getTitle());
    }

    private final TextWatcher addTaskTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String titleTask = text_ShowTitle.getText().toString().trim();

            button.setEnabled(!(titleTask.isEmpty()));

            if(button.isEnabled()==true) {
                button.setBackgroundColor(getResources().getColor(R.color.greenOn));
            } else {
                button.setBackgroundColor(getResources().getColor(R.color.greenOff));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}