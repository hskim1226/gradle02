package com.apexsoft.framework.birt.spring.core;

import org.eclipse.birt.report.engine.api.IPDFRenderOption;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Birt view to render PDF-based BIRT reports
 *
 * @author Josh Long
 * @author Jason Weathersby
 */
public class PdfSingleFormatBirtSaveToFile extends AbstractSingleFormatBirtView {

    public PdfSingleFormatBirtSaveToFile() {
        setContentType("application/pdf");
    }

    @Override
    protected RenderOption renderReport(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response,
                                        BirtViewResourcePathCallback resourcePathCallback, Map<String, Object> appContextValuesMap,
                                        String reportName, String format, IRenderOption options) throws Throwable {

        String oName = reportName;
        if( oName.toLowerCase().endsWith(".rptdesign")){
            oName = oName.replaceAll("(?i).rptdesign", "");
        }

        String fileName = (String)map.get("rptFileName");
        String header = "filename=" + URLEncoder.encode(fileName, "UTF-8") + ".pdf";

        response.setHeader ("Content-Disposition", header);

        PDFRenderOption pdfOptions = new PDFRenderOption(options);
        pdfOptions.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
        pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
        pdfOptions.setOutputFileName(map.get("rptDirectoryFullPath") + "/" + fileName);
        return pdfOptions;
    }
}
