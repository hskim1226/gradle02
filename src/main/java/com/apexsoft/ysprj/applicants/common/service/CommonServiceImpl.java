package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.framework.exception.BusinessException;
import com.apexsoft.framework.interceptor.Converter;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.common.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
@Service
public class CommonServiceImpl implements CommonService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.common.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private Converter converter;

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
            converter.convert(campusList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(collegeList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return collegeList;
    }
    /**
     * 단과대별 모든학과 조회
     *
     * @param paramForSetupCourses
     * @return
     */
    @Override
    public List<CodeNameDepartment> retrieveAllDepartmentByColl(ParamForSetupCourses paramForSetupCourses) {
        List<CodeNameDepartment> codeNameDepartmentList = null;
        try {
            codeNameDepartmentList = commonDAO.queryForList(NAME_SPACE+"CustomDepartmentMapper.selectAllDepartmentByColl",
                    paramForSetupCourses,
                    CodeNameDepartment.class);
            converter.convert(codeNameDepartmentList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return codeNameDepartmentList;
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
            converter.convert(codeNameDepartmentList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(codeNameCourseList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(codeNameDetailMajorList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(codeNameCourseList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(academyResearchIndustryInstitutionList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(codeNameDepartmentList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(codeNameCourseList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(codeNameDetailMajorList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(countryList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return countryList;
    }

    /**
     * 국가 코드로 국가이름 검색
     *
     * @param cntrCode
     * @return
     */
    @Override
    public Country retrieveCountryByCode(String cntrCode) {
        Country country = null;
        try {
            country = commonDAO.queryForObject(NAME_SPACE+"CustomCountryMapper.selectCountryByCode",
                    cntrCode,
                    Country.class);
            converter.convert(country, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return country;
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
            converter.convert(schoolList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(commonCodeList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(commonCode, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            converter.convert(commonCodeList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return commonCodeList;
    }

    /**
     * 코드 그룹, 코드로 공통 코드 조회
     *
     * @param paramForCommonCode
     * @return
     */
    @Override
    @Cacheable(value = "commonCodeCache")
    public CommonCode retrieveCommonCodeListByCodeGroupCode(ParamForCommonCode paramForCommonCode) {
        CommonCode commonCode = null;
        try {
            commonCode = commonDAO.queryForObject(NAME_SPACE+"CustomCommonCodeMapper.selectListByCodeGroupCode",
                    paramForCommonCode,
                    CommonCode.class);
            converter.convert(commonCode, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return commonCode;
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
            Campus campus = commonDAO.queryForObject(NAME_SPACE + "CustomCampusMapper.selectNameByCode",
                    campCode,
                    Campus.class);
            converter.convert(campus, request);
            campName = campus.getCampName();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            College college = commonDAO.queryForObject(NAME_SPACE + "CustomCollegeMapper.selectNameByCode",
                    collCode,
                    College.class);
            converter.convert(college, request);
            collName = college.getCollName();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            throw new BusinessException(e.getMessage(), e);
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
            CodeNameDepartment codeNameDepartment = commonDAO.queryForObject(NAME_SPACE + "CustomDepartmentMapper.selectNameByCode",
                    deptCode,
                    CodeNameDepartment.class);
            converter.convert(codeNameDepartment, request);
            deptName = codeNameDepartment.getDeptName();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            CodeNameCourse codeNameCourse = commonDAO.queryForObject(NAME_SPACE + "CustomCourseMapper.selectNameByCode",
                    corsTypeCode,
                    CodeNameCourse.class);
            converter.convert(codeNameCourse, request);
            corsTypeName = codeNameCourse.getCodeVal();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
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
            CodeNameDetailMajor codeNameDetailMajor = commonDAO.queryForObject(NAME_SPACE + "CustomDetailMajorMapper.selectNameByCode",
                    detlMajCode,
                    CodeNameDetailMajor.class);
            converter.convert(codeNameDetailMajor, request);
            detlMajName = codeNameDetailMajor.getDetlMajName();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return detlMajName;
    }

    @Override
    public List<LanguageExam> retrieveLangExamByLangCode(String langCode) {
        List<LanguageExam> langExamList = null;
        try {
            langExamList = commonDAO.queryForList(NAME_SPACE+"CustomLanguageExamMapper.selectByLangCode", langCode, LanguageExam.class);
            converter.convert(langExamList, request);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return langExamList;

    }

    @Override
    public List<Integer> retrieveAvailableApplNos(String userId) {
        List<Integer> availableApplNoList = null;
        try {
            availableApplNoList = commonDAO.queryForList("com.apexsoft.ysprj.applicants.application.sqlmap.CustomApplicationMapper.selectAvailableApplNos", userId, Integer.class);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return availableApplNoList;
    }
}
