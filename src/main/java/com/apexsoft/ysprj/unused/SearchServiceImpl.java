package com.apexsoft.ysprj.unused;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.user.domain.User;
import com.apexsoft.ysprj.user.service.UserAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2014-08-08.
 */
@Deprecated
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public ExecutionContext searchId(User user) {
//        User usersVO1 = userAccountService.retrieveUserByName(user.getName());
//        if( usersVO1 != null ) {
//            ExecutionContext context = new ExecutionContext();
//            String userId = usersVO1.getUsername();
//            userId = userId.substring(0, 3) + StringUtils.repeat("*", userId.length() - 3);
//            context.setData( userId );
//            return context;
//        }
        String userId = null/*userAccountService.retrieveUsername(user)*/;
        if( userId != null ) {
            ExecutionContext context = new ExecutionContext();
            userId = userId.substring(0, 3) + StringUtils.repeat("*", userId.length() - 3);
            context.setData(userId);
            return context;
        }
        return new ExecutionContext(ExecutionContext.FAIL);
    }

    @Override
    public ExecutionContext resetPassword(com.apexsoft.ysprj.user.domain.User user) {
        return new ExecutionContext();
    }
}
