package com.drunck.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.drunck.base.BaseMapper;
import com.drunck.base.impl.BaseServiceImpl;
import com.drunck.domain.SysAdmin;
import com.drunck.domain.SysUserRole;
import com.drunck.mapper.SysAdminMapper;
import com.drunck.mapper.SysUserRoleMapper;
import com.drunck.service.SysAdminService;
import com.drunck.utils.CommonUtil;

@Service("sysAdminService")
public class SysAdminServiceImpl extends BaseServiceImpl implements SysAdminService {
	@Resource
	private SysAdminMapper sysAdminMapper;
	@Resource
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Override
	public BaseMapper<?> getMapper() {
		return sysAdminMapper;
	}

	@Override
	public SysAdmin queryByLoginName(String username) {
		SysAdmin param = new SysAdmin();
		param.setUserName(username);
		SysAdmin sysAdmin = sysAdminMapper.selectOne(param);
		return sysAdmin;
	}

	@Override
	public void save(SysAdmin sysAdmin, String roleId) {
		Date date = new Date();
		sysAdmin.setId(CommonUtil.gitUUID());
		sysAdmin.setCreateTime(date);
		sysAdmin.setUpdateTime(date);
		sysAdmin.setIsDel(false);
		
		sysAdminMapper.insertSelective(sysAdmin);
		
		SysUserRole sysUserRole = new SysUserRole(sysAdmin.getId(), roleId);
		
		sysUserRoleMapper.insertSelective(sysUserRole);
	}
}
