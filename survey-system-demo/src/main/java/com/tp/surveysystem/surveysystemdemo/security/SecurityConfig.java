package com.tp.surveysystem.surveysystemdemo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.debug("Authentication configuration happened");
		
		PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
        auth.inMemoryAuthentication()
        	.passwordEncoder(passwordEncoder)
        	.withUser("user").password("secret1").roles("USER")
        	.and()
        	.withUser("admin").password("secret1").roles("ADMIN");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
        	.and().authorizeRequests()
        	.antMatchers("/survey/**").hasRole("USER")
        	.antMatchers("/users/**").hasRole("USER")
        	.antMatchers("/**").hasRole("ADMIN")
//        	.antMatchers("/").permitAll()
            .and().csrf().disable()
            .headers().frameOptions().disable();
    }
}
