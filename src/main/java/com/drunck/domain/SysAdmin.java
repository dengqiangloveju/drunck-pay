package com.drunck.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sys_admin")
public class SysAdmin implements Serializable{
	private static final long serialVersionUID = -2338221672801757312L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(name="Any",sequenceName="seq_userid")
	@GeneratedValue(generator = "UUID")
	private String id;

	private String userName;

	private String password;
	
	private Boolean enable;

	private Date createTime;

	private Date updateTime;

	private Boolean isDel;

	public SysAdmin() {
	}
	
	public SysAdmin(String userName, String password, Boolean enable, Date createTime, Date updateTime, Boolean isDel) {
		this.userName = userName;
		this.password = password;
		this.enable = enable;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isDel = isDel;
	}

	public SysAdmin(String id, String userName, String password, Boolean enable, Date createTime, Date updateTime, Boolean isDel) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.enable = enable;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isDel = isDel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}
}