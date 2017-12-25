package com.drunck.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.drunck.domain.SysAdmin;
import com.drunck.domain.SysRole;
import com.drunck.mapper.SysAdminMapper;
import com.drunck.mapper.SysRoleMapper;

@Service("customUserService")
public class CustomUserService implements UserDetailsService {
	@Resource
	private SysAdminMapper sysAdminMapper;
	@Resource
	private SysRoleMapper sysRoleMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysAdmin param = new SysAdmin();
		param.setUserName(username);
		SysAdmin sysAdmin = sysAdminMapper.selectOne(param);
		if (sysAdmin == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		// 用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
		List<SysRole> roles = sysRoleMapper.queryRolesByUserId(sysAdmin.getId());
		for (SysRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return new User(sysAdmin.getUserName(), sysAdmin.getPassword(), authorities);
	}

}
