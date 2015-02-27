package com.apexsoft.framework.security;

import com.apexsoft.ysprj.user.domain.User;
import com.apexsoft.ysprj.user.service.UserAccountService;
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
    private UserAccountService userAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userAccountService.retrieveUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("account name not found");
        }

        UserSessionVO userSessionVO = new UserSessionVO();
        userSessionVO.setUsername( user.getUserId() );
        userSessionVO.setName( user.getName() );
        userSessionVO.setPassword( user.getPswd() );
        userSessionVO.setEnabled( user.isEnabled() );
        userSessionVO.setEmail( user.getMailAddr() );
        userSessionVO.setAuthorities(userAccountService.retrieveAuthorities(username));

        return userSessionVO;
    }

}
