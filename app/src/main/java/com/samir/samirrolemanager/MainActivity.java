package com.samir.samirrolemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //For initializing the Database
    protected volatile static HashMap<Integer, List<String>> roles = new HashMap<Integer, List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(MainActivity.this);
        RoleManagerDAO dao = roleManagerDB.roleManagerDAO();

        //Initialize the data by reading from csv files and onto a map,
        // then insert map contents to DB
        FileReadHelper fr = new FileReadHelper(this);
        fr.execute(this);

        //set an alarm with the alarm manager to check for apps running, if any app has shutdown de-activate its roles
        Intent intentRunningAppAlarm = new Intent(MainActivity.this, RunningAppsAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intentRunningAppAlarm, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 15000;
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }

    public void onClickViewPA(View view) {
        Intent intent = new Intent(MainActivity.this, activityPA.class);
        startActivity(intent);
    }

    public void onClickViewUA(View view) {
        Intent intent = new Intent(MainActivity.this, activityUA.class);
        startActivity(intent);
    }
}