package com.apexsoft.ysprj.preview.web;

import com.apexsoft.framework.birt.spring.core.AbstractSingleFormatBirtView;
import com.apexsoft.framework.birt.spring.core.HtmlSingleFormatBirtView;
import com.apexsoft.framework.birt.spring.core.PdfSingleFormatBirtView;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.content.IReportContent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2014-08-19.
 */
public class BirtViewFactory implements FactoryBean<AbstractSingleFormatBirtView>, ApplicationContextAware, InitializingBean {

    @Autowired
    private HttpServletRequest request;

    private ApplicationContext context;
    private AbstractSingleFormatBirtView view;
    private String format;

    private String reportNameRequestParameter = "reportName";
    private String documentNameRequestParameter = "documentName";
    private String reportFormatRequestParameter = "reportFormat";
    private String requestEncoding = "UTF-8";
    private String reportOutputFormat = "html";

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public AbstractSingleFormatBirtView getObject() throws Exception {
        String f = request.getParameter("format");
        System.out.println(f);
        if( format.equalsIgnoreCase(IRenderOption.OUTPUT_FORMAT_PDF) ) {
            return context.getBean(PdfSingleFormatBirtView.class);
        } else if( format.equalsIgnoreCase(IRenderOption.OUTPUT_FORMAT_HTML) ) {
            return context.getBean(HtmlSingleFormatBirtView.class);
        }
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return AbstractSingleFormatBirtView.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        Assert.hasText(format, "the 'format' must not be null");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
