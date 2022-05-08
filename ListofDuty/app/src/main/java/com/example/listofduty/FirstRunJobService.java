package com.example.listofduty;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.util.Log;
import java.util.Calendar;

//JobService untuk me-schedule periodic job (NotificationJobService atau Job2)
public class FirstRunJobService extends JobService {
    private JobScheduler mScheduler;
    Calendar startTime, targetTime;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i("DailyNotificationService", "onStartJob1");

        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        doBackgroundWork();

        return false;
    }

    private void doBackgroundWork() {
        new Thread(() -> {
            //inisiasi value dengan waktu terkini
            startTime = Calendar.getInstance();
            targetTime = Calendar.getInstance();

            //menambah jarak waktu sebanyak 1 hari, kemudian
            //menjadikan detik dan milidetik bernilai 0 agar deadline senantiasa konsisten
            targetTime.add(Calendar.DATE, 1);
            targetTime.set(Calendar.SECOND,0);
            targetTime.set(Calendar.MILLISECOND,0);

            Log.i("TimeKeeper", "TargetedTime   :"+targetTime.getTime());
            Log.i("TimeKeeper", "StartedTime    :"+startTime.getTime());
            Log.i("TimeKeeper", "TimeDifference :"+(targetTime.getTimeInMillis()-startTime.getTimeInMillis()));

            //scheduling periodic job
            ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getName());
            JobInfo builder1 = new JobInfo.Builder(102, serviceName)
                    .setPeriodic(15*60*1000L)
                    .setPersisted(true)
                    .build();

            if(mScheduler.schedule(builder1) == JobScheduler.RESULT_SUCCESS) {
                Log.i("DailyNotificationService", "MainActivity thread id: "+Thread.currentThread().getId()+", successfully scheduled for Job2");
            } else {
                Log.i("DailyNotificationService", "MainActivity thread id: "+Thread.currentThread().getId()+", Could not be scheduled for Job2");
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i("DailyNotificationService", "onStopJob1");
        return false;
    }
}

