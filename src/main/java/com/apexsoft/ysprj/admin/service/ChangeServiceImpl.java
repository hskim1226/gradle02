package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.admin.control.form.ChangeInfoForm;
import com.apexsoft.ysprj.admin.control.form.ChangeSearchPageForm;
import com.apexsoft.ysprj.admin.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DhKim on 2014-09-14.
 */
@Service
public class ChangeServiceImpl implements ChangeService {

    private final static String NAME_SPACE = "admin.change.";
    private final static String NAME_SPACE_INFO= "admin.applicant.";
    @Autowired
    private CommonDAO commonDAO;

    public void createInfoChange( ChangeInfoForm changeInfoForm ) {
        ApplicantInfo applInfo = null;

        applInfo = commonDAO.queryForObject(NAME_SPACE_INFO+"retrieveApplicantInfoByKey", changeInfoForm.getApplNo(), ApplicantInfo.class);
        if( applInfo == null ){
            //에러 로직 처리
        }
        String itemName = changeInfoForm.getInfoRadio();
        String colName = "";
        String tblName = "appl";

        if( itemName.equals("korName")){
            changeInfoForm.setBeforeItem( applInfo.getKorName());
            colName = "KOR_NAME";
        }
        if( itemName.equals("engName")){
            changeInfoForm.setBeforeItem( applInfo.getEngName());
            colName = "eng_name";
        }
        if( itemName.equals("engSur")){
            changeInfoForm.setBeforeItem( applInfo.getEngSur());
            colName = "eng_sur";
        }
        if( itemName.equals("rgstNo")){
            changeInfoForm.setBeforeItem( applInfo.getRgstNo());
            colName = "rgst_no";
        }
        if( itemName.equals("telNum")){
            changeInfoForm.setBeforeItem( applInfo.getTelNum());
            colName = "TEL_NUM";
        }
        if( itemName.equals("mobiNum")){
            changeInfoForm.setBeforeItem( applInfo.getMobiNum());
            colName = "MOBI_NUM";
        }
        if( itemName.equals("mailAddr")){
            changeInfoForm.setBeforeItem( applInfo.getMailAddr());
            colName = "MAIL_ADDR";
        }
        if( itemName.equals("addr")){
            changeInfoForm.setBeforeItem( applInfo.getAddr());
            colName = "ADDR";
        }
        if( itemName.equals("detlAddr")){
            changeInfoForm.setBeforeItem( applInfo.getDetlAddr());
            colName = "DETL_ADDR";
        }
        if( itemName.equals("emerContName")){
            changeInfoForm.setBeforeItem( applInfo.getEmerContName());
            colName = "EMER_CONT_NAME";
            tblName = "gene";
        }
        if( itemName.equals("emerContTel")){
            changeInfoForm.setBeforeItem( applInfo.getEmerContTel());
            colName = "EMER_CONT_TEL";
            tblName = "gene";
        }
        changeInfoForm.setColName(colName);
        SimpleDateFormat dayFormat =  new SimpleDateFormat("yyyyMMdd");
        long todayTime = System.currentTimeMillis();

        ApplicationChange appChg = new ApplicationChange();
        appChg.setApplNo(changeInfoForm.getApplNo());
        appChg.setAdmsNo(changeInfoForm.getAdmsNo());
        appChg.setReqDay( dayFormat.format(new Date(todayTime)));
        appChg.setReqUserId("-UserId--");
        appChg.setReqName("--홍길동---");
        appChg.setApplChgCode("00001");//정보변경
        appChg.setChgStsCode("00001");//접수


        ApplicationChangeItem appItemChg = new ApplicationChangeItem();
        appItemChg.setAdmsNo(changeInfoForm.getAdmsNo());
        appItemChg.setAftItemDetl(changeInfoForm.getAfterItem());
        appItemChg.setBefItemDetl(changeInfoForm.getBeforeItem());
        appItemChg.setItemName(itemName);
        appItemChg.setChgItemSeq(1);
        if(false){
        if(tblName.equals("appl")){
            commonDAO.queryForInt(NAME_SPACE+"changeOneApplInfoByApplNo", changeInfoForm);
        }else {
            commonDAO.queryForInt(NAME_SPACE + "changeOneApplGeneInfoByApplNo", changeInfoForm);
        }}
        int seqNo =0;
        seqNo = commonDAO.queryForInt(NAME_SPACE+"retrieveChangeMaxKey", changeInfoForm);
        seqNo = seqNo+1;
        appChg.setChgNo(seqNo);
        appItemChg.setChgNo(seqNo);

        commonDAO.queryForInt(NAME_SPACE+"insertChg", appChg);
        commonDAO.queryForInt(NAME_SPACE+"insertItem", appItemChg);

    }


    public PageInfo<ChangeInfo> retrieveChangePaginatedList(ChangeSearchPageForm searchForm){

        PageStatement tempStst = new PageStatement(NAME_SPACE+"retrieveChangeCount", NAME_SPACE+"retrieveChangeList");
        PageInfo<ChangeInfo> tempPageInfo;
        tempPageInfo = commonDAO.queryForPagenatedList( tempStst, searchForm, searchForm.getPage().getNo(), searchForm.getPage().getRows() );
        List<ChangeInfo> tempInfoList = tempPageInfo.getData();
        return tempPageInfo;
    }

}
