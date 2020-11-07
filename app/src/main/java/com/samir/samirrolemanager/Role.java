package com.samir.samirrolemanager;

import java.util.List;

public class Role {
    private String roleHeading;
    private List<String> rolePerms;

    public List<String> getRolePerms() {
        return rolePerms;
    }

    public void setRolePerms(List<String> rolePerms) {
        this.rolePerms = rolePerms;
    }

    public Role(String roleHeading, List<String> rolePerms) {
        this.roleHeading = roleHeading;
        this.rolePerms = rolePerms;
    }


    public String getRoleHeading() {
        return roleHeading;
    }

    public void setRoleHeading(String roleHeading) {
        this.roleHeading = roleHeading;
    }
}
