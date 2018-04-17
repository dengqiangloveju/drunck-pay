package com.drunck.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.drunck.base.BaseMapper;
import com.drunck.base.impl.BaseServiceImpl;
import com.drunck.domain.SysRole;
import com.drunck.domain.SysRolePrivilage;
import com.drunck.mapper.SysRoleMapper;
import com.drunck.mapper.SysRolePrivilageMapper;
import com.drunck.service.SysRoleService;
import com.drunck.utils.CommonUtil;

@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl implements SysRoleService {
	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource
	private SysRolePrivilageMapper sysRolePrivilageMapper;
	
	@Override
	public BaseMapper<?> getMapper() {
		return sysRoleMapper;
	}

	@Override
	public void save(SysRole sysRole, String[] pids) {
		sysRole.setId(CommonUtil.gitUUID());
		sysRoleMapper.insertSelective(sysRole);
		if(pids!=null && pids!=null) {
			for(int i=0; i<pids.length; i++) {
				SysRolePrivilage sysRolePrivilage = new SysRolePrivilage(sysRole.getId(), pids[i]);
				sysRolePrivilageMapper.insertSelective(sysRolePrivilage);
			}
		}
	}

	@Override
	public void update(SysRole sysRole, String[] pids) {
		sysRoleMapper.updateByPrimaryKeySelective(sysRole);
		SysRolePrivilage params = new SysRolePrivilage(sysRole.getId());
		sysRolePrivilageMapper.delete(params);
		if(pids!=null && pids!=null) {
			for(int i=0; i<pids.length; i++) {
				SysRolePrivilage sysRolePrivilage = new SysRolePrivilage(sysRole.getId(), pids[i]);
				sysRolePrivilageMapper.insertSelective(sysRolePrivilage);
			}
		}
	}

	@Override
	public void deleteById(String id) {
		sysRoleMapper.deleteByPrimaryKey(id);
		SysRolePrivilage params = new SysRolePrivilage(id);
		sysRolePrivilageMapper.delete(params);
	}
}
