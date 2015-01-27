package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.exception.YSNoRedirectBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by hanmomhanda on 15. 1. 9.
 *
 * 원서 기본 정보 CRUD
 */
@Service
public class BasisServiceImpl implements BasisService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String APP_INFO_SAVED = "00001";       // 기본정보 저장

//    @Override
//    public ExecutionContext createBasis(Application application,
//                                        ApplicationGeneral applicationGeneral) {
//        ExecutionContext ec = new ExecutionContext();
//        int r1 = 0, r2 = 0, applNo = 0;
//        Date date = new Date();
//
//        application.setApplStsCode(APP_INFO_SAVED);
//        application.setCreDate(date);
//        r1 = commonDAO.insertItem(application, NAME_SPACE, "CustomApplicationMapper");
//        applNo = application.getApplNo();
//
//        applicationGeneral.setApplNo(applNo);
//        applicationGeneral.setCreDate(date);
//        r2 = commonDAO.insertItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");
//
//        if ( r1 > 0 && r2 > 0 ) {
//            ec.setResult(ExecutionContext.SUCCESS);
//            ec.setMessage(messageResolver.getMessage("U312"));
//            ec.setData(new ApplicationIdentifier(applNo));
//        } else {
//            ec.setResult(ExecutionContext.FAIL);
//            ec.setMessage(messageResolver.getMessage("U306"));
//            String errCode = null;
//            if ( r1 == 0 ) errCode = "ERR0001";
//            else if ( r2 == 0 ) errCode = "ERR0006";
////            else if ( r3 == 0 ) errCode = messageResolver.getMessage("ERR0026");
//            ec.setErrCode(errCode);
//            ec.setData(new ApplicationIdentifier(0, APP_NULL_STATUS));
//        }
//        return ec;
//    }

    @Override
    public ExecutionContext retrieveBasis(int applNo) {
        ExecutionContext ec = new ExecutionContext();
        Basis basis = new Basis();

        Application application = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        application = application == null ? new Application() : application;
        basis.setApplication(application);

        ApplicationGeneral applicationGeneral = commonDAO.queryForObject(NAME_SPACE + "ApplicationGeneralMapper.selectByPrimaryKey",
                applNo, ApplicationGeneral.class);
        applicationGeneral = applicationGeneral == null ? new ApplicationGeneral() : applicationGeneral;
        basis.setApplicationGeneral(applicationGeneral);

        ApplicationForeigner applicationForeigner = commonDAO.queryForObject(NAME_SPACE + "ApplicationForeignerMapper.selectByPrimaryKey",
                applNo, ApplicationForeigner.class);
        applicationForeigner = applicationForeigner == null ? new ApplicationForeigner() : applicationForeigner;
        basis.setApplicationForeigner(applicationForeigner);

        ec.setResult(ExecutionContext.SUCCESS);
        ec.setData(basis);

        return ec;
    }

    @Override
    public ExecutionContext saveBasis(Basis basis) {
        ExecutionContext ec = new ExecutionContext();
        Application application = basis.getApplication();
        ApplicationGeneral applicationGeneral = basis.getApplicationGeneral();
        String userId = application.getUserId();
        boolean isMultipleApplicationAllowed = true;
        boolean isValidInsertRequest = false;
        boolean isInsert = false;
        int r1 = 0, r2 = 0, applNo;
        Date date = new Date();

        if (application.getApplNo() == null) {
            isInsert = true;
            if (isMultipleApplicationAllowed) {
                isValidInsertRequest = true;
            } else {
                if (hasApplication(userId)) {
                    isValidInsertRequest = false;
                } else {
                    isValidInsertRequest = true;
                }
            }
            if (isValidInsertRequest) {
                application.setApplStsCode(APP_INFO_SAVED);
                application.setCreId(userId);
                application.setCreDate(date);
                r1 = commonDAO.insertItem(application, NAME_SPACE, "CustomApplicationMapper");
                applNo = application.getApplNo();

                applicationGeneral.setApplNo(applNo);
                applicationGeneral.setCreId(userId);
                applicationGeneral.setCreDate(date);
                r2 = commonDAO.insertItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");
            }
        } else {
            isInsert = false;
            application.setModId(userId);
            application.setModDate(date);
            applicationGeneral.setModId(userId);
            applicationGeneral.setModDate(date);
            r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
            r2 = commonDAO.updateItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");
        }

        if ( r1 > 0 && r2 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U315"));
            ec.setData(new ApplicationIdentifier(application.getApplNo()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U316"));
            String errCode = null;
            if (isInsert) {
                if (r1 == 0) errCode = "ERR0001";
                else if (r2 == 0) errCode = "ERR0006";
            } else {
                if (r1 == 0) errCode = "ERR0003";
                else if (r2 == 0) errCode = "ERR0008";
            }
            ec.setErrCode(errCode);
            if (!isValidInsertRequest) {
                YSNoRedirectBizException nrBizException = new YSNoRedirectBizException(ec);
                Map<String, Object> map = nrBizException.getPreviousDataMap();
                map.put("basis", basis);
                throw nrBizException;
            } else {
                applNo = application.getApplNo() == null ? 0 : application.getApplNo();
                ec.setData(new ApplicationIdentifier(applNo));
                throw new YSBizException(ec);
            }
        }

        return ec;
    }

    private boolean hasApplication(String userId) {
        Application application = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationMapper.selectApplByUserId",
                userId, Application.class);

        return application != null;
    }

//    @Override
//    public ExecutionContext updateBasis(Application application,
//                                        ApplicationGeneral applicationGeneral) {
//        ExecutionContext ec = new ExecutionContext();
//        int r1 = 0, r2 = 0;
//        Date date = new Date();
//
//        application.setModDate(date);
//        applicationGeneral.setModDate(date);
//        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
//        r2 = commonDAO.updateItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");
//
//        if ( r1 > 0 && r2 > 0 ) {
//            ec.setResult(ExecutionContext.SUCCESS);
//            ec.setMessage(messageResolver.getMessage("U315"));
//            ec.setData(new ApplicationIdentifier(application.getApplNo(), application.getApplStsCode(),
//                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
//        } else {
//            ec.setResult(ExecutionContext.FAIL);
//            ec.setMessage(messageResolver.getMessage("U316"));
//            String errCode = null;
//            if ( r1 == 0 ) errCode = "ERR0003";
//            else if ( r2 == 0 ) errCode = "ERR0008";
//            ec.setErrCode(errCode);
//            ec.setData(new ApplicationIdentifier(application.getApplNo(), APP_NULL_STATUS));
//        }
//        return ec;
//    }

//    @Override
//    public ExecutionContext deleteBasis(Application application, ApplicationGeneral applicationGeneral) {
//        return null;
//    }
}
