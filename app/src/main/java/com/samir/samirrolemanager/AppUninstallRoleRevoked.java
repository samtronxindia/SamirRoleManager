package com.samir.samirrolemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static com.samir.samirrolemanager.RoleManagerDB.databaseWriteExecutor;

public class AppUninstallRoleRevoked extends AppCompatActivity {

    private static volatile String[] mRoleArray = {"role1","role2","role3","role4","role5","role6","role7","role8","role9","role10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_uninstall_role_revoked);

        Intent intent = getIntent();
        String mPackageName = intent.getStringExtra(MyBroadcastReceiver.mPackage);

        revokeRoleFromApp(mPackageName);
        deactivateRoleFromApp(mPackageName);
        removeFromRunningApps(mPackageName);
        onBackPressed();
    }

    private void removeFromRunningApps(String mPackageName){
        databaseWriteExecutor.execute(() -> {
                RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(getApplicationContext());
                RoleManagerDAO dao = roleManagerDB.roleManagerDAO();
                for(RunningApps runApp : dao.getRunningApps()){
                    if(runApp.getRunningApp() != null){
                        if(runApp.getRunningApp().equals(mPackageName)){
                            dao.deleteFromRunningApp(mPackageName);
                            break;
                        }
                    }
                }
        });
    }

    private void deactivateRoleFromApp(String mPackageName) {
        databaseWriteExecutor.execute(() -> {
                RoleManagerDB roleManagerDB = RoleManagerDB.getDatabase(getApplicationContext());
                RoleManagerDAO dao = roleManagerDB.roleManagerDAO();
                for(String mRole : mRoleArray) {
                    switch (mRole) {
                        case "role1":
                            for (RoleActiveApp ractive1 : dao.getRolesActive()) {
                                if (ractive1.getRoleActive1() != null) {
                                    if (ractive1.getRoleActive1().equals(mPackageName)) {
                                        dao.deleteFromRoleActiveApp1(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role2":
                            for (RoleActiveApp ractive2 : dao.getRolesActive()) {
                                if (ractive2.getRoleActive2() != null) {
                                    if (ractive2.getRoleActive2().equals(mPackageName)) {
                                        dao.deleteFromRoleActiveApp2(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role3":
                            for (RoleActiveApp ractive3 : dao.getRolesActive()) {
                                if (ractive3.getRoleActive3() != null) {
                                    if (ractive3.getRoleActive3().equals(mPackageName)) {
                                        dao.deleteFromRoleActiveApp3(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role4":
                            for (RoleActiveApp ractive4 : dao.getRolesActive()) {
                                if (ractive4.getRoleActive4() != null) {
                                    if (ractive4.getRoleActive4().equals(mPackageName)) {
                                        dao.deleteFromRoleActiveApp4(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role5":
                            for (RoleActiveApp ractive5 : dao.getRolesActive()) {
                                if (ractive5.getRoleActive5() != null) {
                                    if (ractive5.getRoleActive5().equals(mPackageName)) {
                                        dao.deleteFromRoleActiveApp5(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role6":
                            for (RoleActiveApp ractive6 : dao.getRolesActive()) {
                                if (ractive6.getRoleActive6() != null) {
                                    if (ractive6.getRoleActive6().equals(mPackageName)) {
                                        dao.deleteFromRoleActiveApp6(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role7":
                            for (RoleActiveApp ractive7 : dao.getRolesActive()) {
                                if (ractive7.getRoleActive7() != null) {
                                    if (ractive7.getRoleActive7().equals(mPackageName)) {
                                        dao.deleteFromRoleActiveApp7(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role8":
                            for (RoleActiveApp ractive8 : dao.getRolesActive()) {
                                if (ractive8.getRoleActive8() != null) {
                                    if (ractive8.getRoleActive8().equals(mPackageName)) {
                                        dao.deleteFromRoleActiveApp8(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role9":
                            for (RoleActiveApp ractive9 : dao.getRolesActive()) {
                                if (ractive9.getRoleActive9() != null) {
                                    if (ractive9.getRoleActive9().equals(mPackageName)) {
                                        dao.deleteFromRoleActiveApp9(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role10":
                            for (RoleActiveApp ractive10 : dao.getRolesActive()) {
                                if (ractive10.getRoleActive10() != null) {
                                    if (ractive10.getRoleActive10().equals(mPackageName)) {
                                        dao.deleteFromRoleActiveApp10(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        default:
                            throw new IllegalStateException("Role not found!");
                    }
                }
        });
    }

    private void revokeRoleFromApp(String mPackageName) {
        databaseWriteExecutor.execute(() -> {
                RoleManagerDAO dao = RoleManagerDB.INSTANCE.roleManagerDAO();
                for(String mRole : mRoleArray) {
                    switch (mRole) {
                        case "role1":
                            for (RoleApp ra1 : dao.getRolesForApp()) {
                                if (ra1.getRoleApp1() != null) {
                                    if (ra1.getRoleApp1().equals(mPackageName)) {
                                        dao.deleteFromRoleApp1(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role2":
                            for (RoleApp ra2 : dao.getRolesForApp()) {
                                if (ra2.getRoleApp2() != null) {
                                    if (ra2.getRoleApp2().equals(mPackageName)) {
                                        dao.deleteFromRoleApp2(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role3":
                            for (RoleApp ra3 : dao.getRolesForApp()) {
                                if (ra3.getRoleApp3() != null) {
                                    if (ra3.getRoleApp3().equals(mPackageName)) {
                                        dao.deleteFromRoleApp3(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role4":
                            for (RoleApp ra4 : dao.getRolesForApp()) {
                                if (ra4.getRoleApp4() != null) {
                                    if (ra4.getRoleApp4().equals(mPackageName)) {
                                        dao.deleteFromRoleApp4(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role5":
                            for (RoleApp ra5 : dao.getRolesForApp()) {
                                if (ra5.getRoleApp5() != null) {
                                    if (ra5.getRoleApp5().equals(mPackageName)) {
                                        dao.deleteFromRoleApp5(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role6":
                            for (RoleApp ra6 : dao.getRolesForApp()) {
                                if (ra6.getRoleApp6() != null) {
                                    if (ra6.getRoleApp6().equals(mPackageName)) {
                                        dao.deleteFromRoleApp6(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role7":
                            for (RoleApp ra7 : dao.getRolesForApp()) {
                                if (ra7.getRoleApp7() != null) {
                                    if (ra7.getRoleApp7().equals(mPackageName)) {
                                        dao.deleteFromRoleApp7(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role8":
                            for (RoleApp ra8 : dao.getRolesForApp()) {
                                if (ra8.getRoleApp8() != null) {
                                    if (ra8.getRoleApp8().equals(mPackageName)) {
                                        dao.deleteFromRoleApp8(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role9":
                            for (RoleApp ra9 : dao.getRolesForApp()) {
                                if (ra9.getRoleApp9() != null) {
                                    if (ra9.getRoleApp9().equals(mPackageName)) {
                                        dao.deleteFromRoleApp9(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        case "role10":
                            for (RoleApp ra10 : dao.getRolesForApp()) {
                                if (ra10.getRoleApp10() != null) {
                                    if (ra10.getRoleApp10().equals(mPackageName)) {
                                        dao.deleteFromRoleApp10(mPackageName);
                                        break;
                                    }
                                    continue;
                                }
                            }
                            break;
                        default:
                            throw new IllegalStateException("Role not found!");
                    }
                }
        });
    }
}