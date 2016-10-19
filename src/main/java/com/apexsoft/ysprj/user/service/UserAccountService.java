package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.user.domain.User;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface UserAccountService {

    ExecutionContext registerUserAndAuthority(User user);

    User retrieveUser(String userName);

    Integer changePassword(User user);

    List<SimpleGrantedAuthority> retrieveAuthorities(String userName);

    ExecutionContext modifyUser( User user);

    ExecutionContext retrieveUserId(User user);

    ExecutionContext retrievePwdLink(User user);

    ExecutionContext isUserIdAvailable(User user);

    ExecutionContext isEmailAvailable(User user);

    ExecutionContext checkPwd(User user);

    List<User> retrieveEmails(String email);
}
