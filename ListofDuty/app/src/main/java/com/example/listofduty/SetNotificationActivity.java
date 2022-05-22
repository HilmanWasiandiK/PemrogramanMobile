package com.example.listofduty;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import android.content.ComponentName;
import android.app.job.JobScheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import android.app.job.JobInfo;
import android.content.SharedPreferences;
import android.sax.TextElementListener;
import android.widget.TextClock;
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
    private SharedPreferences sharedpreferences_NotificationTime;
    private Calendar currentTime, pickedTime, targetTime;
    private MaterialTimePicker materialTimePicker;
    private Button button1, button2, button3;
    private JobScheduler mScheduler;
    private TextClock tcCurrentTime;
    private TextView tvTargetTime;
    private DateFormat dateFormat;

    public static final String SHARED_NOTIFICATiON_TIME = "SharedNotificationTime";
    public static final String TEXT = "-- : -- : --";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_notification);

        sharedpreferences_NotificationTime = getSharedPreferences(SHARED_NOTIFICATiON_TIME, MODE_PRIVATE);
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        dateFormat = new SimpleDateFormat("HH : mm : ss");
        tcCurrentTime = findViewById(R.id.currentTime);
        tvTargetTime = findViewById(R.id.targetTime);
        button1 = findViewById(R.id.selectTimeButton);
        button2 = findViewById(R.id.startButton);
        button3 = findViewById(R.id.stopButton);

        //set tampilan awal ketika membuka halaman
        tvTargetTime.setText(sharedpreferences_NotificationTime.getString(TEXT, "-- : -- : --"));
        if (!isScheduledJob()) {
            button1.setBackgroundColor(button1.getContext().getResources().getColor(R.color.blueOn));
            button2.setBackgroundColor(button2.getContext().getResources().getColor(R.color.greenOn));
            button3.setBackgroundColor(button3.getContext().getResources().getColor(R.color.redOff));
        } else {
            button1.setBackgroundColor(button1.getContext().getResources().getColor(R.color.blueOff));
            button2.setBackgroundColor(button2.getContext().getResources().getColor(R.color.greenOff));
            button3.setBackgroundColor(button3.getContext().getResources().getColor(R.color.redOn));
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isScheduledJob()) {
                    showTimePicker();

                    Log.d("Select", "Opening Material Time Picker");
                } else {
                    Log.d("Select", "Cancel the scheduled notification first!");
                    Toast.makeText(getApplicationContext(),"Cancel the scheduled notification first!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isScheduledJob()) {
                    scheduleJob();

                    button1.setText("SELECT TIME");
                    button1.setBackgroundColor(button1.getContext().getResources().getColor(R.color.blueOff));
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

                    button1.setBackgroundColor(button1.getContext().getResources().getColor(R.color.blueOn));
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
                //inisiasi value targetTime dengan waktu terkini, kemudian
                //mengubah jam dan menit sesuai dengan yang dipilih pada material time picker, dan
                //menjadikan detik dan milidetik bernilai 0
                pickedTime = Calendar.getInstance();
                pickedTime.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());
                pickedTime.set(Calendar.MINUTE, materialTimePicker.getMinute());
                pickedTime.set(Calendar.SECOND, 0);
                pickedTime.set(Calendar.MILLISECOND, 0);

                button1.setText("Picked Time= "+dateFormat.format(pickedTime.getTime()));
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
        targetTime = pickedTime;

        //jika user tidak menyetel waktu terlebih dahulu, maka waktu penjadwalan sama dengan waktu
        //terkini ketika tombol "start scheduler" ditekan. Namun nedik dan milidetik 0
        if(targetTime == null) {
            targetTime = Calendar.getInstance();
            targetTime.set(Calendar.SECOND, 0);
            targetTime.set(Calendar.MILLISECOND, 0);
        }
        tvTargetTime.setText(dateFormat.format(targetTime.getTime()));
        sharedpreferences_NotificationTime.edit().putString(TEXT, tvTargetTime.getText().toString()).apply();

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
        pickedTime = null;
        tvTargetTime.setText("-- : -- : --");
        sharedpreferences_NotificationTime.edit().putString(TEXT, tvTargetTime.getText().toString()).apply();

        Log.i("DailyNotificationService", "jobCancelled");
    }
}