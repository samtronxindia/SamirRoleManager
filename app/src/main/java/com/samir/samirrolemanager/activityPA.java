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
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class activityPA extends AppCompatActivity {

    private Role_ListAdapter mAdapter;

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

    //getter method for getting role heading from Strings.xml
    private String getRoleHeading(String roleHeading, String roleHeadingExtra) {
        String roleOneHeading;
        String tempRoleHeading = roleHeading + roleHeadingExtra;
        int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
        roleOneHeading = this.getString(resId);
        return roleOneHeading;
    }
}