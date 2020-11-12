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

    @ColumnInfo
    private String role11;

    @ColumnInfo
    private String role12;

    @ColumnInfo
    private String role13;

    @ColumnInfo
    private String role14;

    @ColumnInfo
    private String role15;

    @ColumnInfo
    private String role16;

    @ColumnInfo
    private String role17;

    @ColumnInfo
    private String role18;

    @ColumnInfo
    private String role19;

    @ColumnInfo
    private String role20;

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

    public String getRole11() { return role11; }

    public void setRole11(String role11) { this.role11 = role11; }

    public String getRole12() { return role12; }

    public void setRole12(String role12) { this.role12 = role12; }

    public String getRole13() { return role13; }

    public void setRole13(String role13) { this.role13 = role13; }

    public String getRole14() { return role14; }

    public void setRole14(String role14) { this.role14 = role14; }

    public String getRole15() { return role15; }

    public void setRole15(String role15) { this.role15 = role15; }

    public String getRole16() { return role16; }

    public void setRole16(String role16) { this.role16 = role16; }

    public String getRole17() { return role17; }

    public void setRole17(String role17) { this.role17 = role17; }

    public String getRole18() { return role18; }

    public void setRole18(String role18) { this.role18 = role18; }

    public String getRole19() { return role19; }

    public void setRole19(String role19) { this.role19 = role19; }

    public String getRole20() { return role20; }

    public void setRole20(String role20) { this.role20 = role20; }
}
