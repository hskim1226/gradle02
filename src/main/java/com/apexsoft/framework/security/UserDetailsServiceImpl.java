package com.apexsoft.framework.security;

import com.apexsoft.ysprj.user.domain.Users;
import com.apexsoft.ysprj.user.service.UsersAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 19.
 * Time: 오후 8:38
 * To change this template use File | Settings | File Templates.
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersAccountService usersAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersAccountService.retrieveUser(username);
        if (users == null) {
            throw new UsernameNotFoundException("account name not found");
        }

        UserSessionVO userSessionVO = new UserSessionVO();
        userSessionVO.setUsername( users.getUserId() );
        userSessionVO.setName( users.getName() );
        userSessionVO.setPassword( users.getPswd() );
        userSessionVO.setEnabled( users.isEnabled() );
        userSessionVO.setEmail( users.getMailAddr() );
        userSessionVO.setAuthorities(usersAccountService.retrieveAuthorities(username));

        return userSessionVO;
    }

}
