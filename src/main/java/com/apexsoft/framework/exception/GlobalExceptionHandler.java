package com.apexsoft.framework.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
    private final String RUNTIME_ERROR = "런타임 오류";

    @Autowired
    private MessageResolver messageResolver;

    @ExceptionHandler(SQLException.class)
    public ModelAndView handleSQLException(HttpServletRequest request, SQLException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = new ExecutionContext();
        logger.error("SQLException Occured:: URL=" + request.getRequestURL());
        logger.error("Message:: " + e.getMessage());
        logger.error("Cause:: " + e.getCause());
        logger.error("StackTrace::" + ExceptionUtils.getFullStackTrace(e));

        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(RUNTIME_ERROR);
        ec.setErrCode("ERR9980");
        mv.addObject("ec", ec);
        return mv;
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleNullPointerException(HttpServletRequest request,
                                                   NullPointerException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = new ExecutionContext();
        logger.error("NullPointerException Occured:: URL=" + request.getRequestURL());
        logger.error("Message:: " + e.getMessage());
        logger.error("Cause:: " + e.getCause());
        logger.error("FilteredStackTrace::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(RUNTIME_ERROR);
        ec.setErrCode("ERR9990");
        mv.addObject("ec", ec);

        return mv;
    }

    @ExceptionHandler(YSBizException.class)
    public ModelAndView handleBizException(HttpServletRequest request,
                                           YSBizException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = e.getExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        logger.error("YSBizException Occured :: URL=" + request.getRequestURL());
        logger.error("Message:: " + e.getMessage());
        logger.error("Cause:: " + e.getCause());
        logger.error("ErrorInfo :: " + eInfo.toString());
        logger.error("ErrorType :: " + e.toString());
        logger.error("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        mv.addObject("ec", e.getExecutionContext());

        return mv;
    }

    @ExceptionHandler(YSNoRedirectBizException.class)
    public ModelAndView handleNoRedirectBizException(HttpServletRequest request,
                                                     YSNoRedirectBizException e){
        ModelAndView mv = new ModelAndView(e.getTargetView());
        ExecutionContext ec = e.getExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        logger.error("YSNoRedirectBizException Occured :: URL=" + request.getRequestURL());
        logger.error("Message:: " + e.getMessage());
        logger.error("Cause:: " + e.getCause());
        logger.error("ErrorInfo :: " + eInfo.toString());
        logger.error("ErrorType :: " + e.toString());
        logger.error("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        mv.addObject("ec", e.getExecutionContext());

        return mv;
    }

    @ExceptionHandler(MyBatisSystemException.class)
    public ModelAndView handleMyBatisSystemException(HttpServletRequest request,
                                                     MyBatisSystemException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = new ExecutionContext();
        logger.error("MyBatisSystemException Occured:: URL=" + request.getRequestURL());
        logger.error("Message:: " + e.getMessage());
        logger.error("Cause:: " + e.getCause());
        logger.error("FilteredStackTrace::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(RUNTIME_ERROR);
        ec.setErrCode("ERR9970");
        mv.addObject("ec", ec);

        return mv;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(HttpServletRequest request,
                                                   IllegalArgumentException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = new ExecutionContext();
        logger.error("IllegalArgumentException Occured:: URL=" + request.getRequestURL());
        logger.error("Message:: " + e.getMessage());
        logger.error("Cause:: " + e.getCause());
        logger.error("FilteredStackTrace::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(RUNTIME_ERROR);
        ec.setErrCode("ERR9960");
        mv.addObject("ec", ec);

        return mv;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleHttpRequestMethodNotSupportedException(HttpServletRequest request,
                                                                     Exception e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = new ExecutionContext();
        logger.error("HttpRequestMethodNotSupportedException Occured:: URL=" + request.getRequestURL());
        logger.error("Message:: " + e.getMessage());
        logger.error("Cause:: " + e.getCause());
        logger.error("FilteredStackTrace::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(messageResolver.getMessage("U901"));
        ec.setErrCode("ERR9950");
        mv.addObject("ec", ec);

        return mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request,
                                        Exception e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = new ExecutionContext();
        logger.error("Exception Occured:: URL=" + request.getRequestURL());
        logger.error("Message:: " + e.getMessage());
        logger.error("Cause:: " + e.getCause());
        logger.error("FilteredStackTrace::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(RUNTIME_ERROR);
        ec.setErrCode("ERR9999");
        mv.addObject("ec", ec);

        return mv;
    }
}
