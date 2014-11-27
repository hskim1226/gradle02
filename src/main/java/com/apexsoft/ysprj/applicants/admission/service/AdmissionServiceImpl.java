package com.apexsoft.ysprj.applicants.admission.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.admission.domain.AdmissionCourseMajor;
import com.apexsoft.ysprj.applicants.admission.domain.AdmissionCourseMajorLanguage;
import com.apexsoft.ysprj.applicants.admission.domain.ParamForAdmissionCourseMajor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
@Service
public class AdmissionServiceImpl implements AdmissionService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.admission.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;


    /**
     * 세부 전공에 따른 어학 필수 여부
     *
     * @param param
     * @return
     */
    @Override
    public AdmissionCourseMajor retrieveEngMdtYn(ParamForAdmissionCourseMajor param) {
        AdmissionCourseMajor admissionCourseMajor = null;
        try {
            admissionCourseMajor = commonDAO.queryForObject(NAME_SPACE + "CustomAdmissionCourseMajorMapper.selectYnByAdmsNoDetlMajCode",
                    param, AdmissionCourseMajor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return admissionCourseMajor;
    }

    /**
     * 세부 전공 별 제출 가능 영어시험목록 조회
     *
     * @param param
     * @return
     */
    @Override
    public List<AdmissionCourseMajorLanguage> retrieveAvailableEngExamList(ParamForAdmissionCourseMajor param) {
        List<AdmissionCourseMajorLanguage> admissionCourseMajorLanguageList = null;
        try {
            admissionCourseMajorLanguageList = commonDAO.queryForList(NAME_SPACE + "CustomAdmissionCourseMajorLanguageMapper.selectAvailableExamListByAdmsNoDetlMajCode",
                    param, AdmissionCourseMajorLanguage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return admissionCourseMajorLanguageList;
    }
}
