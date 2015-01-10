package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값
    private final String APP_INFO_SAVED = "00001";       // 기본정보 저장

    @Override
    public ExecutionContext createBasis(Application application,
                                        ApplicationGeneral applicationGeneral) {
        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0, applNo = 0;
        Date date = new Date();

        application.setApplStsCode(APP_INFO_SAVED);
        application.setCreDate(date);
        r1 = commonDAO.insertItem(application, NAME_SPACE, "ApplicationMapper");

        Application tA = commonDAO.queryForObject(NAME_SPACE + "CustomApplicationMapper.selectApplByApplForInsertOthers",
                application, Application.class);
        applNo = tA.getApplNo();

        applicationGeneral.setApplNo(applNo);
        applicationGeneral.setCreDate(date);
        r2 = commonDAO.insertItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");

        if ( r1 > 0 && r2 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U312"));
            ec.setData(new ApplicationIdentifier(applNo, tA.getApplStsCode(),
                    tA.getAdmsNo(), tA.getEntrYear(), tA.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U306"));
            String errCode = null;
            if ( r1 == 0 ) errCode = "ERR0001";
            else if ( r2 == 0 ) errCode = "ERR0006";
//            else if ( r3 == 0 ) errCode = messageResolver.getMessage("ERR0026");
            ec.setErrCode(errCode);
            ec.setData(new ApplicationIdentifier(0, APP_NULL_STATUS));
        }
        return ec;
    }

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
    public ExecutionContext updateBasis(Application application,
                                        ApplicationGeneral applicationGeneral) {
        ExecutionContext ec = new ExecutionContext();
        int r1 = 0, r2 = 0;
        Date date = new Date();

        application.setModDate(date);
        applicationGeneral.setModDate(date);
        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");
        r2 = commonDAO.updateItem(applicationGeneral, NAME_SPACE, "ApplicationGeneralMapper");

        if ( r1 > 0 && r2 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U315"));
            ec.setData(new ApplicationIdentifier(application.getApplNo(), application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U316"));
            String errCode = null;
            if ( r1 == 0 ) errCode = "ERR0003";
            else if ( r2 == 0 ) errCode = "ERR0008";
//            else if ( r3 == 0 ) errCode = messageResolver.getMessage("ERR0028");
            ec.setErrCode(errCode);
            ec.setData(new ApplicationIdentifier(application.getApplNo(), APP_NULL_STATUS));
        }
        return ec;
    }

    @Override
    public ExecutionContext deleteBasis(Application application, ApplicationGeneral applicationGeneral) {
        return null;
    }
}
