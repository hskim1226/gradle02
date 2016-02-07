package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.BasisService;
import com.apexsoft.ysprj.applicants.application.validator.BasisValidator;
import com.apexsoft.ysprj.applicants.common.util.CryptoUtil;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import com.apexsoft.ysprj.applicants.common.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by hanmomhanda on 15. 1. 9.
 *
 * 원서 기본 정보 컨트롤러
 */
@Controller
@RequestMapping(value="/application/basis")
public class BasisController {

    @Autowired
    private BasisService basisService;

    @Autowired
    private BasisValidator basisValidator;

    @Autowired
    private ServletContext context;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @Autowired
    WebUtil webUtil;

    private final String TARGET_VIEW = "application/basis";

    /**
     * 기본정보 최초 작성 및 수정 화면
     *
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView getBasis(@ModelAttribute Basis basis,
                                 BindingResult bindingResult,
                                 HttpServletRequest request,
                                 Principal principal,
                                 ModelAndView mv) {
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) return mv;

        webUtil.blockGetMethod(request, basis.getApplication());

        mv.addObject("isSYSADMIN", "Apex1234".equals(principal.getName()));

        ExecutionContext ec = removeHyphen(basisService.retrieveBasis(basis));
        try {
            ec = processForeignPersonalInfo(ec);
        } catch (IOException e) {
            exceptionThrower("U347", "ERR0043", basis.getApplication());
        }

        Map<String, Object> map = (Map<String, Object>)ec.getData();
        addObjectToMV(mv, map, ec);

        return mv;
    }

    /**
     * 기본 정보 저장
     *
     * @param formData
     * @param principal
     * @return
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView saveBasis(@ModelAttribute Basis formData,
                                  Principal principal,
                                  BindingResult bindingResult,
                                  HttpServletRequest request,
                                  ModelAndView mv) {
        webUtil.blockGetMethod(request, formData.getApplication());

        basisValidator.validate(formData, bindingResult);
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", MessageResolver.getMessage("U334"));
            ExecutionContext ecRetrieve = basisService.retrieveSelectionMap(formData);
            if (ExecutionContext.SUCCESS.equals(ecRetrieve.getResult())) {
                Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
                mv.addAllObjects(map);
                removeHyphen(formData);
            } else {
                mv = getErrorMV("common/error", ecRetrieve);
            }
            return mv;
        }

        ExecutionContext ec;

        Application application = formData.getApplication();
        application.setModId(application.getUserId());

        applyEncryption(application);
        applyEncryptionForeigner(formData, application);

        ec = basisService.saveBasis(formData);

        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            ExecutionContext ecRetrieve = removeHyphen(basisService.retrieveBasis(formData));
            ExecutionContext ecRetrieveEncr = null;
            try {
                ecRetrieveEncr = processForeignPersonalInfo(ecRetrieve);
            } catch (IOException e) {
                exceptionThrower("U347", "ERR0043", formData.getApplication());
            }
            if (ExecutionContext.SUCCESS.equals(ecRetrieve.getResult()) && ExecutionContext.SUCCESS.equals(ecRetrieveEncr.getResult())) {
                Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
                addObjectToMV(mv, map, ec);
            } else {
                mv = getErrorMV("common/error", ExecutionContext.FAIL.equals(ecRetrieve.getResult()) ? ecRetrieve : ecRetrieveEncr);
            }
        } else {
            mv = getErrorMV("common/error", ec);
        }
        mv.addObject("isSYSADMIN", "Apex1234".equals(principal.getName()));
        return mv;
    }

    private void applyEncryptionForeigner(@ModelAttribute Basis formData, Application application) {

        try {
            if (application.isForeignAppl()) {
                ApplicationForeigner applicationForeigner = formData.getApplicationForeigner();
                String fornRgstNo = applicationForeigner.getFornRgstNo();
                String paspNo = applicationForeigner.getPaspNo();
                String visaNo = applicationForeigner.getVisaNo();

                applicationForeigner.setFornRgstNoEncr((fornRgstNo != null && !StringUtil.EMPTY_STRING.equals(fornRgstNo)) ? CryptoUtil.getCryptedString(context, fornRgstNo, true) : StringUtil.EMPTY_STRING);
                applicationForeigner.setPaspNoEncr((paspNo != null && !StringUtil.EMPTY_STRING.equals(paspNo)) ? CryptoUtil.getCryptedString(context, paspNo, true) : StringUtil.EMPTY_STRING);
                applicationForeigner.setVisaNoEncr((visaNo != null && !StringUtil.EMPTY_STRING.equals(visaNo)) ? CryptoUtil.getCryptedString(context, visaNo, true) : StringUtil.EMPTY_STRING);
            }
        } catch (IOException e) {
            exceptionThrower("U316", "ERR0043", application);
        }
    }

    private void applyEncryption(Application application) {

        String applStsCode = application.getApplStsCode();
        if (applStsCode == null || applStsCode.trim().length() == 0) {
            try {
                String rgstLatter = application.getRgstEncr();
                application.setRgstHash(getSha256(application.getRgstBornDate() + rgstLatter));
                application.setRgstEncr((rgstLatter != null && !StringUtil.EMPTY_STRING.equals(rgstLatter)) ? CryptoUtil.getCryptedString(context, rgstLatter, true) : StringUtil.EMPTY_STRING);
            } catch (IOException e) {
                exceptionThrower("U316", "ERR0043", application);
            }
        }
    }

    /**
     * 지원 취소
     * 지원 취소 후 내 원서 화면으로 이동
     *
     * @param formData
     * @param principal
     * @return
     */
    @RequestMapping(value="/cancel", method = RequestMethod.POST)
    public ModelAndView cancelBasis(@ModelAttribute Basis formData,
                                  Principal principal,
                                  BindingResult bindingResult,
                                    HttpServletRequest request,
                                  ModelAndView mv) {
        webUtil.blockGetMethod(request, formData.getApplication());

        mv.setViewName("application/mylist");
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", MessageResolver.getMessage("U334"));
            ExecutionContext ecRetrieve = basisService.retrieveBasis(formData);
            if (ecRetrieve.getResult().equals(ExecutionContext.SUCCESS)) {
                Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();                
                mv.addAllObjects(map);
            } else {
                mv = getErrorMV("common/error", ecRetrieve);
            }
            return mv;
        }

        ExecutionContext ec;
//        String userId = principal.getName();
        Application application = formData.getApplication();
//        application.setUserId(userId);
//        application.setModId(userId);

        ec = basisService.cancelBasis(formData);

        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            ExecutionContext ecApplicationByUserId;
            ParamForApplication p = new ParamForApplication();
            p.setUserId(principal.getName());

            ecApplicationByUserId = basisService.retrieveInfoListByParamObj(p, "CustomApplicationMapper.selectApplByUserId", CustomMyList.class);

            if (ExecutionContext.SUCCESS.equals(ecApplicationByUserId.getResult())) {
                mv.addObject("myList", ecApplicationByUserId.getData());
            } else {
                mv = getErrorMV("common/error", ecApplicationByUserId);
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
        mv.addAllObjects(map);
        mv.addObject("resultMsg", ec.getMessage());
    }

    /**
     * 전화번호 류 하이픈 제거
     */
    private ExecutionContext removeHyphen(ExecutionContext result) {
        Map<String, Object> map = (Map<String, Object>)result.getData();
        Basis basis = (Basis)map.get("basis");

        removeHyphen(basis);

        return result;
    }

    /**
     * 전화번호 류 하이픈 제거
     * @param basis
     */
    private void removeHyphen(Basis basis) {
        Application application = basis.getApplication();
        application.setFaxNum(StringUtil.removeHyphen(application.getFaxNum()));
        application.setTelNum(StringUtil.removeHyphen(application.getTelNum()));
        application.setMobiNum(StringUtil.removeHyphen(application.getMobiNum()));
        application.setRgstNo(StringUtil.removeHyphen(application.getRgstNo()));

        ApplicationForeigner applicationForeigner = basis.getApplicationForeigner();
        if (applicationForeigner != null) {
            applicationForeigner.setHomeTel(StringUtil.removeHyphen(applicationForeigner.getHomeTel()));
            applicationForeigner.setHomeEmrgTel(StringUtil.removeHyphen(applicationForeigner.getHomeEmrgTel()));
            applicationForeigner.setKorEmrgTel(StringUtil.removeHyphen(applicationForeigner.getKorEmrgTel()));
            applicationForeigner.setFornRgstNo(StringUtil.removeHyphen(applicationForeigner.getFornRgstNo()));
        }

        ApplicationGeneral applicationGeneral = basis.getApplicationGeneral();
        if (applicationGeneral != null) {
            applicationGeneral.setEmerContTel(StringUtil.removeHyphen(applicationGeneral.getEmerContTel()));
        }
    }

    /**
     * 외국인 전형 지원자 외국인등록번호, 여권번호, 비자번호 암호화 적용
     * @param result
     * @return
     * @throws IOException
     */
    private ExecutionContext processForeignPersonalInfo(ExecutionContext result) throws IOException {
        Map<String, Object> map = (Map<String, Object>)result.getData();
        Basis basis = (Basis)map.get("basis");

        processForeignPersonalInfo(basis);

        return result;
    }

    /**
     * 외국인 전형 지원자 외국인등록번호, 여권번호, 비자번호 암호화 적용
     * @param basis
     * @throws IOException
     */
    private void processForeignPersonalInfo(Basis basis) throws IOException {
        ApplicationForeigner applicationForeigner = basis.getApplicationForeigner();
        String fornRgstNoEncr = applicationForeigner.getFornRgstNoEncr();
        String paspNoEncr = applicationForeigner.getPaspNoEncr();
        String visaNoEncr = applicationForeigner.getVisaNoEncr();

        applicationForeigner.setFornRgstNo(fornRgstNoEncr != null && !StringUtil.EMPTY_STRING.equals(fornRgstNoEncr) ? CryptoUtil.getCryptedString(context, fornRgstNoEncr, false) : StringUtil.EMPTY_STRING);
        applicationForeigner.setPaspNo(paspNoEncr != null && !StringUtil.EMPTY_STRING.equals(paspNoEncr) ? CryptoUtil.getCryptedString(context, paspNoEncr, false) : StringUtil.EMPTY_STRING);
        applicationForeigner.setVisaNo(visaNoEncr != null && !StringUtil.EMPTY_STRING.equals(visaNoEncr) ? CryptoUtil.getCryptedString(context, visaNoEncr, false) : StringUtil.EMPTY_STRING);
    }

    /**
     * 해쉬 값 반환
     * @param input
     * @return
     */
    private String getSha256(String input) {
        String sha256 = shaPasswordEncoder.encodePassword(input, "");
        return sha256;
    }

    /**
     * 암호화 관련 예외 발생 시 예외 정보 처리
     *
     * @param msgCode
     * @param errCode
     * @param application
     * @throws YSBizException
     */
    private void exceptionThrower(String msgCode, String errCode, Application application) throws YSBizException {
        ExecutionContext ec;
        ec = new ExecutionContext(ExecutionContext.FAIL);
        ec.setMessage(MessageResolver.getMessage(msgCode));
        ec.setErrCode(errCode);
        Map<String, String> errMap = new HashMap<>();
        errMap.put("applNo", String.valueOf(application.getApplNo()));
        errMap.put("situation", "Error while loading props for En/Decryption");
        ec.setErrorInfo(new ErrorInfo(errMap));
        throw new YSBizException(ec);
    }
}
