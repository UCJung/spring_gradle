package com.spay.module.member.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("member")
public class Member implements Serializable {

	protected static final long serialVersionUID = 6505725258389129194L;
	protected long no;
	protected String id;
	protected String password;
	protected String name;
	protected short status;
	
	public Member(){}
	public Member(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.status = 0;
	}
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}

}
