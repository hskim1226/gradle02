package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.framework.persistence.dao.page.PagenateInfo;
import com.apexsoft.ysprj.admin.control.form.*;
import com.apexsoft.ysprj.admin.domain.*;
import com.apexsoft.ysprj.applicants.admission.domain.Admission;
import com.apexsoft.ysprj.applicants.admission.domain.AdmissionName;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdminStatsServiceImpl implements AdminStatsService{


    private final static String NAME_SPACE = "admin.applicant.";
    private final static String CANCEL_NAME_SPACE = "admin.cancel.";
    private final static String ADMIN_NAME_SPACE = "com.apexsoft.ysprj.admin.sqlmap.";
    private final static String APPL_NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";
    private final static String ADMS_NAME_SPACE = "com.apexsoft.ysprj.applicants.admission.sqlmap.";
    private final static String COMMON_NAME_SPACE = "com.apexsoft.ysprj.applicants.common.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AdmsNo admsNo;

    public List<ApplicantCnt> retrieveUnpaidApplicantCntByDept(CourseSearchGridForm searchForm) {
        List<ApplicantCnt> campusList = null;
        try {

            campusList = commonDAO.queryForList(NAME_SPACE+"selectUnpaidApplicantCnt", searchForm, ApplicantCnt.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return campusList;
    }

    public List<ApplicantCnt> retrieveApplicantDetailCntByDept(CourseSearchGridForm searchForm) {
        List<ApplicantCnt> campusList = null;
        try {

            campusList = commonDAO.queryForList(NAME_SPACE+"selectApplicantDetailCnt", searchForm, ApplicantCnt.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return campusList;
    }

    //최근 1주일간 지원자 수 조회
    public List<ApplicantCnt> retrieveApplicantCntByRecent(CourseSearchGridForm searchForm) {
        List<ApplicantCnt> campusList = null;
        try {

            campusList = commonDAO.queryForList(NAME_SPACE+"selectApplicantRecentCntByDept", searchForm, ApplicantCnt.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return campusList;
    }


    public List<ApplicantCnt> retrieveApplicantCntByDept(CourseSearchGridForm searchForm) {
        List<ApplicantCnt> campusList = null;
        try {

            campusList = commonDAO.queryForList(NAME_SPACE+"selectApplicantCnt", searchForm, ApplicantCnt.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return campusList;
    }


    public List<ApplicantDetailCnt> selectApplicantCntByAdmsDept(CourseSearchGridForm searchForm){
        List<ApplicantDetailCnt> cntList = null;
        try {

            cntList = commonDAO.queryForList(NAME_SPACE+"selectApplicantCntByAdmsDept", searchForm, ApplicantDetailCnt.class);
            for( ApplicantDetailCnt aCnt :cntList){
                aCnt.setAdmsName(shortAdmsCodeName(aCnt.getAdmsNo()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cntList;
    }

    private String shortAdmsCodeName(String admsCode){
        String admsName = "";

        if( admsNo.getGeneral().equals(admsCode)) {
            admsName = "일반";
        }else if( admsNo.getForeign().equals(admsCode)){
            admsName ="외국인";
        }else if( admsNo.getEarly().equals(admsCode)){
            admsName ="조기";
        }
        return admsName;
    }


}
