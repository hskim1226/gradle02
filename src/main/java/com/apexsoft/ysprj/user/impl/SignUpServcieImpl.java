package com.apexsoft.ysprj.user.impl;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.user.service.SignUpService;
import com.apexsoft.ysprj.user.service.UsersAccountService;
import com.apexsoft.ysprj.user.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 21.
 * Time: 오후 10:19
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
@Service
public class SignUpServcieImpl implements SignUpService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersAccountService usersAccountService;

    @Override
    public ExecutionContext registerUser(Users users) {
        users.setPswd(passwordEncoder.encode(users.getPswd()));
        usersAccountService.registerUser(users);

        return new ExecutionContext();
    }

    @Override
    public ExecutionContext checkAvailable(Users users) {
        if (usersAccountService.retrieveUser(users.getUserId()) != null ){
            return new ExecutionContext(ExecutionContext.FAIL);
        } else {
            return new ExecutionContext(ExecutionContext.SUCCESS);
        }
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
