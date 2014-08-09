package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.common.vo.ExecutionContext;

/**
 * Created by Administrator on 2014-08-08.
 */
public interface SearchService {

    ExecutionContext searchId(UsersVO usersVO);
    ExecutionContext searchPassword(UsersVO usersVO);
}
