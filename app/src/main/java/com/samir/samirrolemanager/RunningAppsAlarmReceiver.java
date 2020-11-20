package com.samir.samirrolemanager;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;

import androidx.core.app.NotificationCompat;
import  android.os.Looper;

import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.samir.samirrolemanager.RoleManagerDB.databaseWriteExecutor;

public class RunningAppsAlarmReceiver extends BroadcastReceiver {

    private static volatile String TAG = "RunningAppsAlarmReceiver";
    private static volatile boolean appIsNoLongerRunning = false;
    private static volatile boolean deactivatedRoles = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.v(TAG, "Inside AlarmReceiver!");

        AppShutdownRevokeRoleHelper appShutdownRevokeRoleHelper = new AppShutdownRevokeRoleHelper(context);
        appShutdownRevokeRoleHelper.execute(context);

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mRunningAppProcessInfo = activityManager.getRunningAppProcesses();
        PackageManager packageManager = context.getPackageManager();
        for (ActivityManager.RunningAppProcessInfo rApps : mRunningAppProcessInfo) {

            //did not get package name, so skip
            if (rApps.pkgList.length == 0) {
                continue;
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(rApps.pkgList[0], PackageManager.GET_ACTIVITIES);

                //check if package is not system, since we dont modify anything for system apps
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    Log.v(TAG, "Inside For > try > if | LRU = " + rApps.lru);
                    //Log.v(TAG,"Inside for > try > if | Found App gone into background packageInfo.packageName = "  + packageInfo.packageName);
                    if (rApps.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_CACHED) {
                        Log.v(TAG, "Inside For > try > if > if | Found App gone into background:1 " + packageInfo.packageName);
                        Log.v(TAG, "Inside For > try > if > if | LRU = " + rApps.lru);
                        if (rApps.lru == 0) {
                            deactivatedRoles = false;
                            Log.v(TAG, "Deactivated roles = " + deactivatedRoles);
                        }
                        if (rApps.lru == 700 && !deactivatedRoles) {
                            deactivatedRoles = true;
                            Log.v(TAG, "Inside for > try > if > if > if |: Found App gone into background packageInfo.packageName = " + packageInfo.packageName);
                            Log.v(TAG, "Inside for > try > if > if > if |: Found App gone into background rApps.pkgList[0] = " + rApps.pkgList[0]);

                            Intent intent1 = new Intent();
                            ComponentName cn = new ComponentName(context, com.samir.samirrolemanager.AppBackgroundRoleDeactivatorActivity.class);
                            intent1.setComponent(cn);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent1.setAction("com.samir.samirrolemanager.ROLEDEACTIVATOR");
                            intent1.addCategory("android.intent.category.ALTERNATIVE");
                            intent1.putExtra("PackageToDeactivateRole",rApps.pkgList[0]);
                            if (intent1.resolveActivity(context.getPackageManager()) != null) {
                                context.startActivity(intent1);
                            } else {
                                Log.v("TAG", "Activity not found!");
                            }
                        }
                    }
                }
            }catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}