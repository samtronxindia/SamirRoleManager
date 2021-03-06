package com.samir.samirrolemanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoleManagerDAO {

    //for RolePerssion (PA) table
    @Query("SELECT * FROM role_permission")
    LiveData<List<RolePermission>> getPermsForRoleLiveData();

    //for RolePerssion (PA) table
    @Query("SELECT * FROM role_permission")
    List<RolePermission> getPermsForRole();

    @Query("DELETE FROM role_permission")
    void deleteAllRolePerms();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPermRole1(RolePermission role1);

    @Query("UPDATE role_permission SET role1=:role1  WHERE permId=:permId")
    void updatePermRole1(int permId, String role1);

    @Query("UPDATE role_permission SET role2=:role2  WHERE permId=:permId")
    void updatePermRole2(int permId, String role2);

    @Query("UPDATE role_permission SET role3=:role3  WHERE permId=:permId")
    void updatePermRole3(int permId, String role3);

    @Query("UPDATE role_permission SET role4=:role4  WHERE permId=:permId")
    void updatePermRole4(int permId, String role4);

    @Query("UPDATE role_permission SET role5=:role5  WHERE permId=:permId")
    void updatePermRole5(int permId, String role5);

    @Query("UPDATE role_permission SET role6=:role6  WHERE permId=:permId")
    void updatePermRole6(int permId, String role6);

    @Query("UPDATE role_permission SET role7=:role7  WHERE permId=:permId")
    void updatePermRole7(int permId, String role7);

    @Query("UPDATE role_permission SET role8=:role8  WHERE permId=:permId")
    void updatePermRole8(int permId, String role8);

    @Query("UPDATE role_permission SET role9=:role9  WHERE permId=:permId")
    void updatePermRole9(int permId, String role9);

    @Query("UPDATE role_permission SET role10=:role10  WHERE permId=:permId")
    void updatePermRole10(int permId, String role10);

    @Query("UPDATE role_permission SET role11=:role11  WHERE permId=:permId")
    void updatePermRole11(int permId, String role11);

    @Query("UPDATE role_permission SET role12=:role12  WHERE permId=:permId")
    void updatePermRole12(int permId, String role12);

    @Query("UPDATE role_permission SET role13=:role13  WHERE permId=:permId")
    void updatePermRole13(int permId, String role13);

    @Query("UPDATE role_permission SET role14=:role14  WHERE permId=:permId")
    void updatePermRole14(int permId, String role14);

    @Query("UPDATE role_permission SET role15=:role15  WHERE permId=:permId")
    void updatePermRole15(int permId, String role15);

    //for RoleApp (UA) table
    @Query("SELECT * FROM role_app")
    LiveData<List<RoleApp>> getAppsForRoleLiveData();

    @Query("SELECT * FROM role_app")
    List<RoleApp> getRolesForApp();

    @Query("DELETE FROM role_app")
    void deleteAllRoleApps();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoleApp1(RoleApp roleApp1);

    @Query("DELETE FROM role_app WHERE roleApp1=:roleApp1")
    void deleteFromRoleApp1(String roleApp1);

    @Query("DELETE FROM role_app WHERE roleApp2=:roleApp2")
    void deleteFromRoleApp2(String roleApp2);

    @Query("DELETE FROM role_app WHERE roleApp3=:roleApp3")
    void deleteFromRoleApp3(String roleApp3);

    @Query("DELETE FROM role_app WHERE roleApp4=:roleApp4")
    void deleteFromRoleApp4(String roleApp4);

    @Query("DELETE FROM role_app WHERE roleApp5=:roleApp5")
    void deleteFromRoleApp5(String roleApp5);

    @Query("DELETE FROM role_app WHERE roleApp6=:roleApp6")
    void deleteFromRoleApp6(String roleApp6);

    @Query("DELETE FROM role_app WHERE roleApp7=:roleApp7")
    void deleteFromRoleApp7(String roleApp7);

    @Query("DELETE FROM role_app WHERE roleApp8=:roleApp8")
    void deleteFromRoleApp8(String roleApp8);

    @Query("DELETE FROM role_app WHERE roleApp9=:roleApp9")
    void deleteFromRoleApp9(String roleApp9);

    @Query("DELETE FROM role_app WHERE roleApp10=:roleApp10")
    void deleteFromRoleApp10(String roleApp10);

    @Query("DELETE FROM role_app WHERE roleApp11=:roleApp11")
    void deleteFromRoleApp11(String roleApp11);

    @Query("DELETE FROM role_app WHERE roleApp12=:roleApp12")
    void deleteFromRoleApp12(String roleApp12);

    @Query("DELETE FROM role_app WHERE roleApp13=:roleApp13")
    void deleteFromRoleApp13(String roleApp13);

    @Query("DELETE FROM role_app WHERE roleApp14=:roleApp14")
    void deleteFromRoleApp14(String roleApp14);

    @Query("DELETE FROM role_app WHERE roleApp15=:roleApp15")
    void deleteFromRoleApp15(String roleApp15);

    @Query("UPDATE role_app SET roleApp1=:roleApp1  WHERE roleId=:roleId")
    void updateRoleApp1(int roleId, String roleApp1);

    @Query("UPDATE role_app SET roleApp2=:roleApp2  WHERE roleId=:roleId")
    void updateRoleApp2(int roleId, String roleApp2);

    @Query("UPDATE role_app SET roleApp3=:roleApp3  WHERE roleId=:roleId")
    void updateRoleApp3(int roleId, String roleApp3);

    @Query("UPDATE role_app SET roleApp4=:roleApp4  WHERE roleId=:roleId")
    void updateRoleApp4(int roleId, String roleApp4);

    @Query("UPDATE role_app SET roleApp5=:roleApp5  WHERE roleId=:roleId")
    void updateRoleApp5(int roleId, String roleApp5);

    ///
    @Query("UPDATE role_app SET roleApp6=:roleApp6  WHERE roleId=:roleId")
    void updateRoleApp6(int roleId, String roleApp6);

    @Query("UPDATE role_app SET roleApp7=:roleApp7  WHERE roleId=:roleId")
    void updateRoleApp7(int roleId, String roleApp7);

    @Query("UPDATE role_app SET roleApp8=:roleApp8  WHERE roleId=:roleId")
    void updateRoleApp8(int roleId, String roleApp8);

    @Query("UPDATE role_app SET roleApp9=:roleApp9  WHERE roleId=:roleId")
    void updateRoleApp9(int roleId, String roleApp9);

    @Query("UPDATE role_app SET roleApp10=:roleApp10  WHERE roleId=:roleId")
    void updateRoleApp10(int roleId, String roleApp10);

    @Query("UPDATE role_app SET roleApp11=:roleApp11  WHERE roleId=:roleId")
    void updateRoleApp11(int roleId, String roleApp11);

    @Query("UPDATE role_app SET roleApp12=:roleApp12  WHERE roleId=:roleId")
    void updateRoleApp12(int roleId, String roleApp12);

    @Query("UPDATE role_app SET roleApp13=:roleApp13  WHERE roleId=:roleId")
    void updateRoleApp13(int roleId, String roleApp13);

    @Query("UPDATE role_app SET roleApp14=:roleApp14  WHERE roleId=:roleId")
    void updateRoleApp14(int roleId, String roleApp14);

    @Query("UPDATE role_app SET roleApp15=:roleApp15  WHERE roleId=:roleId")
    void updateRoleApp15(int roleId, String roleApp15);

    //for RoleActiveApp table
    @Query("SELECT * FROM role_active")
    List<RoleActiveApp> getRolesActive();

    @Query("DELETE FROM role_active")
    void deleteAllRoleActive();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoleActive1(RoleActiveApp roleActive1);

    @Query("DELETE FROM role_active WHERE roleActive1=:roleActive1")
    void deleteFromRoleActiveApp1(String roleActive1);

    @Query("DELETE FROM role_active WHERE roleActive2=:roleActive2")
    void deleteFromRoleActiveApp2(String roleActive2);

    @Query("DELETE FROM role_active WHERE roleActive3=:roleActive3")
    void deleteFromRoleActiveApp3(String roleActive3);

    @Query("DELETE FROM role_active WHERE roleActive4=:roleActive4")
    void deleteFromRoleActiveApp4(String roleActive4);

    @Query("DELETE FROM role_active WHERE roleActive5=:roleActive5")
    void deleteFromRoleActiveApp5(String roleActive5);

    @Query("DELETE FROM role_active WHERE roleActive6=:roleActive6")
    void deleteFromRoleActiveApp6(String roleActive6);

    @Query("DELETE FROM role_active WHERE roleActive7=:roleActive7")
    void deleteFromRoleActiveApp7(String roleActive7);

    @Query("DELETE FROM role_active WHERE roleActive8=:roleActive8")
    void deleteFromRoleActiveApp8(String roleActive8);

    @Query("DELETE FROM role_active WHERE roleActive9=:roleActive9")
    void deleteFromRoleActiveApp9(String roleActive9);

    @Query("DELETE FROM role_active WHERE roleActive10=:roleActive10")
    void deleteFromRoleActiveApp10(String roleActive10);

    @Query("DELETE FROM role_active WHERE roleActive11=:roleActive11")
    void deleteFromRoleActiveApp11(String roleActive11);

    @Query("DELETE FROM role_active WHERE roleActive12=:roleActive12")
    void deleteFromRoleActiveApp12(String roleActive12);

    @Query("DELETE FROM role_active WHERE roleActive13=:roleActive13")
    void deleteFromRoleActiveApp13(String roleActive13);

    @Query("DELETE FROM role_active WHERE roleActive14=:roleActive14")
    void deleteFromRoleActiveApp14(String roleActive14);

    @Query("DELETE FROM role_active WHERE roleActive15=:roleActive15")
    void deleteFromRoleActiveApp15(String roleActive15);

    @Query("UPDATE role_active SET roleActive1=:roleActive1  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive1(int roleActiveId, String roleActive1);

    @Query("UPDATE role_active SET roleActive2=:roleActive2  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive2(int roleActiveId, String roleActive2);

    @Query("UPDATE role_active SET roleActive3=:roleActive3  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive3(int roleActiveId, String roleActive3);

    @Query("UPDATE role_active SET roleActive4=:roleActive4  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive4(int roleActiveId, String roleActive4);

    @Query("UPDATE role_active SET roleActive5=:roleActive5  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive5(int roleActiveId, String roleActive5);

    ///
    @Query("UPDATE role_active SET roleActive6=:roleActive6  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive6(int roleActiveId, String roleActive6);

    @Query("UPDATE role_active SET roleActive7=:roleActive7  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive7(int roleActiveId, String roleActive7);

    @Query("UPDATE role_active SET roleActive8=:roleActive8  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive8(int roleActiveId, String roleActive8);

    @Query("UPDATE role_active SET roleActive9=:roleActive9  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive9(int roleActiveId, String roleActive9);

    @Query("UPDATE role_active SET roleActive10=:roleActive10  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive10(int roleActiveId, String roleActive10);

    @Query("UPDATE role_active SET roleActive11=:roleActive11  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive11(int roleActiveId, String roleActive11);

    @Query("UPDATE role_active SET roleActive12=:roleActive12  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive12(int roleActiveId, String roleActive12);

    @Query("UPDATE role_active SET roleActive13=:roleActive13  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive13(int roleActiveId, String roleActive13);

    @Query("UPDATE role_active SET roleActive14=:roleActive14  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive14(int roleActiveId, String roleActive14);

    @Query("UPDATE role_active SET roleActive15=:roleActive15  WHERE roleActiveId=:roleActiveId")
    void updateRoleActive15(int roleActiveId, String roleActive15);

    //for RunningApps table
    @Query("SELECT * FROM running_apps")
    List<RunningApps> getRunningApps();

    @Query("DELETE FROM running_apps")
    void deleteAllRunningApps();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRunningApp(RunningApps runningApps);

    @Query("DELETE FROM running_apps WHERE runningApp=:runningApp")
    void deleteFromRunningApp(String runningApp);

    @Query("UPDATE running_apps SET runningApp=:runningApp, appRunning=:appRunning  WHERE runningAppId=:runningAppId")
    void updateRunningApp(int runningAppId, String runningApp, boolean appRunning);

    //for custom role permissions table
    @Query("SELECT * FROM cus_role_permission WHERE cusRoleInternalName=:cusRoleInternalName")
    CustomRolePermission getCustomRolePermission(String cusRoleInternalName);

    @Query("SELECT EXISTS(SELECT * FROM cus_role_permission WHERE cusDefiningApp=:cusDefiningApp)")
    boolean isCustomRolePermissionByPkg(String cusDefiningApp);

    @Query("SELECT * FROM cus_role_permission WHERE cusDefiningApp=:cusDefiningApp")
    CustomRolePermission getCustomRolePermissionByPkg(String cusDefiningApp);

    @Query("DELETE FROM cus_role_permission")
    void deleteAllCustomRolePerms();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCustomRolePerm(CustomRolePermission customRolePermission);

    @Query("UPDATE cus_role_permission SET cusRoleName=:cusRoleName, cusRoleInternalName=:cusRoleInternalName, cusRoleDescription=:cusRoleDescription, cusDefiningApp=:cusDefiningApp  WHERE cusPermId=:cusPermId")
    void updateCusRolePerms(int cusPermId, String cusRoleName, String cusRoleInternalName, String cusRoleDescription, String cusDefiningApp);

}
