package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.ysprj.applicants.common.domain.*;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface CommonService {

    List<Campus> retrieveCampus();
    List<College> retrieveCollegeByCampus(String campusCode);
    List<CodeNameDepartment> retrieveAllDepartmentByColl(ParamForSetupCourses paramForSetupCourses);
    List<CodeNameDepartment> retrieveGeneralDepartmentByAdmsColl(ParamForSetupCourses paramForSetupCourses);
    List<CodeNameCourse> retrieveGeneralCourseByAdmsDept(ParamForSetupCourses paramForSetupCourses);
    List<CodeNameDetailMajor> retrieveGeneralDetailMajorByAdmsDeptCors(ParamForSetupCourses paramForSetupCourses);
    List<CodeNameCourse> retrieveCommissionCourseByAdmsDept(ParamForSetupCourses paramForSetupCourses);
    List<AcademyResearchIndustryInstitution> retrieveAriInst();
    List<CodeNameDepartment> retrieveAriInstDepartmentByAdmsAriInst(ParamForSetupCourses paramForSetupCourses);
    List<CodeNameCourse> retrieveAriInstCourseByAdmsDeptAriInst(ParamForSetupCourses paramForSetupCourses);
    List<CodeNameDetailMajor> retrieveAriInstDetailMajorByAdmsDeptAriInst(ParamForSetupCourses paramForSetupCourses);
    List<Country> retrieveCountryByName(String keyword);
    Country retrieveCountryByCode(String cntrCode);
    List<School> retrieveSchoolByTypeName(String type, String keyword);
    List<CommonCode> retrieveCommonCodeByCodeGroup(String codeGrp);
    CommonCode retrieveCommonCodeByCodeGroupCode(String codeGrp, String code);
    List<CommonCode> retrieveCommonCodeListByCodeGroupKeyword(ParamForCommonCode paramForCommonCode);
    CommonCode retrieveCommonCodeListByCodeGroupCode(ParamForCommonCode paramForCommonCode);

    String retrieveCampNameByCode(String campCode);
    String retrieveCollNameByCode(String collCode);
    String retrieveAriInstNameByCode(String ariInstCode);
    String retrieveDeptNameByCode(String deptCode);
    String retrieveCorsTypeNameByCode(String corsTypeCode);
    String retrieveDetlMajNameByCode(String detlMajCode);

    List<LanguageExam> retrieveLangExamByLangCode(String langCode);
    List<Integer> retrieveAvailableApplNos(String userId);
}
