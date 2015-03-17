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
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.plugin2.message.Message;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ContentHandler;
import java.security.*;
import java.security.cert.CertificateException;
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
                application.setRgstEncr(getEncryptedString(application.getRgstEncr()));
            } catch (IOException e) {
                ec = new ExecutionContext(ExecutionContext.FAIL);
                Map<String, Object> errMap = new HashMap<String, Object>();
                errMap.put("applNo", application.getApplNo());
                errMap.put("situation", "thrown when encryption");
                ec.setErrorInfo(new ErrorInfo(errMap));
                throw new YSBizException(ec);
            }
        }

        ec = basisService.saveBasis(formData);

        if (ExecutionContext.SUCCESS.equals(ec.getResult())) {
            ExecutionContext ecRetrieve = removeHyphen(basisService.retrieveBasis(formData));
            if (ExecutionContext.SUCCESS.equals(ecRetrieve.getResult())) {
                Map<String, Object> map = (Map<String, Object>)ecRetrieve.getData();
                addObjectToMV(mv, map, ec);
            } else {
                mv = getErrorMV("common/error", ecRetrieve);
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

        Application application = basis.getApplication();
        application.setFaxNum(StringUtil.removeHyphen(application.getFaxNum()));
        application.setTelNum(StringUtil.removeHyphen(application.getTelNum()));
        application.setMobiNum(StringUtil.removeHyphen(application.getMobiNum()));
        application.setRgstNo(StringUtil.removeHyphen(application.getRgstNo()));

        ApplicationForeigner applicationForeigner = basis.getApplicationForeigner();
        applicationForeigner.setHomeTel(StringUtil.removeHyphen(applicationForeigner.getHomeTel()));
        applicationForeigner.setHomeEmrgTel(StringUtil.removeHyphen(applicationForeigner.getHomeEmrgTel()));
        applicationForeigner.setKorEmrgTel(StringUtil.removeHyphen(applicationForeigner.getKorEmrgTel()));
        applicationForeigner.setFornRgstNo(StringUtil.removeHyphen(applicationForeigner.getFornRgstNo()));

        ApplicationGeneral applicationGeneral = basis.getApplicationGeneral();
        applicationGeneral.setEmerContTel(StringUtil.removeHyphen(applicationGeneral.getEmerContTel()));

        return result;
    }

    private String getSha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new YSBizException(e);
        } catch (UnsupportedEncodingException e) {
            throw new YSBizException(e);
        }
    }

    private String getEncryptedString(String input) throws IOException {
        Properties prop = new Properties();
        InputStream is = context.getResourceAsStream("WEB-INF/grad-ks");
        String encrypted = null;

        try {
            prop.load(is);
            TextEncryptor textEncryptor = Encryptors.queryableText(prop.getProperty("ENC_PSWD"), prop.getProperty("ENC_SALT"));
            encrypted = textEncryptor.encrypt(input);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new YSBizException(e);
            }
        }
        return encrypted;
    }
}
