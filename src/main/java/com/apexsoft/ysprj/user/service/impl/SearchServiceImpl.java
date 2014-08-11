package com.apexsoft.ysprj.user.service.impl;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.user.service.SearchService;
import com.apexsoft.ysprj.user.service.UsersService;
import com.apexsoft.ysprj.user.service.UsersVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2014-08-08.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private UsersService usersService;

    @Override
    public ExecutionContext searchId(UsersVO usersVO) {
//        UsersVO usersVO1 = usersService.retrieveUserByName(usersVO.getName());
//        if( usersVO1 != null ) {
//            ExecutionContext context = new ExecutionContext();
//            String userId = usersVO1.getUsername();
//            userId = userId.substring(0, 3) + StringUtils.repeat("*", userId.length() - 3);
//            context.setData( userId );
//            return context;
//        }
        String userId = usersService.retrieveUsername(usersVO);
        if( userId != null ) {
            ExecutionContext context = new ExecutionContext();
            userId = userId.substring(0, 3) + StringUtils.repeat("*", userId.length() - 3);
            context.setData(userId);
            return context;
        }
        return new ExecutionContext(ExecutionContext.FAIL);
    }

    @Override
    public ExecutionContext resetPassword(UsersVO usersVO) {
        return new ExecutionContext();
    }
}
