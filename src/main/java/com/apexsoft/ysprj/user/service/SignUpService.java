package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.user.domain.Users;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 21.
 * Time: 오후 10:17
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public interface SignUpService {

    ExecutionContext registerUser(Users users);

    ExecutionContext checkAvailable(Users users);
}
