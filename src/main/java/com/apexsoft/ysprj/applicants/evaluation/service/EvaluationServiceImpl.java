package com.apexsoft.ysprj.applicants.evaluation.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.evaluation.domain.*;
import com.apexsoft.ysprj.applicants.application.domain.*;
import com.apexsoft.ysprj.applicants.evaluation.domain.MandatoryNAppliedDoc;
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

       public  DocGroup retrieveDocGroupByApplNo( int applNo) {

        DocGroupNode docGroup = createDocGroup();
        DocGroupLeaf basicGroup = new DocGroupLeaf("기본",1);
        DocGroupLeaf forgnGroup = new DocGroupLeaf("외국인",2 );
        DocGroupLeaf instGroup = new DocGroupLeaf("학연산",3 );
        DocGroupLeaf langGroup = new DocGroupLeaf("어학",4 );
        DocGroupNode academyGroup = new DocGroupNode("학력",5);
        DocGroupLeaf underGroup = new DocGroupLeaf("대학",1);
        DocGroupLeaf gradGroup = new DocGroupLeaf("대학원",2);
        DocGroupLeaf overSeaGroup = new DocGroupLeaf("해외학위",3);
        DocGroupLeaf etcGroup = new DocGroupLeaf("기타",6);

        try {


            basicGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectBasicDocListByApplNoWTMandatory", applNo,MandatoryNAppliedDoc.class));
            forgnGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "select_________ByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            instGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectInstDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            langGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectLangDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));

            underGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectUnderDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            gradGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectGradDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));
            overSeaGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectOverSeaDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));

            academyGroup.addDocGroup(underGroup);
            academyGroup.addDocGroup(gradGroup);
            academyGroup.addDocGroup(overSeaGroup);

            etcGroup.setMandDocList(commonDAO.queryForList(NAME_SPACE + "selectEtcDocListByApplNoWTMandatory", applNo, MandatoryNAppliedDoc.class));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return docGroup;
    }



    public  List<DocGroupFile> retrieveManApplDocListByApplNo( int applNo) {
        List<DocGroupFile> docGrpList = new ArrayList<DocGroupFile>();
        DocGroupFile docGrp;
        DocGroupFile docSubGrp;
        List<MandatoryNAppliedDoc> tmpDocList;

        try {
            docGrp = new DocGroupFile();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return docGrpList;
    }

}
