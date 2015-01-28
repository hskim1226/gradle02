package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.admission.domain.ParamForAdmissionCourseMajor;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 1. 26.
 *
 * 원서 어학/경력 정보 컨트롤러
 */
@Controller
@RequestMapping(value="/application/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private CommonService commonService;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String TARGET_VIEW = "application/document";

//    /**
//     * 제출 문서 정보를 조회해서 파일 첨부 화면에 뿌려질 데이터 구성
//     *
//     * @param applicationIdentifier
//     * @return
//     */
//    private ExecutionContext setupDocument(ApplicationIdentifier applicationIdentifier) {
//        ExecutionContext ec;
//
//        Map<String, Object> ecDataMap = new HashMap<String, Object>();
//        Map<String, Object> commonCodeMap = new HashMap<String, Object>();
//        Document document;
//
//        int applNo = applicationIdentifier.getApplNo();
//        String admsNo = applicationIdentifier.getAdmsNo();
//        String entrYear = applicationIdentifier.getEntrYear();
//        String admsTypeCode = applicationIdentifier.getAdmsTypeCode();
//
//        ec = documentService.retrieveDocument(applNo);
//        document = (Document)ec.getData();
//        Application application = document.getApplication();
//
//        ParamForAdmissionCourseMajor param = new ParamForAdmissionCourseMajor();
//        param.setAdmsNo(admsNo);
//        param.setDeptCode(application.getDeptCode());
//        param.setCorsTypeCode(application.getCorsTypeCode());
//        param.setDetlMajCode(application.getDetlMajCode());
//
//        ecDataMap.put("document", document);
//        ecDataMap.put("common", commonCodeMap);
//        ec.setData(ecDataMap);
//
//        return ec;
//    }

    /**
     * 어학/경력 정보 최초작성/수정 화면
     *
     * @param formData
     * @return
     */
    /**
     * 첨부 파일 최초 작성 및 수정 화면
     *
     * @param formData
     * @param bindingResult
     * @param mv
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView getDocument(@ModelAttribute Document formData,
                                    BindingResult bindingResult,
                                    ModelAndView mv) {
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) return mv;

        ExecutionContext ec = documentService.retrieveDocument(formData);

        Map<String, Object> map = (Map<String, Object>)ec.getData();
        addObjectToMV(mv, map, ec);

        return mv;
    }

    /**
     * 첨부 파일 정보 저장
     *
     * @param formData
     * @param principal
     * @param bindindResult
     * @param mv
     * @return
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView saveDocument(@ModelAttribute Document formData,
                                     Principal principal,
                                     BindingResult bindindResult,
                                     ModelAndView mv) {
        mv.setViewName(TARGET_VIEW);
        if (bindindResult.hasErrors()) return mv;

        ExecutionContext ec = null;
        String userId = principal.getName();

        Application application = formData.getApplication();
        int applNo = application.getApplNo();
        application.setUserId(userId);
        application.setModId(userId);

//        List<CustomApplicationExperience> exprList = formData.getApplicationExperienceList();

        ec = documentService.saveDocument(formData);

        if (ec.getResult().equals(ExecutionContext.SUCCESS)) {
            ExecutionContext ecRetrieve = documentService.retrieveDocument(formData);

            if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> setupMap = (Map<String, Object>)ecRetrieve.getData();
                addObjectToMV(mv, setupMap, ec);
            } else {
                mv = getErrorMV("common/error", ecRetrieve);
            }
        } else {
            mv = getErrorMV("common/error", ec);
        }

        return mv;
    }

    /**
     * 에러 발생 시 ExecutionContext를 model에 넣고 에러 페이지로 전달
     *
     * @param errorViewName
     * @param ec
     * @return
     */
    private ModelAndView getErrorMV(String errorViewName, ExecutionContext ec) {
        ModelAndView mv = new ModelAndView(errorViewName);
        mv.addObject("ec", ec);
        return mv;
    }

    /**
     * ModelAndView에 데이터 추가
     *
     * @param mv
     * @param map
     * @param ec
     */
    private void addObjectToMV(ModelAndView mv, Map<String, Object> map, ExecutionContext ec) {
        mv.addObject("document", map.get("document"));
        mv.addObject("resultMsg", ec.getMessage());
    }
}
