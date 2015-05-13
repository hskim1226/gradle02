package com.apexsoft.ysprj.applicants.common.control;

import com.apexsoft.framework.birt.spring.core.BirtEngineFactory;
import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.domain.BirtRequest;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014-08-01.
 */
@Controller
@RequestMapping("/application")
public class BirtController {

    @Autowired
    BirtService birtService;

    @Autowired
    CommonDAO commonDAO;

    @Autowired
    BirtEngineFactory birtEngineFactory;

    @Autowired
    WebUtil webUtil;

    @Value("#{app['rpt.format']}")
    private String REPORT_FORMAT;

    private static final Logger logger = LoggerFactory.getLogger(BirtController.class);

    private final String RPT_APPLICATION_KR = "yonsei-appl-kr";
    private final String RPT_APPLICATION_EN = "yonsei-appl-en";

    private final String RPT_ADMISSION_KR = "yonsei-adms-kr";
    private final String RPT_ADMISSION_EN = "yonsei-adms-en";

//    내 원서의 '지원서 보기에서 수험표, 지원서를 누르는 경우
//    application/print 를 통하는데 application/print와 application/preview가 하는 일이 정확히 같아서
//    application/print는 deprate처리하고 application/preview로 일원화
//    @RequestMapping(value = "/print")
//    public ModelAndView previewApplicationByParam(BirtRequest birtRequest,
//                                                  Principal principal,
//                                                  ModelAndView mv) {
//        mv.setViewName("pdfSingleFormatBirtView");
//        Application application = birtRequest.getApplication();
//        if (application == null) {
//            filterApplicationNull(principal);
//        } else {
//            String admsTypeCode = application.getAdmsTypeCode();
//            String reqType = birtRequest.getReqType();
//            String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
//            String reportName = "yonsei-" + reqType + "-" + lang;
//            int applNo = application.getApplNo();
//
//            mv.addObject("reportFormat", REPORT_FORMAT);
//            mv.addObject("reportName", reportName);
//            ExecutionContext ec = birtService.processBirt(applNo, reportName);
//            mv.addAllObjects((Map<String, Object>)ec.getData());
//        }
//
//        return mv;
//    }

    @RequestMapping(value = "/print/{applNo}/{reportFormat}/{reportName}")
    public ModelAndView previewApplicationByRESTful(@PathVariable("applNo") Integer applNo,
                                                    @PathVariable("reportFormat") String reportFormat,
                                                    @PathVariable("reportName") String reportName,
                                                    ModelAndView mv) {
        mv.setViewName("pdfSingleFormatBirtView");
        mv.addObject("reportFormat", reportFormat);
        mv.addObject("reportName", reportName);
        ExecutionContext ec = birtService.processBirt(applNo, reportName);
        mv.addAllObjects((Map<String, Object>)ec.getData());
        return mv;
    }

    @RequestMapping(value = "/preview")
    public ModelAndView previewAppInfo(BirtRequest birtRequest,
                                       Principal principal,
                                       HttpServletRequest request,
                                       ModelAndView mv) {
        webUtil.blockGetMethod(request, birtRequest.getApplication());
        mv.setViewName("pdfSingleFormatBirtView");
        Application application = birtRequest.getApplication();
        Map<String, Object> bigDataMap = null;
        if (application == null) {
            filterApplicationNull(principal);
        } else {
            String admsTypeCode = application.getAdmsTypeCode();
            String reqType = birtRequest.getReqType();
            String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
            String reportName = "yonsei-" + reqType + "-" + lang;
            int applNo = application.getApplNo();
            mv.addObject("reportFormat", REPORT_FORMAT);
            mv.addObject("reportName", reportName);
            ExecutionContext ec = birtService.processBirt(applNo, reportName);
            bigDataMap = (Map<String, Object>)ec.getData();
            mv.addAllObjects(bigDataMap);
            logger.debug("in BirtController bigDataMap clear start");
            bigDataMap.clear();
            bigDataMap = null;
            logger.debug("in BirtController bigDataMap clear end");
        }

        return mv;
    }

    /**
     * 결제 완료 후 성공 화면에서 호출
     * 수험번호가 채번된 원서 PDF 파일을 로컬에 저장
     *
     * @param birtRequest
     * @param principal
     * @param mv
     * @return
     */
    @RequestMapping(value = "/generate/application")
    public ModelAndView generateApplicationFile(BirtRequest birtRequest,
                                                Principal principal,
                                                HttpServletRequest request,
                                                ModelAndView mv) {
        webUtil.blockGetMethod(request, birtRequest.getApplication());
        Application application = birtRequest.getApplication();
        if (application == null) {
            filterApplicationNull(principal);
        } else {
            int applNo = application.getApplNo();

            Application applicationFromDB = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                    applNo, Application.class);
            String admsTypeCode = applicationFromDB.getAdmsTypeCode();
            String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode)? "en" : "kr";
            String reportName = "yonsei-appl-" + lang;

            mv.setViewName("pdfSingleFormatBirtSaveToFile");
            mv.addObject("reportFormat", REPORT_FORMAT);
            mv.addObject("reportName", reportName);
            ExecutionContext ec = birtService.processBirt(applNo, reportName);
            mv.addAllObjects((Map<String, Object>)ec.getData());
        }

        return mv;
    }

    /**
     * 결제 완료 후 성공 화면에서 호출
     * 수험번호가 채번된 수험표 PDF 파일을 로컬에 저장
     *
     * @param birtRequest
     * @param principal
     * @param mv
     * @return
     */
    @RequestMapping(value = "/generate/slip")
    public ModelAndView generateSlipFile(BirtRequest birtRequest,
                                         Principal principal,
                                         HttpServletRequest request,
                                         ModelAndView mv) {
        webUtil.blockGetMethod(request, birtRequest.getApplication());
        Application application = birtRequest.getApplication();
        if (application == null) {
            filterApplicationNull(principal);
        } else {
            int applNo = application.getApplNo();

            Application applicationFromDB = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                    applNo, Application.class);
            String admsTypeCode = applicationFromDB.getAdmsTypeCode();
            String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
            String reportName = "yonsei-adms-" + lang;

            mv.setViewName("pdfSingleFormatBirtSaveToFile");
            mv.addObject("reportFormat", REPORT_FORMAT);
            mv.addObject("reportName", reportName);
            ExecutionContext ec = birtService.processBirt(applNo, reportName);
            mv.addAllObjects((Map<String, Object>)ec.getData());
        }

        return mv;
    }

    private void filterApplicationNull(Principal principal) {
        ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
        ec.setMessage(MessageResolver.getMessage("U343"));

        ec.setErrCode("ERR0071");
        Map<String, String> errorInfo = new HashMap<String, String>();
        errorInfo.put("userId", principal.getName());
        ec.setErrorInfo(new ErrorInfo(errorInfo));
        throw new YSBizException(ec);
    }
}
