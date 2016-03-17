package com.apexsoft.framework.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.file.exception.*;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 1. 25.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    private final String DEFAULT_ERROR_VIEW_NAME = "common/error";
    private final String RUNTIME_ERROR = "런타임 오류";

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

    @ExceptionHandler(FileUploadException.class)
    @ResponseBody
    public ExecutionContext handleFileUploadException(HttpServletRequest request,
                                                      FileUploadException e){
        ExecutionContext ec = e.getExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        ec.setMessage(MessageResolver.getMessage(e.getUserMessageCode()));
        logger.error("FileUploadException Occured :: URL=" + request.getRequestURL());
        logger.error("Message:: " + e.getMessage());
        logger.error("ErrorCode:: " + e.getErrorCode());
        logger.error("Cause:: " + e.getCause());
        logger.error("ErrorInfo :: " + (eInfo != null ? eInfo.toString() : ""));
        logger.error("ErrorType :: " + e.toString());
        logger.error("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        return ec;
    }

    @ExceptionHandler(EncryptedPDFException.class)
    @ResponseBody
    public ExecutionContext handleEncryptedPDFException(HttpServletRequest request,
                                                        EncryptedPDFException e){
        ExecutionContext ec = e.getExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        ec.setMessage(MessageResolver.getMessage(e.getUserMessageCode()));
        logger.debug("EncryptedPDFException Occured :: URL=" + request.getRequestURL());
        logger.debug("Message:: " + StringUtil.getEmptyIfNull(ec.getErrCode()));
        logger.debug("ErrorCode:: " + StringUtil.getEmptyIfNull(e.getErrorCode()));
        logger.debug("Cause:: " + e.getCause());
        logger.debug("ErrorInfo :: " + (eInfo != null ? eInfo.toString() : ""));
        logger.debug("ErrorType :: " + e.toString());
        logger.debug("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        return ec;
    }

    @ExceptionHandler(PasswordedPDFException.class)
    @ResponseBody
    public ExecutionContext handlePasswordedPDFException(HttpServletRequest request,
                                                        PasswordedPDFException e){
        ExecutionContext ec = e.getExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        ec.setMessage(MessageResolver.getMessage(e.getUserMessageCode()));
        logger.debug("PasswordedPDFException Occured :: URL=" + request.getRequestURL());
        logger.debug("Message:: " + StringUtil.getEmptyIfNull(ec.getErrCode()));
        logger.debug("ErrorCode:: " + StringUtil.getEmptyIfNull(e.getErrorCode()));
        logger.debug("Cause:: " + e.getCause());
        logger.debug("ErrorInfo :: " + (eInfo != null ? eInfo.toString() : ""));
        logger.debug("ErrorType :: " + e.toString());
        logger.debug("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        return ec;
    }

    @ExceptionHandler(PDFNotNumberedException.class)
    @ResponseBody
    public ExecutionContext handlePDFNotFoundPDFException(HttpServletRequest request,
                                                          PDFNotNumberedException e){
        ExecutionContext ec = e.getExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        logger.error("EncryptedPDFException Occured :: URL=" + request.getRequestURL());
        logger.error("Message:: " + StringUtil.getEmptyIfNull(ec.getMessage()));
        logger.error("ErrorCode:: " + StringUtil.getEmptyIfNull(e.getErrorCode()));
        logger.error("Cause:: " + e.getCause());
        logger.error("ErrorInfo :: " + (eInfo != null ? eInfo.toString() : ""));
        logger.error("ErrorType :: " + e.toString());
        logger.error("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        return ec;
    }

    @ExceptionHandler(FileNoticeException.class)
    @ResponseBody
    public ExecutionContext handleFileNoticeException(HttpServletRequest request,
                                                      FileNoticeException e){
        ExecutionContext ec = e.getExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        ec.setMessage(MessageResolver.getMessage(e.getUserMessageCode()));
        logger.debug("FileNoticeException Occured :: URL=" + request.getRequestURL());
        logger.debug("Message:: " + StringUtil.getEmptyIfNull(ec.getErrCode()));
        logger.debug("ErrorCode:: " + StringUtil.getEmptyIfNull(e.getErrorCode()));
        logger.debug("Cause:: " + e.getCause());
        logger.debug("ErrorInfo :: " + ( eInfo != null ? eInfo.toString() : ""));
        logger.debug("ErrorType :: " + e.toString());
        logger.debug("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        return ec;
    }

    @ExceptionHandler(PDFMergeException.class)
    @ResponseBody
    public ExecutionContext handlePDFMergeException(HttpServletRequest request, HttpServletResponse response,
                                                    PDFMergeException e){
        ExecutionContext ec = e.getExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        ec.setMessage(MessageResolver.getMessage(e.getUserMessageCode(), new Object[]{eInfo.getInfo().get("fileName")}));
        logger.debug("PDFMergeException Occured :: URL=" + request.getRequestURL());
        logger.debug("Message:: " + StringUtil.getEmptyIfNull(ec.getErrCode()));
        logger.debug("ErrorCode:: " + StringUtil.getEmptyIfNull(e.getErrorCode()));
        logger.debug("Cause:: " + e.getCause());
        logger.debug("ErrorInfo :: " + (eInfo != null ? eInfo.toString() : ""));
        logger.debug("ErrorType :: " + e.toString());
        logger.debug("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        return ec;
    }

    @ExceptionHandler(WrongFileFormatException.class)
    @ResponseBody
    public ExecutionContext handleWrongFileFormatException(HttpServletRequest request, HttpServletResponse response,
                                                    WrongFileFormatException e){
        ExecutionContext ec = e.getExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        ec.setMessage(MessageResolver.getMessage(e.getUserMessageCode(), new Object[]{eInfo.getInfo().get("originalFileName")}));
        logger.debug("WrongFileFormatException Occured :: URL=" + request.getRequestURL());
        logger.debug("Message:: " + StringUtil.getEmptyIfNull(ec.getErrCode()));
        logger.debug("ErrorCode:: " + StringUtil.getEmptyIfNull(e.getErrorCode()));
        logger.debug("Cause:: " + e.getCause());
        logger.debug("ErrorInfo :: " + (eInfo != null ? eInfo.toString() : ""));
        logger.debug("ErrorType :: " + e.toString());
        logger.debug("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        return ec;
    }

    @ExceptionHandler(NotFoundInS3Exception.class)
    @ResponseBody
    public ExecutionContext handleNotFoundInS3Exception(HttpServletRequest request, HttpServletResponse response,
                                                        NotFoundInS3Exception e){
        ExecutionContext ec = e.getEc();

        logger.debug("NotFoundInS3Exception Occured :: URL=" + request.getRequestURL());
        logger.debug("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        return ec;
    }

    @ExceptionHandler(YSBizException.class)
    public ModelAndView handleBizException(HttpServletRequest request,
                                           YSBizException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = e.getExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        logger.error("YSBizException Occured :: URL=" + request.getRequestURL());
        logger.error("Message:: " + ec.getMessage());
        logger.error("Cause:: " + e.getCause());
        logger.error("ErrorInfo :: " + (eInfo != null ? eInfo.toString() : ""));
        logger.error("ErrorType :: " + e.toString());
        logger.error("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        mv.addObject("ec", e.getExecutionContext());

        return mv;
    }

    @ExceptionHandler(PostPaymentException.class)
    public ModelAndView handlePostPaymentException(HttpServletRequest request,
                                                   PostPaymentException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        List<String> errorInfo = e.getErrorArgs();
        logger.error("PostPaymentException Occured :: URL=" + request.getRequestURL());
        for (int i = 0, len = errorInfo.size() ; i < len ; ) {
            logger.error(errorInfo.get(i++) + ":: " + errorInfo.get(i++));
        }
        logger.error("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));
        mv.addObject("ec", e.getExecutionContext());

        return mv;
    }

    @ExceptionHandler(YSBizNoticeException.class)
    public ModelAndView handleBizNoticeException(HttpServletRequest request,
                                                 YSBizNoticeException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = e.getExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        logger.debug("YSBizNoticeException Occured :: URL=" + request.getRequestURL());
        logger.debug("Message:: " + ec.getMessage());
        logger.debug("Cause:: " + e.getCause());
        logger.debug("ErrorInfo :: " + (eInfo != null ? eInfo.toString() : ""));
        logger.debug("ErrorType :: " + e.toString());
        logger.debug("FilteredStackTrace ::" +
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
        logger.error("ErrorInfo :: " + (eInfo != null ? eInfo.toString() : ""));
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
                                                                     HttpRequestMethodNotSupportedException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = new ExecutionContext();
        logger.debug("HttpRequestMethodNotSupportedException Occured:: URL=" + request.getRequestURL());
        logger.debug("Message:: " + e.getMessage());
        logger.debug("Cause:: " + e.getCause());
        logger.debug("FilteredStackTrace::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

        ec.setResult(ExecutionContext.FAIL);
        ec.setMessage(MessageResolver.getMessage("U901"));
        ec.setErrCode("ERR9950");
        mv.addObject("ec", ec);

        return mv;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingServletRequestParameterException(HttpServletRequest request,
                                           MissingServletRequestParameterException e){
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW_NAME);
        ExecutionContext ec = new ExecutionContext();
        ErrorInfo eInfo = ec.getErrorInfo();
        logger.error("MissingServletRequestParameterException Occured :: URL=" + request.getRequestURL());
        logger.error("Message:: " + ec.getMessage());
        logger.error("Cause:: " + e.getCause());
        logger.error("ErrorInfo :: " + (eInfo != null ? eInfo.toString() : ""));
        logger.error("ErrorType :: " + e.toString());
        logger.error("FilteredStackTrace ::" +
                StackTraceFilter.getFilteredCallStack(e.getStackTrace(), "com.apexsoft", false));

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
