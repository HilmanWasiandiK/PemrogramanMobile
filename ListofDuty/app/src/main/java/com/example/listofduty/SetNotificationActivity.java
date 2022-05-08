package com.example.listofduty;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import android.content.ComponentName;
import android.app.job.JobScheduler;
import java.text.SimpleDateFormat;
import android.app.job.JobInfo;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import java.util.Date;
import java.util.List;

public class SetNotificationActivity extends AppCompatActivity {
    private MaterialTimePicker materialTimePicker;
    private Calendar currentTime, targetTime;
    private Button button1, button2, button3;
    private JobScheduler mScheduler;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_notification);

        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        textView = findViewById(R.id.selectedTime);
        button1 = findViewById(R.id.selectTimeButton);
        button2 = findViewById(R.id.startButton);
        button3 = findViewById(R.id.stopButton);

        //set Textview text dengan jam terkini ketika membuka halaman
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH : mm");
        Date date = new Date();
        textView.setText(timeFormatter.format(date));

        //set tampilan awal setiap Button ketika membuka halaman
        button1.setBackgroundColor(button1.getContext().getResources().getColor(R.color.blueOn));
        if (!isScheduledJob()) {
            button2.setBackgroundColor(button2.getContext().getResources().getColor(R.color.greenOn));
            button3.setBackgroundColor(button3.getContext().getResources().getColor(R.color.redOff));
        } else {
            button2.setBackgroundColor(button2.getContext().getResources().getColor(R.color.greenOff));
            button3.setBackgroundColor(button3.getContext().getResources().getColor(R.color.redOn));
        }

        //default untuk targetTime jika user tidak mengatur waktu secara manual
        targetTime = Calendar.getInstance();
        targetTime.set(Calendar.SECOND, 0);
        targetTime.set(Calendar.MILLISECOND, 0);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isScheduledJob()) {
                    scheduleJob();
                    button2.setBackgroundColor(button2.getContext().getResources().getColor(R.color.greenOff));
                    button3.setBackgroundColor(button3.getContext().getResources().getColor(R.color.redOn));
                    Log.d("Start", "The scheduled notification created!");
                    Toast.makeText(getApplicationContext(),"The scheduled notification created!",Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Start", "There's already scheduled notification!");
                    Toast.makeText(getApplicationContext(),"There's already scheduled notification!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isScheduledJob()) {
                    Log.d("Stop", "There's no scheduled notification!");
                    Toast.makeText(getApplicationContext(),"There's no scheduled notification!",Toast.LENGTH_SHORT).show();
                } else {
                    cancelJob();
                    button2.setBackgroundColor(button2.getContext().getResources().getColor(R.color.greenOn));
                    button3.setBackgroundColor(button3.getContext().getResources().getColor(R.color.redOff));
                    Log.d("Stop", "The scheduled notification cancelled!");
                    Toast.makeText(getApplicationContext(),"The scheduled notification cancelled!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showTimePicker() {
        materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(0)
                .setMinute(0)
                .setTitleText("SET SCHEDULED TIME FOR DAILY NOTIFICATION")
                .build();
        materialTimePicker.show(getSupportFragmentManager(), "daily_notification");

        materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(String.format("%02d", (materialTimePicker.getHour()))+" : "+String.format("%02d", materialTimePicker.getMinute()));

                //inisiasi value targetTime dengan waktu terkini, kemudian
                //mengubah jam dan menit sesuai dengan yang dipilih pada material time picker, dan
                //menjadikan detik dan milidetik bernilai 0
                targetTime = Calendar.getInstance();
                targetTime.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());
                targetTime.set(Calendar.MINUTE, materialTimePicker.getMinute());
                targetTime.set(Calendar.SECOND, 0);
                targetTime.set(Calendar.MILLISECOND, 0);
            }
        });
    }

    private boolean isScheduledJob() {
        List<JobInfo> allPendingJobs = mScheduler.getAllPendingJobs();
        boolean jobFound = false;

        if (allPendingJobs.size() > 0) {
            for (JobInfo job : allPendingJobs) {
                if (job.getId() == 101 || job.getId() == 102) {
                    jobFound = true;
                    break;
                }
            }
        }
        return jobFound;
    }

    private void scheduleJob() {
        currentTime = Calendar.getInstance();

        //jika waktu yang dipilih pada material time picker kurang dari sama dengan waktu terkini,
        //maka waktu ditambah 1 hari (24 jam)
        if(targetTime.compareTo(currentTime) <= 0) {
            targetTime.add(Calendar.DATE, 1);
        }

        Log.i("TimeKeeper", "TargetedTime   :"+targetTime.getTime());
        Log.i("TimeKeeper", "StartedTime    :"+currentTime.getTime());
        Log.i("TimeKeeper", "TimeDifference :"+(targetTime.getTimeInMillis()-currentTime.getTimeInMillis()));

        ComponentName serviceName = new ComponentName(getPackageName(), FirstRunJobService.class.getName());
        JobInfo builder1 = new JobInfo.Builder(101, serviceName)
                .setOverrideDeadline(targetTime.getTimeInMillis()-currentTime.getTimeInMillis())
                .setMinimumLatency(targetTime.getTimeInMillis()-currentTime.getTimeInMillis())
                .setPersisted(true)
                .build();

        if(mScheduler.schedule(builder1) == JobScheduler.RESULT_SUCCESS) {
            Log.i("DailyNotificationService", "MainActivity thread id: "+Thread.currentThread().getId()+", successfully scheduled for Job1");
        } else {
            Log.i("DailyNotificationService", "MainActivity thread id: "+Thread.currentThread().getId()+", could not be scheduled for Job1");
        }
    }

    private void cancelJob() {
        mScheduler.cancelAll();
        Log.i("DailyNotificationService", "jobCancelled");
    }
}