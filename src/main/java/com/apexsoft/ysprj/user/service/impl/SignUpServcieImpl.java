package com.apexsoft.ysprj.user.service.impl;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.user.service.SignUpService;
import com.apexsoft.ysprj.user.service.UsersService;
import com.apexsoft.ysprj.user.service.UsersVO;
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
@Service
public class SignUpServcieImpl implements SignUpService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersService usersService;

    @Override
    public ExecutionContext registerUser(UsersVO usersVO) {
        usersVO.setPassword(passwordEncoder.encode(usersVO.getPassword()));
        usersService.registerUser(usersVO);

        return new ExecutionContext();
    }

    @Override
    public ExecutionContext checkAvailable(UsersVO usersVO) {
        if (usersService.retrieveUser(usersVO.getUsername()) != null ){
            return new ExecutionContext(ExecutionContext.FAIL);
        } else {
            return new ExecutionContext(ExecutionContext.SUCCESS);
        }
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
