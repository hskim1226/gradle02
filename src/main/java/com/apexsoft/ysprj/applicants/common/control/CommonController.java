package com.apexsoft.ysprj.applicants.common.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.common.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 6.
 */
@Controller
@RequestMapping(value="/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Resource(name = "messageResolver")
    private MessageResolver messageResolver;

    @RequestMapping(value="/code/campus", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext getCampusCode()
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        List<Campus> campusList = commonService.retrieveCampus();

        String json = jacksonObjectMapper.writeValueAsString(campusList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(campusList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/college/{campCode}", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext getCollegeByCampus(@PathVariable("campCode") String campCode)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        List<College> collegeList = commonService.retrieveCollegeByCampus(campCode);

        String json = jacksonObjectMapper.writeValueAsString(collegeList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(collegeList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/general/department/{admsNo}/{collCode}", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext retrieveGeneralDepartmentByAdmsColl(@PathVariable("admsNo") String admsNo,
                                                      @PathVariable("collCode") String collCode)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        ParamForSetupCourses paramForSetupCourses = new ParamForSetupCourses();
        paramForSetupCourses.setAdmsNo(admsNo);
        paramForSetupCourses.setCollCode(collCode);
        List<CodeNameDepartment> codeNameDepartmentList = commonService.retrieveGeneralDepartmentByAdmsColl(paramForSetupCourses);

        String json = jacksonObjectMapper.writeValueAsString(codeNameDepartmentList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(codeNameDepartmentList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/general/course/{admsNo}/{deptCode}", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext retrieveGeneralCourseByAdmsDept(@PathVariable("admsNo") String admsNo,
                                       @PathVariable("deptCode") String deptCode)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        ParamForSetupCourses paramForSetupCourses = new ParamForSetupCourses();
        paramForSetupCourses.setAdmsNo(admsNo);
        paramForSetupCourses.setDeptCode(deptCode);
        List<CodeNameCourse> codeNameCourseList = commonService.retrieveGeneralCourseByAdmsDept(paramForSetupCourses);

        String json = jacksonObjectMapper.writeValueAsString(codeNameCourseList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(codeNameCourseList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/general/detailMajor/{admsNo}/{deptCode}/{corsTypeCode}", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext retrieveGeneralDetailMajorByAdmsDeptCors(@PathVariable("admsNo") String admsNo,
                                                  @PathVariable("deptCode") String deptCode,
                                                  @PathVariable("corsTypeCode") String corsTypeCode)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        ParamForSetupCourses paramForSetupCourses = new ParamForSetupCourses();
        paramForSetupCourses.setAdmsNo(admsNo);
        paramForSetupCourses.setDeptCode(deptCode);
        paramForSetupCourses.setCorsTypeCode(corsTypeCode);
        List<CodeNameDetailMajor> codeNameDetailMajorList = commonService.retrieveGeneralDetailMajorByAdmsDeptCors(paramForSetupCourses);

        String json = jacksonObjectMapper.writeValueAsString(codeNameDetailMajorList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(codeNameDetailMajorList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/commission/course/{admsNo}/{deptCode}", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext retrieveCommissionCourseByAdmsDept(@PathVariable("admsNo") String admsNo,
                                                     @PathVariable("deptCode") String deptCode)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        ParamForSetupCourses paramForSetupCourses = new ParamForSetupCourses();
        paramForSetupCourses.setAdmsNo(admsNo);
        paramForSetupCourses.setDeptCode(deptCode);
        List<CodeNameCourse> codeNameCourseList = commonService.retrieveCommissionCourseByAdmsDept(paramForSetupCourses);

        String json = jacksonObjectMapper.writeValueAsString(codeNameCourseList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(codeNameCourseList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/ariInst", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext retrieveAriInst()
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        List<AcademyResearchIndustryInstitution> academyResearchIndustryInstitutionList = commonService.retrieveAriInst();

        String json = jacksonObjectMapper.writeValueAsString(academyResearchIndustryInstitutionList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(academyResearchIndustryInstitutionList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/ariInst/department/{admsNo}/{ariInstCode}", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext retrieveAriInstDepartmentByAdmsAriInst(@PathVariable("admsNo") String admsNo,
                                                     @PathVariable("ariInstCode") String ariInstCode)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        ParamForSetupCourses paramForSetupCourses = new ParamForSetupCourses();
        paramForSetupCourses.setAdmsNo(admsNo);
        paramForSetupCourses.setAriInstCode(ariInstCode);
        List<CodeNameDepartment> codeNameDepartmentList = commonService.retrieveAriInstDepartmentByAdmsAriInst(paramForSetupCourses);

        String json = jacksonObjectMapper.writeValueAsString(codeNameDepartmentList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(codeNameDepartmentList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/ariInst/course/{admsNo}/{deptCode}/{ariInstCode}", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext retrieveAriInstCourseByAdmsDeptAriInst(@PathVariable("admsNo") String admsNo,
                                                         @PathVariable("deptCode") String deptCode,
                                                         @PathVariable("ariInstCode") String ariInstCode)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        ParamForSetupCourses paramForSetupCourses = new ParamForSetupCourses();
        paramForSetupCourses.setAdmsNo(admsNo);
        paramForSetupCourses.setDeptCode(deptCode);
        paramForSetupCourses.setAriInstCode(ariInstCode);
        List<CodeNameCourse> codeNameDepartmentList = commonService.retrieveAriInstCourseByAdmsDeptAriInst(paramForSetupCourses);

        String json = jacksonObjectMapper.writeValueAsString(codeNameDepartmentList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(codeNameDepartmentList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/ariInst/detailMajor/{admsNo}/{deptCode}/{ariInstCode}/{corsTypeCode}", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext retrieveAriInstDetailMajorByAdmsDeptAriInst(@PathVariable("admsNo") String admsNo,
                                                         @PathVariable("deptCode") String deptCode,
                                                         @PathVariable("ariInstCode") String ariInstCode,
                                                         @PathVariable("corsTypeCode") String corsTypeCode)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        ParamForSetupCourses paramForSetupCourses = new ParamForSetupCourses();
        paramForSetupCourses.setAdmsNo(admsNo);
        paramForSetupCourses.setDeptCode(deptCode);
        paramForSetupCourses.setAriInstCode(ariInstCode);
        paramForSetupCourses.setCorsTypeCode(corsTypeCode);
        List<CodeNameDetailMajor> codeNameDepartmentList = commonService.retrieveAriInstDetailMajorByAdmsDeptAriInst(paramForSetupCourses);

        String json = jacksonObjectMapper.writeValueAsString(codeNameDepartmentList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(codeNameDepartmentList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/country/{cntr}", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext retrieveCountryByKeyword(@PathVariable("cntr") String cntr)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        List<Country> countryList = commonService.retrieveCountryByName(cntr);
        String json = jacksonObjectMapper.writeValueAsString(countryList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(countryList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/school/{schl}", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext retrieveSchoolByKeyword(@PathVariable("schl") String schl)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {
        List<School> schoolList = commonService.retrieveSchoolByName(schl);
        String json = jacksonObjectMapper.writeValueAsString(schoolList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(schoolList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }

    @RequestMapping(value="/code/codeGroup/{codeGrp}", method= RequestMethod.GET)
    @ResponseBody
    public ExecutionContext retrieveCodeValueByCodeGroup(@PathVariable("codeGrp") String codeGrp)
            throws NoSuchAlgorithmException, JsonProcessingException, UnsupportedEncodingException {

        List<CommonCode> commonCodeList = commonService.retrieveCommonCodeValueByCodeGroup(codeGrp);
        String json = jacksonObjectMapper.writeValueAsString(commonCodeList);

        ExecutionContext executionContext = new ExecutionContext();
        if (!(commonCodeList.size() > 0)) {
            executionContext.setMessage(messageResolver.getMessage("U300"));
        }
        executionContext.setData(json);

        return executionContext;
    }
}
