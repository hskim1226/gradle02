package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
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

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값
    private final String LANG_CAREER_SAVED = "00003";    // 어학/경력 저장

    /**
     * 어학/경력 정보 생성
     *
     * @param application
     * @param languageGroupList
     * @param applicationExperienceList
     * @return
     */
    @Override
    public ExecutionContext createLangCareer(Application application,
                                             List<LanguageGroup> languageGroupList,
                                             List<ApplicationExperience> applicationExperienceList) {
        ExecutionContext ec = new ExecutionContext();
        int r1, r2 = 0, r3 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();
        String userId = application.getUserId();

        application.setApplStsCode(LANG_CAREER_SAVED);
        application.setModDate(date);
        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

        if ( languageGroupList != null ) {
            // TODO - dhoonkim - 어학 성적 생성 시 langGroup, langList 처리
//            for( LanguageGroup languageGroup : languageGroupList) {
//                languageGroup.setApplNo(applNo);
//                languageGroup.setLangSeq(++idx);
//                languageGroup.setCreId(userId);
//                languageGroup.setCreDate(date);
//            }
//            r2 = commonDAO.insertList(applicationLanguageList, NAME_SPACE, "ApplicationLanguageMapper");
        }
        idx = 0;
        if ( applicationExperienceList != null ) {
            for( ApplicationExperience applicationExperience : applicationExperienceList) {
                applicationExperience.setApplNo(applNo);
                applicationExperience.setExprSeq(++idx);
                applicationExperience.setCreId(userId);
                applicationExperience.setCreDate(date);
            }
            r3 = commonDAO.insertList(applicationExperienceList, NAME_SPACE, "ApplicationExperienceMapper");
        }

        if ( r1 > 0 && r2 > 0 && r3 > 0) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U319"));
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U320"));
            String errCode = null;
            if ( r1 == 0 ) errCode = "ERR0003";
            else if ( r2 == 0 ) errCode = "ERR0016";
            else if ( r3 == 0 ) errCode = "ERR0021";
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode(errCode);
        }
        return ec;
    }

    @Override
    public ExecutionContext retrieveLangCareer(int applNo) {
        ExecutionContext ec = new ExecutionContext();
        LangCareer langCareer = new LangCareer();

        Application application = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        application = application == null ? new Application() : application;
        langCareer.setApplication(application);

        ApplicationGeneral applicationGeneral = commonDAO.queryForObject(NAME_SPACE + "ApplicationGeneralMapper.selectByPrimaryKey",
                applNo, ApplicationGeneral.class);
        applicationGeneral = applicationGeneral == null ? new ApplicationGeneral() : applicationGeneral;

        langCareer.setApplication(application);
        langCareer.setApplicationGeneral(applicationGeneral);
        langCareer.setLanguageGroupList(retrieveLanguageGroupListByApplNo(applNo));
        List<CustomApplicationExperience> applicationExperienceList = retrieveInfoListByApplNo(applNo, "CustomApplicationExperienceMapper", CustomApplicationExperience.class);
        langCareer.setApplicationExperienceList(setUserDataStatus(applicationExperienceList, UserCUDType.UPDATE));

        ec.setResult(ExecutionContext.SUCCESS);
        ec.setData(langCareer);

        return ec;
    }

    private List<LanguageGroup> retrieveLanguageGroupListByApplNo(int applNo) {

        List<LanguageGroup> langGroupList = null;

        try { // TODO - omw - 예외 처리 부분 조치 필요

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
        }catch (Exception e) {
            e.printStackTrace();
        }
        return langGroupList;
    }

    @Override
    public ExecutionContext saveLangCareer(LangCareer langCareer) {
        ExecutionContext ec = new ExecutionContext();

        // TODO - dhoonkim - 어학 저장 처리
//
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

    @Override
    public ExecutionContext updateLangCareer(Application application,
                                             List<LanguageGroup> languageGroupList,
                                             List<ApplicationExperience> applicationExperienceList) {
        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();
        String userId = application.getUserId();

        // TODO - dhoonkim - 어학 성적 수정 처리 - 현재는 all delete 후 reinsert 하는 방식임
//        deleteListByApplNo(applNo, "CustomApplicationLanguageMapper");
//        if ( languageGroupList != null ) {
//            for( LanguageGroup languageGroup : languageGroupList) {
//                languageGroup.setApplNo(applNo);
//                languageGroup.setLangSeq(++idx);
//                languageGroup.setModId(userId);
//                languageGroup.setModDate(date);
//            }
//            r1 = commonDAO.insertList(applicationLanguageList, NAME_SPACE, "ApplicationLanguageMapper");
//        }
        idx = 0;
        deleteListByApplNo(applNo, "CustomApplicationExperienceMapper");
        if ( applicationExperienceList != null ) {
            for( ApplicationExperience applicationExperience : applicationExperienceList) {
                applicationExperience.setApplNo(applNo);
                applicationExperience.setExprSeq(++idx);
                applicationExperience.setModId(userId);
                applicationExperience.setModDate(date);
            }
            r2 = commonDAO.insertList(applicationExperienceList, NAME_SPACE, "ApplicationExperienceMapper");
        }

        if ( r1 > 0 && r2 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U319"));
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U320"));
            String errCode = null;
            if ( r1 == 0 ) errCode = "ERR0018";
            else if ( r2 == 0 ) errCode = "ERR0023";
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode(errCode);
        }
        return ec;
    }

    /**
     * 입학원서 부분 정보 삭제
     *
     * @param applNo
     * @param MapperName
     * @return
     */
    @Override
    public int deleteListByApplNo(int applNo, String MapperName) {
        return commonDAO.delete(NAME_SPACE + MapperName + ".deleteListByApplNo", applNo);
    }

    @Override
    public ExecutionContext deleteLangCareer(Application application,
                                             List<LanguageGroup> languageGroupList,
                                             List<ApplicationExperience> applicationExperienceList) {
        return null;
    }

    @Override
    public <T> List<T> retrieveInfoListByApplNo(int applNo, String mapperName, Class<T> clazz) {
        List<T> infoList = null;
        try {
            infoList = commonDAO.queryForList(NAME_SPACE + mapperName + ".selectByApplNo",
                    applNo, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return infoList;
    }

    private List<CustomApplicationExperience> setUserDataStatus(List<CustomApplicationExperience> list, UserCUDType udt) {
        for (CustomApplicationExperience applicationExperience : list) {
            applicationExperience.setUserCUDType(udt);
        }
        return list;
    }
}
