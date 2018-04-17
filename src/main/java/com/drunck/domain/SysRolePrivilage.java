package com.drunck.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class SysRolePrivilage implements Serializable{
	private static final long serialVersionUID = -6503169727581663533L;
	
	@Id
	@GeneratedValue(generator = "UUID")
    private String id;

    private String roleId;

    private String privilageId;
    
    public SysRolePrivilage() {}
    
    public SysRolePrivilage(String roleId) {
		this.roleId = roleId;
	}
    
    public SysRolePrivilage(String roleId, String privilageId) {
		this.roleId = roleId;
		this.privilageId = privilageId;
	}

    public SysRolePrivilage(String id, String roleId, String privilageId) {
		this.id = id;
		this.roleId = roleId;
		this.privilageId = privilageId;
	}

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