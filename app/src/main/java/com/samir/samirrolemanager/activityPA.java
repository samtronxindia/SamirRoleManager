package com.samir.samirrolemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class activityPA extends AppCompatActivity {

    private Role_ListAdapter mAdapter;
    private final String TAG="activityPA";

    //For initializing the PA in recyclerview with header/list_items
    List<Role> roleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pa);

        //Code that creates the RecyclerView and connects it with an adapter and the data
        // Get a handle to the RecyclerView.
        //member variables for the recycler view
        RecyclerView mRecyclerView = findViewById(R.id.mainRecyclerView);

        // Create an adapter and supply the data to be displayed.
        mAdapter = new Role_ListAdapter(roleList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        // Connect the adapter with the RecyclerView.
        mAdapter.notifyDataSetChanged();

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(activityPA.this);
        RoleManagerDAO dao = roleManagerDB.roleManagerDAO();

        AssetManager assetManager = getAssets();
        Log.v(TAG,"Just a list of all assets.");
        try {
            String[] files = assetManager.list("");
            for (String file : files){
                Log.v(TAG, "List of items inside assets: " + file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Observer to monitor for changes in the PA
        dao.getPermsForRoleLiveData().observe(this, new Observer<List<RolePermission>>() {
            @Override
            public void onChanged(List<RolePermission> rolePermissions) {
                initData2();
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    //grantRuntimePermission method in PermissionManagerService has been modified to be able to grant dangerous as well as normal permissions.
    protected static boolean grantPermission(String packageName,
                                             String permission, Context context) {
        PackageManager packageManager = context.getPackageManager();
        UserHandle user = Process.myUserHandle();
        packageManager.grantRuntimePermission(packageName, permission, user);
        return true;
    }

    //revokeRuntimePermission method in PermissionManagerService has been modified to be able to revoke dangerous as well as normal permissions.
    protected static boolean revokePermission(String packageName,
                                             String permission, Context context) {
        PackageManager packageManager = context.getPackageManager();
        UserHandle user = Process.myUserHandle();
        packageManager.revokeRuntimePermission(packageName, permission, user);
        return true;
    }

    protected static boolean checkPermission(String packageName,
                                              String permission, Context context) {
        PackageManager packageManager = context.getPackageManager();
        UserHandle user = Process.myUserHandle();
        if(packageManager.checkPermission(permission, packageName) == PackageManager.PERMISSION_GRANTED){
            return true;
        } else{
            return false;
        }
    }

    public void initData2() {

        Log.v("Stacktrace:",
                new Throwable().fillInStackTrace().getStackTrace()[1].getMethodName().toString());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(activityPA.this);

                RoleManagerDAO dao = roleManagerDB.roleManagerDAO();

                roleList.clear();

                String roleHeading = "role";
                roleOneInit(dao, roleHeading);
                roleTwoInit(dao, roleHeading);
                roleThreeInit(dao, roleHeading);
                roleFourInit(dao, roleHeading);
                roleFiveInit(dao, roleHeading);
                roleSixInit(dao, roleHeading);
                roleSevenInit(dao, roleHeading);
                roleEightInit(dao, roleHeading);
                roleNineInit(dao, roleHeading);
                roleTenInit(dao, roleHeading);

                //custom roles
                roleElevenInit(dao);
                roleTwelveInit(dao);
                roleThirteenInit(dao);
                roleFourteenInit(dao);
                roleFifteenInit(dao);
            }
        });
    }

    private void roleOneInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "1_heading");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole1() != null){
                rolePerms.add(rp.getRole1().toString());
                Log.v("From activityPA:role_1 ",rp.getRole1().toString());
            }
        }
        roleList.add(new Role(tempRoleHeading, rolePerms));
    }
    private void roleTwoInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "2_heading");
        List<RolePermission> permsForRole = dao.getPermsForRole();
        for (int i = 0; i < permsForRole.size(); i++) {
            RolePermission rp = permsForRole.get(i);
            if (rp.getRole2() != null) {
                rolePerms.add(rp.getRole2().toString());
                Log.v("From activityPA:role_2 ", rp.getRole2().toString());
            }
        }
        roleList.add(new Role(tempRoleHeading, rolePerms));
    }
    private void roleThreeInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "3_heading");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole3() != null){
                rolePerms.add(rp.getRole3().toString());
                Log.v("From activityPA:role_3 ",rp.getRole3().toString());
            }
        }
        roleList.add(new Role(tempRoleHeading, rolePerms));
    }
    private void roleFourInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "4_heading");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole4() != null){
                rolePerms.add(rp.getRole4().toString());
                Log.v("From activityPA:role_4 ",rp.getRole4().toString());
            }
        }
        roleList.add(new Role(tempRoleHeading, rolePerms));
    }
    private void roleFiveInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "5_heading");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole5() != null){
                rolePerms.add(rp.getRole5().toString());
                Log.v("From activityPA:role_5 ",rp.getRole5().toString());
            }
        }
        roleList.add(new Role(tempRoleHeading, rolePerms));
    }
    private void roleSixInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "6_heading");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole6() != null){
                rolePerms.add(rp.getRole6().toString());
                Log.v("From activityPA:role_6 ",rp.getRole6().toString());
            }
        }
        roleList.add(new Role(tempRoleHeading, rolePerms));
    }
    private void roleSevenInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "7_heading");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole7() != null){
                rolePerms.add(rp.getRole7().toString());
                Log.v("From activityPA:role_7 ",rp.getRole7().toString());
            }
        }
        roleList.add(new Role(tempRoleHeading, rolePerms));
    }
    private void roleEightInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "8_heading");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole8() != null){
                rolePerms.add(rp.getRole8().toString());
                Log.v("From activityPA:role_8 ",rp.getRole8().toString());
            }
        }
        roleList.add(new Role(tempRoleHeading, rolePerms));
    }
    private void roleNineInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "9_heading");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole9() != null){
                rolePerms.add(rp.getRole9().toString());
                Log.v("From activityPA:role_9 ",rp.getRole9().toString());
            }
        }
        roleList.add(new Role(tempRoleHeading, rolePerms));
    }
    private void roleTenInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "10_heading");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole10() != null){
                rolePerms.add(rp.getRole10().toString());
                Log.v("From activityPA:role_10 ",rp.getRole10().toString());
            }
        }
        roleList.add(new Role(tempRoleHeading, rolePerms));
    }
    /////////////////////////////

    private void roleElevenInit(RoleManagerDAO dao) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        CustomRolePermission customRolePermission = dao.getCustomRolePermission("role11");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole11() != null){
                rolePerms.add(rp.getRole11().toString());
                Log.v("From activityPA:role_11 ",rp.getRole11().toString());
            }
        }
        if(customRolePermission.getCusRoleName() != null){
            roleList.add(new Role(customRolePermission.getCusRoleName(), rolePerms));
        }
    }
    private void roleTwelveInit(RoleManagerDAO dao) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        CustomRolePermission customRolePermission = dao.getCustomRolePermission("role12");
        List<RolePermission> permsForRole = dao.getPermsForRole();
        for (int i = 0; i < permsForRole.size(); i++) {
            RolePermission rp = permsForRole.get(i);
            if (rp.getRole12() != null) {
                rolePerms.add(rp.getRole12().toString());
                Log.v("From activityPA:role_12 ", rp.getRole12().toString());
            }
        }
        if(customRolePermission.getCusRoleName() != null) {
            roleList.add(new Role(customRolePermission.getCusRoleName(), rolePerms));
        }
    }
    private void roleThirteenInit(RoleManagerDAO dao) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        CustomRolePermission customRolePermission = dao.getCustomRolePermission("role13");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole13() != null){
                rolePerms.add(rp.getRole13().toString());
                Log.v("From activityPA:role_13 ",rp.getRole13().toString());
            }
        }
        if(customRolePermission.getCusRoleName() != null) {
            roleList.add(new Role(customRolePermission.getCusRoleName(), rolePerms));
        }
    }
    private void roleFourteenInit(RoleManagerDAO dao) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        CustomRolePermission customRolePermission = dao.getCustomRolePermission("role14");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole14() != null){
                rolePerms.add(rp.getRole14().toString());
                Log.v("From activityPA:role_14 ",rp.getRole14().toString());
            }
        }
        if(customRolePermission.getCusRoleName() != null) {
            roleList.add(new Role(customRolePermission.getCusRoleName(), rolePerms));
        }
    }
    private void roleFifteenInit(RoleManagerDAO dao) {
        String tempRoleHeading;
        List<String> rolePerms = new ArrayList<>();
        CustomRolePermission customRolePermission = dao.getCustomRolePermission("role15");
        for (RolePermission rp : dao.getPermsForRole()) {
            if(rp.getRole15() != null){
                rolePerms.add(rp.getRole15().toString());
                Log.v("From activityPA:role_15 ",rp.getRole15().toString());
            }
        }
        if(customRolePermission.getCusRoleName() != null) {
            roleList.add(new Role(customRolePermission.getCusRoleName(), rolePerms));
        }
    }

    //getter method for getting role heading from Strings.xml
    private String getRoleHeading(String roleHeading, String roleHeadingExtra) {
        String roleOneHeading;
        String tempRoleHeading = roleHeading + roleHeadingExtra;
        int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
        roleOneHeading = this.getString(resId);
        return roleOneHeading;
    }
}