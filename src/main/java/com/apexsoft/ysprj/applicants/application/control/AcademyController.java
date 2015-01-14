package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 12.
 *
 * 원서 학력 정보 컨트롤러
 */
@Controller
@RequestMapping(value="/application/academy")
public class AcademyController {

    @Autowired
    private AcademyService academyService;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String APP_INFO_SAVED = "00001";
    private final String ACAD_SAVED = "00002";

    private final String TARGET_VIEW = "application/academy";

    /**
     * 학력 정보를 조회해서 학력 정보 화면에 뿌려질 데이터 구성
     *
     * @param applicationIdentifier
     * @return
     */
    private ExecutionContext setupAcademy(ApplicationIdentifier applicationIdentifier) {
        ExecutionContext ec;

        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Academy academy;

        int applNo = applicationIdentifier.getApplNo();
        String admsNo = applicationIdentifier.getAdmsNo();
        String entrYear = applicationIdentifier.getEntrYear();
        String admsTypeCode = applicationIdentifier.getAdmsTypeCode();

        if (applNo > 0) {
            ec = academyService.retrieveAcademy(applNo);
            academy = (Academy)ec.getData();
        } else {
            ec = new ExecutionContext();
            academy = new Academy();
            Application application = new Application();
            application.setAdmsNo(admsNo);
            application.setEntrYear(entrYear);
            application.setAdmsTypeCode(admsTypeCode);
            List<CustomApplicationAcademy> collegeList = new ArrayList<CustomApplicationAcademy>();
            List<CustomApplicationAcademy> graduateList = new ArrayList<CustomApplicationAcademy>();
            academy.setApplication(application);
            academy.setCollegeList(collegeList);
            academy.setGraduateList(graduateList);
        }

        ecDataMap.put("academy", academy);
        ec.setData(ecDataMap);

        return ec;
    }

    /**
     * 학력 정보 최초작성/수정 화면
     *
     * @param model
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView getAcademy(@ModelAttribute Academy model) {
        ModelAndView mv = new ModelAndView(TARGET_VIEW);

        Application application = model.getApplication();
        int applNo = application.getApplNo();
        String admsNo = application.getAdmsNo();
        String entrYear = application.getEntrYear();
        String admsTypeCode = application.getAdmsTypeCode();

        ExecutionContext ec = setupAcademy(new ApplicationIdentifier(applNo, admsNo, entrYear, admsTypeCode));
        Map<String, Object> map = (Map<String, Object>)ec.getData();
        addObjectToMV(mv, map, ec);

        return mv;
    }

    /**
     * 학력 정보 저장
     *
     * @param academy
     * @param principal
     * @return
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView saveAcademy(@ModelAttribute Academy academy,
                                    Principal principal,
                                    HttpServletRequest request) {
        ModelAndView mv = new ModelAndView(TARGET_VIEW);
        List<Object> acadSeqList = new ArrayList<Object>();

        Map map = request.getParameterMap();
        Set<Map.Entry> set = map.entrySet();

        for( Map.Entry entry : set) {

            String key = entry.getKey().toString();

            if ((key.startsWith("collegeList") || key.startsWith("graduateList")) && key.endsWith("acadSeq")) {
                Object strAcadSeq = request.getParameter(key);
                acadSeqList.add(strAcadSeq);
            }
        }

        ExecutionContext ec = null;
        String userId = principal.getName();

        Application application = academy.getApplication();
        int applNo = application.getApplNo();
        application.setUserId(userId);
        application.setModId(userId);

        academy.setCollegeList(preProcessAcadList(academy.getCollegeList(), acadSeqList, userId, applNo));
        academy.setGraduateList(preProcessAcadList(academy.getGraduateList(), acadSeqList, userId, applNo));

        if (academy.getApplication().getApplStsCode().equals(APP_INFO_SAVED)) { //insert
            ec = academyService.createAcademy(application,
                    academy.getCollegeList(),
                    academy.getGraduateList());
        } else { //update
            ec = academyService.updateAcademy(application,
                    academy.getCollegeList(),
                    academy.getGraduateList());
        }

        if (ec.getResult().equals(ExecutionContext.SUCCESS)) {
            ApplicationIdentifier data = (ApplicationIdentifier)ec.getData();
            ExecutionContext ecSetupAcademy = setupAcademy(data);

            if (ecSetupAcademy.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> setupMap = (Map<String, Object>)ecSetupAcademy.getData();
                addObjectToMV(mv, setupMap, ec);
            } else {
                mv = getErrorMV("common/error", ecSetupAcademy);
            }
        } else {
            mv = getErrorMV("common/error", ec);
        }

//        return getEntireInfo(data.getApplNo(), application.getAdmsNo(),
//                application.getEntrYear(), application.getAdmsTypeCode(), "application/appinfo",
//                entireApplication);

        return mv;
    }

    /**
     * acadSeq를 통해 화면의 학력 내용에서 C,U,D 할 목록을 추려내서 서비스 계층에 전달
     *
     * @param academyList
     * @param acadSeqList
     * @param userId
     * @return
     */
    private List<CustomApplicationAcademy> preProcessAcadList(List<CustomApplicationAcademy> academyList,
                                                              List<Object> acadSeqList,
                                                              String userId,
                                                              int applNo) {
        for ( int i = 0 ; i < academyList.size() ; i++ ) {
            CustomApplicationAcademy aa = academyList.get(i);
            aa.setApplNo(applNo);
            boolean toBeRemoved = true;
            for (Object acadSeq : acadSeqList) {
                if ( aa.getAcadSeq() != null && aa.getAcadSeq().toString().equals(acadSeq) ) {
                    toBeRemoved = false;
                    aa.setModId(userId);
                } else if (aa.getAcadSeq() == null && aa.getSchlCode() != null && aa.getSchlCode().length() > 0) {
                    toBeRemoved = false;
                    aa.setCreId(userId);
                }
            }
            if (toBeRemoved) academyList.remove(aa);
        }
        return academyList;
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
        mv.addObject("academy", map.get("academy"));
        mv.addObject("resultMsg", ec.getMessage());
    }
}
