package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.user.domain.Users;

/**
 * Created by Administrator on 2014-08-08.
 */
public interface SearchService {

    ExecutionContext searchId(Users users);
    ExecutionContext resetPassword(Users users);
}
