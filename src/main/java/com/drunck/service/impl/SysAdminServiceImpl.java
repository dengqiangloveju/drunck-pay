package com.drunck.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.drunck.base.BaseMapper;
import com.drunck.base.impl.BaseServiceImpl;
import com.drunck.mapper.SysAdminMapper;
import com.drunck.service.SysAdminService;

@Service("sysAdminService")
public class SysAdminServiceImpl extends BaseServiceImpl implements SysAdminService {
	@Resource
	private SysAdminMapper sysAdminMapper;
	
	@Override
	public BaseMapper<?> getMapper() {
		return sysAdminMapper;
	}
}
