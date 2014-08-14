package com.apexsoft.ysprj.preview.web;

import org.eclipse.birt.report.engine.api.*;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2014-08-01.
 */
public class BirtView extends AbstractView {

    private final static String DEFAULT_REPORT_FORMAT = "pdf";
    private final static String REPORTS_DIRECTORY = "/reports";

    private IReportEngine birtEngine;
    private String reportNameRequestParameter;
    private String reportFormatRequestParameter;
    private IRenderOption renderOption;

    public void setRenderOption(IRenderOption ro) {
        this.renderOption = ro;
    }

    public void setReportNameRequestParameter(String rn) {
        Assert.hasText(rn, "The report name parameter must not be null");
        this.reportNameRequestParameter = rn;
    }

    public void setReportFormatRequestParameter(String rf) {
        Assert.hasText(rf, "The report format parameter must not be null");
        this.reportFormatRequestParameter = rf;
    }

    @Override
    protected void renderMergedOutputModel(
            Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String reportName = request.getParameter( this.reportNameRequestParameter );
        String format = request.getParameter( this.reportFormatRequestParameter);
        ServletContext sc = request.getSession().getServletContext();
        if( format == null ) {
            format = DEFAULT_REPORT_FORMAT;
        }
        IReportRunnable runnable = null;
        runnable = birtEngine.openReportDesign( sc.getRealPath( REPORTS_DIRECTORY ) + "/" + reportName );
        IRunAndRenderTask runAndRenderTask = birtEngine.createRunAndRenderTask( runnable );
//        task.setParameterValues( discoverAndSetParameters( runnable, request ) );

        response.setContentType( birtEngine.getMIMEType( format ) );
        IRenderOption options = null == this.renderOption ? new RenderOption() : this.renderOption;
        if( format.equalsIgnoreCase( "html" ) ) {
            HTMLRenderOption htmlOptions = new HTMLRenderOption( options );
            htmlOptions.setOutputFormat( IRenderOption.OUTPUT_FORMAT_HTML );
            htmlOptions.setOutputStream( response.getOutputStream() );
            htmlOptions.setImageHandler( new HTMLServerImageHandler());
            htmlOptions.setBaseImageURL( request.getContextPath() + "/images" );
            htmlOptions.setImageDirectory( sc.getRealPath( "/images") );
            runAndRenderTask.setRenderOption( htmlOptions );
        } else if( format.equalsIgnoreCase( "pdf" ) ) {
            PDFRenderOption pdfRenderOption = new PDFRenderOption( options );
            pdfRenderOption.setOutputFormat( IRenderOption.OUTPUT_FORMAT_PDF );
            pdfRenderOption.setOption( IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE );
            pdfRenderOption.setOutputStream( response.getOutputStream() );
            runAndRenderTask.setRenderOption( pdfRenderOption );
        } else {
            String att = "download." + format;
            response.setHeader( "Content-Disposition", "attachment; filename=\"" + att + "\"");
            options.setOutputStream( response.getOutputStream() );
            runAndRenderTask.setRenderOption( options );
        }
        runAndRenderTask.getAppContext().put( EngineConstants.APPCONTEXT_BIRT_VIEWER_HTTPSERVET_REQUEST, request );
        runAndRenderTask.run();
        runAndRenderTask.close();
    }

    public void setBirtEngine(IReportEngine birtEngine) {
        this.birtEngine = birtEngine;
    }
}
