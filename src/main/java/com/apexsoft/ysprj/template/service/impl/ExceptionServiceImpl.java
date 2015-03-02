package com.apexsoft.ysprj.template.service.impl;

import com.apexsoft.framework.exception.BusinessException;
import com.apexsoft.ysprj.template.service.ExceptionService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 9. 4.
 * Time: 오후 10:27
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ExceptionServiceImpl implements ExceptionService {
    @Override
    public void makeException() {
        if ( true ) throw new BusinessException("Exception Occurred");
    }
}
