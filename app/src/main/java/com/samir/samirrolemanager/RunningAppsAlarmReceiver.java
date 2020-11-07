package com.samir.samirrolemanager;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Handler;
import android.util.Log;

import java.util.List;

import static com.samir.samirrolemanager.RoleManagerDB.databaseWriteExecutor;

public class RunningAppsAlarmReceiver extends BroadcastReceiver {

    private static volatile String TAG = "RunningAppsAlarmReceiver";
    private static volatile boolean appIsNoLongerRunning = false;
    private static volatile String[] mRoleArray = {"role1","role2","role3","role4","role5","role6","role7","role8","role9","role10"};
    String appNotRunningName;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.v(TAG, "Inside AlarmReceiver!");

        AppShutdownRevokeRoleHelper appShutdownRevokeRoleHelper = new AppShutdownRevokeRoleHelper(context);
        appShutdownRevokeRoleHelper.execute(context);
    }
}