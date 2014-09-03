package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.user.domain.Users;
import com.apexsoft.ysprj.user.web.form.UserSearchForm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface UsersAccountService {

    void registerUserAndAuthority(Users users);

    ExecutionContext registerUser(Users users);

    Users retrieveUser(String userName);

    Integer resetPassword(Users users);

    Integer changePassword(Users users);

    List<SimpleGrantedAuthority> retrieveAuthorities(String userName);

    // List<Users> retrieveUserList() throws Exception;

    PageInfo<Users> getUserPaginatedList(UserSearchForm userSearchForm);

    //void modifyUsersGrade(String[] usernames, String[] grades);

    Integer modifyUsers(Users users);

    ExecutionContext retrieveUserIds(Users users, int showLength);

    ExecutionContext isUserIdAvailable(Users users);
}
