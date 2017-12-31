package com.drunck.service;

import com.drunck.domain.SysAdmin;

public interface CacheService {
	public SysAdmin save(SysAdmin sysAdmin);
	public SysAdmin update(SysAdmin sysAdmin);
	public SysAdmin queryById(String id);
	public void deleteById(String id);
}
