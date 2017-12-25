package com.drunck.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.drunck.domain.SysAdmin;
import com.drunck.domain.SysPrivilage;
import com.drunck.mapper.SysAdminMapper;
import com.drunck.mapper.SysPrivilageMapper;
import com.drunck.mapper.SysRoleMapper;

@Service("userDetailService")
public class MyUserDetailService implements UserDetailsService {
	@Resource
	private SysAdminMapper sysAdminMapper;
	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource
	private SysPrivilageMapper sysPrivilageMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysAdmin param = new SysAdmin();
		param.setUserName(username);
		SysAdmin sysAdmin = sysAdminMapper.selectOne(param);
		if (sysAdmin == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}

		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(sysAdmin);
		// 封装成spring security的user
		User userdetail = new User(
				sysAdmin.getUserName(), 
				sysAdmin.getPassword(), 
				sysAdmin.getEnable(), // 账号状态 0 表示停用 1表示启用
				true, 
				true, 
				true, 
				grantedAuths // 用户的权限
		);
		return userdetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(SysAdmin sysAdmin) {
		List<SysPrivilage> privileges = sysPrivilageMapper.queryPrivilagesByUserId(sysAdmin.getId());
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (SysPrivilage privilege : privileges) {
			// 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
			// 关联代码：applicationContext-security.xml
			// 关联代码：com.huaxin.security.MySecurityMetadataSource#loadResourceDefine
			authSet.add(new SimpleGrantedAuthority("ROLE_" + privilege.getResKey()));
		}
		return authSet;
	}

}
