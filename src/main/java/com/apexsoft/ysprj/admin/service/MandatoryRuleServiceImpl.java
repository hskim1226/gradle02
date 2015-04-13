package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.admin.domain.MandatoryContainer;
import com.apexsoft.ysprj.applicants.application.domain.ParamForApplicationMandatoryDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MandatoryRuleServiceImpl implements MandatoryRuleService {
	

    private final static String NAME_SPACE = "admin.guideline.GuidelineMapper.";
    
    @Autowired
    private CommonDAO commonDAO;


    /* 코드별 필수 요건그룹을 생성하여 반환한다
    *
    */
    private  List<MandatoryContainer> getWholeCodeMandTree( String admsNo, String codeGrp, String code ){

        List<MandatoryContainer> mandList;

        //TODO 파라미터 셋팅해야 함.
        //mandList = getWholeMandatoryContainer( "CODE", param );
        return null;
    }



    /* 학과-전공 필수 요건그룹을 생성하여 반환한다
    *  파라미터 조건인 그룹번호( 00005 어학) 별로 조회된다
    *
    */
    public  List<MandatoryContainer> getWholeDeptMajMandTree( ParamForApplicationMandatoryDoc param ){


        List<MandatoryContainer> commMandList;

        List<MandatoryContainer> admsList;
        List<MandatoryContainer> admsDeptList;
        List<MandatoryContainer> admsCorsList;
        List<MandatoryContainer> admsCorsMajList;

        admsList = getWholeMandatoryContainer("ADMS", param );
        admsDeptList = getWholeMandatoryContainer("ADMS_DEPT", param );
        admsCorsList = getWholeMandatoryContainer("ADMS_CORS", param );
        admsCorsMajList =getWholeMandatoryContainer("ADMS_CORS_MAJ", param );

        commMandList = getValidMandItem(admsList,admsDeptList, admsCorsList, admsCorsMajList);

        return commMandList;
    }


    //학과-전공 필수요건의 계층을 따라서 조회한다
    private List<MandatoryContainer> getWholeMandatoryContainer(String tbName,ParamForApplicationMandatoryDoc param) {


        List<MandatoryContainer> rContList = null;
        try {
            if ("ADMS".equals(tbName)) {
                rContList = commonDAO.queryForList(NAME_SPACE + "selectAdmsMandatoryList", param, MandatoryContainer.class);
            }else { if("ADMS_DEPT".equals(tbName)) {
                rContList = commonDAO.queryForList(NAME_SPACE + "selectDeptMandatoryList", param, MandatoryContainer.class);
            }else { if( "ADMS_CORS".equals(tbName)) {
                rContList = commonDAO.queryForList(NAME_SPACE + "selectAdmsCorsMandatoryList", param, MandatoryContainer.class);
            }else { if( "ADMS_CORS_MAJ".equals(tbName)) {
                rContList = commonDAO.queryForList(NAME_SPACE + "selectAdmsCorsMajMandatoryList", param, MandatoryContainer.class);
            }else { if( "CODE".equals(tbName)) {
                rContList = commonDAO.queryForList(NAME_SPACE + "selectCodeMandatoryList", param, MandatoryContainer.class);
            }}}}}

            if( rContList!= null){
                for ( MandatoryContainer aCont : rContList){
                    aCont.setSubContList( getSubContainer(tbName,aCont));

                }
            }
        }catch (Exception e) {
            e.printStackTrace();

        }
        return rContList;
    }

    //학과-전공 하부 계층의 모든 필수요건을 조회한다
    private List<MandatoryContainer> getSubContainer(String tbName, MandatoryContainer pCont) {

        List<MandatoryContainer> rContList = null;
        try {
            if (!"Y".equals(pCont.getLastYn())) {

                if ("ADMS".equals(tbName)) {
                    rContList = commonDAO.queryForList(NAME_SPACE + "selectAdmsMandatoryList", pCont, MandatoryContainer.class);
                }else { if("ADMS_DEPT".equals(tbName)) {
                    rContList = commonDAO.queryForList(NAME_SPACE + "selectDeptMandatoryList", pCont, MandatoryContainer.class);
                }else { if( "ADMS_CORS".equals(tbName)) {
                    rContList = commonDAO.queryForList(NAME_SPACE + "selectAmdsCorsMandatoryList", pCont, MandatoryContainer.class);
                }else { if( "ADMS_CORS_MAJ".equals(tbName)) {
                    rContList = commonDAO.queryForList(NAME_SPACE + "selectAmdsCorsMajMandatoryList", pCont, MandatoryContainer.class);
                }else { if( "CODE".equals(tbName)) {
                    rContList = commonDAO.queryForList(NAME_SPACE + "selectCodeMandatoryList", MandatoryContainer.class);
                }}}}}

                if( rContList!= null){
                    for ( MandatoryContainer aCont : rContList){
                        aCont.setSubContList( getSubContainer(tbName,aCont));

                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rContList;

    }

    //학과-전공 중복된 조건은 하부 설정에 우선하므로 상위의 설정된 조건을 덮어쓴다
    private  List<MandatoryContainer> getValidMandItem(List<MandatoryContainer> admsMandList,
                                                       List<MandatoryContainer> admsDeptMandList,
                                                       List<MandatoryContainer> admsCorsMandList,
                                                       List<MandatoryContainer> admsCorsMajMandList){

        List<MandatoryContainer> mandList = new ArrayList<MandatoryContainer>();
        for( MandatoryContainer mandItem : admsCorsMajMandList ){
            mandItem.setBelong("세부전공");
            mandList.add(mandItem);
        }
        for( MandatoryContainer mandItem : admsCorsMandList ){
            boolean newFg = true;
            for ( MandatoryContainer prevMand: mandList) {
                if(  prevMand.getGrpCode().equals(mandItem.getGrpCode()) &&
                        prevMand.getGrpLevel() == mandItem.getGrpLevel() &&
                        prevMand.getItemCodeGrp().equals(mandItem.getItemCodeGrp()) &&
                        prevMand.getItemCode().equals(mandItem.getItemCode())){
                    newFg = false;
                    break;
                }
            }
            if(newFg){
                mandItem.setBelong("지원과정");
                mandList.add(mandItem);
            }

        }
        for( MandatoryContainer mandItem : admsDeptMandList ){
            boolean newFg = true;
            for ( MandatoryContainer prevMand: mandList) {
                if(  prevMand.getGrpCode().equals(mandItem.getGrpCode()) &&
                        prevMand.getGrpLevel() == mandItem.getGrpLevel() &&
                        prevMand.getItemCodeGrp().equals(mandItem.getItemCodeGrp()) &&
                        prevMand.getItemCode().equals(mandItem.getItemCode())){
                    newFg = false;
                    break;
                }
            }
            if(newFg) {
                mandItem.setBelong("지원학과");
                mandList.add(mandItem);
            }
        }
        for( MandatoryContainer mandItem : admsMandList ){
            boolean newFg = true;
            for ( MandatoryContainer prevMand: mandList) {
                if(  prevMand.getGrpCode().equals(mandItem.getGrpCode()) &&
                        prevMand.getGrpLevel() == mandItem.getGrpLevel() &&
                        prevMand.getItemCodeGrp().equals(mandItem.getItemCodeGrp()) &&
                        prevMand.getItemCode().equals(mandItem.getItemCode())){
                    newFg = false;
                    break;
                }
            }
            if(newFg) {
                mandItem.setBelong("지원전형");
                mandList.add(mandItem);
            }
        }
        return mandList;
    }


    /* 조회된 학과-전공 언어요건에 따라서 전체 전공별 언어요건을 생성한다
    *
    */
    private void  makeAdmsCorsMajLangInof( String admsNo  ){

        List<MandatoryContainer> mandList;
        ParamForApplicationMandatoryDoc param = new ParamForApplicationMandatoryDoc();
        param.setAdmsNo(admsNo);
        //TODO 파라미터 확인 필요

        try {
            mandList = getWholeDeptMajMandTree(param);
            insertLangMandInfo(mandList);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //언어 요건의 계층을 모두 입력한다.
    private void  insertLangMandInfo(  List<MandatoryContainer> mandList  ){
        try{
           for (MandatoryContainer aCont : mandList){
               //TODO insert 문. ADMS_CORS_MAJ_LANG 에 해당 전공번호로 입력
               //container 정보에 Key 가 될 정보 포함 (seq 제외)
               if( aCont.getSubContList() != null && aCont.getSubContList().size() >0 ){
                   insertLangMandInfo( aCont.getSubContList() );
               }
           }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* 조회된 학과-전공 필수문서요건에 따라서 전체 전공별 필수문서요건을 생성한다
    *
    */
    private void  makeAdmsCorsMajMdtInfo( String admsNo  ){

        List<MandatoryContainer> mandList;
        ParamForApplicationMandatoryDoc param = new ParamForApplicationMandatoryDoc();
        param.setAdmsNo(admsNo);
        //TODO 파라미터 확인 필요
        try {
            mandList = getWholeDeptMajMandTree(param);
            insertdDocMandInfo(mandList);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //필수문서의 계층을 모두 입력한다.
    private void  insertdDocMandInfo(  List<MandatoryContainer> mandList  ){
        try{
            for (MandatoryContainer aCont : mandList){
                //TODO insert 문. ADMS_CORS_MAJ_MDT 에 해당 전공번호로 입력
                //container 정보에 Key 가 될 정보 포함 (seq 제외)
                if( aCont.getSubContList() != null && aCont.getSubContList().size() >0 ){
                    insertdDocMandInfo( aCont.getSubContList() );
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }





}
