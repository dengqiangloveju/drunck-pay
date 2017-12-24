package com.drunck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drunck.base.BaseMapper;
import com.drunck.domain.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole>{

	List<SysRole> queryRolesByUserId(@Param("userId") String userId);
}