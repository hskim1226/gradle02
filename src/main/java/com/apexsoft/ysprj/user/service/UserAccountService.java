package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.user.domain.User;
import com.apexsoft.ysprj.user.web.form.UserSearchForm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface UserAccountService {

    ExecutionContext registerUserAndAuthority(User user);

    ExecutionContext registerUser(User user);

    User retrieveUser(String userName);

//    Integer resetPassword(User user);

    Integer changePassword(User user);

    List<SimpleGrantedAuthority> retrieveAuthorities(String userName);

    // List<User> retrieveUserList() throws Exception;

    PageInfo<User> getUserPaginatedList(UserSearchForm userSearchForm);

    //void modifyUsersGrade(String[] usernames, String[] grades);

    ExecutionContext modifyUser( User user);

    ExecutionContext retrieveUserId(User user);

    ExecutionContext retrievePwdLink(User user);

    ExecutionContext isUserIdAvailable(User user);

    ExecutionContext checkPwd(User user);
}
