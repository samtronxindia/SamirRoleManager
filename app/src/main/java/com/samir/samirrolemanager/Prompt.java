package com.samir.samirrolemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static com.samir.samirrolemanager.RoleManagerDB.databaseWriteExecutor;

public class Prompt extends AppCompatActivity {

    private static volatile boolean alreadyGranted = false;
    private static String TAG = "Prompt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        Intent intent = getIntent();
        String mPackageName = intent.getStringExtra(RoleRequestReceiver.mPackage);
        String mRole = intent.getStringExtra(RoleRequestReceiver.mRole);

        ArrayList<String> roleDetails = new ArrayList<>(getRoleDetails(this, mRole));

        String mRoleHeading = roleDetails.get(0);
        String mRoleDescription = roleDetails.get(1);


        alreadyGranted = false;
        checkRoleAlreadyGrantedToApp(mRole, mPackageName);

        //only show role request dialog if role was not already granted.
        if(!alreadyGranted){
            showRoleRequestDialog(mPackageName, mRole, mRoleHeading, mRoleDescription);
        }else{
            onBackPressed();
        }
    }

    public ArrayList<String> getRoleDetails(Context mContext, String role){
        String getRoleHeading = role + "_heading";
        String getRoleDescription = role + "_description";

        String packageName = mContext.getPackageName();

        String[] roleSplit = role.split("(?<=\\D)(?=\\d)");

        Log.v(TAG,"Split role: " + roleSplit[0] + " role number = " + roleSplit[1]);

        ArrayList<String> roleDet = new ArrayList<>();

        if(Integer.parseInt(roleSplit[1]) <= 10){
            int resId = mContext.getResources().getIdentifier(getRoleHeading,"string", packageName);
            String roleHeading = mContext.getString(resId);

            resId = mContext.getResources().getIdentifier(getRoleDescription,"string",packageName);
            String roleDescription = mContext.getString(resId);
            roleDet.add(0, roleHeading);
            roleDet.add(1, roleDescription);
            return roleDet;
        } else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(Prompt.this);
                    RoleManagerDAO dao = roleManagerDB.roleManagerDAO();

                    CustomRolePermission customRolePermission = dao.getCustomRolePermission(role);

                    roleDet.add(0,customRolePermission.getCusRoleName().toString());
                    roleDet.add(1,customRolePermission.getCusRoleDescription().toString());
                }
            });

        }
        return roleDet;
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
                //activityPA.grantPermission(mPackageName, mPermission, getApplicationContext());
                grantRoleToApp(mRole, mPackageName);
                Toast.makeText(getApplicationContext(),"Role Granted!",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                onBackPressed();
                //finish();
            }
        });
        builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(),"Role Denied!",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                onBackPressed();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void checkRoleAlreadyGrantedToApp(String mRole, String mPackageName) {
        databaseWriteExecutor.execute(() -> {
                RoleManagerDAO dao = RoleManagerDB.INSTANCE.roleManagerDAO();
                switch(mRole){
                    case "role1":
                        for (RoleApp ra1 : dao.getRolesForApp()) {
                            if(ra1.getRoleApp1() != null){
                                if(ra1.getRoleApp1().equals(mPackageName)){
                                    alreadyGranted = true;
                                    break;
                                }
                                continue;
                            }
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
                        }
                        break;
                    default:
                        Log.v(TAG,"Role not found!");
                }
        });
    }

    private void grantRoleToApp(String mRole, String mPackageName) {
        databaseWriteExecutor.execute(() -> {
                RoleManagerDAO dao = RoleManagerDB.INSTANCE.roleManagerDAO();
                switch(mRole){
                    case "role1":
                        for (RoleApp ra1 : dao.getRolesForApp()) {
                            if(ra1.getRoleApp1() != null){
                                continue;
                            }
                            dao.updateRoleApp1(ra1.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role2":
                        for (RoleApp ra2 : dao.getRolesForApp()) {
                            if(ra2.getRoleApp2() != null){
                                continue;
                            }
                            dao.updateRoleApp2(ra2.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role3":
                        for (RoleApp ra3 : dao.getRolesForApp()) {
                            if(ra3.getRoleApp3() != null){
                                continue;
                            }
                            dao.updateRoleApp3(ra3.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role4":
                        for (RoleApp ra4 : dao.getRolesForApp()) {
                            if(ra4.getRoleApp4() != null){
                                continue;
                            }
                            dao.updateRoleApp4(ra4.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role5":
                        for (RoleApp ra5 : dao.getRolesForApp()) {
                            if(ra5.getRoleApp5() != null){
                                continue;
                            }
                            dao.updateRoleApp5(ra5.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role6":
                        for (RoleApp ra6 : dao.getRolesForApp()) {
                            if(ra6.getRoleApp6() != null){
                                continue;
                            }
                            dao.updateRoleApp6(ra6.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role7":
                        for (RoleApp ra7 : dao.getRolesForApp()) {
                            if(ra7.getRoleApp7() != null){
                                continue;
                            }
                            dao.updateRoleApp7(ra7.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role8":
                        for (RoleApp ra8 : dao.getRolesForApp()) {
                            if(ra8.getRoleApp8() != null){
                                continue;
                            }
                            dao.updateRoleApp8(ra8.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role9":
                        for (RoleApp ra9 : dao.getRolesForApp()) {
                            if(ra9.getRoleApp9() != null){
                                continue;
                            }
                            dao.updateRoleApp9(ra9.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role10":
                        for (RoleApp ra10 : dao.getRolesForApp()) {
                            if(ra10.getRoleApp10() != null){
                                continue;
                            }
                            dao.updateRoleApp10(ra10.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role11":
                        for (RoleApp ra11 : dao.getRolesForApp()) {
                            if(ra11.getRoleApp11() != null){
                                continue;
                            }
                            dao.updateRoleApp11(ra11.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role12":
                        for (RoleApp ra12 : dao.getRolesForApp()) {
                            if(ra12.getRoleApp12() != null){
                                continue;
                            }
                            dao.updateRoleApp12(ra12.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role13":
                        for (RoleApp ra13 : dao.getRolesForApp()) {
                            if(ra13.getRoleApp13() != null){
                                continue;
                            }
                            dao.updateRoleApp13(ra13.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role14":
                        for (RoleApp ra14 : dao.getRolesForApp()) {
                            if(ra14.getRoleApp14() != null){
                                continue;
                            }
                            dao.updateRoleApp14(ra14.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    case "role15":
                        for (RoleApp ra15 : dao.getRolesForApp()) {
                            if(ra15.getRoleApp15() != null){
                                continue;
                            }
                            dao.updateRoleApp15(ra15.getRoleId(),mPackageName);
                            break;
                        }
                        break;
                    default:
                        Log.v(TAG,"Role not found!");
                }
        });
    }
}