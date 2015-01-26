package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * 파일 업로드 정보 생성
     *
     * @param application
     * @param docGroupFileList
     * @return
     */
    @Override
    public ExecutionContext createFileUpload(Application application,
                                             List<DocGroupFile> docGroupFileList) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();
        String userId = application.getUserId();

        application.setApplStsCode(FILE_UPLOAD_SAVED);
        application.setModDate(date);
        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

//        if ( docGroupFileList != null ) {
//            for( DocGroupFile docGroupFile : docGroupFileList) {
//                List<MandatoryNAppliedDoc> mDocList = docGroupFile.getMandDocList();
//                if ( mDocList != null ) {
//                    for (MandatoryNAppliedDoc mDoc : mDocList) {
//                        mDoc.setApplNo(applNo);
//                        mDoc.setDocSeq(++idx);
//                        mDoc.setCreId(userId);
//                        mDoc.setCreDate(date);
//                    }
//                }
//                r1 += commonDAO.insertList(mDocList, NAME_SPACE, "ApplicationDocumentMapper");
//            }
//        }

        if ( r1 == idx ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U325"));
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U326"));
            String errMsg = messageResolver.getMessage("ERR0031");
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode("ERR0031");
        }

        return ec;
    }

    /**
     * 파일 업로드 정보 수정
     *
     * @param application
     * @param docGroupFileList
     * @return
     */
    @Override
    public ExecutionContext updateFileUpload(Application application,
                                             List<DocGroupFile> docGroupFileList) {

        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();
        String userId = application.getUserId();

//        deleteListByApplNo(applNo, "CustomApplicationDocumentMapper");
//        if ( docGroupFileList != null ) {
//            for( DocGroupFile docGroupFile : docGroupFileList) {
//                List<MandatoryNAppliedDoc> mDocList = docGroupFile.getMandDocList();
//                if ( mDocList != null ) {
//                    for (MandatoryNAppliedDoc mDoc : mDocList) {
//                        mDoc.setApplNo(applNo);
//                        mDoc.setDocSeq(++idx);
//                        mDoc.setModId(userId);
//                        mDoc.setModDate(date);
//                    }
//                }
//                r1 += commonDAO.insertList(mDocList, NAME_SPACE, "ApplicationDocumentMapper");
//            }
//        }

        if ( r1 == idx ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U325"));
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U326"));
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode("ERR0033");
        }
        return ec;
    }

    @Override
    public ExecutionContext deleteFileUpload(Application application,
                                      List<DocGroupFile> docGroupFileList) {
        return null;
    }

    @Override
    public ExecutionContext retrieveDocument(int applNo) {
        ExecutionContext ec = new ExecutionContext();
        Document document = new Document();

        Application application = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        application = application == null ? new Application() : application;
        document.setApplication(application);

        document.setApplication(application);
        List<TotalApplicationDocumentContainer> documentContainerList =
                retrieveManatoryApplicatoinlDocListByApplNo(applNo);
        document.setDocumentContainerList(documentContainerList);

        ec.setResult(ExecutionContext.SUCCESS);
        ec.setData(document);

        return ec;
    }

    @Override
    public ExecutionContext saveDocument(Document document) {

        ExecutionContext ec = new ExecutionContext();
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

    @Override
    public List<TotalApplicationDocumentContainer> retrieveManatoryApplicatoinlDocListByApplNo(int applNo) {

        List<TotalApplicationDocumentContainer> applContList = new ArrayList<TotalApplicationDocumentContainer>();

        try {

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return applContList;
    }


    //학과별 문서요건 정보 조회
    private TotalApplicationDocumentContainer retrieveDeptDocumentByApplNo( int applNo) {
        TotalApplicationDocumentContainer rApplDoc = null;
        List<TotalApplicationDocumentContainer> applDocList;
        try{

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


        }catch (Exception e) {
            e.printStackTrace();

        }
        return rApplDoc;
    }

    //점수가 입력된 어학문서 정보 조회
    private  TotalApplicationDocumentContainer retrieveLanguageDocumentByApplNo( int applNo) {
        TotalApplicationDocumentContainer rApplDoc = null;
        List<TotalApplicationDocumentContainer> applDocList;
        try{
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


        }catch (Exception e) {
            e.printStackTrace();

        }
        return rApplDoc;
    }

    //입력된 학력정보별 문서요건 정보 조회
    private  TotalApplicationDocumentContainer retrieveAcademyDocumentByApplNo( int applNo, String admsNo ) {
        TotalApplicationDocumentContainer aCont = null;
        List<TotalApplicationDocumentContainer> applDocList = new ArrayList<TotalApplicationDocumentContainer>();
        ParamForAcademy param = new ParamForAcademy();

        try{
            aCont.setDisplayGrpFg(true);
            param.setApplNo(applNo);
            param.setAcadTypeCode("00002");//대학
            List<CustomApplicationAcademy>acadList;
            acadList =commonDAO.queryForList(NAME_SPACE + "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode", param, CustomApplicationAcademy.class);

            //대학 갯수만큼 켄네이너 생성
            for(CustomApplicationAcademy aAcad : acadList){

                aCont = new TotalApplicationDocumentContainer();
                aCont.setApplNo( aAcad.getApplNo());
                aCont.setGrpLabel(aAcad.getSchlName() + " 관련서류");

                applDocList.add(aCont);

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
                codeParam.setItemTypeCode("00003");

                List<TotalApplicationDocumentContainer> subDocList;
                subDocList = commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class);

                //해외학위 필수서류 셋팅
                codeParam.setAdmsCodeGrp("SCHL_CNTR");
                codeParam.setAdmsCode(aAcad.getSchlCntrCode());
                subDocList.addAll(commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class));

                for( TotalApplicationDocumentContainer aSubDoc : subDocList ){
                    aSubDoc.setDocGrp(aAcad.getAcadSeq());
                    aSubDoc.setAdmsNo(admsNo);
                    aSubDoc.setSubContainer( getSubCodeDocumentContainer(aSubDoc));
                }

            }
            //대학원 갯수만큼 켄네이너 생성
            param.setAcadTypeCode("00004");//대학원
            acadList =commonDAO.queryForList(NAME_SPACE + "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode",param,CustomApplicationAcademy.class);

            for(CustomApplicationAcademy aAcad : acadList){

                aCont = new TotalApplicationDocumentContainer();
                aCont.setApplNo( aAcad.getApplNo());
                aCont.setGrpLabel( aAcad.getSchlName() + " 관련서류" );
                applDocList.add(aCont);
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
                codeParam.setItemTypeCode("00004");//대학원

                List<TotalApplicationDocumentContainer> subDocList;
                subDocList = commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class);

                //해외학위 필수서류 셋팅
                codeParam.setAdmsCodeGrp("SCHL_CNTR");
                codeParam.setAdmsCode(aAcad.getSchlCntrCode());
                subDocList.addAll(commonDAO.queryForList(NAME_SPACE +"CustomApplicationDocumentMapper.selectCodeMandatoryGroupByCode",codeParam,TotalApplicationDocumentContainer.class));

                for( TotalApplicationDocumentContainer aSubDoc : subDocList ){
                    aSubDoc.setDocGrp(aAcad.getAcadSeq());
                    aSubDoc.setAdmsNo(admsNo);
                    aSubDoc.setSubContainer( getSubCodeDocumentContainer(aSubDoc));
                }

            }
        }catch (Exception e) {
            e.printStackTrace();

        }
        return aCont;
    }

    //지원특성별 문서요건 정보 조회 - from mad_code 테이블
    private  List<TotalApplicationDocumentContainer> retrieveCodeDocumentByApplNo( int applNo, String admsNo  ) {
        List<TotalApplicationDocumentContainer> rContList = new ArrayList<TotalApplicationDocumentContainer>() ;
        TotalApplicationDocumentContainer aCont = null;

        try{
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


        }catch (Exception e) {
            e.printStackTrace();

        }
        return rContList;
    }

    private  List<TotalApplicationDocumentContainer> getSubDeptDocumentContainer( TotalApplicationDocumentContainer pCont){
        List<TotalApplicationDocumentContainer> rContList = null;

        try{
            if (!"Y".equals( pCont.getLastYn())) {
                rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalApplicationDocumentList", pCont, TotalApplicationDocumentContainer.class);
                if (rContList != null) {
                    for (TotalApplicationDocumentContainer aCont : rContList) {
                        aCont.setSubContainer(getSubDeptDocumentContainer(aCont));

                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();

        }
        return rContList;
    }

    private  List<TotalApplicationDocumentContainer> getSubCodeDocumentContainer( TotalApplicationDocumentContainer pCont){
        List<TotalApplicationDocumentContainer> rContList = null;

        try{
            if (!"Y".equals( pCont.getLastYn())) {
                rContList = commonDAO.queryForList(NAME_SPACE + "CustomApplicationDocumentMapper.selectTotalCodeApplicationDocumentList", pCont, TotalApplicationDocumentContainer.class);
                if (rContList != null) {
                    for (TotalApplicationDocumentContainer aCont : rContList) {
                        aCont.setSubContainer(getSubCodeDocumentContainer(aCont));

                    }
                }
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
        }catch (Exception e) {
            e.printStackTrace();

        }
        return rContList;
    }
}
