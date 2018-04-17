package com.drunck.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.drunck.base.BaseMapper;
import com.drunck.base.impl.BaseServiceImpl;
import com.drunck.mapper.SysRolePrivilageMapper;
import com.drunck.service.SysRolePrivilageService;

@Service("sysRolePrivilageService")
public class SysRolePrivilageServiceImpl extends BaseServiceImpl implements SysRolePrivilageService {
	@Resource
	private SysRolePrivilageMapper sysRolePrivilageMapper;
	
	@Override
	public BaseMapper<?> getMapper() {
		return sysRolePrivilageMapper;
	}
}
