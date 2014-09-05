package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.common.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * 캠퍼스 조회
     *
     * @return
     */
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

    /**
     * 캠퍼스 별 단과대학 조회
     *
     * @param campusCode
     * @return
     */
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

    /**
     * 단과대별 학과 조회
     *
     * @param paramForSetupCourses
     * @return
     */
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

    /**
     * 학과별 세부 과정 조회
     *
     * @param paramForSetupCourses
     * @return
     */
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

    /**
     * 학과, 과정 별 세부 전공 조회
     *
     * @param paramForSetupCourses
     * @return
     */
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

    /**
     * 학과별 위탁과정 조회
     *
     * @param paramForSetupCourses
     * @return
     */
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

    /**
     * 학연산 조회
     *
     * @return
     */
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

    /**
     * 학연산 학과 조회
     *
     * @param paramForSetupCourses
     * @return
     */
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

    /**
     * 학연산 학과별 과정 조회
     *
     * @param paramForSetupCourses
     * @return
     */
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

    /**
     * 학연산 학과별 세부전공 조회
     *
     * @param paramForSetupCourses
     * @return
     */
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

    /**
     * 검색어로 국가 조회
     *
     * @param keyword
     * @return
     */
    @Override
    public List<Country> retrieveCountryByName(String keyword) {
        List<Country> countryList = null;
        try {
            countryList = commonDAO.queryForList(NAME_SPACE+"CustomCountryMapper.selectCountryListByName",
                    keyword,
                    Country.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }

    /**
     * 학교유형, 검색어로 학교 조회
     *
     * @param schlType
     * @param keyword
     * @return
     */
    @Override
    public List<School> retrieveSchoolByTypeName(String schlType, String keyword) {
        List<School> schoolList = null;
        ParamForSchoolSearch paramForSchoolSearch = new ParamForSchoolSearch();
        paramForSchoolSearch.setSchlType(schlType);
        paramForSchoolSearch.setKeyword(keyword);

        try {
            schoolList = commonDAO.queryForList(NAME_SPACE+"CustomSchoolMapper.selectSchoolListByTypeName",
                    paramForSchoolSearch,
                    School.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schoolList;
    }

    /**
     * 코드그룹으로 공통코드 목록 조회
     *
     * @param codeGrp
     * @return
     */
    @Override
    @Cacheable(value = "commonCodeListCache")
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

    /**
     * 코드 그룹, 코드로 공통 코드 값 조회
     *
     * @param codeGrp
     * @param code
     * @return
     */
    @Override
    @Cacheable(value = "commonCodeCache")
    public CommonCode retrieveCommonCodeValueByCodeGroupCode(String codeGrp, String code) {
        CommonCode commonCode = null;
        ParamForCommonCode paramForCommonCode = new ParamForCommonCode();
        paramForCommonCode.setCodeGrp(codeGrp);
        paramForCommonCode.setCode(code);

        try {
            commonCode = commonDAO.queryForObject(NAME_SPACE + "CustomCommonCodeMapper.selectByCodeGroupCode",
                    paramForCommonCode,
                    CommonCode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonCode;
    }

    /**
     * 코드 그룹, 키워드로 공통 코드 목록 검색
     *
     * @param paramForCommonCode
     * @return
     */
    @Override
    @Cacheable(value = "commonCodeCache")
    public List<CommonCode> retrieveCommonCodeListByCodeGroupKeyword(ParamForCommonCode paramForCommonCode) {
        List<CommonCode> commonCodeList = null;
        try {
            commonCodeList = commonDAO.queryForList(NAME_SPACE + "CustomCommonCodeMapper.selectListByCodeGroupKeyword",
                    paramForCommonCode,
                    CommonCode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonCodeList;
    }

    /**
     * 코드로 캠퍼스 이름 검색
     *
     * @param campCode
     * @return
     */
    @Override
    public String retrieveCampNameByCode(String campCode) {
        String campName = null;
        try {
            campName = commonDAO.queryForObject(NAME_SPACE + "CustomCampusMapper.selectNameByCode",
                    campCode,
                    String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return campName;
    }

    /**
     * 코드로 대학 이름 검색
     *
     * @param collCode
     * @return
     */
    @Override
    public String retrieveCollNameByCode(String collCode) {
        String collName = null;
        try {
            collName = commonDAO.queryForObject(NAME_SPACE + "CustomCollegeMapper.selectNameByCode",
                    collCode,
                    String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collName;
    }

    /**
     * 코드로 학연산 이름 검색
     *
     * @param ariInstCode
     * @return
     */
    @Override
    public String retrieveAriInstNameByCode(String ariInstCode) {
        String ariInstName = null;
        try {
            ariInstName = commonDAO.queryForObject(NAME_SPACE + "CustomAcademyResearchIndustryInstitutionMapper.selectNameByCode",
                    ariInstCode,
                    String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ariInstName;
    }

    /**
     * 코드로 학과 이름 검색
     *
     * @param deptCode
     * @return
     */
    @Override
    public String retrieveDeptNameByCode(String deptCode) {
        String deptName = null;
        try {
            deptName = commonDAO.queryForObject(NAME_SPACE + "CustomDepartmentMapper.selectNameByCode",
                    deptCode,
                    String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deptName;
    }

    /**
     * 코드로 지원과정 이름 검색
     *
     * @param corsTypeCode
     * @return
     */
    @Override
    public String retrieveCorsTypeNameByCode(String corsTypeCode) {
        String corsTypeName = null;
        try {
            corsTypeName = commonDAO.queryForObject(NAME_SPACE + "CustomCourseMapper.selectNameByCode",
                    corsTypeCode,
                    String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return corsTypeName;
    }

    /**
     * 코드로 세부전공 이름 검색
     *
     * @param detlMajCode
     * @return
     */
    @Override
    public String retrieveDetlMajNameByCode(String detlMajCode) {
        String detlMajName = null;
        try {
            detlMajName = commonDAO.queryForObject(NAME_SPACE + "CustomDetailMajorMapper.selectNameByCode",
                    detlMajCode,
                    String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detlMajName;
    }
}
