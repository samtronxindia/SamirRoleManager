package com.samir.samirrolemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> mRoleList = new LinkedList<>();

    protected final static Object rolesLock = new Object();

    //member variables for the recycler view
    private RecyclerView mRecyclerView;
    private Role_ListAdapter mAdapter;

    private RoleManagerDB INSTANCE;

    LinkedList<String> fileNames = new LinkedList<>();

    protected static HashMap<Integer, List<String>> roles = new HashMap<Integer, List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("From onCreate","!");

        setContentView(R.layout.activity_main);

        //Code that creates the RecyclerView and connects it with an adapter and the data
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new Role_ListAdapter(this, mRoleList);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FileReadHelper fr = new FileReadHelper(this);
        fr.execute(this);

        RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(MainActivity.this);
        RoleManagerDAO dao = roleManagerDB.roleManagerDAO();

        initData();

        dao.getPermsForRoleLiveData().observe(this, new Observer<List<RolePermission>>() {
            @Override
            public void onChanged(List<RolePermission> rolePermissions) {
                initData();
                mAdapter.notifyDataSetChanged();
            }
        });

/*        //timer to update the recyclerview with DB data
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // When you need to modify a UI element, do so on the UI thread.
                // 'getActivity()' is required as this is being ran from a Fragment.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // This code will always run on the UI thread, therefore is safe to modify UI elements.

                    }
                });
            }
        }, 0, 10000);*/

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RoomExplorer.show(getApplicationContext(), RoleManagerDB.class, "word_database");
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

    public void initData() {

        Log.v("Stacktrace:",
                new Throwable().fillInStackTrace().getStackTrace()[1].getMethodName().toString());
        RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(MainActivity.this);
        RoleManagerDAO dao = roleManagerDB.roleManagerDAO();
        boolean alreadyAddedRole1 = false;
        boolean alreadyAddedRole2 = false;
        boolean alreadyAddedRole3 = false;
        boolean alreadyAddedRole4 = false;
        boolean alreadyAddedRole5 = false;
        boolean alreadyAddedRole6 = false;
        boolean alreadyAddedRole7 = false;
        boolean alreadyAddedRole8 = false;
        boolean alreadyAddedRole9 = false;
        boolean alreadyAddedRole10 = false;
        mRoleList.clear();

        String roleHeading = "role";

        for (RolePermission rp1 : dao.getPermsForRole()) {
            if(rp1.getRole1() != null){
                if(!alreadyAddedRole1){
                    String tempRoleHeading = roleHeading + "1_heading";
                    int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
                    mRoleList.addLast(this.getString(resId));
                    alreadyAddedRole1 = true;
                }
                mRoleList.addLast(rp1.getRole1().toString());
                Log.v("From MainActivity:role_1 ",rp1.getRole1().toString());
            }
        }
        for (RolePermission rp1 : dao.getPermsForRole()) {
            if(rp1.getRole2() != null){
                if(!alreadyAddedRole2){
                    String tempRoleHeading = roleHeading + "2_heading";
                    int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
                    mRoleList.addLast(this.getString(resId));
                    alreadyAddedRole2 = true;
                }
                mRoleList.addLast(rp1.getRole2().toString());
                Log.v("From MainActivity:role_2 ",rp1.getRole2().toString());
            }
        }
        for (RolePermission rp1 : dao.getPermsForRole()) {
            if(rp1.getRole3() != null){
                if(!alreadyAddedRole3){
                    String tempRoleHeading = roleHeading + "3_heading";
                    int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
                    mRoleList.addLast(this.getString(resId));
                    alreadyAddedRole3 = true;
                }
                mRoleList.addLast(rp1.getRole3().toString());
                Log.v("From MainActivity:role_3 ",rp1.getRole3().toString());
            }
        }
        for (RolePermission rp1 : dao.getPermsForRole()) {
            if(rp1.getRole4() != null){
                if(!alreadyAddedRole4){
                    String tempRoleHeading = roleHeading + "4_heading";
                    int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
                    mRoleList.addLast(this.getString(resId));
                    alreadyAddedRole4 = true;
                }
                mRoleList.addLast(rp1.getRole4().toString());
                Log.v("From MainActivity:role_4 ",rp1.getRole4().toString());
            }
        }
        for (RolePermission rp1 : dao.getPermsForRole()) {
            if(rp1.getRole5() != null){
                if(!alreadyAddedRole5){
                    String tempRoleHeading = roleHeading + "5_heading";
                    int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
                    mRoleList.addLast(this.getString(resId));
                    alreadyAddedRole5 = true;
                }
                mRoleList.addLast(rp1.getRole5().toString());
                Log.v("From MainActivity:role_5 ",rp1.getRole5().toString());
            }
        }
        for (RolePermission rp1 : dao.getPermsForRole()) {
            if(rp1.getRole6() != null){
                if(!alreadyAddedRole6){
                    String tempRoleHeading = roleHeading + "6_heading";
                    int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
                    mRoleList.addLast(this.getString(resId));
                    alreadyAddedRole6 = true;
                }
                mRoleList.addLast(rp1.getRole6().toString());
                Log.v("From MainActivity:role_6 ",rp1.getRole6().toString());
            }
        }
        for (RolePermission rp1 : dao.getPermsForRole()) {
            if(rp1.getRole7() != null){
                if(!alreadyAddedRole7){
                    String tempRoleHeading = roleHeading + "7_heading";
                    int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
                    mRoleList.addLast(this.getString(resId));
                    alreadyAddedRole7 = true;
                }
                mRoleList.addLast(rp1.getRole7().toString());
                Log.v("From MainActivity:role_7 ",rp1.getRole7().toString());
            }
        }
        for (RolePermission rp1 : dao.getPermsForRole()) {
            if(rp1.getRole8() != null){
                if(!alreadyAddedRole8){
                    String tempRoleHeading = roleHeading + "8_heading";
                    int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
                    mRoleList.addLast(this.getString(resId));
                    alreadyAddedRole8 = true;
                }
                mRoleList.addLast(rp1.getRole4().toString());
                Log.v("From MainActivity:role_8 ",rp1.getRole8().toString());
            }
        }
        for (RolePermission rp1 : dao.getPermsForRole()) {
            if(rp1.getRole9() != null){
                if(!alreadyAddedRole9){
                    String tempRoleHeading = roleHeading + "9_heading";
                    int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
                    mRoleList.addLast(this.getString(resId));
                    alreadyAddedRole9 = true;
                }
                mRoleList.addLast(rp1.getRole9().toString());
                Log.v("From MainActivity:role_9 ",rp1.getRole9().toString());
            }
        }
        for (RolePermission rp1 : dao.getPermsForRole()) {
            if(rp1.getRole10() != null){
                if(!alreadyAddedRole10){
                    String tempRoleHeading = roleHeading + "10_heading";
                    int resId = this.getResources().getIdentifier(tempRoleHeading,"string", getPackageName());
                    mRoleList.addLast(this.getString(resId));
                    alreadyAddedRole10 = true;
                }
                mRoleList.addLast(rp1.getRole10().toString());
                Log.v("From MainActivity:role_10 ",rp1.getRole10().toString());
            }
        }
    }
}