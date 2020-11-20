package com.samir.samirrolemanager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NewPackageInstalledReceiver extends BroadcastReceiver {
    private final String TAG="NewPackageInstalledReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String launchedPkg = intent.getExtras().getString("NewPackageAdded");
        Log.v(TAG, "New package installed, broadcast received for package: " + launchedPkg);

        Context addedPkgContext;

        try {
            addedPkgContext = context.createPackageContext(launchedPkg, 0);
            Log.v(TAG, "Listing files for package: " + launchedPkg + " on first launch.");
            String[] list = addedPkgContext.getAssets().list("");
            for (String f1 : list) {
                Log.v(TAG, f1);
                Pattern p = Pattern.compile("^role");
                Matcher m = p.matcher(f1);
                if (m.find()) {
                    Intent intent1 = new Intent();
                    intent1.setAction("com.samir.samirrolemanager.ADDNEWROLE");
                    ComponentName cn = new ComponentName(context, NewAppAddRoleActivity.class);
                    intent1.setComponent(cn);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.putExtra("LaunchedPackage",launchedPkg);
                    intent1.addCategory("android.intent.category.ALTERNATIVE");
                    if (intent1.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent1);
                    }else{
                        Log.v("TAG","Activity not found!");
                    }
                }
            }
        }catch (PackageManager.NameNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}