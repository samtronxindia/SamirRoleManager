package com.samir.samirrolemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import static com.samir.samirrolemanager.RoleManagerDB.databaseWriteExecutor;

public class AppBackgroundRoleDeactivatorActivity extends AppCompatActivity {

    private final String TAG = "AppBackgroundRoleDeactivatorActivity";
    private static volatile String[] mRoleArray = {"role1","role2","role3","role4","role5","role6","role7","role8","role9","role10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_background_role_deactivator);

        Intent intent = getIntent();
        String packageToDeactivate = intent.getExtras().getString("PackageToDeactivateRole");

        deactivateRoleForBackgroundApp(packageToDeactivate);
        onBackPressed();

        Log.v(TAG,"Launched background app role deactivator activity.");
    }

    private void deactivateRoleForBackgroundApp(String packageToDeactivate) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.v(TAG,"Inside run()");
                RoleManagerDAO dao = RoleManagerDB.INSTANCE.roleManagerDAO();
                for (String mRole : mRoleArray) {
                    switch (mRole) {
                        case "role1":
                            for (RoleActiveApp ractive1 : dao.getRolesActive()) {
                                if (ractive1.getRoleActive1() != null) {
                                    if (ractive1.getRoleActive1().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role1 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp1(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole1() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole1().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role2":
                            for (RoleActiveApp ractive2 : dao.getRolesActive()) {
                                if (ractive2.getRoleActive2() != null) {
                                    if (ractive2.getRoleActive2().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role2 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp2(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole2() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole2().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role3":
                            for (RoleActiveApp ractive3 : dao.getRolesActive()) {
                                if (ractive3.getRoleActive3() != null) {
                                    if (ractive3.getRoleActive3().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role3 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp3(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole3() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole3().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role4":
                            for (RoleActiveApp ractive4 : dao.getRolesActive()) {
                                if (ractive4.getRoleActive4() != null) {
                                    if (ractive4.getRoleActive4().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role4 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp4(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole4() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole4().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role5":
                            for (RoleActiveApp ractive5 : dao.getRolesActive()) {
                                if (ractive5.getRoleActive5() != null) {
                                    if (ractive5.getRoleActive5().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role5 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp5(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole5() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole5().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role6":
                            for (RoleActiveApp ractive6 : dao.getRolesActive()) {
                                if (ractive6.getRoleActive6() != null) {
                                    if (ractive6.getRoleActive6().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role6 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp6(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole6() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole6().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role7":
                            for (RoleActiveApp ractive7 : dao.getRolesActive()) {
                                if (ractive7.getRoleActive7() != null) {
                                    if (ractive7.getRoleActive7().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role7 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp7(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole7() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole7().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role8":
                            for (RoleActiveApp ractive8 : dao.getRolesActive()) {
                                if (ractive8.getRoleActive8() != null) {
                                    if (ractive8.getRoleActive8().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role8 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp8(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole8() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole8().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role9":
                            for (RoleActiveApp ractive9 : dao.getRolesActive()) {
                                if (ractive9.getRoleActive9() != null) {
                                    if (ractive9.getRoleActive9().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role9 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp9(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole9() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole9().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role10":
                            for (RoleActiveApp ractive10 : dao.getRolesActive()) {
                                if (ractive10.getRoleActive10() != null) {
                                    if (ractive10.getRoleActive10().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role10 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp10(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole10() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole10().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role11":
                            for (RoleActiveApp ractive11 : dao.getRolesActive()) {
                                if (ractive11.getRoleActive11() != null) {
                                    Log.v(TAG, "Debugging role active 11 | First if");
                                    if (ractive11.getRoleActive11().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Calling showRoleDeactivatedNotification for role11=====================");
                                        dao.deleteFromRoleActiveApp11(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole11() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole11().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role12":
                            for (RoleActiveApp ractive12 : dao.getRolesActive()) {
                                if (ractive12.getRoleActive12() != null) {
                                    if (ractive12.getRoleActive12().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role12 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp12(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole12() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole12().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role13":
                            for (RoleActiveApp ractive13 : dao.getRolesActive()) {
                                if (ractive13.getRoleActive13() != null) {
                                    if (ractive13.getRoleActive13().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role13 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp13(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole13() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole13().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role14":
                            for (RoleActiveApp ractive14 : dao.getRolesActive()) {
                                if (ractive14.getRoleActive14() != null) {
                                    if (ractive14.getRoleActive14().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role14 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp14(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole14() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole14().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role15":
                            for (RoleActiveApp ractive15 : dao.getRolesActive()) {
                                if (ractive15.getRoleActive15() != null) {
                                    if (ractive15.getRoleActive15().equals(packageToDeactivate)) {
                                        Log.v(TAG, "Role15 active, now deactivating.");
                                        dao.deleteFromRoleActiveApp15(packageToDeactivate);
                                        for (RolePermission rp1 : dao.getPermsForRole()) {
                                            if (rp1.getRole15() != null) {
                                                activityPA.revokePermission(packageToDeactivate, rp1.getRole15().toString(), AppBackgroundRoleDeactivatorActivity.this);
                                            }
                                        }
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        default:
                            Log.v(TAG, "Role was not found.");
                    }
                }
            }
        });
    }
}