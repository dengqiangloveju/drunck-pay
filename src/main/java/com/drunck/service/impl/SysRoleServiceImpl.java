package com.drunck.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.drunck.base.BaseMapper;
import com.drunck.base.impl.BaseServiceImpl;
import com.drunck.mapper.SysRoleMapper;
import com.drunck.service.SysRoleService;

@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl implements SysRoleService {
	@Resource
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public BaseMapper<?> getMapper() {
		return sysRoleMapper;
	}
}
