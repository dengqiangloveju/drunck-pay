package com.drunck.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drunck.base.BaseService;
import com.drunck.domain.SysPrivilage;

public interface SysPrivilageService extends BaseService {

	List<SysPrivilage> queryMenu(@Param("userId") String userId);
}
