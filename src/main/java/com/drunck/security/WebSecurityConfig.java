package com.drunck.security;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
	private MyUserDetailService myUserDetailService;
    @Resource
    private MySecurityFilter mySecurityFilter;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }
	
	/***设置不拦截规则*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/druid/**");
        web.ignoring().antMatchers("/ui/**", "/page/**");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		//解决不能加载iframe问题
		http.headers().frameOptions().disable();
        http.authorizeRequests()
		        //.antMatchers("/ui/**","/page/**")
				//.permitAll()
		        .antMatchers("/sys/getCode")
		        .permitAll()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .csrf()  
                .disable()
                .formLogin()
                .loginPage("/sys/loginfrom")//登陆页面
                .loginProcessingUrl("/login")//登陆处理路径
                .usernameParameter("username")//登陆用户名参数  
                .passwordParameter("password")//登陆密码参数 
                .failureUrl("/sys/loginfrom")//登陆失败路径
                .permitAll() //登录页面用户任意访问
                .successHandler(loginSuccessHandler)
                //.defaultSuccessUrl("/sys/login", true)//登陆成功路径
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/sys/loginfrom")
                .invalidateHttpSession(true)
                .permitAll() //注销行为任意访问
        		.and()
        		.sessionManagement()
        		.maximumSessions(1)
        		.expiredUrl("/sys/loginfrom")
        		.and()
        		.and()
        		.exceptionHandling()
        		.accessDeniedPage("/page/403.html");
        http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class);
    }
}
