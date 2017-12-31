package com.drunck.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.drunck.domain.SysAdmin;
import com.drunck.service.SysAdminService;

@Component("loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Resource
	private SysAdminService sysAdminService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User userDetails = (User) authentication.getPrincipal();
		SysAdmin sysAdmin = sysAdminService.queryByLoginName(userDetails.getUsername());
		request.getSession().setAttribute("currentUser", sysAdmin);
        this.redirectStrategy.sendRedirect(request, response, "/sys/login"); 
		
	}
	
}
