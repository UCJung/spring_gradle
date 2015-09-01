package com.spay.web.config;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.spay.member.bo.MemberBO;

@Configuration
@EnableWebSecurity
public class SpringSecutiryConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MemberBO memberBO;
	
	private static final String ADMIN_ROLE = "ADMIN";

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberBO)
			.passwordEncoder(passwordEncoder());
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
