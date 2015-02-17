package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
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

        List<LanguageGroup> langGroupList = retrieveLanguageGroupListByApplNo(applNo, applicationGeneralFromDB);
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

        List<LanguageGroup> langGroupList = retrieveLanguageGroupListByApplNo(applNo, applicationGeneralFromDB);
        langCareer.setLanguageGroupList(langGroupList);

        List<CustomApplicationExperience> applicationExperienceList = retrieveInfoListByApplNo(applNo, "CustomApplicationExperienceMapper", CustomApplicationExperience.class);
        langCareer.setApplicationExperienceList(applicationExperienceList);

        for(CustomApplicationExperience aExpr :applicationExperienceList  ){
            aExpr.setSaveFg(true);
        }

        commonCodeMap.put( "toflTypeList", commonService.retrieveCommonCodeValueByCodeGroup("TOFL_TYPE") );
        commonCodeMap.put( "fornExmpList", commonService.retrieveCommonCodeValueByCodeGroup("FORN_EXMP") );
        commonCodeMap.put( "ieltsLevelList", commonService.retrieveCommonCodeValueByCodeGroup("IELT_LEVL") );
        commonCodeMap.put( "topikLevelList", commonService.retrieveCommonCodeValueByCodeGroup("TOPK_LEVL") );

        ecDataMap.put("langCareer", langCareer);
        ecDataMap.put("common", commonCodeMap);
        ec.setData(ecDataMap);

        return ec;
    }

    private List<LanguageGroup> retrieveLanguageGroupListByApplNo(int applNo, ApplicationGeneral applicationGeneral) {

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
            aCont.setMdtSeq(alangGroup.getMdtSeq());

            //두번째 레벨의 정보를 가져온다
            aLangList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalLanguageDoc", aCont, TotalApplicationLanguageContainer.class);

            //하부 레벨의 정보를 채운다
            for (TotalApplicationLanguageContainer alang : aLangList) {
                alang.setApplNo(applNo);
                List<TotalApplicationLanguageContainer> aSubList = new ArrayList<TotalApplicationLanguageContainer>();
                alang.setSubContainer(getSubLangContainer(alangGroup, alang, aSubList, applicationGeneral));
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

        List<LanguageGroup> languageGroupList = langCareer.getLanguageGroupList();
        List<CustomApplicationExperience> exprList = langCareer.getApplicationExperienceList();

        int currentStsCode = Integer.parseInt(application.getApplStsCode());
        if (currentStsCode < Integer.parseInt(LANG_CAREER_SAVED)) {
            rUpAppl++;
            application.setApplStsCode(LANG_CAREER_SAVED);
            application.setModDate(new Date());
            upAppl = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
        }




        //어학정보 저장
        for( LanguageGroup aGroup : languageGroupList){
            List<TotalApplicationLanguageContainer> langList = aGroup.getLangList();

            for(TotalApplicationLanguageContainer aLangOrExempt : langList){
                List<TotalApplicationLanguageContainer> subContainer = aLangOrExempt.getSubContainer();

                for (TotalApplicationLanguageContainer aCont : subContainer) {
                    //저장요청건
                    if( aCont.isCheckedFg()) {

                        //면제 해당여부 처리
                        if ("ENG_EXMP1".equals(aLangOrExempt.getSelGrpCode())) {
                            if(aCont.isCheckedFg()) {
                                applicationGene.setApplNo(applNo);
                                rUpApplGen++;
                                if ( applicationGene.getForlExmpCode() != null || applicationGene.getForlExmpCode() != "" ) {
                                    upApplGen = upApplGen + commonDAO.updateItem(applicationGene, NAME_SPACE, "ApplicationGeneralMapper");
                                }
                            }
                        } else {
                            aCont.setLangExamGrp(aCont.getItemGrpCode());
                            aCont.setLangExamCode(aCont.getItemCode());
                            //기존정보 처리
                            if(aCont.isLangInfoSaveFg()){
                                //APPL_LANG,  UPDATE
                                rUpdate++;
                                aCont.setModId(application.getModId());
                                aCont.setModDate(date);
                                update = update + commonDAO.updateItem( aCont, NAME_SPACE, "ApplicationLanguageMapper");

                            }else{ //신규 입력정보
                                //APPL_LANG, INSERT
                                rInsert++;
                                int maxSeq = commonDAO.queryForInt(NAME_SPACE +"CustomApplicationLanguageMapper.selectMaxSeqByApplNo", applNo ) ;
                                maxSeq++;
                                aCont.setLangSeq(maxSeq);
                                aCont.setCreId(application.getModId());
                                aCont.setCreDate(date);
                                insert = insert + commonDAO.insertItem( aCont, NAME_SPACE, "ApplicationLanguageMapper");
                            }
                        }
                    }else if(aCont.isLangInfoSaveFg() ) {//기존 정보 선택 취소

                        // 외국어 면제 선택 상태를 해제하고 영어성적 입력한 경우, 면제 코드를 빈 문자열로 초기화
                        if ("ENG_EXMP1".equals(aLangOrExempt.getSelGrpCode())) {
                            applicationGene = new ApplicationGeneral();
                            applicationGene.setApplNo(applNo);
                            rUpApplGen++;
                            applicationGene.setForlExmpCode("");
                            upApplGen = upApplGen+ commonDAO.updateItem(applicationGene, NAME_SPACE, "ApplicationGeneralMapper");
                        } else {
                            rDelete++;
                            //APPL_LANG, APPL_DOC, DELETE
                            delete = delete + commonDAO.delete(NAME_SPACE + "ApplicationLanguageMapper.deleteByPrimaryKey", aCont);
                            if( aCont.isFileUploadFg()){
                                rDelete++;
                                ApplicationDocument aDoc = new ApplicationDocument();
                                aDoc.setApplNo(applNo);
                                aDoc.setDocSeq(aCont.getDocSeq());
                                delete = delete + commonDAO.delete(NAME_SPACE + "ApplicationDocumentMapper.deleteByPrimaryKey", aDoc);
                                // TODO : 파일 삭제를 여기서 하거나, 혹은 파일 삭제는 여기서 하지 않고 일별 batch 등으로 처리하거나
                            }
                        }
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
    private  List<TotalApplicationLanguageContainer> getSubLangContainer( LanguageGroup aLangGroup,
                                                                          TotalApplicationLanguageContainer pCont,
                                                                          List<TotalApplicationLanguageContainer> pList,
                                                                          ApplicationGeneral applicationGeneral){
        List<TotalApplicationLanguageContainer> rContList = null;

        if (!"Y".equals( pCont.getLastYn())) {
            //pCont.setGrpLabel( pCont.getDocItemName());
            pList.add(pCont);

            rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalLanguageDoc", pCont, TotalApplicationLanguageContainer.class);
            if (rContList != null) {
                for (TotalApplicationLanguageContainer aCont : rContList) {
                    aCont.setApplNo(pCont.getApplNo());
                    aCont.setSubContainer(getSubLangContainer(aLangGroup, aCont, pList, applicationGeneral));
                }
            }
            //TODO 어학 전체를 넣을지, 등록된 것만 넣을지 결정
            //if(pCont.getApplNo()== null || pCont.getApplNo().equals(""))
            //    pCont.setApplNo(applNo);
        }else{
            //pCont 에 세부정보를 채움
            //pCont = commonDAO.queryForObject( NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalLanguageDoc",pCont,TotalApplicationLanguageContainer.class );
            if( pCont.getLangSeq() != null && pCont.getLangSeq() > 0 ) {
                pCont.setLangInfoSaveFg(true);
                pCont.setCheckedFg(true);
            } else {
                pCont.setLangInfoSaveFg(false);
                pCont.setCheckedFg(false);
            }

            if( pCont.getDocSeq() > 0 ) {
                pCont.setFileUploadFg(true);
            } else {
                pCont.setFileUploadFg(false);
            }

            if ("ENG_EXMP1".equals(pCont.getSelGrpCode()) || "KOR_EXMP1".equals(pCont.getSelGrpCode())) {
                TotalApplicationLanguageContainer exemptContainer = new TotalApplicationLanguageContainer();
                pCont.setLastYn("N");
                exemptContainer.setApplNo(pCont.getApplNo());
                exemptContainer.setItemName(aLangGroup.getExamGrpName() + " 성적 면제 해당자");
                exemptContainer.setLastYn("Y");
                if (applicationGeneral.getForlExmpCode() != null && applicationGeneral.getForlExmpCode().length() > 0) {
                    exemptContainer.setLangInfoSaveFg(true);
                    exemptContainer.setCheckedFg(true);
                }
                pCont.getSubContainer().add(exemptContainer);

                if (rContList == null) {
                    rContList = new ArrayList<TotalApplicationLanguageContainer>();
                }
                rContList.add(exemptContainer);
            }
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
