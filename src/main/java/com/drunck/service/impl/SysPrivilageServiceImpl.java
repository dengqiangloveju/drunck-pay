package com.drunck.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.drunck.base.BaseMapper;
import com.drunck.base.impl.BaseServiceImpl;
import com.drunck.domain.SysPrivilage;
import com.drunck.domain.SysRole;
import com.drunck.mapper.SysPrivilageMapper;
import com.drunck.mapper.SysRoleMapper;
import com.drunck.service.SysPrivilageService;

@Service("sysPrivilageService")
public class SysPrivilageServiceImpl extends BaseServiceImpl implements SysPrivilageService {
	@Resource
	private SysPrivilageMapper sysPrivilageMapper;
	@Resource
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public BaseMapper<?> getMapper() {
		return sysPrivilageMapper;
	}

	@Override
	public List<SysPrivilage> queryMenu(String userId) {
		SysRole sysRole = sysRoleMapper.queryRolesByUserId(userId);
		return sysPrivilageMapper.queryMenuByRoleId(sysRole.getId());
	}

	@Override
	public List<SysPrivilage> queryTree() {
		return sysPrivilageMapper.queryTree();
	}
}
