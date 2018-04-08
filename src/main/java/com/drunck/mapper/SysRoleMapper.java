package com.drunck.mapper;

import org.apache.ibatis.annotations.Param;

import com.drunck.base.BaseMapper;
import com.drunck.domain.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole>{

	SysRole queryRolesByUserId(@Param("userId") String userId);
}