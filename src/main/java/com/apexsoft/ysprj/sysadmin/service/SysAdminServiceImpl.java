package com.apexsoft.ysprj.sysadmin.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.exception.YSBizNoticeException;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.service.BirtService;
import com.apexsoft.ysprj.applicants.common.service.PDFService;
import com.apexsoft.ysprj.sysadmin.domain.BackUpApplDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 4. 16.
 */
@Service
public class SysAdminServiceImpl implements  SysAdminService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.sysadmin.sqlmap.";

    @Autowired
    private BirtService birtService;

    @Autowired
    private PDFService pdfService;

    @Autowired
    private CommonDAO commonDAO;

    /**
     * Batch로 최종 PDF 파일 다건을 생성한다.
     * 다건 처리를 위해 예외 발생 시 던지지 않고
     * map에 담아 반환
     *
     * @param application
     * @return
     */
    public ExecutionContext processReGenMergeUpload(Application application) {
        ExecutionContext ecResult = new ExecutionContext();
        Map<String, Object> map = new HashMap<String, Object>();

        String admsTypeCode = application.getAdmsTypeCode();
        int applNo = application.getApplNo();
        String lang = "C".equals(admsTypeCode) || "D".equals(admsTypeCode) ? "en" : "kr";
        String reportName = "yonsei-appl-" + lang;

        ExecutionContext ecGenAppl = null;

        try {
            ecGenAppl = birtService.generateBirtFile(applNo, reportName);
        } catch (Exception e) {
            ecGenAppl = new ExecutionContext(ExecutionContext.FAIL);
        } finally {
            map.put("genAppl", ecGenAppl.getResult());
        }

        reportName = "yonsei-adms-" + lang;
        ExecutionContext ecGenAdms = null;
        try {
            ecGenAdms = birtService.generateBirtFile(applNo, reportName);
        } catch (Exception e) {
            ecGenAdms = new ExecutionContext(ExecutionContext.FAIL);
        } finally {
            map.put("genAdms", ecGenAdms.getResult());
        }

        ExecutionContext ecPdfMerge = null;
        try {
            ecPdfMerge = pdfService.getMergedPDFByApplicants(applNo);
        } catch (Exception e) {
            ecPdfMerge = new ExecutionContext(ExecutionContext.FAIL);
            if (e instanceof YSBizNoticeException) {
                YSBizNoticeException ybne = (YSBizNoticeException)e;
                ExecutionContext ec = ybne.getExecutionContext();
                List<Application> encryptedPdfList = (List<Application>)ec.getData();
                map.put("encryptedPdfList", encryptedPdfList);
            }
        } finally {
            map.put("mergePdf", ecPdfMerge.getResult());
        }

        ecResult.setData(map);

        return ecResult;
    }

    public ExecutionContext downloadAllPdf() {

        ExecutionContext ec = new ExecutionContext();
        List<BackUpApplDoc> backUpApplDocList = null;

        try {
            backUpApplDocList = commonDAO.queryForList(NAME_SPACE + "SysAdminMapper.selectAllPdfInfo", BackUpApplDoc.class);
        } catch (YSBizException e) {
            e.printStackTrace();
        }
System.out.println("appl counts : " + backUpApplDocList.size());
        ec.setData(backUpApplDocList);
        return ec;
    }
}
