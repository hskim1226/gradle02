package com.apexsoft.ysprj.user.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface UsersService {
	//void registerUser(UsersVO usersVO);

	UsersVO retrieveUser(String userName);

    List<SimpleGrantedAuthority> retrieveAuthorities(String userName);

   // List<UsersVO> retrieveUserList() throws Exception;

   // Map<String, Object> getUserPaginatedList(ComDefaultVO searchVO);

    //void modifyUsersGrade(String[] usernames, String[] grades);

    //void modifyUsers(UsersVO usersVO);
}
