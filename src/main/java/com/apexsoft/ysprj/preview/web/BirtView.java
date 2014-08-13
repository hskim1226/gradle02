package com.apexsoft.ysprj.preview.web;

import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014-08-01.
 */
public class BirtView extends AbstractView {
    private IReportEngine birtReportEngine;

    public BirtView() {
        setContentType( "application/pdf" );
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType( getContentType() );
        resp.setHeader( "Content-Disposition", "inline; filename=test.pdf" );
        String reportName = "reports/form.rptdesign";

        Map<String, String> params = new HashMap<String, String>();
        for( Enumeration<String> names = req.getParameterNames(); names.hasMoreElements(); ) {
            String name = names.nextElement();
            params.put( name, req.getParameter(name) );
        }
        String[] cols = params.keySet().toArray( new String[params.size()] );

        String id = req.getParameter("id");

        ServletContext sc = req.getSession().getServletContext();
        birtReportEngine = BirtEngine.getBirtEngine(sc);

        IReportRunnable design = null;
        try {
            design = birtReportEngine.openReportDesign( sc.getRealPath("/" + reportName) );
            ReportDesignHandle report = (ReportDesignHandle) design.getDesignHandle();

            buildReport( report );

            IRunAndRenderTask task = birtReportEngine.createRunAndRenderTask( design );

            HTMLRenderOption options = new HTMLRenderOption();
//			options.setOutputFormat( HTMLRenderOption.OUTPUT_FORMAT_HTML );
            options.setOutputFormat( HTMLRenderOption.OUTPUT_FORMAT_PDF );
            options.setOutputStream( resp.getOutputStream() );
            options.setBaseImageURL( req.getContextPath() + "/images" );
            options.setImageDirectory( sc.getRealPath("/images") );
            task.setRenderOption( options );

            task.run();
            task.close();
        } catch (EngineException e) {
            e.printStackTrace();
            throw new ServletException();
        } catch (SemanticException e) {
            e.printStackTrace();
            throw new ServletException();
        }
    }

    private void buildReport(ReportDesignHandle report) throws SemanticException {

    }
}
