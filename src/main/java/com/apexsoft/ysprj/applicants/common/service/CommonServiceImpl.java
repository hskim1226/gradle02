package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationGeneral;
import com.apexsoft.ysprj.applicants.application.domain.EntireApplication;
import com.apexsoft.ysprj.applicants.application.domain.ParamForInitialApply;
import com.apexsoft.ysprj.applicants.common.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
@Service
public class CommonServiceImpl implements CommonService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.common.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public List<Campus> retrieveCampus() {
        List<Campus> campusList = null;
        try {
            campusList = commonDAO.queryForList(NAME_SPACE+"CustomCampusMapper.selectAll", Campus.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return campusList;
    }

    @Override
    public List<College> retrieveCollegeByCampus(String campusCode) {
        List<College> collegeList = null;
        try {
            collegeList = commonDAO.queryForList(NAME_SPACE+"CustomCollegeMapper.selectByCampus", campusCode, College.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collegeList;
    }

    @Override
    public List<CodeNameDepartment> retrieveGeneralDepartmentByAdmsColl(ParamForSetupCourses paramForSetupCourses) {
        List<CodeNameDepartment> codeNameDepartmentList = null;
        try {
            codeNameDepartmentList = commonDAO.queryForList(NAME_SPACE+"CustomDepartmentMapper.selectByAdmsColl",
                    paramForSetupCourses,
                    CodeNameDepartment.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeNameDepartmentList;
    }

    @Override
    public List<CodeNameCourse> retrieveGeneralCourseByAdmsDept(ParamForSetupCourses paramForSetupCourses) {
        List<CodeNameCourse> codeNameCourseList = null;
        try {
            codeNameCourseList = commonDAO.queryForList(NAME_SPACE+"CustomCourseMapper.selectGeneralByAdmsDept",
                    paramForSetupCourses,
                    CodeNameCourse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeNameCourseList;
    }

    @Override
    public List<CodeNameDetailMajor> retrieveGeneralDetailMajorByAdmsDeptCors(ParamForSetupCourses paramForSetupCourses) {
        List<CodeNameDetailMajor> codeNameDetailMajorList = null;
        try {
            codeNameDetailMajorList = commonDAO.queryForList(NAME_SPACE+"CustomDetailMajorMapper.selectGeneralByAdmsDeptCors",
                    paramForSetupCourses,
                    CodeNameDetailMajor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeNameDetailMajorList;
    }

    @Override
    public List<CodeNameCourse> retrieveCommissionCourseByAdmsDept(ParamForSetupCourses paramForSetupCourses) {
        List<CodeNameCourse> codeNameCourseList = null;
        try {
            codeNameCourseList = commonDAO.queryForList(NAME_SPACE+"CustomCourseMapper.selectCommissionByAdmsDept",
                    paramForSetupCourses,
                    CodeNameCourse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeNameCourseList;
    }

    @Override
    public List<AcademyResearchIndustryInstitution> retrieveAriInst() {
        List<AcademyResearchIndustryInstitution> academyResearchIndustryInstitutionList = null;
        try {
            academyResearchIndustryInstitutionList = commonDAO.queryForList(NAME_SPACE+"CustomAcademyResearchIndustryInstitutionMapper.selectAll",
                    AcademyResearchIndustryInstitution.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return academyResearchIndustryInstitutionList;
    }

    @Override
    public List<CodeNameDepartment> retrieveAriInstDepartmentByAdmsAriInst(ParamForSetupCourses paramForSetupCourses) {
        List<CodeNameDepartment> codeNameDepartmentList = null;
        try {
            codeNameDepartmentList = commonDAO.queryForList(NAME_SPACE+"CustomDepartmentMapper.selectByAdmsAriInst",
                    paramForSetupCourses,
                    CodeNameDepartment.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeNameDepartmentList;
    }

    @Override
    public List<CodeNameCourse> retrieveAriInstCourseByAdmsDeptAriInst(ParamForSetupCourses paramForSetupCourses) {
        List<CodeNameCourse> codeNameCourseList = null;
        try {
            codeNameCourseList = commonDAO.queryForList(NAME_SPACE+"CustomCourseMapper.selectAriInstByAdmsDeptAriInst",
                    paramForSetupCourses,
                    CodeNameCourse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeNameCourseList;
    }

    @Override
    public List<CodeNameDetailMajor> retrieveAriInstDetailMajorByAdmsDeptAriInst(ParamForSetupCourses paramForSetupCourses) {
        List<CodeNameDetailMajor> codeNameDetailMajorList = null;
        try {
            codeNameDetailMajorList = commonDAO.queryForList(NAME_SPACE+"CustomDetailMajorMapper.selectAriInstByAdmsDeptAriInstCors",
                    paramForSetupCourses,
                    CodeNameDetailMajor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeNameDetailMajorList;
    }

    @Override
    public List<Country> retrieveCountryByName(String keyword) {
        List<Country> countryList = null;
        try {
            countryList = commonDAO.queryForList(NAME_SPACE+"CustomCountryMapper.retrieveCountryByName",
                    keyword,
                    Country.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }

    @Override
    public List<CommonCode> retrieveCommonCodeValueByCodeGroup(String codeGrp) {
        List<CommonCode> commonCodeList = null;
        try {
            commonCodeList = commonDAO.queryForList(NAME_SPACE+"CustomCommonCodeMapper.selectAllByCodeGroup",
                    codeGrp,
                    CommonCode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonCodeList;
    }


}
