package com.apexsoft.framework.excel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apexsoft.ysprj.admin.control.form.CourseSearchForm;
import com.apexsoft.ysprj.admin.domain.ApplicantInfoEntire;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationLanguage;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;
/**
 * Created by Dhkim on 2015-03-05.
 */
public class ApplicantListExcelDownloadView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

//	}
//
//    @Override
//    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
//                                      HttpServletRequest request, HttpServletResponse response)
//            throws Exception {

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
        rows.createCell(0).setCellValue("수험번호");
        rows.createCell(1).setCellValue("모집구분");
        rows.createCell(2).setCellValue("캠퍼스");
        rows.createCell(3).setCellValue("연구소");
        rows.createCell(4).setCellValue("학과매핑코드");
        rows.createCell(5).setCellValue("세부전공");
        rows.createCell(6).setCellValue("한국성명");
        rows.createCell(7).setCellValue("영문이름(성)");
        rows.createCell(8).setCellValue("영문이름(이름)");
        rows.createCell(9).setCellValue("주민등록번호");
        rows.createCell(10).setCellValue("국적");
        rows.createCell(11).setCellValue("토플종류");
        rows.createCell(12).setCellValue("토플시행일");
        rows.createCell(13).setCellValue("토플성적");
        rows.createCell(14).setCellValue("IELT응시일");
        rows.createCell(15).setCellValue("IELT성적");
        rows.createCell(16).setCellValue("토익시행일");
        rows.createCell(17).setCellValue("토익성적");
        rows.createCell(18).setCellValue("텝스시행일");
        rows.createCell(19).setCellValue("텝스성적");
        rows.createCell(20).setCellValue("GRE시행일");
        rows.createCell(21).setCellValue("GRE성적");
        rows.createCell(22).setCellValue("면제코드");
        rows.createCell(23).setCellValue("지원자우편번호");
        rows.createCell(24).setCellValue("지원자주소1");
        rows.createCell(25).setCellValue("지원자주소2");
        rows.createCell(26).setCellValue("지원자전화번호");
        rows.createCell(27).setCellValue("지원자핸드폰번호");
        rows.createCell(28).setCellValue("지원자이메일주소");
        rows.createCell(29).setCellValue("보호자관계");
        rows.createCell(30).setCellValue("보호자연락처");
        rows.createCell(31).setCellValue("지원일자");


        int index = 0;
        for( ApplicantInfoEntire aAppl : applList){
            index++;
            rows = sheet.createRow(index);
            rows.createCell(0).setCellValue(aAppl.getApplId());
            rows.createCell(1).setCellValue(aAppl.getAdmsType());
            rows.createCell(2).setCellValue(aAppl.getCampName());//"캠퍼스"
            rows.createCell(3).setCellValue(aAppl.getAriInstName());//"연구소"
            rows.createCell(4).setCellValue(aAppl.getDeptCode());//"학과매핑코드"
            rows.createCell(5).setCellValue(aAppl.getDetlMajName());//"세부전공"
            rows.createCell(6).setCellValue(aAppl.getKorName());//"한국성명"
            rows.createCell(7).setCellValue(aAppl.getEngSur());//"영문이름(성)"
            rows.createCell(8).setCellValue(aAppl.getEngName());//"영문이름(이름)"
            rows.createCell(9).setCellValue(aAppl.getRgstNo());//"주민등록번호"
            rows.createCell(10).setCellValue(aAppl.getCitzCntrCode());//"국적"
            if(aAppl.getForlExmpCode() == null || "".equals(aAppl.getForlExmpCode())){
                for( ApplicationLanguage aLang : aAppl.getLangList()) {
                    if ("LANG_EXAM".equals(aLang.getLangExamGrp())) {
                        if ("00001".equals(aLang.getLangExamCode())) {//토플
                            rows.createCell(11).setCellValue(aLang.getToflTypeCode());//"토플종류"
                            rows.createCell(12).setCellValue(aLang.getExamDay());//"토플시행일"
                            rows.createCell(13).setCellValue(aLang.getLangGrad());//"토플성적"
                        } else if ("00004".equals(aLang.getLangExamCode())) {//IELT
                            rows.createCell(14).setCellValue(aLang.getExamDay());//"IELT응시일"
                            rows.createCell(15).setCellValue(aLang.getLangGrad());//"IELT성적"
                        } else if ("00002".equals(aLang.getLangExamCode())) {//토익
                            rows.createCell(16).setCellValue(aLang.getExamDay());//"토익시행일"
                            rows.createCell(17).setCellValue(aLang.getLangGrad());//"토익성적"
                        } else if ("00003".equals(aLang.getLangExamCode())) {//텝스
                            rows.createCell(18).setCellValue(aLang.getExamDay());//"텝스시행일"
                            rows.createCell(19).setCellValue(aLang.getLangGrad());//"텝스성적
                        } else if ("00005".equals(aLang.getLangExamCode())) {//GRE
                            rows.createCell(20).setCellValue(aLang.getExamDay());//"GRE시행일"
                            rows.createCell(21).setCellValue(aLang.getLangGrad());//"GRE성적"
                        }
                    }else{
                        ;//한국어 레벨 입력해야 됨
                    }
                }
            }else {
                    rows.createCell(22).setCellValue(aAppl.getForlExmpCode());//"면제코드"
            }
            rows.createCell(23).setCellValue(aAppl.getPaspNo());//"지원자우편번호"
            rows.createCell(24).setCellValue(aAppl.getAddr());//"지원자주소1"
            rows.createCell(25).setCellValue(aAppl.getDetlAddr());//"지원자주소2"
            rows.createCell(26).setCellValue(aAppl.getTelNum());//"지원자전화번호"
            rows.createCell(27).setCellValue(aAppl.getMobiNum());//"지원자핸드폰번호"
            rows.createCell(28).setCellValue(aAppl.getMailAddr());//"지원자이메일주소"
            rows.createCell(29).setCellValue(aAppl.getEmerContCode());//"보호자관계"
            rows.createCell(30).setCellValue(aAppl.getEmerContTel());//"보호자연락처"
            rows.createCell(31).setCellValue(df.format(aAppl.getApplDate()));//"지원일자"
        }




    }

}



