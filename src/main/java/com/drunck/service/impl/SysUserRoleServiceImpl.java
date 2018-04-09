package com.drunck.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.drunck.base.BaseMapper;
import com.drunck.base.impl.BaseServiceImpl;
import com.drunck.mapper.SysUserRoleMapper;
import com.drunck.service.SysUserRoleService;

@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseServiceImpl implements SysUserRoleService {
	@Resource
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public BaseMapper<?> getMapper() {
		return sysUserRoleMapper;
	}
	
}
