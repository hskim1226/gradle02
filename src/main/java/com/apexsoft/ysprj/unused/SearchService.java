package com.apexsoft.ysprj.unused;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.user.domain.User;

/**
 * Created by Administrator on 2014-08-08.
 */
@Deprecated
public interface SearchService {

    ExecutionContext searchId(com.apexsoft.ysprj.user.domain.User user);
    ExecutionContext resetPassword(User user);
}
