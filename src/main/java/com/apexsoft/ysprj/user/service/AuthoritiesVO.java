package com.apexsoft.ysprj.user.service;


public class AuthoritiesVO {
	
	private String username;
	
	private String authority;

	public String getUsername() {
		return username;
	}

	public AuthoritiesVO setUsername(String username) {
		this.username = username;
        return this;
	}

	public String getAuthority() {
		return authority;
	}

	public AuthoritiesVO setAuthority(String authority) {
		this.authority = authority;
        return this;
	}
	
	

}
