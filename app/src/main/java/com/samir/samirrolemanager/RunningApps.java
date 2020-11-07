package com.samir.samirrolemanager;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "running_apps")
public class RunningApps {

    @PrimaryKey
    @NonNull
    private int runningAppId;

    @ColumnInfo
    private String runningApp;

    @ColumnInfo
    private boolean appRunning;

    public RunningApps(){}

    public String getRunningApp() {
        return runningApp;
    }

    public void setRunningApp(String runningApp) {
        this.runningApp = runningApp;
    }

    public int getRunningAppId() {
        return runningAppId;
    }

    public void setRunningAppId(int runningAppId) {
        this.runningAppId = runningAppId;
    }

    public boolean getAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }
}
