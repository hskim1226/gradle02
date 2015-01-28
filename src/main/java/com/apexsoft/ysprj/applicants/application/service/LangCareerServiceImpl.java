package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.admission.domain.ParamForAdmissionCourseMajor;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 13.
 */
@Service
public class LangCareerServiceImpl implements LangCareerService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    @Autowired
    private CommonService commonService;

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값
    private final String LANG_CAREER_SAVED = "00003";    // 어학/경력 저장

    @Override
    public ExecutionContext retrieveLangCareer(LangCareer langCareer) {
        ExecutionContext ec = new ExecutionContext();

        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> commonCodeMap = new HashMap<String, Object>();

        Application applicationFromUser = langCareer.getApplication();

        int applNo = applicationFromUser.getApplNo();

        Application applicationFromDB = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        langCareer.setApplication(applicationFromDB);

        ApplicationGeneral applicationGeneralFromDB = commonDAO.queryForObject(NAME_SPACE + "ApplicationGeneralMapper.selectByPrimaryKey",
                applNo, ApplicationGeneral.class);
        applicationGeneralFromDB = applicationGeneralFromDB == null ? new ApplicationGeneral() : applicationGeneralFromDB;
        langCareer.setApplicationGeneral(applicationGeneralFromDB);

        List<LanguageGroup> langGroupList = retrieveLanguageGroupListByApplNo(applNo);
        langCareer.setLanguageGroupList(setLangUserDataStatus(langGroupList, UserCUDType.UPDATE));

        List<CustomApplicationExperience> applicationExperienceList = retrieveInfoListByApplNo(applNo, "CustomApplicationExperienceMapper", CustomApplicationExperience.class);
        langCareer.setApplicationExperienceList(setExprUserDataStatus(applicationExperienceList, UserCUDType.UPDATE));

        commonCodeMap.put( "toflTypeList", commonService.retrieveCommonCodeValueByCodeGroup("TOFL_TYPE") );
        commonCodeMap.put( "fornExmpList", commonService.retrieveCommonCodeValueByCodeGroup("FORN_EXMP") );

        ecDataMap.put("langCareer", langCareer);
        ecDataMap.put("common", commonCodeMap);
        ec.setData(ecDataMap);

        return ec;
    }

    private List<LanguageGroup> retrieveLanguageGroupListByApplNo(int applNo) {

        List<LanguageGroup> langGroupList = null;

        langGroupList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectLanguageGroupByApplNo",
                                                applNo, LanguageGroup.class);
        if( langGroupList == null || langGroupList.size()==0){
            LanguageGroup aGroup  = new LanguageGroup();
            aGroup.setExamCodeGrp("LANG_EXAM");
            aGroup.setExamGrpName("영어");
            aGroup.setSelGrpCode("LANG_EXAM");
            aGroup.setExamCode("00001");
            langGroupList.add(aGroup);
        }
        ParamForTotalLang param = new ParamForTotalLang();
        List<TotalApplicationLanguage> aLangList;
        for (LanguageGroup alangGroup : langGroupList) {
            param.setApplNo(applNo);
            param.setSelGrpCode(alangGroup.getSelGrpCode());
            param.setUpCodeGrp(alangGroup.getExamCodeGrp());
            param.setUpCode(alangGroup.getExamCode());

            aLangList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalLanguageInfoByApplNo",
                                                param, TotalApplicationLanguage.class);

            for (TotalApplicationLanguage alang : aLangList) {
                if( alang.getLangSeq() != null && alang.getLangSeq() > 0 )
                    alang.setLangInfoSaveFg(true);
                else
                    alang.setLangInfoSaveFg(false);

                if( alang.getDocSeq() > 0 )
                    alang.setFileUploadFg(true);
                else
                    alang.setFileUploadFg(false);
            }
            alangGroup.setLangList(aLangList);
        }

        return langGroupList;
    }

    @Override
    public ExecutionContext saveLangCareer(LangCareer langCareer) {
        ExecutionContext ec = new ExecutionContext();

        int r0 = 0, insert = 0, update = 0, delete = 0;
        Application application = langCareer.getApplication();
        int applNo = application.getApplNo();

        List<LanguageGroup> langList = langCareer.getLanguageGroupList();
        List<CustomApplicationExperience> exprList = langCareer.getApplicationExperienceList();

        // TODO - dhoonkim - 해당 리스트로 서비스 호출하는 부분 작성 필요
        // 어학 정보는 화면에서 받아온 값과 DB의 값을 대조해서 insert, update, delete(?) 등 상태 분기
        // 경력 정보는 AcademyServiceImpl을 참고해서 작성


        // TODO - dhoonkim - 아래는 학력 정보 저장 시 사용한 결과 처리 부분으로 어학/경력 정보에 맞게 수정 필요
//        if ( r0 == 1 && insert == insertResult && update == updateResult && delete == deleteResult) {
//            ec.setResult(ExecutionContext.SUCCESS);
//            ec.setMessage(messageResolver.getMessage("U317"));
//            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
//                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
//        } else {
//            ec.setResult(ExecutionContext.FAIL);
//            ec.setMessage(messageResolver.getMessage("U318"));
//            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
//            String errCode = null;
//            if ( r0 == 0 ) errCode = "ERR0003";
//            if ( insert != insertResult ) errCode = "ERR0011";
//            if ( update != updateResult ) errCode = "ERR0013";
//            if ( delete != deleteResult ) errCode = "ERR0014";
//            ec.setErrCode(errCode);
//        }
        return ec;
    }

    private <T> List<T> retrieveInfoListByApplNo(int applNo, String mapperName, Class<T> clazz) {
        List<T> infoList = null;
        try {
            infoList = commonDAO.queryForList(NAME_SPACE + mapperName + ".selectByApplNo",
                    applNo, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return infoList;
    }

    private List<CustomApplicationExperience> setExprUserDataStatus(List<CustomApplicationExperience> list, UserCUDType userCUDType) {
        for (CustomApplicationExperience item : list) {
            item.setUserCUDType(userCUDType);
        }
        return list;
    }

    private List<LanguageGroup> setLangUserDataStatus(List<LanguageGroup> list, UserCUDType userCUDType) {
        for (LanguageGroup groupItem : list) {
            List<TotalApplicationLanguage> langList = groupItem.getLangList();
            for (TotalApplicationLanguage langItem : langList) {
                langItem.setUserCUDType(userCUDType);
            }
        }
        return list;
    }
}
