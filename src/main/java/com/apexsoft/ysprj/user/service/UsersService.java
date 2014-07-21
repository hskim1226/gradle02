package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface UsersService {

	UsersVO retrieveUser(String userName);

    List<SimpleGrantedAuthority> retrieveAuthorities(String userName);

    void registerUser(UsersVO usersVO);

    // List<UsersVO> retrieveUserList() throws Exception;

   // Map<String, Object> getUserPaginatedList(ComDefaultVO searchVO);

    //void modifyUsersGrade(String[] usernames, String[] grades);

    //void modifyUsers(UsersVO usersVO);
}
