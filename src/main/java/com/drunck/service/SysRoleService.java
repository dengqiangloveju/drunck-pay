package com.drunck.service;

import com.drunck.base.BaseService;
import com.drunck.domain.SysRole;

public interface SysRoleService extends BaseService {

	void save(SysRole sysRole, String[] pids);

	void update(SysRole sysRole, String[] pids);
	
	void deleteById(String id);
}
