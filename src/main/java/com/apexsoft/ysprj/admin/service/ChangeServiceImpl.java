package com.apexsoft.ysprj.admin.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.admin.control.form.ChangeInfoForm;
import com.apexsoft.ysprj.admin.control.form.ChangeSearchPageForm;
import com.apexsoft.ysprj.admin.domain.ApplicantInfo;
import com.apexsoft.ysprj.admin.domain.ApplicationChange;
import com.apexsoft.ysprj.admin.domain.CustomApplicationChange;
import com.apexsoft.ysprj.applicants.admission.domain.Admission;
import com.apexsoft.ysprj.applicants.admission.domain.AdmissionName;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationGeneral;
import com.apexsoft.ysprj.applicants.common.domain.*;
import com.apexsoft.ysprj.applicants.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DhKim on 2014-09-14.
 */
@Service
public class ChangeServiceImpl implements ChangeService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.admin.sqlmap.";
    private final static String NAME_SPACE_APPLICATION = "com.apexsoft.ysprj.applicants.application.sqlmap.";
    private final static String NAME_SPACE_INFO= "admin.applicant.";
    private final static String ADMS_NAME_SPACE = "com.apexsoft.ysprj.applicants.admission.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AdmsNo admsNo;

    @Value("#{app['adms.enterYear']}")
    private String enterYear;

    @Value("#{app['adms.enterEarlyYear']}")
    private String enterEarlyYear;

    @Override
    public ExecutionContext createInfoChange( ChangeInfoForm changeInfoForm, String userId ) {

        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();
        ApplicantInfo applInfo = null;
        String itemName = changeInfoForm.getColName();
        String colName = "";
        String tblName = "appl";
        int upAppl = 0, insert = 0, update = 0, delete = 0;
        int rUpApplGen = 0;
        int rUpAppl = 0, rInsert = 0, rUpdate = 0, rDelete = 0;
        Boolean applChangeFg = false;
        Boolean geneChangeFg = false;
        Boolean fornChangeFg = false;


            applInfo = commonDAO.queryForObject(NAME_SPACE_INFO+"retrieveApplicantInfoByKey", changeInfoForm.getApplNo(), ApplicantInfo.class);
            if( applInfo == null ){
                //에러 로직 처리
            }

            Date date = new Date();
            Application changeAppl = new Application();
            ApplicationGeneral changeGene = new ApplicationGeneral();
            changeAppl.setApplNo(changeInfoForm.getApplNo());
            changeGene.setApplNo(changeInfoForm.getApplNo());

            if( itemName.equals("korName")){
                changeAppl.setKorName( changeInfoForm.getAftVal());
                colName = "성명";
                applChangeFg =true;
            }
            if( itemName.equals("engName")){
                changeAppl.setEngName( changeInfoForm.getAftVal());
                colName = "영문명";
                applChangeFg =true;
            }
            if( itemName.equals("engSur")){
                changeAppl.setEngSur( changeInfoForm.getAftVal());
                colName = "영문성";
                applChangeFg =true;
            }
            if( itemName.equals("bornDay")){
                changeAppl.setBornDay( changeInfoForm.getAftVal());
                colName = "생년월일";
                applChangeFg =true;
            }
            if( itemName.equals("rgstBornDate")){
                changeAppl.setRgstBornDate( changeInfoForm.getAftVal());
                colName = "생년월일";
                applChangeFg =true;
            }
            if( itemName.equals("telNum")){
                changeAppl.setTelNum( changeInfoForm.getAftVal());
                colName = "전화번호";
                applChangeFg =true;
            }
            if( itemName.equals("mobiNum")){
                changeAppl.setMobiNum( changeInfoForm.getAftVal());
                colName = "휴대폰번호";
                applChangeFg =true;
            }
            if( itemName.equals("mailAddr")){
                changeAppl.setMailAddr( changeInfoForm.getAftVal());
                colName = "이메일 주소";
                applChangeFg =true;
            }
            if( itemName.equals("addr")){
                changeAppl.setAddr( changeInfoForm.getAftVal());
                colName = "주소";
                applChangeFg =true;
            }
            if( itemName.equals("detlAddr")){
                changeAppl.setDetlAddr(changeInfoForm.getAftVal());
                colName = "상세주소";
                applChangeFg =true;
            }
            if( itemName.equals("emerContName")){
                changeGene.setEmerContName( changeInfoForm.getAftVal());
                colName = "비상연락대상자";
                geneChangeFg = true;
            }
            if( itemName.equals("emerContTel")){
                changeGene.setEmerContTel( changeInfoForm.getAftVal());
                colName = "비상연락번호";
                geneChangeFg = true;
            }
            changeInfoForm.setColName(colName);
            changeAppl.setModId(userId);
            changeAppl.setModDate(date);
            changeGene.setModId(userId);
            changeGene.setModDate(date);

            ApplicationChange appChg = new ApplicationChange();

            appChg.setApplNo(changeInfoForm.getApplNo());
            appChg.setAdmsNo(changeInfoForm.getAdmsNo());
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            appChg.setReqDay( format.format(date));
            appChg.setReqUserId(userId);
            appChg.setReqName(userId);
            appChg.setActDay( format.format(date));
            appChg.setActUserId(userId);
            appChg.setApplChgCode("00001");//정보변경
            appChg.setChgStsCode("00003");//반영완료
            appChg.setChgColm( itemName);
            appChg.setChgColmName( colName);
            appChg.setBefVal(changeInfoForm.getBefVal());
            appChg.setAftVal(changeInfoForm.getAftVal());
            appChg.setCnclResn(changeInfoForm.getCnclResn());
            appChg.setCreId(userId);
            appChg.setCreDate(date);


            int maxSeq =0;
            if( applChangeFg ) {
                rUpAppl++;
                update = update + commonDAO.updateItem(changeAppl, NAME_SPACE_APPLICATION, "ApplicationMapper");
            }
            if( geneChangeFg ) {
                rUpAppl++;
                update = update + commonDAO.updateItem(changeGene, NAME_SPACE_APPLICATION, "ApplicationGeneralMapper");
            }
            if( rUpAppl > 0 ) {
                rInsert++;
                maxSeq = commonDAO.queryForInt(NAME_SPACE + "CustomApplicationChangeMapper.selectMaxSeqByAdmsNo", changeInfoForm);
                maxSeq ++;
                appChg.setChgNo(maxSeq);
                insert = insert +commonDAO.insertItem( appChg, NAME_SPACE, "ApplicationChangeMapper");
            }

            if ( rUpAppl == update && insert == rInsert) {
                ec.setResult(ExecutionContext.SUCCESS);
                ec.setMessage(MessageResolver.getMessage("U319"));
                ecDataMap.put("changeInfoForm", changeInfoForm);

            } else {
                ec.setResult(ExecutionContext.FAIL);
                ec.setMessage(MessageResolver.getMessage("U320"));

                String errCode = null;
                if ( rUpAppl != upAppl ) errCode = "ERR0003";
                if ( insert != rInsert ) errCode = "ERR0017";
                if ( update != rUpdate ) errCode = "ERR0018";
                if ( delete != rDelete ) errCode = "ERR0019";
                ec.setErrCode(errCode);
                throw new YSBizException(ec);
            }
            return ec;


    }

    @Override
    public ExecutionContext createUnitChange( CustomApplicationChange changeInfoForm, String userId ) {

        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();
        ApplicantInfo applInfo = null;

        int upAppl = 0, insert = 0, update = 0, delete = 0;
        int rUpApplGen = 0;
        int rUpAppl = 0, rInsert = 0, rUpdate = 0, rDelete = 0;
        Boolean applChangeFg = false;
        Boolean geneChangeFg = false;


        applInfo = commonDAO.queryForObject(NAME_SPACE_INFO+"retrieveApplicantInfoByKey", changeInfoForm.getApplNo(), ApplicantInfo.class);
        if( applInfo == null ){
            //에러 로직 처리
        }

        Date date = new Date();

        //지원단위 변경은 관리자가 수동을 처리.(자동처리 안함)
        /*
        Application changeAppl = new Application();
        changeAppl.setApplNo(changeInfoForm.getApplNo());
        changeAppl.setModId(userId);
        changeAppl.setModDate(date);


        rUpAppl++;
        update = update + commonDAO.updateItem(changeAppl, NAME_SPACE_APPLICATION, "ApplicationMapper");

        */

        ApplicationChange appChg = new ApplicationChange();


        appChg.setApplNo(changeInfoForm.getApplNo());
        appChg.setAdmsNo(changeInfoForm.getAdmsNo());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        appChg.setReqDay( format.format(date));
        appChg.setReqUserId(userId);
        appChg.setReqName(userId);
        //appChg.setActDay( format.format(date));
        appChg.setActUserId(userId);
        appChg.setApplChgCode("00002");//지원단위변경
        appChg.setChgStsCode("00001");//접수
        appChg.setChgColmName("--");
        appChg.setBefVal(changeInfoForm.getBefVal());
        appChg.setAftVal(changeInfoForm.getAftVal());
        appChg.setCnclResn(changeInfoForm.getCnclResn());
        appChg.setCreId(userId);
        appChg.setCreDate(date);


        int maxSeq =0;


        rInsert++;
        maxSeq = commonDAO.queryForInt(NAME_SPACE + "CustomApplicationChangeMapper.selectMaxSeqByAdmsNo", changeInfoForm);
        maxSeq ++;
        appChg.setChgNo(maxSeq);
        insert = insert +commonDAO.insertItem( appChg, NAME_SPACE, "ApplicationChangeMapper");


        if ( rUpAppl == update && insert == rInsert) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U319"));
            ecDataMap.put("changeInfoForm", changeInfoForm);

        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U320"));

            String errCode = null;
            if ( rUpAppl != upAppl ) errCode = "ERR0003";
            if ( insert != rInsert ) errCode = "ERR0017";
            if ( update != rUpdate ) errCode = "ERR0018";
            if ( delete != rDelete ) errCode = "ERR0019";
            ec.setErrCode(errCode);
            throw new YSBizException(ec);
        }
        return ec;

    }

    @Override
        public ExecutionContext createApplicationCancel( ChangeInfoForm changeInfoForm, String userId ) {

        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();
        ApplicantInfo applInfo = null;

        int upAppl = 0, insert = 0, update = 0, delete = 0;
        int rUpApplGen = 0;
        int rUpAppl = 0, rInsert = 0, rUpdate = 0, rDelete = 0;
        Boolean applChangeFg = false;
        Boolean geneChangeFg = false;


        applInfo = commonDAO.queryForObject(NAME_SPACE_INFO+"retrieveApplicantInfoByKey", changeInfoForm.getApplNo(), ApplicantInfo.class);
        if( applInfo == null ){
            //에러 로직 처리
        }

        Date date = new Date();
        /*지원취소는 시스템관리자가 수동을 처리.(자동처리 안함)
        Application changeAppl = new Application();
        changeAppl.setApplNo(changeInfoForm.getApplNo());
        changeAppl.setApplStsCode("00022");//지원취소 코드
        changeAppl.setModId(userId);
        changeAppl.setModDate(date);
        rUpAppl++;
        update = update + commonDAO.updateItem(changeAppl, NAME_SPACE_APPLICATION, "ApplicationMapper");
        */

        ApplicationChange appChg = new ApplicationChange();

        appChg.setApplNo(changeInfoForm.getApplNo());
        appChg.setAdmsNo(changeInfoForm.getAdmsNo());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        appChg.setReqDay( format.format(date));
        appChg.setReqUserId(userId);
        appChg.setReqName(userId);
        //appChg.setActDay( format.format(date));
        appChg.setActUserId(userId);
        appChg.setApplChgCode("00003");//지원취소
        appChg.setChgStsCode("00001");//접수
        appChg.setChgColmName("--");
        appChg.setBefVal(changeInfoForm.getBefVal());
        appChg.setAftVal("--지원취소--");
        appChg.setCnclResn(changeInfoForm.getCnclResn());
        appChg.setCreId(userId);
        appChg.setCreDate(date);

        int maxSeq =0;

        rInsert++;
        maxSeq = commonDAO.queryForInt(NAME_SPACE + "CustomApplicationChangeMapper.selectMaxSeqByAdmsNo", changeInfoForm);
        maxSeq ++;
        appChg.setChgNo(maxSeq);
        insert = insert +commonDAO.insertItem( appChg, NAME_SPACE, "ApplicationChangeMapper");


        if ( rUpAppl == update && insert == rInsert) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U319"));
            ecDataMap.put("changeInfoForm", changeInfoForm);

        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U320"));

            String errCode = null;
            if ( rUpAppl != upAppl ) errCode = "ERR0003";
            if ( insert != rInsert ) errCode = "ERR0017";
            if ( update != rUpdate ) errCode = "ERR0018";
            if ( delete != rDelete ) errCode = "ERR0019";
            ec.setErrCode(errCode);
            throw new YSBizException(ec);
        }
        return ec;
    }

    @Override
    public ExecutionContext createEtcInfoChange( ChangeInfoForm changeInfoForm, String userId ) {

        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();
        ApplicantInfo applInfo = null;

        int upAppl = 0, insert = 0, update = 0, delete = 0;
        int rUpApplGen = 0;
        int rUpAppl = 0, rInsert = 0, rUpdate = 0, rDelete = 0;
        Boolean applChangeFg = false;
        Boolean geneChangeFg = false;


        applInfo = commonDAO.queryForObject(NAME_SPACE_INFO+"retrieveApplicantInfoByKey", changeInfoForm.getApplNo(), ApplicantInfo.class);
        if( applInfo == null ){
            //에러 로직 처리
        }

        Date date = new Date();
        ApplicationChange appChg = new ApplicationChange();

        appChg.setApplNo(changeInfoForm.getApplNo());
        appChg.setAdmsNo(changeInfoForm.getAdmsNo());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        appChg.setReqDay( format.format(date));
        appChg.setReqUserId(userId);
        appChg.setReqName(userId);
        appChg.setActUserId(userId);
        appChg.setApplChgCode("00001");//지원취소
        appChg.setChgStsCode("00001");//접수
        appChg.setChgColmName("-기타-");
        appChg.setBefVal(changeInfoForm.getBefVal());
        appChg.setAftVal(changeInfoForm.getAftVal());
        appChg.setCnclResn(changeInfoForm.getCnclResn());
        appChg.setCreId(userId);
        appChg.setCreDate(date);

        int maxSeq =0;

        rInsert++;
        maxSeq = commonDAO.queryForInt(NAME_SPACE + "CustomApplicationChangeMapper.selectMaxSeqByAdmsNo", changeInfoForm);
        maxSeq ++;
        appChg.setChgNo(maxSeq);
        insert = insert +commonDAO.insertItem( appChg, NAME_SPACE, "ApplicationChangeMapper");


        if ( rUpAppl == update && insert == rInsert) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U319"));
            ecDataMap.put("changeInfoForm", changeInfoForm);

        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U320"));

            String errCode = null;
            if ( rUpAppl != upAppl ) errCode = "ERR0003";
            if ( insert != rInsert ) errCode = "ERR0017";
            if ( update != rUpdate ) errCode = "ERR0018";
            if ( delete != rDelete ) errCode = "ERR0019";
            ec.setErrCode(errCode);
            throw new YSBizException(ec);
        }
        return ec;
    }

    public ExecutionContext retrieveChangePaginatedList(ChangeSearchPageForm searchForm){
        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();

        PageInfo<CustomApplicationChange>  tempPageInfo =null;

        List<AdmissionName> admsList = null;
        List<Campus> campList = null;
        List<College> collList = null;
        List<CodeNameDepartment> deptList = null;
        List<CommonCode> applChgCodeList = null;
        List<CommonCode> chgStsCodeList = null;
        ParamForSetupCourses param = new ParamForSetupCourses();
        param.setAdmsNo(searchForm.getAdmsNo());
        param.setCollCode(searchForm.getCollCode());
        param.setDeptCode(searchForm.getDeptCode());

        admsList = commonDAO.queryForList(ADMS_NAME_SPACE +"CustomAdmissionMapper.selectAdmsNameByYear", enterYear, AdmissionName.class);
        // 2016-01 조기 전형 없으므로 아래 행 주석 처리
        admsList.addAll( commonDAO.queryForList(ADMS_NAME_SPACE +"CustomAdmissionMapper.selectAdmsNameByYear", enterEarlyYear, AdmissionName.class));
        addShortAdmissionName(admsList);

        campList = commonService.retrieveCampus();
        collList = commonService.retrieveCollegeByCampus( searchForm.getCampCode() );
        deptList = commonService.retrieveGeneralDepartmentByAdmsColl(param);

        applChgCodeList= commonService.retrieveCommonCodeByCodeGroup("APPL_CHG");
        chgStsCodeList= commonService.retrieveCommonCodeByCodeGroup("CHG_STS");
        try{

            if( searchForm.getChgNo()!= null || searchForm.getChgStsCode()!= null || searchForm.getAdmsNo()!= null || searchForm.getKorName()!= null) {
                PageStatement tempStst = new PageStatement(NAME_SPACE+"CustomApplicationChangeMapper.retrieveChangeCount", NAME_SPACE+"CustomApplicationChangeMapper.retrieveChangeList");
                tempPageInfo = commonDAO.queryForPagenatedList( tempStst, searchForm, searchForm.getPage().getNo(), searchForm.getPage().getRows() );
                List<CustomApplicationChange> tempInfoList = tempPageInfo.getData();
                ecDataMap.put("chgList", tempInfoList);
                ecDataMap.put("totalCnt", tempPageInfo.getTotalRowCount());


            }else{
                ecDataMap.put("chgList", new ArrayList<CustomApplicationChange>());
                ecDataMap.put("totalCnt", 0);
            }
            if (applChgCodeList != null)    selectionMap.put("applChgCodeList", applChgCodeList);
            if (chgStsCodeList != null) selectionMap.put("chgStsCodeList", chgStsCodeList);
            if (admsList != null)       selectionMap.put("admsList", admsList);
            if (campList != null)       selectionMap.put("campList", campList);
            if (collList != null)       selectionMap.put("collList", collList);
            if (deptList != null)       selectionMap.put("deptList", deptList);

            ecDataMap.put("selection", selectionMap);
            ecDataMap.put("changeSearchPageForm", searchForm);

            ec.setData(ecDataMap);

        }catch(Exception e){
            e.printStackTrace();

        }
        return ec;
    }

    public ExecutionContext retrieveChangeDetail(String chgId){
        ExecutionContext ec = new ExecutionContext();
        Map<String, Object> ecDataMap = new HashMap<String, Object>();
        Map<String, Object> selectionMap = new HashMap<String, Object>();

        CustomApplicationChange  chgInfo =null;

        List<Admission> admsList = null;
        List<Campus> campList = null;
        List<College> collList = null;
        List<CodeNameDepartment> deptList = null;
        List<CommonCode> applChgCodeList = null;
        List<CommonCode> chgStsCodeList = null;
        ParamForSetupCourses param = new ParamForSetupCourses();


        //chgInfo = commonDAO.queryForObject(ADMS_NAME_SPACE, Admission.class);
        try{
            ecDataMap.put("selection", selectionMap);

            ec.setData(ecDataMap);

        }catch(Exception e){
            e.printStackTrace();

        }
        return ec;
    }
    private void addShortAdmissionName(List<AdmissionName> admsList){
        for( AdmissionName aAdms : admsList){
            if( admsNo.getGeneral().equals(aAdms.getAdmsNo())){
                aAdms.setAdmsName("16년 후기 일반");
            }else if( admsNo.getForeign().equals(aAdms.getAdmsNo())){
                aAdms.setAdmsName("16년 후기 외국인");
            }else if( admsNo.getEarly().equals(aAdms.getAdmsNo())){
                aAdms.setAdmsName("17년 전기 조기");
            }
        }
    }
    private String abridgeAdmsCode(String admsCode){
        String admsName = "";

        if( admsNo.getGeneral().equals(admsCode)) {
            admsName = "16년 후기 일반";
        }else if( admsNo.getForeign().equals(admsCode)){
            admsName ="16년 후기 외국인";
        }else if( admsNo.getEarly().equals(admsCode)){
            admsName ="17년 전기 조기";
        }
        return admsName;
    }
}
