package com.apexsoft.ysprj.applicants.payment.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.applicants.payment.domain.ApplicationPaymentTransaction;
import com.apexsoft.ysprj.applicants.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.*;

/**
 * Created by cosb071 on 15. 4. 7.
 */
@Controller
@RequestMapping(value = "/payment/admin")
public class PaymentAdminController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BirtService birtService;

    @Autowired
    PDFService pdfService;

    @Autowired
    private CommonDAO commonDAO;

    @Value("#{app['file.mergeTestDir']}")
    private String MERGE_TEST_DIR;

    @Value("#{app['s3.midPath']}")
    private String MID_PATH;

    @RequestMapping(value="/paymanual")
    public ModelAndView paymanual( Principal principal, ModelAndView mv ) {

        mv.setViewName("xpay/paymanual");

        String adminID = principal.getName();
        if (!adminID.equals("Apex1234")) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U902"));
            ec.setErrCode("ERR0801");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("adminID", adminID);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        return mv;
    }

    @RequestMapping(value="/exepaymanual")
    public String exepaymanually( ApplicationPaymentTransaction applPayTr ) {

        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                applPayTr.getApplNo(), Application.class);

        // 지원상태가 '00010' / '00021' 만 처리 가능
        if( application == null || application.getApplStsCode() == null ||
                (!application.getApplStsCode().equals("00010") && !application.getApplStsCode().equals("00021")) ) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage("지원상태가 '작성완료(00010/00021)' 이어야 처리 가능합니다.");
            ec.setErrCode("");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("applNo", applPayTr.getApplNo().toString());
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        // 필요한 데이터 처리 (상태, 수험번호, 결제정보)
        paymentService.registerManualPay(applPayTr);

        // 수험표, 지원서 생성 및 Merge
        // 타 대학원 확장 시 TODO - 학교 이름을 파라미터로 받도록
        String admsTypeCode = application.getAdmsTypeCode();
        String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
        String reportName = "yonsei-appl-" + lang;
        ExecutionContext ecGenAppl = birtService.generateBirtFile(application.getApplNo(), reportName);
        reportName = "yonsei-adms-" + lang;
        ExecutionContext ecGenAdms = birtService.generateBirtFile(application.getApplNo(), reportName);
        ExecutionContext ecPdfMerge = pdfService.getMergedPDFByApplicants(applPayTr.getApplNo());
        if ( ExecutionContext.FAIL.equals(ecGenAppl.getResult()) ||
                ExecutionContext.FAIL.equals(ecGenAdms.getResult()) ||
                ExecutionContext.FAIL.equals(ecPdfMerge.getResult()) ) {
            throw new YSBizException();
        }

        return "xpay/resultmanual";
    }

    @RequestMapping(value="/pdfmanual")
    public ModelAndView pdfmanual( Principal principal, ModelAndView mv ) {

        mv.setViewName("xpay/pdfmanual");

        String adminID = principal.getName();
        if (!adminID.equals("Apex1234")) {
            ExecutionContext ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U902"));
            ec.setErrCode("ERR0801");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("adminID", adminID);
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }

        return mv;
    }

    @RequestMapping(value="/exepdfmanual")
    public String exepdfmanually( ApplicationPaymentTransaction applPayTr ) {

        ExecutionContext ec;

        // 수험표, 지원서 생성 및 Merge
        // 타 대학원 확장 시 TODO - 학교 이름을 파라미터로 받도록
        Application application = commonDAO.queryForObject("com.apexsoft.ysprj.applicants.application.sqlmap.ApplicationMapper.selectByPrimaryKey",
                                                           applPayTr.getApplNo(), Application.class);
//        ec = processReGenMergeUpload(application);
        String admsTypeCode = application.getAdmsTypeCode();
        String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
        String reportName = "yonsei-appl-" + lang;
        ExecutionContext ecGenAppl = birtService.generateBirtFile(application.getApplNo(), reportName);
        reportName = "yonsei-adms-" + lang;
        ExecutionContext ecGenAdms = birtService.generateBirtFile(application.getApplNo(), reportName);
        ExecutionContext ecPdfMerge = pdfService.getMergedPDFByApplicants(applPayTr.getApplNo());
        if ( ExecutionContext.FAIL.equals(ecGenAppl.getResult()) ||
                ExecutionContext.FAIL.equals(ecGenAdms.getResult()) ||
                ExecutionContext.FAIL.equals(ecPdfMerge.getResult()) ) {
            throw new YSBizException();
        }

        return "xpay/resultpdf";
    }











}
