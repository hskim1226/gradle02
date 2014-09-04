package com.apexsoft.framework.web.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 27.
 * Time: 오후 7:18
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionResolver implements HandlerExceptionResolver{

    private final static Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    private String htmlErrorView;

    private HttpMessageConverter<?>[] messageConverters;

    public void setHtmlErrorView(String htmlErrorView) {
        this.htmlErrorView = htmlErrorView;
    }

    public void setMessageConverters(HttpMessageConverter<?>[] messageConverters) {
        this.messageConverters = messageConverters;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("", ex);

        ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        String message;
        if ( ex.getClass().isAssignableFrom(BusinessException.class)){
            BusinessException businessException = (BusinessException)ex;
            message = businessException.getMessage();
        }else{
            message = ex.getLocalizedMessage().replaceAll("\\n","");
        }

        List<MediaType> acceptedMediaTypes = httpRequest.getHeaders().getAccept();

        if (acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }

        MediaType.sortByQualityValue(acceptedMediaTypes);

        if ( !acceptedMediaTypes.get(0).equals( MediaType.TEXT_HTML ) ){

            ExecutionContext returnValue = new ExecutionContext(ExecutionContext.FAIL, message);

            if (this.messageConverters != null) {
                for (MediaType acceptedMediaType : acceptedMediaTypes) {
                    for (@SuppressWarnings("rawtypes") HttpMessageConverter messageConverter : this.messageConverters) {
                        if (messageConverter.canWrite(ExecutionContext.class, acceptedMediaType)) {
                            try {
                                messageConverter.write(returnValue, acceptedMediaType, outputMessage);
                            } catch (Exception e) {
                            }

                            return new ModelAndView();
                        }
                    }
                }
            }
        } else {
            ModelAndView htmlMav = new ModelAndView(htmlErrorView).addObject("message", message);

            return htmlMav;
        }
        return new ModelAndView();
    }
}
