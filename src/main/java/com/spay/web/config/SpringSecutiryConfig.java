package com.spay.web.config;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecutiryConfig extends WebSecurityConfigurerAdapter {
    Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String ADMIN_ROLE = "ADMIN";

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}	
	
    @Bean
    public AuthenticationProvider authenticationProvider() {
        SpayAuthenticationProvider authenticationProvider = new SpayAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	 }	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

        http
        .formLogin()
            .loginPage("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .loginProcessingUrl("/j_spring_security_check")
            .defaultSuccessUrl("/")
            .failureUrl("/login?error=loginFailed")
        .and()
        .logout()
        	.logoutUrl("/j_spring_security_logout")
        	.logoutSuccessUrl("/")
        .and()
        .authorizeRequests()
            .antMatchers("/admin**").hasRole(ADMIN_ROLE)
            .antMatchers("/dba**").authenticated();
        
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        passwordEncoder.setPasswordEncryptor(new StrongPasswordEncryptor());
        return passwordEncoder;
    }	
}
