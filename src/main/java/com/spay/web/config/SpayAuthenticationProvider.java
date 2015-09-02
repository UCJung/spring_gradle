package com.spay.web.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.spay.member.bo.MemberBO;

public class SpayAuthenticationProvider extends DaoAuthenticationProvider {

	Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("memberBO")	
    @Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}
	
	@Override
	public void setPasswordEncoder(Object passwordEncoder) {
		super.setPasswordEncoder(passwordEncoder);
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		logger.info("authenticate start");
		
        String userName = (String)authentication.getPrincipal();
        String userPassword = (String)authentication.getCredentials();
        MemberBO memberBO = (MemberBO)getUserDetailsService();
        
        logger.info("username : " + userName);
        logger.info("password : " + userPassword);

        UserDetails user = memberBO.getMemberByName(userName);

        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("user is null");
        }

        validation(user, authentication);
        
        logger.info("validation : 6");

        List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>)user.getAuthorities();
        
        logger.info("validation : 7");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, userPassword,
            grantedAuths);
        
        logger.info("validation : 8");
        Authentication auth = token;
        return auth;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return super.supports(authentication);
	}
	
    private void validation(UserDetails user, Authentication authentication) throws AuthenticationException {
        PasswordEncoder passwordEncoder = (PasswordEncoder)getPasswordEncoder();
        passwordEncoder.setPasswordEncryptor(new StrongPasswordEncryptor());

        String encryptedPassword = user.getPassword();
        String parameteredPassword = (String)authentication.getCredentials();

        logger.info("encryptedPassword : " + encryptedPassword);
        logger.info("parameteredPassword : " + parameteredPassword);
        
        if (!passwordEncoder.isPasswordValid(encryptedPassword, parameteredPassword, "a")) {
            throw new BadCredentialsException("incorrect password");
        }

        if (!user.isEnabled()) {
            throw new LockedException("this user is not useable");
        }
    }	
    
    
}
