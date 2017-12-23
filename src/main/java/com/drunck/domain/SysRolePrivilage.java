package com.drunck.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;

public class SysRolePrivilage implements Serializable{
	private static final long serialVersionUID = -6503169727581663533L;

	@GeneratedValue(generator = "UUID")
    private String id;

    private String roleId;

    private String privilageId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPrivilageId() {
        return privilageId;
    }

    public void setPrivilageId(String privilageId) {
        this.privilageId = privilageId == null ? null : privilageId.trim();
    }
}