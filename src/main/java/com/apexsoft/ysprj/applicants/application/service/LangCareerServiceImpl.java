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
     * @param applicationLanguageList
     * @param applicationExperienceList
     * @return
     */
    @Override
    public ExecutionContext createLangCareer(Application application,
                                             List<ApplicationLanguage> applicationLanguageList,
                                             List<ApplicationExperience> applicationExperienceList) {
        ExecutionContext ec = new ExecutionContext();
        int r1, r2 = 0, r3 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();
        String userId = application.getUserId();

        application.setApplStsCode(LANG_CAREER_SAVED);
        application.setModDate(date);
        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

        if ( applicationLanguageList != null ) {
            for( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                applicationLanguage.setApplNo(applNo);
                applicationLanguage.setLangSeq(++idx);
                applicationLanguage.setCreId(userId);
                applicationLanguage.setCreDate(date);
            }
            r2 = commonDAO.insertList(applicationLanguageList, NAME_SPACE, "ApplicationLanguageMapper");
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
//        langCareer.setLanguageGroupList(retrieveInfoListByApplNo(applNo, "CustomApplicationLanguageMapper", ApplicationLanguage.class));
        langCareer.setLanguageGroupList(retrieveLanguageGroupListByApplNo(applNo));
        langCareer.setApplicationExperienceList(retrieveInfoListByApplNo(applNo, "CustomApplicationExperienceMapper", ApplicationExperience.class));

        ec.setResult(ExecutionContext.SUCCESS);
        ec.setData(langCareer);

        return ec;
    }

    private List<LanguageGroup> retrieveLanguageGroupListByApplNo(int applNo) {

        List<LanguageGroup> langGroupList = null;
        try {

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
                for( TotalApplicationLanguage alang : aLangList){
                    if( alang.getDocSeq() > 0 )
                        alang.setLangInfoSaveFg(true);
                    else
                        alang.setLangInfoSaveFg(false);
                }
                alangGroup.setLangList(aLangList);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return langGroupList;
    }

    @Override
    public ExecutionContext updateLangCareer(Application application,
                                             List<ApplicationLanguage> applicationLanguageList,
                                             List<ApplicationExperience> applicationExperienceList) {
        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();
        String userId = application.getUserId();

        deleteListByApplNo(applNo, "CustomApplicationLanguageMapper");
        if ( applicationLanguageList != null ) {
            for( ApplicationLanguage applicationLanguage : applicationLanguageList) {
                applicationLanguage.setApplNo(applNo);
                applicationLanguage.setLangSeq(++idx);
                applicationLanguage.setModId(userId);
                applicationLanguage.setModDate(date);
            }
            r1 = commonDAO.insertList(applicationLanguageList, NAME_SPACE, "ApplicationLanguageMapper");
        }
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
                                             List<ApplicationLanguage> applicationLanguageList,
                                             List<ApplicationExperience> applicationExperienceList) {
        return null;
    }

    private <T> List<T> retrieveInfoListByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz) {
        List<T> infoList = null;

        infoList = commonDAO.queryForList(NAME_SPACE + mapperNameSqlId,
                    parameter, clazz);


        return infoList;
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


}
