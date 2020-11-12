package com.samir.samirrolemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import static com.samir.samirrolemanager.RoleManagerDB.databaseWriteExecutor;

public class RoleActivationConfirmation extends AppCompatActivity {

    private static volatile boolean alreadyGranted = false;
    private static volatile boolean roleActivated = false;
    private static volatile boolean normalPermInRole = false;
    private final String TAG = "RoleActivationConfirmation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_activation_confirmation);

        Intent intent = getIntent();
        String mPackageName = intent.getStringExtra(RoleRequestReceiver.mPackage);
        String mRole = intent.getStringExtra(RoleRequestReceiver.mRole);

        alreadyGranted = false;
        roleActivated = false;
        normalPermInRole = false;

        //check if role has been granted to app, if granted, set alreadyGranted to true
        checkRoleAlreadyGrantedToApp(mRole, mPackageName);
        if(!alreadyGranted){
            showRoleNotGrantedDialog(mPackageName, mRole);
        }

        if(alreadyGranted){
            activateRoleForApp(mRole, mPackageName);
            if(normalPermInRole){
                showRoleActivatedDialog(mPackageName, mRole, true);
            }else{
                showRoleActivatedDialog(mPackageName, mRole, false);
            }
        }

    }

    private void showRoleActivatedDialog(String mPackageName, String mRole, Boolean normalPermInRole) {
        alreadyGranted = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);

        //get the role heading
        String mRoleHeading = mRole + "_heading";

        String[] roleSplit = mRole.split("(?<=\\D)(?=\\d)");

        ArrayList<String> roleDet = new ArrayList<>();


        if(Integer.parseInt(roleSplit[1]) <= 10) {
            int resId = getApplicationContext().getResources().getIdentifier(mRoleHeading,"string", getPackageName());
            String roleHeading = getApplicationContext().getString(resId);

            builder.setTitle(roleHeading + " has been activated!");
            //builder.setIcon(R.drawable.icon);
            StringBuilder sb = new StringBuilder();
            sb.append("The requested role has been activated!");
            //sb.append(mRoleDescription);
            builder.setMessage(sb.toString());
            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    if (normalPermInRole) {
                        //Try to re-launch the app
                        Log.v(TAG, "Normal permission in role - restarting app.");
                        Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(mPackageName);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                        startActivity(intent);
                    }
                    dialog.dismiss();
                    onBackPressed();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(RoleActivationConfirmation.this);
                    RoleManagerDAO dao = roleManagerDB.roleManagerDAO();

                    CustomRolePermission customRolePermission = dao.getCustomRolePermission(mRole);

                    roleDet.add(0, customRolePermission.getCusRoleName().toString());
                    roleDet.add(1, customRolePermission.getCusRoleDescription().toString());
                }
            });
            builder.setTitle(roleDet.get(0) + " has been activated!");
            //builder.setIcon(R.drawable.icon);
            StringBuilder sb = new StringBuilder();
            sb.append("The requested role has been activated!");
            //sb.append(mRoleDescription);
            builder.setMessage(sb.toString());
            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    if (normalPermInRole) {
                        //Try to re-launch the app
                        Log.v(TAG, "Normal permission in role - restarting app.");
                        Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(mPackageName);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                        startActivity(intent);
                    }
                    dialog.dismiss();
                    onBackPressed();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void showRoleNotGrantedDialog(String mPackageName, String mRole) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);

        //get the role heading
        String mRoleHeading = mRole + "_heading";
        String[] roleSplit = mRole.split("(?<=\\D)(?=\\d)");

        ArrayList<String> roleDet = new ArrayList<>();

        if(Integer.parseInt(roleSplit[1]) <= 10) {
            int resId = getApplicationContext().getResources().getIdentifier(mRoleHeading, "string", getPackageName());
            String roleHeading = getApplicationContext().getString(resId);

            builder.setTitle("ERROR: " + roleHeading + " has not been granted to " + mPackageName);
            //builder.setIcon(R.drawable.icon);
            StringBuilder sb = new StringBuilder();
            sb.append("Please request this role prior to activating it! ");
            //sb.append(mRoleDescription);
            builder.setMessage(sb.toString());
            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.dismiss();
                    onBackPressed();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(RoleActivationConfirmation.this);
                    RoleManagerDAO dao = roleManagerDB.roleManagerDAO();

                    CustomRolePermission customRolePermission = dao.getCustomRolePermission(mRole);

                    roleDet.add(0,customRolePermission.getCusRoleName().toString());
                    roleDet.add(1,customRolePermission.getCusRoleDescription().toString());
                }
            });

            builder.setTitle("ERROR: " + roleDet.get(0) + " has not been granted to " + mPackageName);
            //builder.setIcon(R.drawable.icon);
            StringBuilder sb = new StringBuilder();
            sb.append("Please request this role prior to activating it! ");
            //sb.append(mRoleDescription);
            builder.setMessage(sb.toString());
            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.dismiss();
                    onBackPressed();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
    }

    private void activateRoleForApp(String mRole, String mPackageName) {
        Log.v(TAG,"Tring to activate role: " + mRole + " for package: " + mPackageName);
        databaseWriteExecutor.execute(() -> {
                RoleManagerDAO dao = RoleManagerDB.INSTANCE.roleManagerDAO();
                switch(mRole){
                    case "role1":
                        for (RoleActiveApp ractive1 : dao.getRolesActive()) {
                            Log.v(TAG," Checking null works package name on location : " + ractive1.getRoleActive1());
                            if(ractive1.getRoleActive1() != null){
                                continue;
                            }
                            //ra1.setRoleApp1(mPackageName);
                            dao.updateRoleActive1(ractive1.getRoleActiveId(),mPackageName);
                            break;
                        }
                        //Log.v(TAG, "Permission INTERNET is: " + activityPA.checkPermission(mPackageName, Manifest.permission.INTERNET, getApplicationContext()));
                        //activityPA.grantPermission(mPackageName,"android.permission.INTERNET",getApplicationContext());
                        //Log.v(TAG, "Permission INTERNET is: " + activityPA.checkPermission(mPackageName, Manifest.permission.INTERNET, getApplicationContext()));
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole1() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole1().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole1().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole1().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role2":
                        for (RoleActiveApp ractive2 : dao.getRolesActive()) {
                            if(ractive2.getRoleActive2() != null){
                                continue;
                            }
                            //ra2.setRoleApp1(mPackageName);
                            dao.updateRoleActive2(ractive2.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole2() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole2().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole2().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole2().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role3":
                        for (RoleActiveApp ractive3 : dao.getRolesActive()) {
                            if(ractive3.getRoleActive3() != null){
                                continue;
                            }
                            //ra3.setRoleApp1(mPackageName);
                            dao.updateRoleActive3(ractive3.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole3() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole3().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole3().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole3().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role4":
                        for (RoleActiveApp ractive4 : dao.getRolesActive()) {
                            if(ractive4.getRoleActive4() != null){
                                continue;
                            }
                            //ra4.setRoleApp1(mPackageName);
                            dao.updateRoleActive4(ractive4.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole4() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole4().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole4().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole4().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role5":
                        for (RoleActiveApp ractive5 : dao.getRolesActive()) {
                            if(ractive5.getRoleActive5() != null){
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            dao.updateRoleActive5(ractive5.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole5() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole5().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole5().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole5().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role6":
                        for (RoleActiveApp ractive6 : dao.getRolesActive()) {
                            if(ractive6.getRoleActive6() != null){
                                continue;
                            }
                            //ra1.setRoleApp1(mPackageName);
                            dao.updateRoleActive6(ractive6.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole6() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole6().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole6().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole6().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role7":
                        for (RoleActiveApp ractive7 : dao.getRolesActive()) {
                            if(ractive7.getRoleActive7() != null){
                                continue;
                            }
                            //ra2.setRoleApp1(mPackageName);
                            dao.updateRoleActive7(ractive7.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole7() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole7().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole7().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole7().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role8":
                        for (RoleActiveApp ractive8 : dao.getRolesActive()) {
                            if(ractive8.getRoleActive8() != null){
                                continue;
                            }
                            //ra3.setRoleApp1(mPackageName);
                            dao.updateRoleActive8(ractive8.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole8() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole8().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole8().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole8().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role9":
                        for (RoleActiveApp ractive9 : dao.getRolesActive()) {
                            if(ractive9.getRoleActive9() != null){
                                continue;
                            }
                            //ra4.setRoleApp1(mPackageName);
                            dao.updateRoleActive9(ractive9.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole9() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole9().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole9().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole9().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role10":
                        for (RoleActiveApp ractive10 : dao.getRolesActive()) {
                            if(ractive10.getRoleActive10() != null){
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            dao.updateRoleActive10(ractive10.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole10() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole10().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole10().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole10().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role11":
                        for (RoleActiveApp ractive11 : dao.getRolesActive()) {
                            if(ractive11.getRoleActive11() != null){
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            dao.updateRoleActive11(ractive11.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole11() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole11().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole11().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole11().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role12":
                        for (RoleActiveApp ractive12 : dao.getRolesActive()) {
                            if(ractive12.getRoleActive12() != null){
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            dao.updateRoleActive12(ractive12.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole12() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole12().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole12().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole12().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role13":
                        for (RoleActiveApp ractive13 : dao.getRolesActive()) {
                            if(ractive13.getRoleActive13() != null){
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            dao.updateRoleActive13(ractive13.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole13() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole13().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole13().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole13().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role14":
                        for (RoleActiveApp ractive14 : dao.getRolesActive()) {
                            if(ractive14.getRoleActive14() != null){
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            dao.updateRoleActive14(ractive14.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole14() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole14().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole14().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole14().toString(),getApplicationContext());
                            }
                        }
                        break;
                    case "role15":
                        for (RoleActiveApp ractive15 : dao.getRolesActive()) {
                            if(ractive15.getRoleActive15() != null){
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            dao.updateRoleActive15(ractive15.getRoleActiveId(),mPackageName);
                            break;
                        }
                        for (RolePermission rp1 : dao.getPermsForRole()) {
                            if (rp1.getRole11() != null) {
                                String protectionLvl;
                                try {
                                    PermissionInfo pi = getPackageManager().getPermissionInfo(rp1.getRole15().toString(),PackageManager.GET_META_DATA);

                                    switch(pi.getProtection()){
                                        case PermissionInfo.PROTECTION_DANGEROUS:
                                            protectionLvl = "dangerous";
                                            break;
                                        case PermissionInfo.PROTECTION_NORMAL:
                                            protectionLvl = "normal";
                                            normalPermInRole = true;
                                            break;
                                        default:
                                            protectionLvl = "notNormalOrDangerous";
                                    }
                                    Log.v(TAG,"Permission: " + rp1.getRole15().toString() + " protection level: " + protectionLvl);
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                                activityPA.grantPermission(mPackageName,rp1.getRole15().toString(),getApplicationContext());
                            }
                        }
                        break;
                    default:
                        Log.v("RoleManagerDB","Activate Role : Role not found!");
                }
        });
    }

    private void checkRoleAlreadyGrantedToApp(String mRole, String mPackageName) {
        Log.v(TAG,"Check if role : " + mRole + " is granted for package: " + mPackageName);

        databaseWriteExecutor.execute(() -> {
                RoleManagerDAO dao = RoleManagerDB.INSTANCE.roleManagerDAO();
                switch(mRole){
                    case "role1":
                        for (RoleApp ra1 : dao.getRolesForApp()) {
                            if(ra1.getRoleApp1() != null){
                                Log.v(TAG, "Inside checkRoleAlreadyGranted: Requested role: " + mRole + " requesting package: " + mPackageName + " package on this location: " + ra1.getRoleApp1());
                                if(ra1.getRoleApp1().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra1.setRoleApp1(mPackageName);
                            //dao.updateRoleApp1(ra1.getRoleId(),mPackageName);
                        }
                        break;
                    case "role2":
                        for (RoleApp ra2 : dao.getRolesForApp()) {
                            if(ra2.getRoleApp2() != null){
                                if(ra2.getRoleApp2().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra2.setRoleApp1(mPackageName);
                            //dao.updateRoleApp2(ra2.getRoleId(),mPackageName);
                        }
                        break;
                    case "role3":
                        for (RoleApp ra3 : dao.getRolesForApp()) {
                            if(ra3.getRoleApp3() != null){
                                if(ra3.getRoleApp3().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra3.setRoleApp1(mPackageName);
                            //dao.updateRoleApp3(ra3.getRoleId(),mPackageName);
                        }
                        break;
                    case "role4":
                        for (RoleApp ra4 : dao.getRolesForApp()) {
                            if(ra4.getRoleApp4() != null){
                                if(ra4.getRoleApp4().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra4.setRoleApp1(mPackageName);
                            //dao.updateRoleApp4(ra4.getRoleId(),mPackageName);
                        }
                        break;
                    case "role5":
                        for (RoleApp ra5 : dao.getRolesForApp()) {
                            if(ra5.getRoleApp5() != null){
                                if(ra5.getRoleApp5().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            //dao.updateRoleApp5(ra5.getRoleId(),mPackageName);
                        }
                        break;
                    case "role6":
                        for (RoleApp ra6 : dao.getRolesForApp()) {
                            if(ra6.getRoleApp6() != null){
                                if(ra6.getRoleApp6().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra1.setRoleApp1(mPackageName);
                            //dao.updateRoleApp1(ra1.getRoleId(),mPackageName);
                        }
                        break;
                    case "role7":
                        for (RoleApp ra7 : dao.getRolesForApp()) {
                            if(ra7.getRoleApp7() != null){
                                if(ra7.getRoleApp7().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra2.setRoleApp1(mPackageName);
                            //dao.updateRoleApp2(ra2.getRoleId(),mPackageName);
                        }
                        break;
                    case "role8":
                        for (RoleApp ra8 : dao.getRolesForApp()) {
                            if(ra8.getRoleApp8() != null){
                                if(ra8.getRoleApp8().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra3.setRoleApp1(mPackageName);
                            //dao.updateRoleApp3(ra3.getRoleId(),mPackageName);
                        }
                        break;
                    case "role9":
                        for (RoleApp ra9 : dao.getRolesForApp()) {
                            if(ra9.getRoleApp9() != null){
                                if(ra9.getRoleApp9().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra4.setRoleApp1(mPackageName);
                            //dao.updateRoleApp4(ra4.getRoleId(),mPackageName);
                        }
                        break;
                    case "role10":
                        for (RoleApp ra10 : dao.getRolesForApp()) {
                            if(ra10.getRoleApp10() != null){
                                if(ra10.getRoleApp10().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            //dao.updateRoleApp5(ra5.getRoleId(),mPackageName);
                        }
                        break;
                    case "role11":
                        for (RoleApp ra11 : dao.getRolesForApp()) {
                            if(ra11.getRoleApp11() != null){
                                if(ra11.getRoleApp11().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            //dao.updateRoleApp5(ra5.getRoleId(),mPackageName);
                        }
                        break;
                    case "role12":
                        for (RoleApp ra12 : dao.getRolesForApp()) {
                            if(ra12.getRoleApp12() != null){
                                if(ra12.getRoleApp12().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            //dao.updateRoleApp5(ra5.getRoleId(),mPackageName);
                        }
                        break;
                    case "role13":
                        for (RoleApp ra13 : dao.getRolesForApp()) {
                            if(ra13.getRoleApp13() != null){
                                if(ra13.getRoleApp13().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            //dao.updateRoleApp5(ra5.getRoleId(),mPackageName);
                        }
                        break;
                    case "role14":
                        for (RoleApp ra14 : dao.getRolesForApp()) {
                            if(ra14.getRoleApp14() != null){
                                if(ra14.getRoleApp14().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            //dao.updateRoleApp5(ra5.getRoleId(),mPackageName);
                        }
                        break;
                    case "role15":
                        for (RoleApp ra15 : dao.getRolesForApp()) {
                            if(ra15.getRoleApp15() != null){
                                if(ra15.getRoleApp15().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            //dao.updateRoleApp5(ra5.getRoleId(),mPackageName);
                        }
                        break;
                    default:
                        Log.v("RoleManagerDB","Check Role Granted : Role not found!");
                }
        });
    }
}