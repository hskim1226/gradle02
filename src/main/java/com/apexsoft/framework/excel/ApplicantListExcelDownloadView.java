package com.apexsoft.framework.excel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.apexsoft.ysprj.admin.control.form.CourseSearchForm;
import com.apexsoft.ysprj.admin.domain.ApplicantInfoEntire;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationLanguage;
import com.apexsoft.ysprj.applicants.application.domain.CustomApplicationAcademy;
/**
 * Created by Dhkim on 2015-03-05.
 */
public class ApplicantListExcelDownloadView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<ApplicantInfoEntire> applList =  (List<ApplicantInfoEntire> )model.get("applList");
        CourseSearchForm searchForm =  (CourseSearchForm)model.get("searchForm");
        String fileName ="";

        DateFormat df = new SimpleDateFormat("yyyymmdd");
        if( searchForm.getAdmsNo()!=null && !searchForm.getAdmsNo().equals("") ){
            fileName = searchForm.getAdmsNo();
        }else{
            fileName = "ALL";
        }
        if( searchForm.getCampCode()!=null && !searchForm.getCampCode().equals("") ){
            fileName = fileName +"-"+ searchForm.getCampCode();
        }else{
            fileName = fileName +"-"+"CAMP";
        }
        if( searchForm.getCollCode()!=null && !searchForm.getCollCode().equals("") ){
            fileName = fileName +"-"+ searchForm.getCollCode();
        }else{
            fileName = fileName +"-"+"COLL";
        }
        if( searchForm.getDeptCode()!=null && !searchForm.getDeptCode().equals("") ){
            fileName = fileName +"-"+ searchForm.getDeptCode();
        }else{
            fileName = fileName +"-"+"DEPT";
        }
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".xls\"");
        //create a wordsheet
//        HSSFSheet sheet = workbook.createSheet("Revenue Report");
        Sheet sheet = workbook.createSheet("Revenue Report");

//        HSSFRow rows = sheet.createRow(0);
        Row rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("지원번호");
        rows.createCell(1).setCellValue("성명");
        rows.createCell(2).setCellValue("영어성명");
        rows.createCell(3).setCellValue("주민번호");
        rows.createCell(4).setCellValue("캠퍼스코드");
        rows.createCell(5).setCellValue("연구소명");
        rows.createCell(6).setCellValue("학과매핑코드");
        rows.createCell(7).setCellValue("세부전공명");
        rows.createCell(8).setCellValue("과정코드");
        rows.createCell(9).setCellValue("국적코드");

        rows.createCell(10).setCellValue("(학부)출신학교");
        rows.createCell(11).setCellValue("(학부)입학일");
        rows.createCell(12).setCellValue("(학부)졸업일");
        rows.createCell(13).setCellValue("(학부)졸업구분");
        rows.createCell(14).setCellValue("(학부)학위등록번호");
        rows.createCell(15).setCellValue("(학부)평량평균");
        rows.createCell(16).setCellValue("(학부)기준평량평균");
        rows.createCell(17).setCellValue("(대학원)출신학교");
        rows.createCell(18).setCellValue("(대학원)입학일");
        rows.createCell(19).setCellValue("(대학원)졸업일");
        rows.createCell(20).setCellValue("(대학원)졸업구분");
        rows.createCell(21).setCellValue("(대학원)학위등록번호");
        rows.createCell(22).setCellValue("(대학원)평량평균");
        rows.createCell(23).setCellValue("(대학원)기준평량평균");

        rows.createCell(24).setCellValue("토플종류");
        rows.createCell(25).setCellValue("토플응시일");
        rows.createCell(26).setCellValue("토플성적");
        rows.createCell(27).setCellValue("토익응시일");
        rows.createCell(28).setCellValue("토익성적");
        rows.createCell(29).setCellValue("텝스응시일");
        rows.createCell(30).setCellValue("텝스성적");
        rows.createCell(31).setCellValue("GRE응행일");
        rows.createCell(32).setCellValue("GRE성적");
        rows.createCell(33).setCellValue("IELT응시일");
        rows.createCell(34).setCellValue("IELT성적");

        rows.createCell(35).setCellValue("핸드폰");
        rows.createCell(36).setCellValue("집전화");
        rows.createCell(37).setCellValue("이메일");
        rows.createCell(38).setCellValue("우편번호");
        rows.createCell(39).setCellValue("주소");
        rows.createCell(40).setCellValue("비상연락처(이름)");
        rows.createCell(41).setCellValue("비상연락처(관계)");
        rows.createCell(42).setCellValue("본국-비상연락처(전화번호)");
        rows.createCell(43).setCellValue("비상연락처(전화번호)");
        rows.createCell(44).setCellValue("학위");
        rows.createCell(45).setCellValue("면제사유");

        rows.createCell(46).setCellValue("장애사항");
        rows.createCell(47).setCellValue("여권번호");
        rows.createCell(48).setCellValue("비자정보");
        rows.createCell(49).setCellValue("비자종류");
        rows.createCell(50).setCellValue("비자번호");
        rows.createCell(51).setCellValue("비자만료일");
        rows.createCell(52).setCellValue("비고");
        rows.createCell(53).setCellValue("자격구분");
        rows.createCell(54).setCellValue("END");


        int index = 0;
        for( ApplicantInfoEntire aAppl : applList){
            index++;
            rows = sheet.createRow(index);
            rows.createCell(0).setCellValue(aAppl.getApplId());
            rows.createCell(1).setCellValue(aAppl.getKorName());//"한국성명"
            String engName="";
            String engSur="";
            if( aAppl.getEngName()!=null) {
                engName = aAppl.getEngName();
            }
            if( aAppl.getEngSur()!=null) {
                engSur = aAppl.getEngSur();
            }
            rows.createCell(2).setCellValue(( engName+" "+engSur).trim());//"영문이름(성)"

            String rsdnNo = aAppl.getRgstNo();
            String fornNo = aAppl.getFornRgstNo();
            String gend = aAppl.getGend();
            String gendNum = "2000000";
            String dispRsndNo ="";
            if( "m".equals(gend)){
                gendNum ="1000000";

            }
            dispRsndNo = aAppl.getRgstBornDate()+gendNum;
            if( rsdnNo==null || rsdnNo.length()<13){
                if( fornNo!=null && !"".equals(fornNo))
                    dispRsndNo = fornNo;

            }else{
                dispRsndNo =rsdnNo;

            }


            rows.createCell(3).setCellValue(dispRsndNo);//"주민등록번호"

            rows.createCell(4).setCellValue(aAppl.getCampCode());//"캠퍼스코드"
            rows.createCell(5).setCellValue(aAppl.getAriInstName());//"연구소 명"
            rows.createCell(6).setCellValue(aAppl.getDeptCode());//"학과매핑코드"

            String detlMajPart ="";
            if("99999".equals( aAppl.getDetlMajCode())) {
                detlMajPart =aAppl.getInpDetlMaj();

            }else if (!"DM000".equals(aAppl.getDetlMajCode())){
                detlMajPart =aAppl.getDetlMajName();
            }
            if( "10202".equals(aAppl.getDeptCode())){
                if( "Y".equals(aAppl.getPartTimeYn())){
                    detlMajPart = detlMajPart + " part-time";
                }
                else{
                    detlMajPart = detlMajPart +" full-time";
                }
            }

            rows.createCell(7).setCellValue(detlMajPart);//"세부전공 명"
            if( aAppl.getCorsTypeCode().startsWith("0")) {
                rows.createCell(8).setCellValue(aAppl.getCorsTypeCode().substring(1));//과정코드
            }
            else{
                rows.createCell(8).setCellValue(aAppl.getCorsTypeCode());
            }
            rows.createCell(9).setCellValue(aAppl.getCitzCntrCode());//"국적"

            String underAcad ="";

            String gradAcad ="";
            for(CustomApplicationAcademy aAcad : aAppl.getAcadList() ){

                if( "00002".equals(aAcad.getAcadTypeCode())){
                    if("Y".equals(aAcad.getLastSchlYn())){
                        String gradType="";
                        String schlName="";
                        String collName="";
                        String majName="";
                        if( aAcad.getSchlName()!=null) {
                            schlName = aAcad.getSchlName();
                        }
                        if( aAcad.getCollName()!=null) {
                            collName = aAcad.getCollName();
                        }
                        if( aAcad.getMajName()!=null) {
                            majName = aAcad.getMajName();
                        }
                        underAcad = schlName +" "+collName+" "+ majName;
                        underAcad.trim();
                        rows.createCell(10).setCellValue(underAcad);//"(학부)출신학교");
                        rows.createCell(11).setCellValue(aAcad.getEntrDay());//"(학부)입학일");
                        rows.createCell(12).setCellValue(aAcad.getGrdaDay());//"(학부)졸업일");
                        if( "00001".equals( aAcad.getGrdaTypeCode())){
                            gradType= "졸업";
                        }else if( "00002".equals( aAcad.getGrdaTypeCode())){
                            gradType= "졸업예정";
                        }else if( "00003".equals( aAcad.getGrdaTypeCode())){
                            gradType= "중퇴";
                        }else if( "00004".equals( aAcad.getGrdaTypeCode())){
                            gradType= "수료";
                        }else if( "00005".equals( aAcad.getGrdaTypeCode())){
                            gradType= "재학";
                        }
                        rows.createCell(13).setCellValue(gradType);//"(학부)졸업구분");
                        rows.createCell(14).setCellValue(aAcad.getDegrNo());//"(학부)학위등록번호");
                        rows.createCell(15).setCellValue(aAcad.getGradAvr());//"(학부)평량평균");
                        rows.createCell(16).setCellValue(aAcad.getGradFull());//"(학부)기준평량평균");
                    }
                }else if("00003".equals(aAcad.getAcadTypeCode())){
                    if("Y".equals(aAcad.getLastSchlYn())){
                        String gradType="";
                        String schlName="";
                        String collName="";
                        String majName="";
                        if( aAcad.getSchlName()!=null) {
                            schlName = aAcad.getSchlName();
                        }
                        if( aAcad.getCollName()!=null) {
                            collName = aAcad.getCollName();
                        }
                        if( aAcad.getMajName()!=null) {
                            majName = aAcad.getMajName();
                        }
                        underAcad = schlName +" "+collName+" "+ majName;
                        underAcad.trim();
                        rows.createCell(17).setCellValue(underAcad);//"(대학원)출신학교");
                        rows.createCell(18).setCellValue(aAcad.getEntrDay());//"(대학원)입학일");
                        rows.createCell(19).setCellValue(aAcad.getGrdaDay());//"(대학원)졸업일");
                        if( "00001".equals( aAcad.getGrdaTypeCode())){
                            gradType= "졸업";
                        }else if( "00002".equals( aAcad.getGrdaTypeCode())){
                            gradType= "졸업예정";
                        }else if( "00003".equals( aAcad.getGrdaTypeCode())){
                            gradType= "중퇴";
                        }else if( "00004".equals( aAcad.getGrdaTypeCode())){
                            gradType= "수료";
                        }else if( "00005".equals( aAcad.getGrdaTypeCode())){
                            gradType= "재학";
                        }
                        rows.createCell(20).setCellValue(gradType);//"(대학원)졸업구분");
                        rows.createCell(21).setCellValue(aAcad.getDegrNo());//"(대학원)학위등록번호");
                        rows.createCell(22).setCellValue(aAcad.getGradAvr());//"(학부)평량평균");
                        rows.createCell(23).setCellValue(aAcad.getGradFull());//"(학부)기준평량평균");
                    }

                }
            }





            String exmpName ="";
            for( ApplicationLanguage aLang : aAppl.getLangList()) {
                if ("LANG_EXAM".equals(aLang.getLangExamGrp())) {
                    if ("00001".equals(aLang.getLangExamCode())) {//토플
                        rows.createCell(24).setCellValue(aLang.getSubCode());//"토플종류"
                        rows.createCell(25).setCellValue(aLang.getExamDay());//"토플시행일"
                        rows.createCell(26).setCellValue(aLang.getLangGrad());//"토플성적"
                    } else if ("00004".equals(aLang.getLangExamCode())) {//IELT
                        rows.createCell(33).setCellValue(aLang.getExamDay());//"IELT응시일"
                        rows.createCell(34).setCellValue(aLang.getLangGrad());//"IELT성적"
                    } else if ("00002".equals(aLang.getLangExamCode())) {//토익
                        rows.createCell(27).setCellValue(aLang.getExamDay());//"토익시행일"
                        rows.createCell(28).setCellValue(aLang.getLangGrad());//"토익성적"
                    } else if ("00003".equals(aLang.getLangExamCode())) {//텝스
                        rows.createCell(29).setCellValue(aLang.getExamDay());//"텝스시행일"
                        rows.createCell(30).setCellValue(aLang.getLangGrad());//"텝스성적
                    } else if ("00005".equals(aLang.getLangExamCode())) {//GRE
                        rows.createCell(31).setCellValue(aLang.getExamDay());//"GRE시행일"
                        rows.createCell(32).setCellValue(aLang.getLangGrad());//"GRE성적"
                    }
                }else if("KORN_EXAM".equals(aLang.getLangExamGrp())){
                   // rows.createCell(32).setCellValue(aLang.getLangGrad());//"토킥 안쓴다"
                }else if ( "EXMP_TYPE".equals(aLang.getLangExamGrp())){

                    if("00001".equals(aLang.getSubCode()) ){
                        exmpName= "영어권외국인";
                    }else if("00002".equals(aLang.getSubCode()) ){
                        exmpName= "영어권졸업자";
                    }else if("00003".equals(aLang.getSubCode()) ){
                        exmpName= "본교석사출신";
                    }else if("00004".equals(aLang.getSubCode()) ){
                        exmpName= "";
                    }else if("00005".equals(aLang.getSubCode()) ){
                        exmpName= "학과면제인정";
                    }else if("00006".equals(aLang.getSubCode()) ){
                        exmpName= "예,의학과, 치의학, 치의학전문대학원 졸업";
                    }else if("00007".equals(aLang.getSubCode()) ){
                        exmpName= "본교 의학과, 의학전문대학원 졸업";
                    }else if("00008".equals(aLang.getSubCode()) ){
                        exmpName= "구사능력증빙";
                    }
                    rows.createCell(45).setCellValue( exmpName);//"면제사유"

                }
            }


            rows.createCell(35).setCellValue(aAppl.getMobiNum());//"핸드폰");

            rows.createCell(37).setCellValue(aAppl.getMailAddr());//"이메일");
            rows.createCell(38).setCellValue(aAppl.getZipCode());//"우편번호");

            //TODO 하드코딩 바꿔야 함
            if("17C".equals(aAppl.getAdmsNo())) {
                rows.createCell(36).setCellValue(aAppl.getHomeEmrgTel());//"전화");
                rows.createCell(39).setCellValue(aAppl.getHomeAddr());//"주소");
                rows.createCell(40).setCellValue(aAppl.getHomeEmrgName());//"비상연락처(이름)");
                rows.createCell(41).setCellValue(aAppl.getHomeEmrgRelaName());//"비상연락처(관계)");
                rows.createCell(42).setCellValue(aAppl.getHomeEmrgTel());//"비상연락처(전화번호)");
                rows.createCell(44).setCellValue("");
            }else{

                String addr="";
                String detlAddr="";
                if( aAppl.getAddr()!=null) {
                    addr = aAppl.getAddr();
                }
                if( aAppl.getDetlAddr()!=null) {
                    detlAddr = aAppl.getDetlAddr();
                }
                rows.createCell(36).setCellValue(aAppl.getTelNum());//"전화");
                rows.createCell(39).setCellValue((addr + " " + detlAddr).trim());//"주소");
                rows.createCell(40).setCellValue(aAppl.getEmerContName());//"비상연락처(이름)");
                rows.createCell(41).setCellValue(aAppl.getEmerContCodeName());//"비상연락처(관계)");
                rows.createCell(42).setCellValue("");
                rows.createCell(44).setCellValue("");
            }
            rows.createCell(43).setCellValue(aAppl.getEmerContTel());//"비상연락처(전화번호)");

            String hndcGrad="";
            String hndcTypeName="";
            if( aAppl.getHndcGrad()!=null) {
                hndcGrad = aAppl.getHndcGrad();
            }
            if( aAppl.getHndcTypeName()!=null) {
                hndcTypeName = aAppl.getHndcTypeName();
            }
            rows.createCell(46).setCellValue((hndcGrad+" "+hndcTypeName).trim());//"장애사항(전화번호)");
            rows.createCell(47).setCellValue(aAppl.getPaspNo());//"여권번호");

            String visaNo="";
            String visaTypeCode="";
            if( aAppl.getVisaNo()!=null) {
                visaNo = aAppl.getVisaNo();
            }
            if( aAppl.getVisaTypeCode()!=null) {
                visaTypeCode = aAppl.getVisaTypeCode();
                if ("00999".equals(visaTypeCode)) {
                    visaTypeCode = "";
                } else if ("00099".equals(visaTypeCode)) {
                    if (aAppl.getVisaTypeEtc() != null) {
                        visaTypeCode = aAppl.getVisaTypeEtc();
                    } else {
                        visaTypeCode = "";
                    }
                }
            }
            rows.createCell(48).setCellValue((visaNo+" "+visaTypeCode).trim());//"비자정보");
            rows.createCell(49).setCellValue(visaTypeCode);//"비자종류");
            rows.createCell(50).setCellValue(visaNo);//"비자번호");
            rows.createCell(51).setCellValue(aAppl.getVisaExprDay());//"비자만료일");
            rows.createCell(52).setCellValue("");
            if("00001".equals(aAppl.getFornTypeCode())){
                rows.createCell(53).setCellValue("외국인");
            }else if("00002".equals(aAppl.getFornTypeCode())){
                rows.createCell(53).setCellValue("16년 해외거주");
            }

            rows.createCell(54).setCellValue("END");
        }




    }

}



