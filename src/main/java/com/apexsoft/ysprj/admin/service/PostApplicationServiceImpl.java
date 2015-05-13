package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.admin.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class PostApplicationServiceImpl implements PostApplicationService{


    private final static String NAME_SPACE = "com.apexsoft.ysprj.admin.sqlmap.";

    private final static String NAME_SPACE_INFO= "admin.applicant.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private CommonService commonService;

    @Override
    public ExecutionContext checkDocumentRead (int applNo, String userId ) {

        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        int  insert = 0;
        int  rInsert = 0;
        Boolean applChangeFg = false;


        ApplicantInfo applInfo = null;
        applInfo = commonDAO.queryForObject(NAME_SPACE_INFO+"retrieveApplicantInfoByKey", applNo, ApplicantInfo.class);
        if( applInfo == null ){
            //에러 로직 처리
        }

        Date date = new Date();
        ApplicationCheck appChk = new ApplicationCheck();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");



        int maxSeq =0;

        rInsert++;
        maxSeq = commonDAO.queryForInt(NAME_SPACE + "CustomApplicationCheckMapper.selectMaxSeqByApplNo", applNo);
        maxSeq ++;
        appChk.setChkNo(maxSeq);
        appChk.setAdmsNo(applInfo.getAdmsNo());
        appChk.setApplNo(applNo);
        appChk.setChkYn("Y");
        appChk.setChkDay( format.format(date));
        appChk.setChkUserId( userId);
        appChk.setChkCode("00002");
        appChk.setCreId(userId);
        appChk.setCreDate(date);
        insert = insert +commonDAO.insertItem( appChk, NAME_SPACE, "ApplicationCheckMapper");


        if ( insert == rInsert) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U319"));
            ecDataMap.put("applInfo",applInfo );
            ec.setData(ecDataMap);

        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U320"));
            String errCode = null;
            if ( insert != rInsert ) errCode = "ERR0017";
            ec.setErrCode(errCode);
            throw new YSBizException(ec);
        }
        return ec;
    }
}
