package com.spay.member.model;

import java.io.Serializable;
import java.util.Collection;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Alias("member")
public class Member implements UserDetails, Serializable {

	private static final long serialVersionUID = 6505725258389129194L;
	private String username;
	private String password;
	
	public Member() { }
	public Member(String name, String password) { 
		this.setUsername(name);
		this.setPassword(password);
	}
	

	
	public void setPassword(String password) {
		this.password = password;
	}	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}