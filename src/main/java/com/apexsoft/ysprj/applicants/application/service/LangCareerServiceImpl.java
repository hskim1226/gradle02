package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.exception.YSNoRedirectBizException;
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
    public ExecutionContext retrieveLangCareer(int applNo) {
        ExecutionContext ec = new ExecutionContext();

        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> commonCodeMap = new HashMap<String, Object>();

        LangCareer langCareer = new LangCareer();

        Application applicationFromDB = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        langCareer.setApplication(applicationFromDB);

        ApplicationGeneral applicationGeneralFromDB = commonDAO.queryForObject(NAME_SPACE + "ApplicationGeneralMapper.selectByPrimaryKey",
                applNo, ApplicationGeneral.class);
        applicationGeneralFromDB = applicationGeneralFromDB == null ? new ApplicationGeneral() : applicationGeneralFromDB;
        langCareer.setApplicationGeneral(applicationGeneralFromDB);

        List<LanguageGroup> langGroupList = retrieveLanguageGroupListByApplNo(applNo);
        langCareer.setLanguageGroupList(langGroupList);

        List<CustomApplicationExperience> applicationExperienceList = retrieveInfoListByApplNo(applNo, "CustomApplicationExperienceMapper", CustomApplicationExperience.class);
        langCareer.setApplicationExperienceList(applicationExperienceList);

        for(CustomApplicationExperience aExpr :applicationExperienceList  ){
            aExpr.setSaveFg(true);
        }

        commonCodeMap.put( "toflTypeList", commonService.retrieveCommonCodeValueByCodeGroup("TOFL_TYPE") );
        commonCodeMap.put( "fornExmpList", commonService.retrieveCommonCodeValueByCodeGroup("FORN_EXMP") );

        ecDataMap.put("langCareer", langCareer);
        ecDataMap.put("common", commonCodeMap);
        ec.setData(ecDataMap);

        return ec;
    }

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
        langCareer.setLanguageGroupList(langGroupList);

        List<CustomApplicationExperience> applicationExperienceList = retrieveInfoListByApplNo(applNo, "CustomApplicationExperienceMapper", CustomApplicationExperience.class);
        langCareer.setApplicationExperienceList(applicationExperienceList);

        for(CustomApplicationExperience aExpr :applicationExperienceList  ){
            aExpr.setSaveFg(true);
        }

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
        /*
        if( langGroupList == null || langGroupList.size()==0){
            LanguageGroup aGroup  = new LanguageGroup();
            aGroup.setExamCodeGrp("LANG_EXAM");
            aGroup.setExamGrpName("영어");
            aGroup.setSelGrpCode("LANG_EXAM");
            aGroup.setExamCode("00001");
            langGroupList.add(aGroup);
        }
        */
        ParamForTotalLang param = new ParamForTotalLang();
        List<TotalApplicationLanguageContainer> aLangList;
        for (LanguageGroup alangGroup : langGroupList) {

            TotalApplicationLanguageContainer aCont = new TotalApplicationLanguageContainer();
            aCont.setApplNo(applNo);
            aCont.setSelGrpCode(alangGroup.getSelGrpCode());
            aCont.setItemGrpCode(alangGroup.getExamCodeGrp());
            aCont.setItemCode(alangGroup.getExamCode());

            //두번째 레벨의 정보를 가져온다
            aLangList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalLanguageDoc", aCont, TotalApplicationLanguageContainer.class);

            //하부 레벨의 정보를 채운다
            for (TotalApplicationLanguageContainer alang : aLangList) {
                alang.setApplNo(applNo);
                List<TotalApplicationLanguageContainer> aSubList = new ArrayList<TotalApplicationLanguageContainer>();
                alang.setSubContainer(getSubLangContainer(alang, aSubList));
            }
            alangGroup.setLangList(aLangList);
        }
        return langGroupList;
    }

    @Override
    public ExecutionContext saveLangCareer(LangCareer langCareer) {
        ExecutionContext ec = new ExecutionContext();

        int upAppl = 0,upApplGen=0, insert = 0, update = 0, delete = 0;

        int rUpAppl = 0, rUpApplGen=0, rInsert = 0, rUpdate = 0, rDelete = 0;
        Application application = langCareer.getApplication();
        ApplicationGeneral applicationGene = langCareer.getApplicationGeneral();
        int applNo = application.getApplNo();

        Date date = new Date();

        List<LanguageGroup> langList = langCareer.getLanguageGroupList();
        List<CustomApplicationExperience> exprList = langCareer.getApplicationExperienceList();

        int currentStsCode = Integer.parseInt(application.getApplStsCode());
        if (currentStsCode < Integer.parseInt(LANG_CAREER_SAVED)) {
            rUpAppl++;
            application.setApplStsCode(LANG_CAREER_SAVED);
            application.setModDate(new Date());
            upAppl = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
        }

        //면제 해당여부 처리
        if("on".equals(langCareer.getCheckForlExmp())) {
            applicationGene.setApplNo(applNo);
            rUpApplGen++;
            if (applicationGene.getForlExmpCode() != null || applicationGene.getForlExmpCode() != "") {
                upApplGen = upApplGen +commonDAO.updateItem(applicationGene, NAME_SPACE, "ApplicationGeneralMapper");
            }
        }else{
            applicationGene = new ApplicationGeneral();
            applicationGene.setApplNo(applNo);
            rUpApplGen++;
            applicationGene.setForlExmpCode("");
            upApplGen = upApplGen+ commonDAO.updateItem(applicationGene, NAME_SPACE, "ApplicationGeneralMapper");

        }


        //어학정보 저장
        for( LanguageGroup aGroup : langList){
            for(TotalApplicationLanguage aLang : aGroup.getLangList()){
                //저장요청건
                if( aLang.isCheckedFg()) {
                    aLang.setLangExamGrp(aLang.getItemGrpCode());
                    aLang.setLangExamCode(aLang.getItemCode());
                    //기존정보 처리
                    if(aLang.isLangInfoSaveFg()){
                        //APPL_LANG,  UPDATE
                        rUpdate++;
                        //TotalApplicationLanguage tmpLang = aLang;
                        aLang.setModId(application.getModId());
                        aLang.setModDate(date);
                        update = update + commonDAO.updateItem( aLang, NAME_SPACE, "ApplicationLanguageMapper");

                    }else{ //신규 입력정보
                        //APPL_LANG, INSERT
                        rInsert++;
                        int maxSeq = commonDAO.queryForInt(NAME_SPACE +"CustomApplicationLanguageMapper.selectMaxSeqByApplNo", applNo ) ;
                        maxSeq++;
                        aLang.setLangSeq(maxSeq);
                        aLang.setCreId(application.getModId());
                        aLang.setCreDate(date);
                        insert = insert + commonDAO.insertItem( aLang, NAME_SPACE, "ApplicationLanguageMapper");
                    }
                }else if(aLang.isLangInfoSaveFg() ) {//기존 정보 선택 취소
                    rDelete++;
                    //APPL_LANG, APPL_DOC, DELETE
                    delete = delete + commonDAO.delete(NAME_SPACE + "ApplicationLanguageMapper.deleteByPrimaryKey", aLang);
                    if( aLang.isFileUploadFg()){
                        rDelete++;
                        ApplicationDocument aDoc = new ApplicationDocument();
                        aDoc.setApplNo(applNo);
                        aDoc.setDocSeq(aLang.getDocSeq());
                        delete = delete + commonDAO.delete(NAME_SPACE + "ApplicationDocumentMapper.deleteByPrimaryKey", aDoc);
                    }
                }
            }
        }
        //경력정보 저장
        for( CustomApplicationExperience aExpr : exprList){

            if( aExpr.isCheckedFg()) {

                if(aExpr.isSaveFg()){
                    //APPL_LANG,  UPDATE
                    rUpdate++;
                    aExpr.setModId(application.getModId());
                    aExpr.setModDate(date);
                    update = update + commonDAO.updateItem( aExpr, NAME_SPACE, "ApplicationExperienceMapper");

                }else{ //신규 입력정보
                    //APPL_LANG, INSERT
                    rInsert++;
                    int maxSeq = commonDAO.queryForInt(NAME_SPACE +"CustomApplicationExperienceMapper.selectMaxSeqByApplNo", applNo ) ;
                    aExpr.setApplNo(applNo);
                    aExpr.setExprSeq(++maxSeq);
                    aExpr.setSaveFg(true);
                    aExpr.setCreId(application.getModId());
                    aExpr.setCreDate(date);
                    insert = insert + commonDAO.insertItem( aExpr, NAME_SPACE, "ApplicationExperienceMapper");
                }
            }else if(aExpr.isSaveFg() ) {//기존 정보 선택 취소
                rDelete++;
                //APPL_LANG, APPL_DOC, DELETE
                delete = delete + commonDAO.delete(NAME_SPACE + "ApplicationExperienceMapper.deleteByPrimaryKey", aExpr);
                if( aExpr.isFileUploadFg()){
                    //TODO file upload 된 doc 삭제
                }

            }
        }

        if ( rUpAppl == upAppl && rUpApplGen == upApplGen && insert == rInsert && update == rUpdate && delete == rDelete) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U319"));
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U320"));
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            String errCode = null;
            if ( rUpAppl != upAppl ) errCode = "ERR0003";
            if ( insert != rInsert ) errCode = "ERR0017";
            if ( update != rUpdate ) errCode = "ERR0018";
            if ( delete != rDelete ) errCode = "ERR0019";
            ec.setErrCode(errCode);
            throw new YSBizException(ec);
        }
        return ec;
    }

    private <T> List<T> retrieveInfoListByApplNo(int applNo, String mapperName, Class<T> clazz) {
        List<T> infoList = null;
        infoList = commonDAO.queryForList(NAME_SPACE + mapperName + ".selectByApplNo",
                applNo, clazz);

        return infoList;
    }


    //하부 그룹이 있으면 하부 그룹을 조회하고, 최말단 이면 상세정보를 조회한다.
    private  List<TotalApplicationLanguageContainer> getSubLangContainer( TotalApplicationLanguageContainer pCont, List<TotalApplicationLanguageContainer> pList){
        List<TotalApplicationLanguageContainer> rContList = null;

        if (!"Y".equals( pCont.getLastYn())) {
            //pCont.setGrpLabel( pCont.getDocItemName());
            pList.add(pCont);

            rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalLanguageDoc", pCont, TotalApplicationLanguageContainer.class);
            if (rContList != null) {
                for (TotalApplicationLanguageContainer aCont : rContList) {
                    aCont.setApplNo(pCont.getApplNo());
                    aCont.setSubContainer(getSubLangContainer(aCont, pList));

                }
            }
            //TODO 어학 전체를 넣을지, 등록된 것만 넣을지 결정
            //if(pCont.getApplNo()== null || pCont.getApplNo().equals(""))
            //    pCont.setApplNo(applNo);
        }else{
            //pCont 에 세부정보를 채움
            //pCont = commonDAO.queryForObject( NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalLanguageDoc",pCont,TotalApplicationLanguageContainer.class );
            if( pCont.getLangSeq() != null && pCont.getLangSeq() > 0 )
                pCont.setLangInfoSaveFg(true);
            else
                pCont.setLangInfoSaveFg(false);

            if( pCont.getDocSeq() > 0 )
                pCont.setFileUploadFg(true);
            else
                pCont.setFileUploadFg(false);
        }
        return rContList;
    }

//    private List<CustomApplicationExperience> setExprUserDataStatus(List<CustomApplicationExperience> list, UserCUDType userCUDType) {
//        for (CustomApplicationExperience item : list) {
//            item.setUserCUDType(userCUDType);
//        }
//        return list;
//    }
//
//    private List<LanguageGroup> setLangUserDataStatus(List<LanguageGroup> list, UserCUDType userCUDType) {
//        for (LanguageGroup groupItem : list) {
//            List<TotalApplicationLanguage> langList = groupItem.getLangList();
//            for (TotalApplicationLanguage langItem : langList) {
//                langItem.setCheckedFg(true);
//            }
//        }
//        return list;
//    }
}
