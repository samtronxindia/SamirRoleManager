package com.samir.samirrolemanager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static com.samir.samirrolemanager.RoleRequestReceiver.mPackage;
import static com.samir.samirrolemanager.RoleRequestReceiver.mRole;
import static com.samir.samirrolemanager.RoleRequestReceiver.roleDescription;
import static com.samir.samirrolemanager.RoleRequestReceiver.roleHeading;

public class RoleActivationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String roleName = intent.getExtras().getString("RoleName");
        String packageName = intent.getExtras().getString("PackageName");

        Log.v(TAG,"Permission request from package: " + packageName + ", permission name: " + roleName);

        //start prompt activity
        Intent intent1 = new Intent();
        intent1.setAction("com.samir.samirrolemanager.ROLECONFIRM");
        ComponentName cn = new ComponentName(context,com.samir.samirrolemanager.RoleActivationConfirmation.class);
        intent1.setComponent(cn);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.putExtra(mRole,roleName);
        intent1.putExtra(mPackage,packageName);
        intent1.addCategory("android.intent.category.ALTERNATIVE");

        if (intent1.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent1);
        }else{
            Log.v("TAG","Activity not found!");
        }

    }
}