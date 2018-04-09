package com.drunck.service;

import java.util.List;

import com.drunck.base.BaseService;
import com.drunck.domain.SysPrivilage;

public interface SysPrivilageService extends BaseService {

	List<SysPrivilage> queryMenu(String userId);

	List<SysPrivilage> queryTree();
}
