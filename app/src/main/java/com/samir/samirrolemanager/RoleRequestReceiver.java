package com.samir.samirrolemanager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class RoleRequestReceiver extends BroadcastReceiver {
    private static final String TAG = "RoleRequestReceiver";

    protected static final String mRole = "Role";
    protected static final String mPackage = "Package";
    protected static final String roleHeading = "RoleHeading";
    protected static final String roleDescription = "RoleDescription";

    @Override
    public void onReceive(Context context, Intent intent) {
        String roleName = intent.getExtras().getString("RoleName");
        String packageName = intent.getExtras().getString("PackageName");

        Log.v(TAG,"Permission request from package: " + packageName + ", permission name: " + roleName);

        //start prompt activity
        Intent intent1 = new Intent();
        intent1.setAction("com.samir.samirrolemanager.PROMPT");
        ComponentName cn = new ComponentName(context,com.samir.samirrolemanager.Prompt.class);
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
        //context.startActivity(intent1);
    }


}