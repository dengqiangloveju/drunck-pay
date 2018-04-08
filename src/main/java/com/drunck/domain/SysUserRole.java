package com.drunck.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class SysUserRole implements Serializable{
	private static final long serialVersionUID = 8447989359667754967L;
	
	@Id
	@GeneratedValue(generator = "UUID")
    private String id;

    private String userId;

    private String roleId;
    
    public SysUserRole() {}

    public SysUserRole(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}