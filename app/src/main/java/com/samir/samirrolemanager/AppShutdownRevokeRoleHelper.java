package com.samir.samirrolemanager;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import static com.samir.samirrolemanager.RoleManagerDB.databaseWriteExecutor;

public class AppShutdownRevokeRoleHelper extends AsyncTask<Context, Void, Void> {

    private static volatile String TAG = "AppShutdownRevokeRoleHelper";
    private static volatile boolean appIsNoLongerRunning = false;
    private static volatile String[] mRoleArray = {"role1","role2","role3","role4","role5","role6","role7","role8","role9","role10"};
    String appNotRunningName;

    private WeakReference<Context> contextWeakReference;
    public AppShutdownRevokeRoleHelper(Context context) {
        contextWeakReference = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(Context... contexts) {

        Context context = contextWeakReference.get();

        //reset the app no longer running indicator variable
        appIsNoLongerRunning = false;

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mRunningAppProcessInfo = activityManager.getRunningAppProcesses();

        PackageManager packageManager = context.getPackageManager();

        RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(context);
        RoleManagerDAO dao = roleManagerDB.roleManagerDAO();
        int appId = 0;
        Boolean packageFound = false;
        for(RunningApps runApp : dao.getRunningApps()){
            //runApp.setAppRunning(false);
            dao.updateRunningApp(runApp.getRunningAppId(),runApp.getRunningApp(),false);
        }


        for (ActivityManager.RunningAppProcessInfo rApps : mRunningAppProcessInfo) {

            //did not get package name, so skip
            if (rApps.pkgList.length == 0){
                continue;
            }
            try{
                PackageInfo packageInfo = packageManager.getPackageInfo(rApps.pkgList[0],PackageManager.GET_ACTIVITIES);

                //check if package is not system, since we dont modify anything for system apps
                if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){

                    //package is not system, add it to our monitoring list
                    for(RunningApps runApp : dao.getRunningApps()){
                        if(runApp.getRunningApp() != null){
                            Log.v(TAG, "Running app from Activity Manager: " + packageInfo.packageName + " and from Room DB: " + runApp.getRunningApp());
                            if(runApp.getRunningApp().equals(packageInfo.packageName)){
                                runApp.setAppRunning(true);
                                dao.updateRunningApp(runApp.getRunningAppId(),runApp.getRunningApp(),true);
                                break;
                            }
                            continue;
                        }
                        dao.updateRunningApp(runApp.getRunningAppId(), packageInfo.packageName,true);
                        Log.v(TAG, "Running app: " + runApp.getRunningApp() + " active boolean : " + runApp.getAppRunning());
                        break;
                    }

                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Context context = contextWeakReference.get();

        databaseWriteExecutor.execute(() -> {
            RoleManagerDAO dao = RoleManagerDB.INSTANCE.roleManagerDAO();
            for (RunningApps runApp : dao.getRunningApps()) {
                //runApp.setAppRunning(false);
                if (runApp.getRunningApp() != null) {
                    Log.v(TAG, "App Running : " + runApp.getAppRunning() + " for app: " + runApp.getRunningApp().toString());
                    if (!runApp.getAppRunning()) {
                        appIsNoLongerRunning = true;
                        appNotRunningName = runApp.getRunningApp().toString();
                    }
                }
            }

            //copied from AppUninstallRoleRevoked
            if (appIsNoLongerRunning) {
                appIsNoLongerRunning = false;
                for (String mRole : mRoleArray) {
                    switch (mRole) {
                        case "role1":
                            for (RoleActiveApp ractive1 : dao.getRolesActive()) {
                                if (ractive1.getRoleActive1() != null) {
                                    if (ractive1.getRoleActive1().equals(appNotRunningName)) {
                                        dao.deleteFromRoleActiveApp1(appNotRunningName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            //now revoke all permissions
                            for (RolePermission rp1 : dao.getPermsForRole()) {
                                if (rp1.getRole1() != null) {
                                    activityPA.revokePermission(appNotRunningName, rp1.getRole1().toString(), context);
                                }
                            }
                            break;
                    case "role2":
                        for (RoleActiveApp ractive2 : dao.getRolesActive()) {
                            if (ractive2.getRoleActive2() != null) {
                                if (ractive2.getRoleActive2().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp2(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole2() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole2().toString(), context);
                            }
                        }
                        break;
                    case "role3":
                        for (RoleActiveApp ractive3 : dao.getRolesActive()) {
                            if (ractive3.getRoleActive3() != null) {
                                if (ractive3.getRoleActive3().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp3(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole3() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole3().toString(), context);
                            }
                        }
                        break;
                    case "role4":
                        for (RoleActiveApp ractive4 : dao.getRolesActive()) {
                            if (ractive4.getRoleActive4() != null) {
                                if (ractive4.getRoleActive4().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp4(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole4() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole4().toString(), context);
                            }
                        }
                        break;
                    case "role5":
                        for (RoleActiveApp ractive5 : dao.getRolesActive()) {
                            if (ractive5.getRoleActive5() != null) {
                                if (ractive5.getRoleActive5().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp5(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole5() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole5().toString(), context);
                            }
                        }
                        break;
                    case "role6":
                        for (RoleActiveApp ractive6 : dao.getRolesActive()) {
                            if (ractive6.getRoleActive6() != null) {
                                if (ractive6.getRoleActive6().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp6(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole6() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole6().toString(), context);
                            }
                        }
                        break;
                    case "role7":
                        for (RoleActiveApp ractive7 : dao.getRolesActive()) {
                            if (ractive7.getRoleActive7() != null) {
                                if (ractive7.getRoleActive7().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp7(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole7() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole7().toString(), context);
                            }
                        }
                        break;
                    case "role8":
                        for (RoleActiveApp ractive8 : dao.getRolesActive()) {
                            if (ractive8.getRoleActive8() != null) {
                                if (ractive8.getRoleActive8().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp8(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole8() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole8().toString(), context);
                            }
                        }
                        break;
                    case "role9":
                        for (RoleActiveApp ractive9 : dao.getRolesActive()) {
                            if (ractive9.getRoleActive9() != null) {
                                if (ractive9.getRoleActive9().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp9(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole9() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole9().toString(), context);
                            }
                        }
                        break;
                    case "role10":
                        for (RoleActiveApp ractive10 : dao.getRolesActive()) {
                            if (ractive10.getRoleActive10() != null) {
                                if (ractive10.getRoleActive10().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp10(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole10() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole10().toString(), context);
                            }
                        }
                        break;
                    case "role11":
                        for (RoleActiveApp ractive11 : dao.getRolesActive()) {
                            if (ractive11.getRoleActive11() != null) {
                                if (ractive11.getRoleActive11().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp11(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole11() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole11().toString(), context);
                            }
                        }
                        break;
                    case "role12":
                        for (RoleActiveApp ractive12 : dao.getRolesActive()) {
                            if (ractive12.getRoleActive12() != null) {
                                if (ractive12.getRoleActive12().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp12(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole12() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole12().toString(), context);
                            }
                        }
                        break;
                    case "role13":
                        for (RoleActiveApp ractive13 : dao.getRolesActive()) {
                            if (ractive13.getRoleActive13() != null) {
                                if (ractive13.getRoleActive13().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp13(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole13() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole13().toString(), context);
                            }
                        }
                        break;
                    case "role14":
                        for (RoleActiveApp ractive14 : dao.getRolesActive()) {
                            if (ractive14.getRoleActive14() != null) {
                                if (ractive14.getRoleActive14().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp14(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole14() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole14().toString(), context);
                            }
                        }
                        break;
                    case "role15":
                        for (RoleActiveApp ractive15 : dao.getRolesActive()) {
                            if (ractive15.getRoleActive15() != null) {
                                if (ractive15.getRoleActive15().equals(appNotRunningName)) {
                                    dao.deleteFromRoleActiveApp15(appNotRunningName);
                                    break;
                                }
                                continue;
                            }
                        }
                        //now revoke all permissions
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole15() != null) {
                                activityPA.revokePermission(appNotRunningName, rp1.getRole15().toString(), context);
                            }
                        }
                        break;
                    default:
                            Log.v(TAG,"Role was not found.");
                    }
                }
                //end copy
            }
        });
    }
}
