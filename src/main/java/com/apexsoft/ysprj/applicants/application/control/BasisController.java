package com.apexsoft.ysprj.applicants.application.control;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.application.service.BasisService;
import com.apexsoft.ysprj.applicants.application.validator.BasisValidator;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
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

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String TARGET_VIEW = "application/basis";

    /**
     * 기본정보 최초 작성 및 수정 화면
     *
     * @return
     */
    @RequestMapping(value="/edit")
    public ModelAndView getBasis(@ModelAttribute Basis basis,
                                 BindingResult bindingResult,
                                 ModelAndView mv) {
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) return mv;

        ExecutionContext ec = removeHyphen(basisService.retrieveBasis(basis));
        try {
            ec = processForeignPersonalInfo(ec);
        } catch (IOException e) {
            ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U347"));
            ec.setErrCode("ERR0043");
            Map<String, Object> errMap = new HashMap<String, Object>();
            errMap.put("applNo", basis.getApplication().getApplNo());
            errMap.put("situation", "Error while loading props for En/Decryption");
            ec.setErrorInfo(new ErrorInfo(errMap));
            throw new YSBizException(ec);
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
                                  ModelAndView mv) {
        basisValidator.validate(formData, bindingResult);
        mv.setViewName(TARGET_VIEW);
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
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
        String userId = principal.getName();
        Application application = formData.getApplication();
        application.setUserId(userId);

        String applStsCode = application.getApplStsCode();
        if (applStsCode == null || applStsCode.trim().length() == 0) {
            try {
                String rgstLatter = application.getRgstEncr();
                application.setRgstHash(getSha256(application.getRgstBornDate() + rgstLatter));
                application.setRgstEncr((rgstLatter != null && !StringUtil.EMPTY_STRING.equals(rgstLatter)) ? getEncryptedString(rgstLatter, true) : StringUtil.EMPTY_STRING);
            } catch (IOException e) {
                ec = new ExecutionContext(ExecutionContext.FAIL);
                ec.setMessage(messageResolver.getMessage("U316"));
                ec.setErrCode("ERR0043");
                Map<String, Object> errMap = new HashMap<String, Object>();
                errMap.put("applNo", application.getApplNo());
                errMap.put("situation", "Error while loading props for En/Decryption");
                ec.setErrorInfo(new ErrorInfo(errMap));
                throw new YSBizException(ec);
            }
        }

        try {
            if ("C".equals(application.getAdmsTypeCode()) || "D".equals(application.getAdmsTypeCode())) {
                ApplicationForeigner applicationForeigner = formData.getApplicationForeigner();
                String fornRgstNo = applicationForeigner.getFornRgstNo();
                String paspNo = applicationForeigner.getPaspNo();
                String visaNo = applicationForeigner.getVisaNo();

                applicationForeigner.setFornRgstNoEncr((fornRgstNo != null && !StringUtil.EMPTY_STRING.equals(fornRgstNo)) ? getEncryptedString(fornRgstNo, true) : StringUtil.EMPTY_STRING);
                applicationForeigner.setPaspNoEncr((paspNo != null && !StringUtil.EMPTY_STRING.equals(paspNo)) ? getEncryptedString(paspNo, true) : StringUtil.EMPTY_STRING);
                applicationForeigner.setVisaNoEncr((visaNo != null && !StringUtil.EMPTY_STRING.equals(visaNo)) ? getEncryptedString(visaNo, true) : StringUtil.EMPTY_STRING);
            }
        } catch (IOException e) {
            ec = new ExecutionContext(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U316"));
            ec.setErrCode("ERR0043");
            Map<String, Object> errMap = new HashMap<String, Object>();
            errMap.put("applNo", application.getApplNo());
            errMap.put("situation", "Error while loading props for En/Decryption");
            ec.setErrorInfo(new ErrorInfo(errMap));
            throw new YSBizException(ec);
        }

        ec = basisService.saveBasis(formData);

        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            ExecutionContext ecRetrieve = removeHyphen(basisService.retrieveBasis(formData));
            ExecutionContext ecRetrieveEncr = null;
            try {
                ecRetrieveEncr = processForeignPersonalInfo(ecRetrieve);
            } catch (IOException e) {
                ecRetrieveEncr = new ExecutionContext(ExecutionContext.FAIL);
                ecRetrieveEncr.setMessage(messageResolver.getMessage("U347"));
                ecRetrieveEncr.setErrCode("ERR0043");
                Map<String, Object> errMap = new HashMap<String, Object>();
                errMap.put("applNo", formData.getApplication().getApplNo());
                errMap.put("situation", "Error while loading props for En/Decryption");
                ec.setErrorInfo(new ErrorInfo(errMap));
                throw new YSBizException(ecRetrieveEncr);
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

        return mv;
    }

    /**
     * 지원 취소
     *
     * @param formData
     * @param principal
     * @return
     */
    @RequestMapping(value="/cancel", method = RequestMethod.POST)
    public ModelAndView cancelBasis(@ModelAttribute Basis formData,
                                  Principal principal,
                                  BindingResult bindingResult,
                                  ModelAndView mv) {

        mv.setViewName("application/mylist");
        if (bindingResult.hasErrors()) {
            mv.addObject("resultMsg", messageResolver.getMessage("U334"));
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
        String userId = principal.getName();
        Application application = formData.getApplication();
        application.setUserId(userId);

        ec = basisService.saveBasis(formData);

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

    private ExecutionContext processForeignPersonalInfo(ExecutionContext result) throws IOException {
        Map<String, Object> map = (Map<String, Object>)result.getData();
        Basis basis = (Basis)map.get("basis");

        processForeignPersonalInfo(basis);

        return result;
    }

    private void processForeignPersonalInfo(Basis basis) throws IOException {
        ApplicationForeigner applicationForeigner = basis.getApplicationForeigner();
        String fornRgstNoEncr = applicationForeigner.getFornRgstNoEncr();
        String paspNoEncr = applicationForeigner.getPaspNoEncr();
        String visaNoEncr = applicationForeigner.getVisaNoEncr();

        applicationForeigner.setFornRgstNo(fornRgstNoEncr != null ? getEncryptedString(fornRgstNoEncr, false) : StringUtil.EMPTY_STRING);
        applicationForeigner.setPaspNo(paspNoEncr != null ? getEncryptedString(paspNoEncr, false) : StringUtil.EMPTY_STRING);
        applicationForeigner.setVisaNo(visaNoEncr != null ? getEncryptedString(visaNoEncr, false) : StringUtil.EMPTY_STRING);
    }

    private String getSha256(String input) {
        String sha256 = shaPasswordEncoder.encodePassword(input, "");
        return sha256;
    }

    private String getEncryptedString(String input, boolean isEncrypt) throws IOException {
        Properties prop = new Properties();
        InputStream is = context.getResourceAsStream("WEB-INF/grad-ks");
        String result = null;

        try {
            prop.load(is);
            TextEncryptor textEncryptor = Encryptors.queryableText(prop.getProperty("ENC_PSWD"), prop.getProperty("ENC_SALT"));
            result = isEncrypt ? textEncryptor.encrypt(input) : textEncryptor.decrypt(input);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new YSBizException(e);
            }
        }
        return result;
    }

    private String getDecryptedString(String input) throws IOException {
        Properties prop = new Properties();
        InputStream is = context.getResourceAsStream("WEB-INF/grad-ks");
        String decrypted = null;

        try {
            prop.load(is);
            TextEncryptor textEncryptor = Encryptors.queryableText(prop.getProperty("ENC_PSWD"), prop.getProperty("ENC_SALT"));
            decrypted = textEncryptor.decrypt(input);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new YSBizException(e);
            }
        }
        return decrypted;
    }
}
