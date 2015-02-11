package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSNoRedirectBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by hanmomhanda on 15. 1. 12.
 */
@Service
public class AcademyServiceImpl implements AcademyService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Resource(name = "messageResolver")
    MessageResolver messageResolver;

    private final String APP_NULL_STATUS = "00000";      // 에러일 때 반환값
    private final String ACAD_SAVED = "00002";           // 학력 저장

    @Override
    public ExecutionContext retrieveAcademy(int applNo) {
        String aaMapperSqlId = "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode";
        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();

        Academy academy = new Academy();

        Application application = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        application = application == null ? new Application() : application;
        academy.setApplication(application);

        ParamForAcademy paramForAcademy = new ParamForAcademy();
        paramForAcademy.setApplNo(applNo);
        paramForAcademy.setAcadTypeCode("00002");
        List<CustomApplicationAcademy> collegeList = retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, CustomApplicationAcademy.class);
        academy.setCollegeList(setUserDataStatus(collegeList, UserCUDType.UPDATE));

        paramForAcademy.setAcadTypeCode(("00003"));
        List<CustomApplicationAcademy> graduateList = retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, CustomApplicationAcademy.class);
        academy.setGraduateList(setUserDataStatus(graduateList, UserCUDType.UPDATE));

        ec.setResult(ExecutionContext.SUCCESS);
        ecDataMap.put("academy", academy);
        ec.setData(ecDataMap);

        return ec;
    }

    @Override
    public ExecutionContext retrieveAcademy(Academy academy) {
        String aaMapperSqlId = "CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode";
        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        int applNo = academy.getApplication().getApplNo();

        Application application = commonDAO.queryForObject(NAME_SPACE + "ApplicationMapper.selectByPrimaryKey",
                applNo, Application.class);
        application = application == null ? new Application() : application;
        academy.setApplication(application);

        ParamForAcademy paramForAcademy = new ParamForAcademy();
        paramForAcademy.setApplNo(applNo);
        paramForAcademy.setAcadTypeCode("00002");
        List<CustomApplicationAcademy> collegeList = retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, CustomApplicationAcademy.class);
        academy.setCollegeList(setUserDataStatus(collegeList, UserCUDType.UPDATE));

        paramForAcademy.setAcadTypeCode(("00003"));
        List<CustomApplicationAcademy> graduateList = retrieveInfoListByParamObj(paramForAcademy, aaMapperSqlId, CustomApplicationAcademy.class);
        academy.setGraduateList(setUserDataStatus(graduateList, UserCUDType.UPDATE));

        ec.setResult(ExecutionContext.SUCCESS);
        ecDataMap.put("academy", academy);
        ec.setData(ecDataMap);

        return ec;
    }

    @Override
    public ExecutionContext saveAcademy(Academy academy) {

        ExecutionContext ec = new ExecutionContext();
        Map<UserCUDType, Integer> iudMapCollege;
        Map<UserCUDType, Integer> iudMapGraduate;

        Application application = academy.getApplication();
        int applNo = application.getApplNo();

        List<CustomApplicationAcademy> collegeList = academy.getCollegeList();
        List<CustomApplicationAcademy> graduateList = academy.getGraduateList();
        List<CustomApplicationAcademy> acadList = new ArrayList<CustomApplicationAcademy>();
        acadList.addAll(collegeList);
        acadList.addAll(graduateList);
        int insert = 0, update = 0, delete = 0;
        for (CustomApplicationAcademy acad : acadList) {
            switch (acad.getUserCUDType()) {
                case INSERT: insert++; break;
                case UPDATE: update++; break;
                case DELETE: delete++; break;
            }
        }

        int currentStsCode = Integer.parseInt(application.getApplStsCode());
        if (currentStsCode < Integer.parseInt(ACAD_SAVED))
            application.setApplStsCode(ACAD_SAVED);
        application.setModDate(new Date());
        int r0 = commonDAO.updateItem(application, NAME_SPACE, "ApplicationMapper");

        ParamForAcademy param = new ParamForAcademy();
        param.setApplNo(applNo);
        param.setOrderBy("ACAD_SEQ");

        param.setAcadTypeCode("00002");
        iudMapCollege = processAcademy(application, collegeList, new Date(), param);

        param.setAcadTypeCode("00003");
        iudMapGraduate = processAcademy(application, graduateList, new Date(), param);

        int insertResult = iudMapCollege.get(UserCUDType.INSERT) + iudMapGraduate.get(UserCUDType.INSERT);
        int updateResult = iudMapCollege.get(UserCUDType.UPDATE) + iudMapGraduate.get(UserCUDType.UPDATE);
        int deleteResult = iudMapCollege.get(UserCUDType.DELETE) + iudMapGraduate.get(UserCUDType.DELETE);

        if ( r0 == 1 && insert == insertResult && update == updateResult && delete == deleteResult) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U317"));
            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U318"));
            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
            String errCode = null;
            if ( r0 == 0 ) errCode = "ERR0003";
            if ( insert != insertResult ) errCode = "ERR0011";
            if ( update != updateResult ) errCode = "ERR0013";
            if ( delete != deleteResult ) errCode = "ERR0014";
            ec.setErrCode(errCode);
            throw new YSNoRedirectBizException(ec);
        }
        return ec;
    }

//    @Override
//    public ExecutionContext updateAcademy(Application application, List<CustomApplicationAcademy> collegeList, List<CustomApplicationAcademy> graduateList) {
//        List<ApplicationAcademy> acadList = new ArrayList<ApplicationAcademy>();
//        acadList.addAll(collegeList);
//        acadList.addAll(graduateList);
//
//        ExecutionContext ec = new ExecutionContext();
//        int applNo = application.getApplNo();
//        ParamForAcademy param = new ParamForAcademy();
//        param.setApplNo(applNo);
//        param.setOrderBy("ACAD_SEQ");
//
//        param.setAcadTypeCode("00002");
//        int r1 = processAcademy(application, collegeList, applNo, new Date(), param);
//
//        param.setAcadTypeCode("00003");
//        int r2 = processAcademy(application, graduateList, applNo, new Date(), param);//
//
//        if ( r1 == collegeList.size() && r2 == graduateList.size() ) {
//            ec.setResult(ExecutionContext.SUCCESS);
//            ec.setMessage(messageResolver.getMessage("U317"));
//            ec.setData(new ApplicationIdentifier(applNo, application.getApplStsCode(),
//                    application.getAdmsNo(), application.getEntrYear(), application.getAdmsTypeCode()));
//        } else {
//            ec.setResult(ExecutionContext.FAIL);
//            ec.setMessage(messageResolver.getMessage("U318"));
//            ec.setData(new ApplicationIdentifier(applNo, APP_NULL_STATUS));
//            ec.setErrCode("ERR0013");
//        }
//        return ec;
//    }

    /**
     * 현재 DB에서 ACAD LIST 가져와서 HashMap을 만들고
     * 화면에서 가져온 리스트 돌면서 HashMap 체크해서
     * 케이스 별로 CRUD 처리
     * 화면 seq 있고, DB seq 있고, 화면 userCUDType 이 UPDATE 면 화면 레코드로 DB를 수정 하고 DB에서 가져온 Map에서 삭제
     * 화면 seq 있고, DB seq 있고, 화면 userCUDType 이 DELETE 면 DB에서 삭제하고 DB에서 가져온 Map에서 삭제
     * 화면 seq 있고, DB seq 없으면 아무 처리 안 함
     * 화면 seq 공백, DB seq 없으면 화면 레코드로 DB에 새 레코드 생성
     *
     * @param application
     * @param academyList
     * @param date
     * @param param
     * @return
     */
    private Map<UserCUDType, Integer> processAcademy(Application application,
                                                     List<CustomApplicationAcademy> academyList,
                                                     Date date,
                                                     ParamForAcademy param) {

        int c1 = 0, u1 = 0, d1 = 0, lastSeq = 0;
        int applNo = application.getApplNo();
        Map<Integer, Integer> seqMap = new HashMap<Integer, Integer>();
        Map<UserCUDType, Integer> iudMap = new HashMap<UserCUDType, Integer>();

        List<ApplicationAcademy> academiesFromDB = commonDAO.queryForList(NAME_SPACE+"CustomApplicationAcademyMapper.selectByApplNoAcadTypeCode", param, ApplicationAcademy.class);

        lastSeq = commonDAO.queryForInt(NAME_SPACE +"CustomApplicationAcademyMapper.selectMaxSeqByApplNo", applNo ) ;

        if ( academiesFromDB.size() > 0 ) {
            //lastSeq = academiesFromDB.get(academiesFromDB.size()-1).getAcadSeq();

            if ( academiesFromDB != null ) {
                for (ApplicationAcademy academy : academiesFromDB) {
                    seqMap.put(academy.getAcadSeq(), academy.getAcadSeq());
                }
            }
        }

        for( CustomApplicationAcademy academyFromView : academyList) {
            int acadSeqFromView = academyFromView.getAcadSeq() == null ? -1 : academyFromView.getAcadSeq();
            if ( acadSeqFromView > 0) { //화면 seq 있는 경우
                if ( seqMap.containsKey(acadSeqFromView) ) { //화면 seq 값이 DB에도 있는 경우
                    if (academyFromView.getUserCUDType().equals(UserCUDType.UPDATE)) {
                        academyFromView.setApplNo(applNo);
                        academyFromView.setModId(application.getUserId());
                        academyFromView.setModDate(date);
                        u1 += commonDAO.updateItem(academyFromView, NAME_SPACE, "ApplicationAcademyMapper");
                        seqMap.remove(acadSeqFromView);
                    } else if (academyFromView.getUserCUDType().equals(UserCUDType.DELETE)) {
                        ApplicationAcademyKey academyKey = new ApplicationAcademyKey();
                        academyKey.setApplNo(applNo);
                        academyKey.setAcadSeq(acadSeqFromView);
                        academyKey.setAcadTypeCode(academyFromView.getAcadTypeCode());
                        d1 += commonDAO.delete(NAME_SPACE + "ApplicationAcademyMapper.deleteByPrimaryKey", academyKey);

                        //file upload 된 doc 삭제
                        ParamForApplicationDocument aParam = new ParamForApplicationDocument();
                        aParam.setApplNo(applNo);
                        aParam.setDocGrp(acadSeqFromView);
                        List<TotalApplicationDocument> aDocList ;
                        aDocList = commonDAO.queryForList( NAME_SPACE+ "CustomApplicationDocumentMapper.selectApplicationDocumentListByDocGrp", aParam , TotalApplicationDocument.class);
                        if( aDocList != null ){
                            for( TotalApplicationDocument aDoc : aDocList){
                                commonDAO.delete(NAME_SPACE + "ApplicationDocumentMapper.deleteByPrimaryKey", aDoc);

                            }
                        }
//
                        seqMap.remove(acadSeqFromView);
                    }
                }
            } else { // 화면 seq 가 숫자가 아닌 경우
                // 새 seq 부여해서 insert
                if (academyFromView.getUserCUDType().equals(UserCUDType.INSERT)
                        && academyFromView.getSchlCntrCode() != null
                        && !academyFromView.getSchlCntrCode().equals("")) {
                    academyFromView.setApplNo(applNo);
                    academyFromView.setAcadSeq(++lastSeq);
                    academyFromView.setCreId(application.getUserId());
                    academyFromView.setCreDate(date);
                    c1 += commonDAO.insertItem(academyFromView, NAME_SPACE, "ApplicationAcademyMapper");
                }
            }
        }
//        //Map에 남아있는 레코드는 delete
//        Set<Map.Entry<Integer, Integer>> entrySet = seqMap.entrySet();
//        for(Map.Entry<Integer, Integer> entry : entrySet) {
//            int seqFromDB = entry.getKey();
//            param.setAcadSeq(seqFromDB);
//            d1 += commonDAO.delete(NAME_SPACE + "CustomApplicationAcademyMapper.deleteByApplNoAcadTypeCodeAcadSeq", param);
//        }
        iudMap.put(UserCUDType.INSERT, c1);
        iudMap.put(UserCUDType.UPDATE, u1);
        iudMap.put(UserCUDType.DELETE, d1);
        return iudMap;
    }

//    @Override
//    public ExecutionContext deleteAcademy(Application application, List<CustomApplicationAcademy> collegeList, List<CustomApplicationAcademy> graduateList) {
//        return null;
//    }

    private <T> List<T> retrieveInfoListByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz) {
        List<T> infoList = null;

        infoList = commonDAO.queryForList(NAME_SPACE + mapperNameSqlId,
                parameter, clazz);

        return infoList;
    }

    private List<CustomApplicationAcademy> setUserDataStatus(List<CustomApplicationAcademy> list, UserCUDType udt) {
        for (CustomApplicationAcademy customApplicationAcademy : list) {
            customApplicationAcademy.setUserCUDType(udt);
        }
        return list;
    }
}
