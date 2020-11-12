package com.samir.samirrolemanager;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cus_role_permission")
public class CustomRolePermission {

    @PrimaryKey
    @NonNull
    private int cusPermId;

    @ColumnInfo
    private String cusRoleName;

    @ColumnInfo
    private String cusRoleInternalName;

    @ColumnInfo
    private String cusRoleDescription;

    @ColumnInfo
    private String cusDefiningApp;

    public CustomRolePermission() {}

    public int getCusPermId() {
        return cusPermId;
    }

    public void setCusPermId(int cusPermId) {
        this.cusPermId = cusPermId;
    }

    public String getCusRoleName() {
        return cusRoleName;
    }

    public void setCusRoleName(String cusRoleName) {
        this.cusRoleName = cusRoleName;
    }

    public String getCusRoleInternalName() {
        return cusRoleInternalName;
    }

    public void setCusRoleInternalName(String cusRoleInternalName) {
        this.cusRoleInternalName = cusRoleInternalName;
    }

    public String getCusRoleDescription() {
        return cusRoleDescription;
    }

    public void setCusRoleDescription(String cusRoleDescription) {
        this.cusRoleDescription = cusRoleDescription;
    }

    public String getCusDefiningApp() {
        return cusDefiningApp;
    }

    public void setCusDefiningApp(String cusDefiningApp) {
        this.cusDefiningApp = cusDefiningApp;
    }
}
