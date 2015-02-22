package com.apexsoft.ysprj.applicants.common.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hanmomhanda on 15. 2. 22.
 */
@Controller
@RequestMapping(value="/pdf")
public class PDFController {

    @Autowired
    PDFService pdfService;

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    private static final Logger logger = LoggerFactory.getLogger(PDFController.class);

    @RequestMapping(value="/merge/applicant/{applNo}")
    @ResponseBody
    public String mergeByApplicant(@PathVariable("applNo") String applNo) {
        ExecutionContext ec = pdfService.getMergedPDFByApplicants(Integer.parseInt(applNo));
        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            return ExecutionContext.SUCCESS;
        } else {
            return ExecutionContext.FAIL;
        }
    }

    @RequestMapping(value="/merge/dept/{deptCode}")
    public void mergeByDept(@PathVariable("deptCode") String deptCode) {

    }
}
