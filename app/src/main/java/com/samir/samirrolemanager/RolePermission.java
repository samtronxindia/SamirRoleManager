package com.samir.samirrolemanager;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "role_permission")
public class RolePermission {

    @PrimaryKey
    @NonNull
    private int permId;

    @ColumnInfo
    private String role1;

    @ColumnInfo
    private String role2;

    @ColumnInfo
    private String role3;

    @ColumnInfo
    private String role4;

    @ColumnInfo
    private String role5;

    @ColumnInfo
    private String role6;

    @ColumnInfo
    private String role7;

    @ColumnInfo
    private String role8;

    @ColumnInfo
    private String role9;

    @ColumnInfo
    private String role10;

    public RolePermission(){}

    public int getPermId() {
        return permId;
    }

    public String getRole1() {
        return role1;
    }

    public String getRole2() {
        return role2;
    }

    public String getRole3() {
        return role3;
    }

    public void setPermId(int permId) {
        this.permId = permId;
    }

    public void setRole1(String role1) {
        this.role1 = role1;
    }

    public void setRole2(String role2) {
        this.role2 = role2;
    }

    public void setRole3(String role3) {
        this.role3 = role3;
    }

    public String getRole4() {
        return role4;
    }

    public void setRole4(String role4) {
        this.role4 = role4;
    }

    public String getRole5() {
        return role5;
    }

    public void setRole5(String role5) {
        this.role5 = role5;
    }

    public String getRole6() {
        return role6;
    }

    public void setRole6(String role6) {
        this.role6 = role6;
    }

    public String getRole7() {
        return role7;
    }

    public void setRole7(String role7) {
        this.role7 = role7;
    }

    public String getRole8() {
        return role8;
    }

    public void setRole8(String role8) {
        this.role8 = role8;
    }

    public String getRole9() {
        return role9;
    }

    public void setRole9(String role9) {
        this.role9 = role9;
    }

    public String getRole10() {
        return role10;
    }

    public void setRole10(String role10) {
        this.role10 = role10;
    }
}
