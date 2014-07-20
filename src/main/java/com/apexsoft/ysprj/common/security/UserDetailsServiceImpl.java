package com.apexsoft.ysprj.common.security;

import com.apexsoft.ysprj.user.service.UsersService;
import com.apexsoft.ysprj.user.service.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 19.
 * Time: 오후 8:38
 * To change this template use File | Settings | File Templates.
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersVO usersVO = usersService.retrieveUser(username);
        if (usersVO == null) {
            throw new UsernameNotFoundException("account name not found");
        }

        usersVO.setAuthorities(usersService.retrieveAuthorities(username));

        return usersVO;
    }
}
