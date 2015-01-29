package com.apexsoft.framework.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by hanmomhanda on 15. 1. 25.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final String DEFAULT_ERROR_VIEW_NAME = "common/error";

    @ExceptionHandler(SQLException.class)
    public ModelAndView handleSQLException(HttpServletRequest request, SQLException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = new ExecutionContext();
        logger.error("SQLException Occured:: URL=" + request.getRequestURL());
        logger.error("StackTrace::" + ExceptionUtils.getFullStackTrace(e));

        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(e.getMessage());
        String errCode = "ERR9980";
        ec.setErrCode(errCode);
        mv.addObject("ec", ec);
        return mv;
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleNullPointerException(HttpServletRequest request,
                                                   NullPointerException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = new ExecutionContext();
        logger.error("NullPointerException Occured:: URL=" + request.getRequestURL());
        logger.error("StackTrace::" + ExceptionUtils.getFullStackTrace(e));

        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(e.getMessage());
        String errCode = "ERR9990";
        ec.setErrCode(errCode);
        mv.addObject("ec", ec);

        return mv;
    }

    @ExceptionHandler(YSBizException.class)
    public ModelAndView handleBizException(HttpServletRequest request,
                                           YSBizException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = new ExecutionContext();
        logger.error("YSBizException Occured:: URL=" + request.getRequestURL());
        logger.error("StackTrace::" + ExceptionUtils.getFullStackTrace(e));

        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(e.getMessage());
        String errCode = "ERR9800";
        ec.setErrCode(errCode);
        mv.addObject("ec", ec);

        return mv;
    }

    @ExceptionHandler(YSNoRedirectBizException.class)
    public ModelAndView handleNoRedirectBizException(HttpServletRequest request,
                                                     YSNoRedirectBizException e){
        ModelAndView mv = new ModelAndView(e.getTargetView());
        ExecutionContext ec = new ExecutionContext();
        logger.error("YSNoRedirectBizException Occured:: URL=" + request.getRequestURL());
        logger.error("StackTrace::" + ExceptionUtils.getFullStackTrace(e));

        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(e.getMessage());
        String errCode = "ERR9900";
        ec.setErrCode(errCode);
        mv.addObject("ec", ec);

        return mv;
    }
}
