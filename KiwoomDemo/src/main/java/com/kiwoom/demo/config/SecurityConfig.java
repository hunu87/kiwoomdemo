package com.kiwoom.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.kiwoom.demo.account.controller.AccountDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/css/**", "/fonts/**", "/js/**", "/less/**", "/scss/**", "/vendor/**", "/images/**", "/img/**", "/webjars/**"); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/test").hasRole("USER")
				.anyRequest().authenticated();
		http
			.formLogin()
				.loginPage("/login").permitAll();
		http	
			.httpBasic();
		
		/* logout setting */
		http
			.logout()
	        // .logoutUrl("")
	        // .logoutSuccessUrl("")
	        // .logoutSuccessHandler(customLogoutSuccessHandler)
	        .invalidateHttpSession(true);
	        // .deleteCookies(JSESSIONID)
		
		/* access denied handler */
		http.exceptionHandling()
			.accessDeniedHandler(new AccountDeniedHandler());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
