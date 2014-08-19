package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.user.domain.Users;
import com.apexsoft.ysprj.user.web.form.UserSearchForm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface UsersService {

	Users retrieveUser(String userName);

    Users retrieveUserDetail(String userName);

    String retrieveUsername(Users users);

    Integer resetPassword(Users users);

    Integer changePassword(Users users);

    List<SimpleGrantedAuthority> retrieveAuthorities(String userName);

    void registerUser(Users users);

    // List<Users> retrieveUserList() throws Exception;

    PageInfo<Users> getUserPaginatedList(UserSearchForm userSearchForm);

    //void modifyUsersGrade(String[] usernames, String[] grades);

    Integer modifyUsers(Users users);
}
