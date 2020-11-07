package com.samir.samirrolemanager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.samir.samirrolemanager.MyBroadcastReceiver.mPackage;

public class AppUninstallReceiver extends BroadcastReceiver {

    private final String TAG = "AppUninstallReceiver";
    private final String packageRemoved = "RemovedPackageName";
    private final String packageFullyRemoved = "FullyRemovedPackageName";

    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        String packageUninstalled = intent.getExtras().getString(packageRemoved);

        if(intent.getAction().equals("android.intent.action.PACKAGE_REMOVED") || intent.getAction().equals("android.intent.action.PACKAGE_FULLY_REMOVED")){
            Log.v(TAG,"Uninstalled package: " + intent.getStringExtra(packageRemoved));

        }

        //start prompt activity
        Intent intent1 = new Intent();
        intent1.setAction("com.samir.samirrolemanager.ROLEREVOKED");
        ComponentName cn = new ComponentName(context,com.samir.samirrolemanager.AppUninstallRoleRevoked.class);
        intent1.setComponent(cn);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.putExtra(mPackage,packageUninstalled);
        intent1.addCategory("android.intent.category.ALTERNATIVE");

        if (intent1.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent1);
        }else{
            Log.v("TAG","Activity not found!");
        }
    }
}