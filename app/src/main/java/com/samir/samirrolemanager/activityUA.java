package com.samir.samirrolemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class activityUA extends AppCompatActivity {

    private UA_Adapter mAdapter2;

    HashMap<String, List<String>> appList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ua);

        //Code that creates the RecyclerView and connects it with an adapter and the data
        // Get a handle to the RecyclerView.
        //member variables for the recycler view
        RecyclerView mRecyclerView = findViewById(R.id.altRecyclerView);

        // Create an adapter and supply the data to be displayed.
        mAdapter2 = new UA_Adapter(appList);
        mRecyclerView.setAdapter(mAdapter2);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        // Connect the adapter with the RecyclerView.
        mAdapter2.notifyDataSetChanged();

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(activityUA.this);
        RoleManagerDAO dao = roleManagerDB.roleManagerDAO();

        //Observer to monitor for changes in the PA
        dao.getAppsForRoleLiveData().observe(this, new Observer<List<RoleApp>>() {
            @Override
            public void onChanged(List<RoleApp> roleApp) {
                initData2();
                mAdapter2.notifyDataSetChanged();
            }
        });
    }

    public void initData2() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(activityUA.this);
                RoleManagerDAO dao = roleManagerDB.roleManagerDAO();

                String roleHeading = "role";
                roleOneAppInit(dao, roleHeading);
                roleTwoAppInit(dao, roleHeading);
                roleThreeAppInit(dao, roleHeading);
                roleFourAppInit(dao, roleHeading);
                roleFiveAppInit(dao, roleHeading);
                roleSixAppInit(dao, roleHeading);
                roleSevenAppInit(dao, roleHeading);
                roleEightAppInit(dao, roleHeading);
                roleNineAppInit(dao, roleHeading);
                roleTenAppInit(dao, roleHeading);
            }
        });
    }
    private void roleOneAppInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> roleApps = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "1_heading");
        for (RoleApp ra : dao.getRolesForApp()) {
            if(ra.getRoleApp1() != null){
                roleApps.add(ra.getRoleApp1().toString());
                Log.v("From activityUA:role_1 ",ra.getRoleApp1().toString());
            }
        }
        appList.put(tempRoleHeading, roleApps);
    }
    private void roleTwoAppInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> roleApps = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "2_heading");
        for (RoleApp ra : dao.getRolesForApp()) {
            if(ra.getRoleApp2() != null){
                roleApps.add(ra.getRoleApp2().toString());
                Log.v("From activityUA:role_2 ",ra.getRoleApp2().toString());
            }
        }
        appList.put(tempRoleHeading, roleApps);
    }
    private void roleThreeAppInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> roleApps = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "3_heading");
        for (RoleApp ra : dao.getRolesForApp()) {
            if(ra.getRoleApp3() != null){
                roleApps.add(ra.getRoleApp3().toString());
                Log.v("From activityUA:role_3 ",ra.getRoleApp3().toString());
            }
        }
        appList.put(tempRoleHeading, roleApps);
    }
    private void roleFourAppInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> roleApps = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "4_heading");
        for (RoleApp ra : dao.getRolesForApp()) {
            if(ra.getRoleApp4() != null){
                roleApps.add(ra.getRoleApp4().toString());
                Log.v("From activityUA:role_4 ",ra.getRoleApp4().toString());
            }
        }
        appList.put(tempRoleHeading, roleApps);
    }
    private void roleFiveAppInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> roleApps = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "5_heading");
        for (RoleApp ra : dao.getRolesForApp()) {
            if(ra.getRoleApp5() != null){
                roleApps.add(ra.getRoleApp5().toString());
                Log.v("From activityUA:role_5 ",ra.getRoleApp5().toString());
            }
        }
        appList.put(tempRoleHeading, roleApps);
    }
    private void roleSixAppInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> roleApps = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "6_heading");
        for (RoleApp ra : dao.getRolesForApp()) {
            if(ra.getRoleApp6() != null){
                roleApps.add(ra.getRoleApp6().toString());
                Log.v("From activityUA:role_6 ",ra.getRoleApp6().toString());
            }
        }
        appList.put(tempRoleHeading, roleApps);
    }
    private void roleSevenAppInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> roleApps = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "7_heading");
        for (RoleApp ra : dao.getRolesForApp()) {
            if(ra.getRoleApp7() != null){
                roleApps.add(ra.getRoleApp7().toString());
                Log.v("From activityUA:role_7 ",ra.getRoleApp7().toString());
            }
        }
        appList.put(tempRoleHeading, roleApps);
    }
    private void roleEightAppInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> roleApps = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "8_heading");
        for (RoleApp ra : dao.getRolesForApp()) {
            if(ra.getRoleApp8() != null){
                roleApps.add(ra.getRoleApp8().toString());
                Log.v("From activityUA:role_8 ",ra.getRoleApp8().toString());
            }
        }
        appList.put(tempRoleHeading, roleApps);
    }
    private void roleNineAppInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> roleApps = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "9_heading");
        for (RoleApp ra : dao.getRolesForApp()) {
            if(ra.getRoleApp9() != null){
                roleApps.add(ra.getRoleApp9().toString());
                Log.v("From activityUA:role_9 ",ra.getRoleApp9().toString());
            }
        }
        appList.put(tempRoleHeading, roleApps);
    }
    private void roleTenAppInit(RoleManagerDAO dao, String roleHeading) {
        String tempRoleHeading;
        List<String> roleApps = new ArrayList<>();
        tempRoleHeading = getRoleHeading(roleHeading, "10_heading");
        for (RoleApp ra : dao.getRolesForApp()) {
            if(ra.getRoleApp10() != null){
                roleApps.add(ra.getRoleApp10().toString());
                Log.v("From activityUA:role_10 ",ra.getRoleApp10().toString());
            }
        }
        appList.put(tempRoleHeading, roleApps);
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