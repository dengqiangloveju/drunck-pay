package com.drunck.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.drunck.base.BaseMapper;
import com.drunck.base.impl.BaseServiceImpl;
import com.drunck.mapper.SysPrivilageMapper;
import com.drunck.service.SysPrivilageService;

@Service("sysPrivilageService")
public class SysPrivilageServiceImpl extends BaseServiceImpl implements SysPrivilageService {
	@Resource
	private SysPrivilageMapper sysPrivilageMapper;
	
	@Override
	public BaseMapper<?> getMapper() {
		return sysPrivilageMapper;
	}
}
