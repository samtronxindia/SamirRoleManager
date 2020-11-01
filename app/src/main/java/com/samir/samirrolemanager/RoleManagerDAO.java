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

    @Insert
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

    //for RoleApp (UA) table
    @Query("SELECT * FROM role_app")
    List<RoleApp> getRolesForApp();

    @Query("DELETE FROM role_app")
    void deleteAllRoleApps();

    @Insert
    void insertRoleApp1(RoleApp roleApp1);

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

    //for RoleActiveApp table
    @Query("SELECT * FROM role_active")
    List<RoleActiveApp> getRolesActive();

    @Query("DELETE FROM role_active")
    void deleteAllRoleActive();

    @Insert
    void insertRoleActive1(RoleActiveApp roleActive1);

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

}
