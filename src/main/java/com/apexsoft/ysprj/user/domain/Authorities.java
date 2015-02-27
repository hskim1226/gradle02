package com.apexsoft.ysprj.user.domain;


public class Authorities {
	
	private String username;
	
	private String authority;

	public String getUsername() {
		return username;
	}

	public Authorities setUsername(String username) {
		this.username = username;
        return this;
	}

	public String getAuthority() {
		return authority;
	}

	public Authorities setAuthority(String authority) {
		this.authority = authority;
        return this;
	}
	
	

}
