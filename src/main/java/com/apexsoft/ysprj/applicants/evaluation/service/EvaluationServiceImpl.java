package com.apexsoft.ysprj.applicants.evaluation.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.evaluation.domain.*;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.evaluation.domain.MandatoryNAppliedDoc;
import com.apexsoft.ysprj.applicants.evaluation.domain.ParamForApplicationMandatoryDoc;
import org.apache.batik.ext.awt.image.rendered.MultiplyAlphaRed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2014-08-12.
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.evaluation.sqlmap.EvaluationDocumentMapper";

    @Autowired
    private CommonDAO commonDAO;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값
    private final String APP_INFO_SAVED = "00001";       // 기본정보 저장
    private final String ACAD_SAVED = "00002";           // 학력 저장
    private final String LANG_CAREER_SAVED = "00003";    // 어학 및 경력 저장
    private final String FILEUPLOADE_SAVED = "00004";    // 첨부파일 저장


    private  DocGroupNode createDocGroup(){
        DocGroupNode rootGroup = new DocGroupNode("전체");
        DocGroupLeaf basicGroup = new DocGroupLeaf("기본");
        DocGroupLeaf overSeaGroup = new DocGroupLeaf("해외학위" );
        DocGroupNode academyGroup = new DocGroupNode("학력");
        DocGroupLeaf univGroup = new DocGroupLeaf("대학");
        DocGroupLeaf gradGroup = new DocGroupLeaf("대학원");
        DocGroupLeaf langGroup = new DocGroupLeaf("어학" );
        DocGroupLeaf instGroup = new DocGroupLeaf("학연산" );
        DocGroupLeaf forgnGroup = new DocGroupLeaf("외국인" );
        DocGroupLeaf etcGroup = new DocGroupLeaf("기타" );

        rootGroup.addDocGroup(basicGroup);
        rootGroup.addDocGroup(academyGroup);
        rootGroup.addDocGroup(overSeaGroup);
        rootGroup.addDocGroup(langGroup);
        rootGroup.addDocGroup(instGroup);
        rootGroup.addDocGroup(forgnGroup);
        rootGroup.addDocGroup(etcGroup);

        academyGroup.addDocGroup(univGroup);
        academyGroup.addDocGroup(gradGroup);
        return rootGroup;
    }
    @Override
    public  DocGroup retrieveDocGroupByApplNo( int applNo) {

        DocGroupNode docGroup = new DocGroupNode();
        DocGroupLeaf basicGroup = null;
        DocGroupLeaf forgnGroup = new DocGroupLeaf("외국인", 2);
        DocGroupLeaf instGroup = new DocGroupLeaf("학연산", 3);
        DocGroupLeaf langGroup = new DocGroupLeaf("어학", 4);
        DocGroupNode academyGroup = new DocGroupNode("학력", 5);
        DocGroupLeaf underGroup = new DocGroupLeaf("대학", 1);
        DocGroupLeaf gradGroup = new DocGroupLeaf("대학원", 2);
        DocGroupLeaf overSeaGroup = new DocGroupLeaf("해외학위", 3);
        DocGroupLeaf etcGroup = new DocGroupLeaf("기타", 6);

        ParamForApplicationMandatoryDoc paramMand = new ParamForApplicationMandatoryDoc();
        paramMand.setAdmsCorsNo(1);
        paramMand.setDetlMajCode("");


        try {

            basicGroup = retrieveByMajor(paramMand);
            forgnGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectFrgnDocListByMajor", paramMand, MandatoryNAppliedDoc.class));
            instGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectInstDocListByMajor", paramMand, MandatoryNAppliedDoc.class));
            langGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectLangDocListByMajor", paramMand, MandatoryNAppliedDoc.class));
            underGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectUnderDocListByMajor", paramMand, MandatoryNAppliedDoc.class));
            gradGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectGradDocListByMajor", paramMand, MandatoryNAppliedDoc.class));
            overSeaGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectOverSeaDocListByMajor", paramMand, MandatoryNAppliedDoc.class));

            academyGroup.addDocGroup(underGroup);
            academyGroup.addDocGroup(gradGroup);
            academyGroup.addDocGroup(overSeaGroup);

            etcGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectEtcDocListByMajor", paramMand, MandatoryNAppliedDoc.class));

            docGroup.addDocGroup(basicGroup);
            docGroup.addDocGroup(academyGroup);
            docGroup.addDocGroup(overSeaGroup);
            docGroup.addDocGroup(langGroup);
            docGroup.addDocGroup(instGroup);
            docGroup.addDocGroup(forgnGroup);
            docGroup.addDocGroup(etcGroup);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return docGroup;
    }

    //전형 체계에 따른 필수 문서를 조회한다
    private DocGroupLeaf retrieveByMajor(ParamForApplicationMandatoryDoc paramMand ){

        DocGroupLeaf basicGroup = new DocGroupLeaf("기본",1);
        List<MandatoryNAppliedDoc> madDocList = null;
        List<MandatoryNAppliedDoc> admsDocList =null;
        List<MandatoryNAppliedDoc> admsDeptDocList =null;
        List<MandatoryNAppliedDoc> admsCorsDocList =null;
        List<MandatoryNAppliedDoc> admsCorsMajDocList =null;

        admsDocList = commonDAO.queryForList(NAME_SPACE + "selectAdmsMandatoryDoc", paramMand, MandatoryNAppliedDoc.class);
        admsDeptDocList = commonDAO.queryForList(NAME_SPACE + "selectDeptMandatoryList", paramMand, MandatoryNAppliedDoc.class);
        admsCorsDocList = commonDAO.queryForList(NAME_SPACE + "selectAdmsCorsMandatoryDoc", paramMand, MandatoryNAppliedDoc.class);
        admsCorsMajDocList = commonDAO.queryForList(NAME_SPACE + "selectAdmsCorsMajMandatoryDoc", paramMand, MandatoryNAppliedDoc.class);

        madDocList= getValidDocItem(  admsDocList, admsDeptDocList, admsCorsDocList, admsCorsMajDocList);
        basicGroup.setMandDocList(madDocList );
        return basicGroup;
    }
    //중복된 문서는 하부 설정에 우선하므로 상위에서 설정된 조건을 제거한다
    private  List<MandatoryNAppliedDoc> getValidDocItem(List<MandatoryNAppliedDoc> admsDocList,
                                                        List<MandatoryNAppliedDoc> admsDeptDocList,
                                                        List<MandatoryNAppliedDoc> admsCorsDocList,
                                                        List<MandatoryNAppliedDoc> admsCorsMajDocList){

        List<MandatoryNAppliedDoc> manDocList = new ArrayList<MandatoryNAppliedDoc>();
        for( MandatoryNAppliedDoc manDoc : admsCorsMajDocList ){
               manDoc.setBelong( "세부전공");
               manDocList.add( manDoc);
        }
        for( MandatoryNAppliedDoc manDoc : admsCorsMajDocList ){
             boolean newFg = true;
             for ( MandatoryNAppliedDoc prevManDoc: manDocList) {
                 if( prevManDoc.getDocItemCode().equals(manDoc.getDocItemCode())){
                     newFg = false;
                     break;
                 }
             }
            if(newFg){
                manDoc.setBelong( "지원과정");
                manDocList.add(manDoc);
            }

        }
        for( MandatoryNAppliedDoc manDoc : admsCorsMajDocList ){
            boolean newFg = true;
            for ( MandatoryNAppliedDoc prevManDoc: manDocList) {
                if( prevManDoc.getDocItemCode().equals(manDoc.getDocItemCode())){
                    newFg = false;
                    break;
                }
            }
            if(newFg) {
                manDoc.setBelong("지원학과");
                manDocList.add(manDoc);
            }
        }
        for( MandatoryNAppliedDoc manDoc : admsCorsMajDocList ){
            boolean newFg = true;
            for ( MandatoryNAppliedDoc prevManDoc: manDocList) {
                if( prevManDoc.getDocItemCode().equals(manDoc.getDocItemCode())){
                    newFg = false;
                    break;
                }
            }
            if(newFg) {
                manDoc.setBelong("지원전형");
                manDocList.add(manDoc);
            }
        }
        return manDocList;
    }
}
