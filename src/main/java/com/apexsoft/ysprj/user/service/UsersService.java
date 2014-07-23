package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.user.web.form.UserSearchForm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface UsersService {

	UsersVO retrieveUser(String userName);

    List<SimpleGrantedAuthority> retrieveAuthorities(String userName);

    void registerUser(UsersVO usersVO);

    // List<UsersVO> retrieveUserList() throws Exception;

    PageInfo<UsersVO> getUserPaginatedList(UserSearchForm userSearchForm);

    //void modifyUsersGrade(String[] usernames, String[] grades);

    //void modifyUsers(UsersVO usersVO);
}
