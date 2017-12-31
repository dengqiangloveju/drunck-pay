package com.drunck.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.drunck.domain.SysAdmin;
import com.drunck.mapper.SysAdminMapper;
import com.drunck.service.CacheService;

@CacheConfig(cacheNames = "ehCache")
@Repository
public class CacheServiceImpl implements CacheService {
	@Resource
	private SysAdminMapper sysAdminMapper;
	
	@CachePut(key = "'key_'+#sysAdmin.getId()")
	@Override
	public SysAdmin save(SysAdmin sysAdmin) {
		SysAdmin admin = new SysAdmin();
		admin.setId(sysAdmin.getId());
		admin.setUserName(sysAdmin.getUserName());
		return admin;
	}
	
	@CachePut(key = "'key_'+#sysAdmin.getId()")
	@Override
	public SysAdmin update(SysAdmin sysAdmin) {
		SysAdmin admin = new SysAdmin();
		admin.setId(sysAdmin.getId());
		admin.setUserName(sysAdmin.getUserName());
		return admin;
	}
	
	@Cacheable(key = "'key_'+#id")
	@Override
	public SysAdmin queryById(String id) {
		System.out.println("查询功能，缓存找不到，直接读库, id=" + id);
		return sysAdminMapper.selectByPrimaryKey(id);
	}
	 
	@CacheEvict(key = "'key_'+#id")
	@Override
	public void deleteById(String id) {
	}

}
