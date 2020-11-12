package com.samir.samirrolemanager;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "role_app")
public class RoleApp {

    @PrimaryKey
    @NonNull
    private int roleId;

    @ColumnInfo
    private String roleApp1;

    @ColumnInfo
    private String roleApp2;

    @ColumnInfo
    private String roleApp3;

    @ColumnInfo
    private String roleApp4;

    @ColumnInfo
    private String roleApp5;

    @ColumnInfo
    private String roleApp6;

    @ColumnInfo
    private String roleApp7;

    @ColumnInfo
    private String roleApp8;

    @ColumnInfo
    private String roleApp9;

    @ColumnInfo
    private String roleApp10;

    @ColumnInfo
    private String roleApp11;

    @ColumnInfo
    private String roleApp12;

    @ColumnInfo
    private String roleApp13;

    @ColumnInfo
    private String roleApp14;

    @ColumnInfo
    private String roleApp15;

    public RoleApp(){}

    public int getRoleId() {
        return roleId;
    }

    public String getRoleApp1() {
        return roleApp1;
    }

    public String getRoleApp2() {
        return roleApp2;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRoleApp1(String roleApp1) {
        this.roleApp1 = roleApp1;
    }

    public String getRoleApp3() {
        return roleApp3;
    }

    public void setRoleApp2(String roleApp2) {
        this.roleApp2 = roleApp2;
    }

    public void setRoleApp3(String roleApp3) {
        this.roleApp3 = roleApp3;
    }

    public String getRoleApp4() {
        return roleApp4;
    }

    public void setRoleApp4(String roleApp4) {
        this.roleApp4 = roleApp4;
    }

    public String getRoleApp5() {
        return roleApp5;
    }

    public void setRoleApp5(String roleApp5) {
        this.roleApp5 = roleApp5;
    }

    public String getRoleApp6() {
        return roleApp6;
    }

    public void setRoleApp6(String roleApp6) {
        this.roleApp6 = roleApp6;
    }

    public String getRoleApp7() {
        return roleApp7;
    }

    public void setRoleApp7(String roleApp7) {
        this.roleApp7 = roleApp7;
    }

    public String getRoleApp8() {
        return roleApp8;
    }

    public void setRoleApp8(String roleApp8) {
        this.roleApp8 = roleApp8;
    }

    public String getRoleApp9() {
        return roleApp9;
    }

    public void setRoleApp9(String roleApp9) {
        this.roleApp9 = roleApp9;
    }

    public String getRoleApp10() {
        return roleApp10;
    }

    public void setRoleApp10(String roleApp10) {
        this.roleApp10 = roleApp10;
    }

    public String getRoleApp11() {
        return roleApp11;
    }

    public void setRoleApp11(String roleApp11) {
        this.roleApp11 = roleApp11;
    }

    public String getRoleApp12() {
        return roleApp12;
    }

    public void setRoleApp12(String roleApp12) {
        this.roleApp12 = roleApp12;
    }

    public String getRoleApp13() {
        return roleApp13;
    }

    public void setRoleApp13(String roleApp13) {
        this.roleApp13 = roleApp13;
    }

    public String getRoleApp14() {
        return roleApp14;
    }

    public void setRoleApp14(String roleApp14) {
        this.roleApp14 = roleApp14;
    }

    public String getRoleApp15() {
        return roleApp15;
    }

    public void setRoleApp15(String roleApp15) {
        this.roleApp15 = roleApp15;
    }
}
