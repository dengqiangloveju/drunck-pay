package com.drunck.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drunck.base.BaseMapper;
import com.drunck.domain.SysPrivilage;

public interface SysPrivilageMapper extends BaseMapper<SysPrivilage>{
	List<SysPrivilage> queryPrivilagesByUserId(@Param("userId") String userId);

	List<SysPrivilage> queryMenuByRoleId(@Param("roleId") String roleId);

	List<SysPrivilage> queryTree();
}