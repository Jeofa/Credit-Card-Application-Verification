package com.bank.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bank.app.security.CustomAuthenticationFilter;

import com.bank.app.security.UsernamePasswordAuthenticationProvider;



@Configuration
@EnableWebSecurity
public class AdminConfiguration {
	
	@Autowired
	private DataSource datasource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new AuthenticationManager() {
			
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return authentication;
			}
		};
		
	}
	
	@Bean
	public JdbcUserDetailsManager userDetailsManager() {
		return new JdbcUserDetailsManager(datasource);
	}
	
	public UsernamePasswordAuthenticationProvider provider() {
		return new UsernamePasswordAuthenticationProvider();
	}
	
	public CustomAuthenticationFilter filter() {
		return new CustomAuthenticationFilter();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.addFilterAt(filter(),UsernamePasswordAuthenticationFilter.class);
//				httpSecurity.authorizeRequests()
//				.and()
//				.formLogin()
//				.loginPage("/admin/login")
//				.loginProcessingUrl("/logindata")
//				.successForwardUrl("/admin/login_success_handler")
//				.and()
//				.logout()
//				.logoutUrl("/logout");
				
		httpSecurity.authorizeRequests()
		.mvcMatchers("/office/**").permitAll()
		.mvcMatchers("/users/**").permitAll()
//		.mvcMatchers("/office/**").authenticated()
		.mvcMatchers("/office/clients").hasAnyAuthority("DevOp","Admin","User")
		.mvcMatchers("/office/clientsreview","/office/updateclient").hasAnyAuthority("DevOp","Admin")
		.mvcMatchers("/office/deleteclient").hasAuthority("Admin")
		
		.mvcMatchers("/office/leadsUpdate","/office/leadsreview","/office/deleteleads").hasAuthority("DevOp")
		
		.mvcMatchers("/office/sms","").hasAnyAuthority("Admin","DevOp","User")
		.mvcMatchers("/office/updatesms","/office/smspost","/office/deletesms","/office/sendbulk").hasAuthority("Admin")
		.mvcMatchers("/office/manage/sms").hasAnyAuthority("Admin","DevOp")
		
		
		.mvcMatchers("/office/users/**").hasAnyAuthority("DevOp","Admin")
		.mvcMatchers("/admin/login")
		.permitAll();
		return httpSecurity.build();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider());
	}

	
	
}
