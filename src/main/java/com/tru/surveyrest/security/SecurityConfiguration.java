package com.tru.surveyrest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#setAuthenticationConfiguration(org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration)
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("rakesh").password("pass").roles("USER").and()
		.withUser("admin").password("admin").roles("USER","ADMIN");
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.httpBasic().and().authorizeRequests()
		.antMatchers("/surveys/**").hasRole("USER")
		.antMatchers("/users/**").hasRole("USER")
		.antMatchers("/**").hasRole("ADMIN")
		.and().csrf().disable().headers().frameOptions().disable();
	}
	
	

}
