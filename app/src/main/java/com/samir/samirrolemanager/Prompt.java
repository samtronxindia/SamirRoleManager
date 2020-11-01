package com.samir.samirrolemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Prompt extends AppCompatActivity {

    private static boolean alreadyGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        Intent intent = getIntent();
        String mPackageName = intent.getStringExtra(MyBroadcastReceiver.mPackage);
        String mRole = intent.getStringExtra(MyBroadcastReceiver.mRole);
        String mRoleHeading = intent.getStringExtra(MyBroadcastReceiver.roleHeading);
        String mRoleDescription = intent.getStringExtra(MyBroadcastReceiver.roleDescription);
        alreadyGranted = false;
        checkRoleAlreadyGrantedToApp(mRole, mPackageName);

        //only show role request dialog if role was not already granted.
        if(!alreadyGranted){
            showRoleRequestDialog(mPackageName, mRole, mRoleHeading, mRoleDescription);
        }
    }

    private void showRoleRequestDialog(String mPackageName, String mRole, String mRoleHeading, String mRoleDescription) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        builder.setTitle("App Requesting : " + mRoleHeading);
        //builder.setIcon(R.drawable.icon);
        StringBuilder sb = new StringBuilder();
        sb.append("Grant role to " + mPackageName + "?\n\n");
        sb.append(mRoleDescription);
        builder.setMessage(sb.toString());
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //MainActivity.grantPermission(mPackageName, mPermission, getApplicationContext());
                grantRoleToApp(mRole, mPackageName);
                Toast.makeText(getApplicationContext(),"Permission Allowed",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                onBackPressed();
                //finish();
            }
        });
        builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                onBackPressed();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showRoleAlreadyGrantedDialog(String mPackageName, String mRole) {
        alreadyGranted = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);

        //get the role heading
        String mRoleHeading = mRole + "_heading";
        int resId = getApplicationContext().getResources().getIdentifier(mRoleHeading,"string", getPackageName());
        String roleHeading = getApplicationContext().getString(resId);

        builder.setTitle("ERROR: Already granted: " + roleHeading);
        //builder.setIcon(R.drawable.icon);
        StringBuilder sb = new StringBuilder();
        sb.append("This role has already been granted to " + mPackageName + "!\n\n");
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

    private void checkRoleAlreadyGrantedToApp(String mRole, String mPackageName) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(getApplicationContext());
                RoleManagerDAO dao = roleManagerDB.roleManagerDAO();
                switch(mRole){
                    case "role1":
                        for (RoleApp ra1 : dao.getRolesForApp()) {
                            if(ra1.getRoleApp1() != null){
                                if(ra1.getRoleApp1().equals(mPackageName)){
                                    showRoleAlreadyGrantedDialog(mPackageName, mRole);
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
                                    showRoleAlreadyGrantedDialog(mPackageName, mRole);
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
                                    showRoleAlreadyGrantedDialog(mPackageName, mRole);
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
                                    showRoleAlreadyGrantedDialog(mPackageName, mRole);
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
                                    showRoleAlreadyGrantedDialog(mPackageName, mRole);
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
                                    showRoleAlreadyGrantedDialog(mPackageName, mRole);
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
                                    showRoleAlreadyGrantedDialog(mPackageName, mRole);
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
                                    showRoleAlreadyGrantedDialog(mPackageName, mRole);
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
                                    showRoleAlreadyGrantedDialog(mPackageName, mRole);
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
                                    showRoleAlreadyGrantedDialog(mPackageName, mRole);
                                    break;
                                }
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            //dao.updateRoleApp5(ra5.getRoleId(),mPackageName);
                        }
                        break;
                    default:
                        throw new IllegalStateException("Role not found!");
                }
            }
        });
    }

    private void grantRoleToApp(String mRole, String mPackageName) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(getApplicationContext());
                RoleManagerDAO dao = roleManagerDB.roleManagerDAO();
                switch(mRole){
                    case "role1":
                        for (RoleApp ra1 : dao.getRolesForApp()) {
                            if(ra1.getRoleApp1() != null){
                                continue;
                            }
                            //ra1.setRoleApp1(mPackageName);
                            dao.updateRoleApp1(ra1.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role2":
                        for (RoleApp ra2 : dao.getRolesForApp()) {
                            if(ra2.getRoleApp2() != null){
                                continue;
                            }
                            //ra2.setRoleApp1(mPackageName);
                            dao.updateRoleApp2(ra2.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role3":
                        for (RoleApp ra3 : dao.getRolesForApp()) {
                            if(ra3.getRoleApp3() != null){
                                continue;
                            }
                            //ra3.setRoleApp1(mPackageName);
                            dao.updateRoleApp3(ra3.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role4":
                        for (RoleApp ra4 : dao.getRolesForApp()) {
                            if(ra4.getRoleApp4() != null){
                                continue;
                            }
                            //ra4.setRoleApp1(mPackageName);
                            dao.updateRoleApp4(ra4.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role5":
                        for (RoleApp ra5 : dao.getRolesForApp()) {
                            if(ra5.getRoleApp5() != null){
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            dao.updateRoleApp5(ra5.getRoleId(),mPackageName);
                        }
                        break;
                    case "role6":
                        for (RoleApp ra6 : dao.getRolesForApp()) {
                            if(ra6.getRoleApp6() != null){
                                continue;
                            }
                            //ra1.setRoleApp1(mPackageName);
                            dao.updateRoleApp6(ra6.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role7":
                        for (RoleApp ra7 : dao.getRolesForApp()) {
                            if(ra7.getRoleApp7() != null){
                                continue;
                            }
                            //ra2.setRoleApp1(mPackageName);
                            dao.updateRoleApp7(ra7.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role8":
                        for (RoleApp ra8 : dao.getRolesForApp()) {
                            if(ra8.getRoleApp8() != null){
                                continue;
                            }
                            //ra3.setRoleApp1(mPackageName);
                            dao.updateRoleApp8(ra8.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role9":
                        for (RoleApp ra9 : dao.getRolesForApp()) {
                            if(ra9.getRoleApp9() != null){
                                continue;
                            }
                            //ra4.setRoleApp1(mPackageName);
                            dao.updateRoleApp9(ra9.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role10":
                        for (RoleApp ra10 : dao.getRolesForApp()) {
                            if(ra10.getRoleApp10() != null){
                                continue;
                            }
                            //ra5.setRoleApp1(mPackageName);
                            dao.updateRoleApp10(ra10.getRoleId(),mPackageName);
                        }
                        break;
                    default:
                        throw new IllegalStateException("Role not found!");
                }
            }
        });
    }
}