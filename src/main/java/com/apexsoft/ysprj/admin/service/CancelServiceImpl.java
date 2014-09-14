package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.admin.control.form.ChangeInfoForm;
import com.apexsoft.ysprj.admin.domain.ApplicantInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by DhKim on 2014-09-14.
 */
public class CancelServiceImpl implements  CancelService {

    private final static String NAME_SPACE = "admin.cancel.";
    @Autowired
    private CommonDAO commonDAO;

    public void changeApplInof( ChangeInfoForm changeInfoForm ) {
        ApplicantInfo applInfo = null;
        applInfo = commonDAO.queryForObject(NAME_SPACE+"retrieveApplicantInfoByKey", changeInfoForm.getApplNo(), ApplicantInfo.class);
        if( applInfo == null ){
            //에러 로직 처리
        }
        commonDAO.queryForInt(NAME_SPACE+"changeOneApplInfoByApplNo", changeInfoForm);
        commonDAO.queryForInt(NAME_SPACE+"insertCancelLog", changeInfoForm);

    }

}
