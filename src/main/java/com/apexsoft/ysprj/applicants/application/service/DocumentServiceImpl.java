package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.admission.domain.ParamForAdmissionCourseMajor;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 23.
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값
    private final String FILE_UPLOAD_SAVED = "00004";    // 첨부파일 저장

    @Override
    public ExecutionContext retrieveDocument(Document document) {

        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();

        Application applicationFromUser = document.getApplication();

        int applNo = applicationFromUser.getApplNo();

        Application applicationFromDB = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        document.setApplication(applicationFromDB);

        List<TotalApplicationDocumentContainer> documentContainerList =
                retrieveManatoryApplicatoinlDocListByApplNo(applNo);
        document.setDocumentContainerList(documentContainerList);

        ecDataMap.put("document", document);
        ec.setData(ecDataMap);

        return ec;
    }

    @Override
    public ExecutionContext saveDocument(Document document) {

        ExecutionContext ec = new ExecutionContext();

        Application application = document.getApplication();
        String applStsCode;
        int currentStsCode = Integer.parseInt(application.getApplStsCode());
        if (currentStsCode < Integer.parseInt(FILE_UPLOAD_SAVED))
            application.setApplStsCode(FILE_UPLOAD_SAVED);

        // TODO - dhoonkim - 첨부파일 저장
//        int r1 = 0, applNo = application.getApplNo(), idx = 0;
//        Date date = new Date();
//        String userId = application.getUserId();
//
//        if ( r1 == idx ) {
//            ec.setResult(ExecutionContext.SUCCESS);
//            ec.setMessage(messageResolver.getMessage("U325"));
//            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
//                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
//        } else {
//            ec.setResult(ExecutionContext.FAIL);
//            ec.setMessage(messageResolver.getMessage("U326"));
//            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
//            ec.setErrCode("ERR0033");
//        }
        return ec;
    }

    private List<TotalApplicationDocumentContainer> retrieveManatoryApplicatoinlDocListByApplNo(int applNo) {

        List<TotalApplicationDocumentContainer> applContList = new ArrayList<TotalApplicationDocumentContainer>();

        Application tempApp = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey", applNo, Application.class);
        String admsNo = tempApp.getAdmsNo();
        //학과별정보 조회
        applContList.add(retrieveDeptDocumentByApplNo(applNo ));

        //학력정보 조회
        applContList.add(retrieveAcademyDocumentByApplNo(applNo, admsNo));

        //어학정보 조회
        applContList.add(retrieveLanguageDocumentByApplNo(applNo));

        //코드별 조건 조회
        applContList.addAll(retrieveCodeDocumentByApplNo(applNo,admsNo));

        return applContList;
    }


    //학과별 문서요건 정보 조회
    private TotalApplicationDocumentContainer retrieveDeptDocumentByApplNo( int applNo) {
        TotalApplicationDocumentContainer rApplDoc = null;
        List<TotalApplicationDocumentContainer> applDocList;
        applDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectBasicTotalDocListByApplNo", applNo, TotalApplicationDocumentContainer.class);
        if( applDocList != null){
            for ( TotalApplicationDocumentContainer aCont : applDocList){
                aCont.setSubContainer( getSubDeptDocumentContainer(aCont));
            }
        }
        rApplDoc = new TotalApplicationDocumentContainer();
        rApplDoc.setSubContainer(applDocList);
        rApplDoc.setGrpLabel("기본-학과지정 제출서류");
        rApplDoc.setDisplayGrpFg(true);

        return rApplDoc;
    }

    //점수가 입력된 어학문서 정보 조회
    private  TotalApplicationDocumentContainer retrieveLanguageDocumentByApplNo( int applNo) {
        TotalApplicationDocumentContainer rApplDoc = null;
        List<TotalApplicationDocumentContainer> applDocList;

        int i =1;
        applDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectLanguageTotalDocListByApplNo", applNo, TotalApplicationDocumentContainer.class);
        if( applDocList != null){
            for ( TotalApplicationDocumentContainer aCont : applDocList){
                aCont.setDocItemName(aCont.getDocItemName()+" 성적표(증명)");
            }
        }
        rApplDoc = new TotalApplicationDocumentContainer();
        rApplDoc.setSubContainer(applDocList);
        rApplDoc.setGrpLabel("어학 관련서류");
        rApplDoc.setDisplayGrpFg(true);

        return rApplDoc;
    }

    //입력된 학력정보별 문서요건 정보 조회
    private  TotalApplicationDocumentContainer retrieveAcademyDocumentByApplNo( int applNo, String admsNo ) {
        TotalApplicationDocumentContainer rApplDoc = null;
        List<TotalApplicationDocumentContainer> subAcadList = new ArrayList<TotalApplicationDocumentContainer>();
        TotalApplicationDocumentContainer aCont = null;
        ParamForAcademy param = new ParamForAcademy();


        rApplDoc = new TotalApplicationDocumentContainer();
        rApplDoc.setSubContainer( new ArrayList<TotalApplicationDocumentContainer>());
        rApplDoc.setGrpLabel("대학 관련서류");
        rApplDoc.setDisplayGrpFg(true);

        param.setApplNo(applNo);
        param.setAcadTypeCode("00002");//대학
        List<CustomApplicationAcademy>acadList;
        acadList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode", param, CustomApplicationAcademy.class);

        //대학 갯수만큼 켄네이너 생성
        for(CustomApplicationAcademy aAcad : acadList){

            aCont = new TotalApplicationDocumentContainer();
            aCont.setApplNo( aAcad.getApplNo());
            aCont.setGrpLabel(aAcad.getSchlName() + " 관련서류");

            subAcadList.add(aCont);

            //학위별 필수서류 셋팅
            aAcad.getAcadSeq();
            aAcad.getGrdaTypeCode();
            aAcad.getSchlCntrCode();
            aAcad.getSchlName();
            ParamForCodeDocument codeParam = new ParamForCodeDocument();
            codeParam.setApplNo(applNo);
            codeParam.setAdmsNo(admsNo);
            codeParam.setAdmsCodeGrp("ACAD_TYPE");
            codeParam.setAdmsCode(aAcad.getAcadTypeCode());
            codeParam.setGrpLevel(1);
            codeParam.setItemCode("00003");//대학

            List<TotalApplicationDocumentContainer> subDocList;
            subDocList = commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class);

            //해외학위 필수서류 셋팅
            codeParam.setAdmsCodeGrp("SCHL_CNTR");
            codeParam.setAdmsCode(aAcad.getSchlCntrCode());
            codeParam.setItemCode("00002");//해외학위
            subDocList.addAll(commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class));

            for( TotalApplicationDocumentContainer aSubDoc : subDocList ){
                aSubDoc.setDocGrp(aAcad.getAcadSeq());
                aSubDoc.setAdmsNo(admsNo);
                aSubDoc.setSubContainer( getSubCodeDocumentContainer(aSubDoc));
            }
            aCont.setSubContainer(subDocList);
            //전체 학력 컨테이너에 추가
            rApplDoc.getSubContainer().add(aCont);

        }


        //대학원 갯수만큼 켄네이너 생성
        param.setAcadTypeCode("00004");//대학원
        acadList =commonDAO.queryForList(NAME_SPACE + "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode",param,CustomApplicationAcademy.class);

        for(CustomApplicationAcademy aAcad : acadList){

            aCont = new TotalApplicationDocumentContainer();
            aCont.setApplNo( aAcad.getApplNo());
            aCont.setGrpLabel( aAcad.getSchlName() + " 관련서류" );

            //필수서류 셋팅
            aAcad.getAcadSeq();
            aAcad.getGrdaTypeCode();
            aAcad.getSchlCntrCode();
            aAcad.getSchlName();
            ParamForCodeDocument codeParam = new ParamForCodeDocument();
            codeParam.setApplNo(applNo);
            codeParam.setAdmsNo(admsNo);
            codeParam.setAdmsCodeGrp( "ACAD_TYPE");
            codeParam.setAdmsCode(aAcad.getAcadTypeCode());
            codeParam.setGrpLevel(1);
            codeParam.setItemCode("00004");//대학원

            List<TotalApplicationDocumentContainer> subDocList;
            subDocList = commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class);

            //해외학위 필수서류 셋팅
            codeParam.setAdmsCodeGrp("SCHL_CNTR");
            codeParam.setAdmsCode(aAcad.getSchlCntrCode());
            codeParam.setItemCode("00002");//해외학위
            subDocList.addAll(commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class));

            for( TotalApplicationDocumentContainer aSubDoc : subDocList ){
                aSubDoc.setDocGrp(aAcad.getAcadSeq());
                aSubDoc.setAdmsNo(admsNo);
                aSubDoc.setSubContainer( getSubCodeDocumentContainer(aSubDoc));
            }
            aCont.setSubContainer(subDocList);
            //전체 학력 컨테이너에 추가
            rApplDoc.getSubContainer().add(aCont);

        }

        return rApplDoc;
    }

    //지원특성별 문서요건 정보 조회 - from mad_code 테이블
    private  List<TotalApplicationDocumentContainer> retrieveCodeDocumentByApplNo( int applNo, String admsNo  ) {
        List<TotalApplicationDocumentContainer> rContList = new ArrayList<TotalApplicationDocumentContainer>() ;
        TotalApplicationDocumentContainer aCont = null;

        Application tempApp = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey", applNo, Application.class);
        ParamForCodeDocument codeParam = new ParamForCodeDocument();
        codeParam.setApplNo(applNo);
        codeParam.setAdmsNo(admsNo);

        //학연산 조회
        if( "00006".equals(tempApp.getApplAttrCode())) {

            codeParam.setAdmsCodeGrp("APPL_ATTR");
            codeParam.setAdmsCode("00003");
            codeParam.setGrpLevel(1);
            codeParam.setItemTypeCode("00006");//학연산

            List<TotalApplicationDocumentContainer> subDocList;
            subDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode", codeParam, TotalApplicationDocumentContainer.class);
            for (TotalApplicationDocumentContainer aSubDoc : subDocList) {
                aSubDoc.setAdmsNo(admsNo);
                aSubDoc.setSubContainer(getSubCodeDocumentContainer(aSubDoc));
            }
            aCont = new TotalApplicationDocumentContainer();
            aCont.setGrpLabel("학연산 관련서류");
            aCont.setDisplayGrpFg(true);
            aCont.setSubContainer(subDocList);
            rContList.add(aCont);
        }


        //외국인 조회
        if(  "00001".equals(tempApp.getFornTypeCode())||"00002".equals(tempApp.getFornTypeCode())||"00003".equals(tempApp.getFornTypeCode()) ) {
            codeParam.setAdmsCodeGrp("FORN_TYPE");
            codeParam.setAdmsCode(tempApp.getApplAttrCode());
            codeParam.setGrpLevel(1);
            codeParam.setItemTypeCode("00007");//

            List<TotalApplicationDocumentContainer> subDocList;
            subDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode", codeParam, TotalApplicationDocumentContainer.class);
            for (TotalApplicationDocumentContainer aSubDoc : subDocList) {
                aSubDoc.setAdmsNo(admsNo);
                aSubDoc.setSubContainer(getSubCodeDocumentContainer(aSubDoc));
            }
            aCont = new TotalApplicationDocumentContainer();
            aCont.setGrpLabel("외국인전형 관련서류");
            aCont.setDisplayGrpFg(true);
            aCont.setSubContainer(subDocList);
            rContList.add(aCont);
        }

        //기타 및 자유입력 조회

        codeParam.setGrpLevel(1);
        codeParam.setItemTypeCode("00009");// 기타 및 추가제출
        List<TotalApplicationDocumentContainer> subDocList;
        subDocList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectMandatoryDocumentByDocType", codeParam, TotalApplicationDocumentContainer.class);
        aCont = new TotalApplicationDocumentContainer();
        for (TotalApplicationDocumentContainer aSubDoc : subDocList) {
            aSubDoc.setAdmsNo(admsNo);
        }
        aCont.setGrpLabel("기타 및 추가제출");
        aCont.setDisplayGrpFg(true);
        aCont.setSubContainer(subDocList);
        rContList.add(aCont);

        return rContList;
    }

    private  List<TotalApplicationDocumentContainer> getSubDeptDocumentContainer( TotalApplicationDocumentContainer pCont){
        List<TotalApplicationDocumentContainer> rContList = null;

        if (!"Y".equals( pCont.getLastYn())) {
            rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalApplicationDocumentList", pCont, TotalApplicationDocumentContainer.class);
            if (rContList != null) {
                for (TotalApplicationDocumentContainer aCont : rContList) {
                    aCont.setSubContainer(getSubDeptDocumentContainer(aCont));

                }
            }
            pCont.setGrpLabel( pCont.getDocItemName());
        }
        return rContList;
    }

    private  List<TotalApplicationDocumentContainer> getSubCodeDocumentContainer( TotalApplicationDocumentContainer pCont){
        List<TotalApplicationDocumentContainer> rContList = null;

        if (!"Y".equals( pCont.getLastYn())) {
            rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalCodeApplicationDocumentList", pCont, TotalApplicationDocumentContainer.class);
            if (rContList != null) {
                for (TotalApplicationDocumentContainer aCont : rContList) {
                    aCont.setSubContainer(getSubCodeDocumentContainer(aCont));

                }
            }
            pCont.setGrpLabel( pCont.getDocItemName());
        }else{
            ApplicationDocument aDoc;

            aDoc = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationDocumentMapper.selectCodeApplicationDocumentByTotalDocumentContainner", pCont, ApplicationDocument.class);
            if( aDoc != null){
                pCont.setDocSeq( aDoc.getDocSeq());
                pCont.setDocName( aDoc.getDocName());
                pCont.setFileExt( aDoc.getFileExt());
                pCont.setImgYn( aDoc.getImgYn());
                pCont.setFilePath( aDoc.getFilePath());
                pCont.setFileName(aDoc.getFileName());
                pCont.setOrgFileName(aDoc.getOrgFileName());
                pCont.setDocItemNameXxen( aDoc.getDocItemNameXxen());
                pCont.setDocGrpName( aDoc.getDocGrpName());
                pCont.setFileUploadFg(true);
            }
        }
        return rContList;
    }
}
