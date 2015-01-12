package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 12.
 */
public class AcademyServiceImpl implements AcademyService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값
    private final String ACAD_SAVED = "00002";           // 학력 저장

    /**
     * 학력 정보 생성
     * @param application
     * @param collegeList
     * @param graduateList
     * @return
     */
    @Override
    public ExecutionContext createAcademy(Application application,
                                          List<CustomApplicationAcademy> collegeList,
                                          List<CustomApplicationAcademy> graduateList) {
        List<ApplicationAcademy> acadList = new ArrayList<ApplicationAcademy>();
        acadList.addAll(collegeList);
        acadList.addAll(graduateList);

        ExecutionContext ec = new ExecutionContext();
        int r1, r2 = 0, applNo = application.getApplNo(), idx = 0;
        Date date = new Date();

        application.setApplStsCode(ACAD_SAVED);
        application.setModDate(date);
        r1 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

        if ( acadList != null ) {
            for( ApplicationAcademy academy : acadList) {
                academy.setApplNo(applNo);
                academy.setAcadSeq(++idx);
                academy.setCreDate(date);
            }
            r2 = commonDAO.insertList(acadList, NAME_SPACE, "ApplicationAcademyMapper");
        }

        if ( r1 > 0 && r2 > 0 ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U317"));
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U318"));
            String errCode = null;
            if ( r1 == 0 ) errCode = "ERR0003";
            else if ( r2 == 0 ) errCode = "ERR0011";
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode(errCode);
        }
        return ec;
    }

    @Override
    public ExecutionContext retrieveAcademy(int applNo) {
        String aaMapperSqlId = "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode";
        ExecutionContext ec = new ExecutionContext();
        Academy academy = new Academy();

        Application application = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        application = application == null ? new Application() : application;
        academy.setApplication(application);

        ParamForAcademy paramForAcademy = new ParamForAcademy();
        paramForAcademy.setApplNo(applNo);
        paramForAcademy.setAcadTypeCode("00002");
        academy.setCollegeList(retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, CustomApplicationAcademy.class));
        paramForAcademy.setAcadTypeCode(("00003"));
        academy.setGraduateList(retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, CustomApplicationAcademy.class));

        ec.setResult(ExecutionContext.SUCCESS);
        ec.setData(academy);

        return ec;
    }

    @Override
    public ExecutionContext updateAcademy(Application application, List<CustomApplicationAcademy> collegeList, List<CustomApplicationAcademy> graduateList) {
        List<ApplicationAcademy> acadList = new ArrayList<ApplicationAcademy>();
        acadList.addAll(collegeList);
        acadList.addAll(graduateList);

        ExecutionContext ec = new ExecutionContext();
        int applNo = application.getApplNo();
        ParamForAcademy param = new ParamForAcademy();
        param.setApplNo(applNo);
        param.setOrderBy("ACAD_SEQ");

        param.setAcadTypeCode("00002");
        int r1 = processAcademy(application, collegeList, applNo, new Date(), param);

        param.setAcadTypeCode("00003");
        int r2 = processAcademy(application, graduateList, applNo, new Date(), param);//

        if ( r1 == collegeList.size() && r2 == graduateList.size() ) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U317"));
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U318"));
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            ec.setErrCode("ERR0013");
        }
        return ec;
    }

    /**
     * 현재 ACAD LIST 가져와서 HashMap을 만들고
     * 화면에서 가져온 리스트 돌면서 HashMap 체크해서
     * 케이스 별로 CRUD 처리
     * 화면 seq 있고, DB seq 있으면 화면 레코드로 수정 하고 Map에서 삭제
     * 화면 seq 있고, DB seq 없는 케이스는 없음
     * 화면 seq 공백, DB seq 없으면 화면 레코드로 생성
     * 화면 seq 공백, DB seq 있으면 Map에 남아있으므로 DB레코드 삭제 하고 Map에서 삭제
     *
     * @param application
     * @param academyList
     * @param applNo
     * @param date
     * @param param
     * @return
     */
    private int processAcademy(Application application,
                               List<CustomApplicationAcademy> academyList,
                               int applNo,
                               Date date,
                               ParamForAcademy param) {

        int c1 = 0, u1 = 0, d1 = 0, lastSeq = 1;
        Map<Integer, Integer> seqMap = new HashMap<Integer, Integer>();

        List<ApplicationAcademy> academiesFromDB = commonDAO.queryForList(NAME_SPACE+"CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode", param, ApplicationAcademy.class);
        if ( academiesFromDB.size() > 0 ) {
            lastSeq = academiesFromDB.get(academiesFromDB.size()-1).getAcadSeq();

            if ( academiesFromDB != null ) {
                for (ApplicationAcademy academy : academiesFromDB) {
                    seqMap.put(academy.getAcadSeq(), academy.getAcadSeq());
                }
            }
        }

        if ( academyList != null ) {
            for( ApplicationAcademy academyFromView : academyList) {
                int acadSeqFromView = academyFromView.getAcadSeq() == null ? -1 : academyFromView.getAcadSeq();
                if ( acadSeqFromView > 0) { //화면 seq 있는 경우
                    if ( seqMap.containsKey(acadSeqFromView) ) { //화면 seq 값이 DB에도 있는 경우
                        //update
                        academyFromView.setModId(application.getUserId());
                        academyFromView.setModDate(date);
                        u1 += commonDAO.updateItem(academyFromView, NAME_SPACE, "ApplicationAcademyMapper");
                        seqMap.remove(acadSeqFromView);
                    }
                } else { // 화면 seq 가 숫자가 아닌 경우
                    // 새 seq 부여해서 insert
                    academyFromView.setApplNo(applNo);
                    academyFromView.setAcadSeq(++lastSeq);
                    academyFromView.setCreId(application.getUserId());
                    academyFromView.setCreDate(date);
                    c1 += commonDAO.insertItem(academyFromView, NAME_SPACE, "ApplicationAcademyMapper");
                }
            }
            //Map에 남아있는 레코드는 delete
            Set<Map.Entry<Integer, Integer>> entrySet = seqMap.entrySet();
            for(Map.Entry<Integer, Integer> entry : entrySet) {
                int seqFromDB = entry.getKey();
                param.setAcadSeq(seqFromDB);
                d1 += commonDAO.delete(NAME_SPACE + "CustomApplicationAcademyMapper.deleteByApplNoAcadTypeCodeAcadSeq", param);
            }
        }
        return c1 + u1;
    }

    @Override
    public ExecutionContext deleteAcademy(Application application, List<CustomApplicationAcademy> collegeList, List<CustomApplicationAcademy> graduateList) {
        return null;
    }

    private <T> List<T> retrieveInfoListByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz) {
        List<T> infoList = null;

        infoList = commonDAO.queryForList(NAME_SPACE + mapperNameSqlId,
                    parameter, clazz);


        return infoList;
    }
}
