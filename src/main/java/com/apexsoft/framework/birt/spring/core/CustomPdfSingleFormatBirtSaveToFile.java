package com.apexsoft.framework.birt.spring.core;

import org.eclipse.birt.report.engine.api.IPDFRenderOption;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Birt view to render PDF-based BIRT reports
 *
 * @author Josh Long
 * @author Jason Weathersby
 */
public class CustomPdfSingleFormatBirtSaveToFile extends CustomAbstractSingleFormatBirtProcessor {

    public CustomPdfSingleFormatBirtSaveToFile() {
//        setContentType("application/pdf");
    }

    @Override
    protected RenderOption renderReport( Map<String, Object> map,
                                         BirtViewResourcePathCallback resourcePathCallback,
                                         String reportName, String format, IRenderOption options ) throws Throwable {

        String fileName = (String)map.get("pdfFileName");
        PDFRenderOption pdfOptions = new PDFRenderOption(options);
        pdfOptions.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
        pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
        pdfOptions.setOutputFileName(map.get("pdfDirectoryFullPath") + "/" + fileName);
        return pdfOptions;
    }
}
