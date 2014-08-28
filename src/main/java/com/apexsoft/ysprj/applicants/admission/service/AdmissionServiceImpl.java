package com.apexsoft.ysprj.applicants.admission.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.admission.domain.AdmissionCourseMajor;
import com.apexsoft.ysprj.applicants.admission.domain.AdmissionCourseMajorKey;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
@Service
public class AdmissionServiceImpl implements AdmissionService {

    // TODO 제3자 정보제공 동의 여부 providePrivateInfo 처리

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.admission.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;


    /**
     * 세부 전공에 따른 어학 필수 여부
     *
     * @param admissionCourseMajorKey
     * @return
     */
    @Override
    public AdmissionCourseMajor retrieveEngMdtYn(AdmissionCourseMajorKey admissionCourseMajorKey) {
        AdmissionCourseMajor admissionCourseMajor = null;
        try {
            admissionCourseMajor = commonDAO.queryForObject(NAME_SPACE + "AdmissionCourseMajorMapper.selectByPK",
                    admissionCourseMajorKey, AdmissionCourseMajor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return admissionCourseMajor;
    }
}
