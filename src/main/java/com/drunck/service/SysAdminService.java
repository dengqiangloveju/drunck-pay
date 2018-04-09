package com.drunck.service;

import com.drunck.base.BaseService;
import com.drunck.domain.SysAdmin;

public interface SysAdminService extends BaseService {

	SysAdmin queryByLoginName(String username);
	
	void save(SysAdmin sysAdmin, String roleId);

	void update(SysAdmin sysAdmin, String roleId);

	void deleteById(String id);
}
