package com.samir.samirrolemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.samir.samirrolemanager.RoleManagerDB.databaseWriteExecutor;

public class NewAppAddRoleActivity extends AppCompatActivity {
    private final String TAG = "NewAppAddRoleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_app_add_role);
        Context addedPkgContext;

        Intent intent = getIntent();
        String launchedPkg = intent.getExtras().getString("LaunchedPackage");

        try {
            addedPkgContext = createPackageContext(launchedPkg,0);
            Log.v(TAG,"Listing files for package: " + launchedPkg + " on first launch.");
            String[] list = addedPkgContext.getAssets().list("");
            for (String f1 : list) {
                Log.v(TAG, f1);
                Pattern p = Pattern.compile("^role");
                Matcher m = p.matcher(f1);
                if (m.find()) {
                    Log.v(TAG, "Filenames after regex pattern match: " + f1);
                    String[] roleNum = f1.split("_");
                    InputStreamReader is = new InputStreamReader(addedPkgContext.getAssets().open(f1));
                    BufferedReader reader = new BufferedReader(is);
                    String roleDescription = reader.readLine();
                    Log.v(TAG,"Role Description: " + roleDescription);
                    String line;
                    List<String> cusRolePerms = new ArrayList<String>();
                    Log.v(TAG,"BEGIN File contents============================================================");
                    while ((line = reader.readLine()) != null) {
                        Log.v(TAG,line);
                        cusRolePerms.add(line);
                    }
                    Log.v(TAG,"END File contents============================================================");
                    //MainActivity.roles.put(Integer.parseInt(roleNum[1]),lines);
                    is.close();
                    reader.close();
                    showNewRoleDialog(launchedPkg,f1,cusRolePerms,roleDescription);
                }
            }
        } catch (PackageManager.NameNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showNewRoleDialog(String mPackageName, String cusRoleName, List<String> cusRolePerms, String mRoleDescription) {

        Log.v(TAG, "Custom role perms from showNewRoleDialog method.");
        for(String cusRolePerm : cusRolePerms){
            Log.v(TAG, cusRolePerm);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        builder.setTitle("New app installation - Add role?");
        StringBuilder sb = new StringBuilder();
        sb.append("The package " + mPackageName + " wants to add a new role.\n\n");
        sb.append("Add " + cusRoleName + " to system roles?\n\n");
        sb.append("Role has following permissions:\n");
        for(String perm : cusRolePerms){
            sb.append(perm + "\n");
        }
        sb.append("\n\n" + mRoleDescription);
        builder.setMessage(sb.toString());
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                addNewCustomRole(cusRoleName, cusRolePerms, mRoleDescription, mPackageName);
                Toast.makeText(getApplicationContext(),"Role has been added!",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                onBackPressed();
            }
        });
        builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(),"Role not added.",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                onBackPressed();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void addNewCustomRole(String cusRoleName, List<String> cusRolePerms, String cusRoleDescription, String cusDefiningApp){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Log.v(TAG, "Custom role perms from addNewCustomRole method.");
                for(String cusRolePerm : cusRolePerms){
                    Log.v(TAG, cusRolePerm);
                }
                RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(NewAppAddRoleActivity.this);
                RoleManagerDAO dao = roleManagerDB.roleManagerDAO();

                for (int i = 11; i <= 15; i++) {
                    String tempCusRoleInternalName = "role" + i;
                    CustomRolePermission customRolePermission = dao.getCustomRolePermission(tempCusRoleInternalName);
                    if (customRolePermission.getCusRoleName() != null) {
                        continue;
                    }
                    Log.v(TAG, "Inside DBExecutor for loop.");
                    Log.v(TAG, "tempCusRoleInternalName = " + tempCusRoleInternalName);
                    dao.updateCusRolePerms(customRolePermission.getCusPermId(), cusRoleName, customRolePermission.getCusRoleInternalName(), cusRoleDescription, cusDefiningApp);
                    switch (tempCusRoleInternalName) {
                        case "role11":
                            Log.v(TAG, "Inside DBExecutor for loop. Inside switch role11");
                            int permId = 1;
                            for (String cusRolePerm : cusRolePerms) {
                                dao.updatePermRole11(permId, cusRolePerm);
                                permId++;
                            }
                            break;
                        case "role12":
                            permId = 1;
                            for (String cusRolePerm : cusRolePerms) {
                                dao.updatePermRole12(permId, cusRolePerm);
                                permId++;
                            }
                            break;
                        case "role13":
                            permId = 1;
                            for (String cusRolePerm : cusRolePerms) {
                                dao.updatePermRole13(permId, cusRolePerm);
                                permId++;
                            }
                            break;
                        case "role14":
                            permId = 1;
                            for (String cusRolePerm : cusRolePerms) {
                                dao.updatePermRole14(permId, cusRolePerm);
                                permId++;
                            }
                            break;
                        case "role15":
                            permId = 1;
                            for (String cusRolePerm : cusRolePerms) {
                                dao.updatePermRole15(permId, cusRolePerm);
                                permId++;
                            }
                            break;
                        default:
                            Log.v(TAG, "Role not found!");
                    }
                    break;
                }
            }

        });

    }
}