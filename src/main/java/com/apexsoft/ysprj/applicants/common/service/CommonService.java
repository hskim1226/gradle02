package com.apexsoft.ysprj.applicants.common.service;

import com.apexsoft.ysprj.applicants.common.domain.*;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface CommonService {

    List<Campus> retrieveCampus();
    List<College> retrieveCollegeByCampus(String campusCode);
    List<CodeNameDepartment> retrieveGeneralDepartmentByAdmsColl(ParamForSetupCourses paramForSetupCourses);
    List<CodeNameCourse> retrieveGeneralCourseByAdmsDept(ParamForSetupCourses paramForSetupCourses);
    List<CodeNameDetailMajor> retrieveGeneralDetailMajorByAdmsDeptCors(ParamForSetupCourses paramForSetupCourses);
    List<CodeNameCourse> retrieveCommissionCourseByAdmsDept(ParamForSetupCourses paramForSetupCourses);
    List<AcademyResearchIndustryInstitution> retrieveAriInst();
    List<CodeNameDepartment> retrieveAriInstDepartmentByAdmsAriInst(ParamForSetupCourses paramForSetupCourses);
    List<CodeNameCourse> retrieveAriInstCourseByAdmsDeptAriInst(ParamForSetupCourses paramForSetupCourses);
    List<CodeNameDetailMajor> retrieveAriInstDetailMajorByAdmsDeptAriInst(ParamForSetupCourses paramForSetupCourses);
    List<Country> retrieveCountryByName(String keyword);
    List<School> retrieveSchoolByTypeName(String type, String keyword);
    List<CommonCode> retrieveCommonCodeValueByCodeGroup(String codeGrp);
    CommonCode retrieveCommonCodeValueByCodeGroupCode(ParamForCommonCode paramForCommonCode);
    List<CommonCode> retrieveCommonCodeListByCodeGroupKeyword(ParamForCommonCode paramForCommonCode);
}
