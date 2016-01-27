package gradnet.selenide;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

/**
 * Created by hanmomhanda on 16. 1. 27.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewApplication {

    private static final String baseUrl = "http://localhost:8080/yonsei";
    private static final String userId = "Abc333";
    private static final String password = "Abc33333";
    private static final WebDriver driver = WebDriverRunner.getWebDriver();
    private static final JavascriptExecutor js = (JavascriptExecutor)driver;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public static void 로그인() throws Exception {
        open(baseUrl + "/user/login");
        $("#username").setValue(userId);
        $("#password").setValue(password);
        $("#btnLogin").click();

        // 아래는 특정 페이지만 테스트 할 때는 주석 처리
//        open(baseUrl);
//        $("#toAdmsList").click();
//        $("#toGeneralApply").click();
//        $("#checkAll").scrollTo();
//        $("#checkAll").click();
//        j("$('#composePaper').click()");
    }

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void t01_기본정보입력_BASIS() throws Exception {
        // 특정 페이지 단독 테스트 시 사용
        $("#modify2").click();

        // 지원 과정 선택
        $("#applAttrCode").selectOption("일반 지원자");
        $("#campCode").selectOption("서울");
        $("#collCode").selectOption("경영대학");
        $("#deptCode").selectOption("경영학과");
        $("#corsTypeCode").selectOption("석사학위과정");
        $("#detlMajCode").selectOption("마케팅");
        $("#partTimeYn").selectOption("풀타임");
        $("#btnBaseSave").click();
        assertEquals("지원 사항을 정확히 지정 하셨습니까?", closeAlertAndGetItsText());

        // 지원자 정보
        $("#application\\.korName").scrollTo();
        $("#application\\.korName").setValue("장보고");
        $("#application\\.engSur").setValue("jang");
        $("#application\\.engName").setValue("bogo");
        j("$('#citzCntrCode').val('118')");
        j("$('#citzCntrCode').change()");
        $("#application\\.rgstBornDate").scrollTo();
        $("#application\\.rgstBornDate").setValue("900909");
        $("#application\\.rgstEncr").setValue("1111118");
        $("#application\\.gend1").click();

        // 지원자 연락처
        j("$('#zipCode').val('143815')");    // hidden 필드는 selenide setValue()로 변경 불가, jQuery로 변경
        j("$('#address').val('서울특별시 광진구 강변북로 423')");    // hidden 필드는 jQuery로 변경
        $("#addressDetail").setValue("777");
        $("#application\\.telNum").setValue("01095147896");
        $("#application\\.mobiNum").setValue("01095147896");
        $("#application\\.mailAddr").setValue("hanmomhanda@gmail.com");

        // 비상연락처
        $("#applicationGeneral\\.emerContName").scrollTo();
        $("#applicationGeneral\\.emerContName").setValue("장수왕");
        $("#applicationGeneral\\.emerContCode").selectOption("지인");
        $("#applicationGeneral\\.emerContTel").setValue("01032145698");

        // 저장
        $("#saveBasis").click();
        assertEquals("기본 정보를 성공적으로 저장했습니다.", closeAlertAndGetItsText());
    }

    @Test
    public void t02_학력정보입력_ACADEMY() throws Exception {
        // 특정 페이지 단독 테스트 시 사용
        $("#modify2").click();

        driver.findElement(By.linkText("2. 학력 정보")).click();

        // 대학 입력
        j("document.getElementById('collegeList0.schlCntrCode').value = '118'");
        j("document.getElementById('collegeList0.korCntrName').value = '대한민국'");
        j("document.getElementById('collegeList0.entrDay').value = '20100301'");
        j("document.getElementById('collegeList0.grdaDay').value = '20140228'");
        $("#collegeList0\\.grdaTypeCode").selectOption("졸업");
        $("#collegeList0\\.degrNo").setValue("grad-874569");
        j("$('#collegeList0\\\\.schlCode').val('219')");    // hidden 필드는 jQuery로 변경
        j("$('#collegeList0\\\\.schlName').val('연세대학교')");    // hidden 필드는 jQuery로 변경
        j("document.getElementById('college-radio-0').scrollTo()");
//        j("$('#college-radio-0').click()");    // 클릭으로 다른 요소의 값을 변경할 때는 jQuery로 안됨
        j("document.getElementById('college-radio-0').click()");    // DOM API로 직접 클릭
        $("#collegeList0\\.collName").setValue("경영대학");
        $("#collegeList0\\.majName").setValue("경영학과");
        $("#collegeList0\\.gradFormCode").selectOption("평량평균");
        $("#collegeList0\\.gradAvr").setValue("4.25");
        $("#collegeList0\\.gradFull").setValue("4.50");

        // 저장
        $("#saveAcademy").scrollTo();
        $("#saveAcademy").click();
        assertEquals("학력 정보를 성공적으로 저장했습니다.", closeAlertAndGetItsText());
    }

    @Test
    public void test03_어학경력정보입력() throws Exception {
        // 특정 페이지 단독 테스트 시 사용
        $("#modify2").click();

        driver.findElement(By.linkText("3. 어학/경력 정보")).click();

        // TOEFL - CBT
        if (!$("#checkLang-0-0-0").isSelected())
            $("#checkLang-0-0-0").click();
        $("#languageGroupList0\\.langList0\\.subContainer0\\.subCode").selectOption("CBT");
        $("#stepStatusTitle").scrollTo();
        j("document.getElementById('languageGroupList0.langList0.subContainer0.examDay').value = '20141103'"); // readonly는 DOM API로
        $("#languageGroupList0\\.langList0\\.subContainer0\\.langGrad").setValue("280");

        // TOEFL - CBT
        if (!$("#checkLang-0-0-2").isSelected())
            $("#checkLang-0-0-2").click();
        j("document.getElementById('languageGroupList0.langList0.subContainer2.examDay').value = '20141108'"); // readonly는 DOM API로
        $("#languageGroupList0\\.langList0\\.subContainer2\\.langGrad").selectOption("8.0");

        // 경력 사항
        $("#saveLangCareer").scrollTo();
        j("document.getElementById('applicationExperienceList0.joinDay').value = '20100407'"); // readonly는 DOM API로
        j("document.getElementById('applicationExperienceList0.retrDay').value = '20140831'"); // readonly는 DOM API로
        $("#applicationExperienceList0\\.corpName").setValue("좋은 회사");
        $("#applicationExperienceList0\\.exprDesc").setValue("마케팅 기획 총괄");

        // 저장
        $("#saveLangCareer").click();
        assertEquals("어학 및 경력 정보를 성공적으로 저장했습니다.", closeAlertAndGetItsText());
    }

    @Test
    public void test04_파일첨부() throws Exception {
        // 특정 페이지 단독 테스트 시 사용
        $("#modify2").click();

        driver.findElement(By.linkText("4. 파일 첨부 및 제출")).click();

        j("scrollBy(0, 1000)");
        $("#docChckYn").click();

        j("scrollByLines(10)");

        $("#file-input-0-0-0").setValue("/home/hanmomhanda/YS-DOC/공 백/뚱석이.jpg");
        $("#upload-button-0-0-0").click();
        assertEquals("파일이 업로드 되었습니다.", closeAlertAndGetItsText());

        $("#file-input-0-0-1").setValue("/home/hanmomhanda/YS-DOC/축변환매트릭스(Axis Transformation Matrices).pdf");
        $("#upload-button-0-0-1").click();
        assertEquals("파일이 업로드 되었습니다.", closeAlertAndGetItsText());

        j("scrollByLines(10)");

        $("#file-input-0-1-0").setValue("/home/hanmomhanda/YS-DOC/2014-국가직무능력표준 개발전문가 모집_작성양식_PDF.pdf");
        $("#upload-button-0-1-0").click();
        assertEquals("파일이 업로드 되었습니다.", closeAlertAndGetItsText());

        j("scrollByLines(20)");

        $("#file-input-1-0-0-0").setValue("/home/hanmomhanda/YS-DOC/Modellipse_소개.pdf");
        $("#upload-button-1-0-0-0").click();
        assertEquals("파일이 업로드 되었습니다.", closeAlertAndGetItsText());

        $("#file-input-1-0-0-1").setValue("/home/hanmomhanda/YS-DOC/제62차 SW 테스트 전문가 양성 교육(일반).pdf");
        $("#upload-button-1-0-0-1").click();
        assertEquals("파일이 업로드 되었습니다.", closeAlertAndGetItsText());

        j("scrollByLines(10)");

        $("#file-input-2-0").setValue("/home/hanmomhanda/YS-DOC/83호_공학_트렌드_GIT_Flow를_활용한_효과적인_소스_형상_관리_Part_2.pdf");
        $("#upload-button-2-0").click();
        assertEquals("파일이 업로드 되었습니다.", closeAlertAndGetItsText());

        $("#file-input-2-1").setValue("/home/hanmomhanda/YS-DOC/79호_공학_트렌드_웹성능테스트-part_2.pdf");
        $("#upload-button-2-1").click();
        assertEquals("파일이 업로드 되었습니다.", closeAlertAndGetItsText());

        j("scrollByLines(10)");

        $("#documentContainerList3\\.subContainer0\\.checkedFg").click();
        $("#documentContainerList3\\.subContainer0\\.docItemName").setValue("기타 서류 1");
        $("#file-input-3-0").setValue("/home/hanmomhanda/YS-DOC/2014-국가직무능력표준 개발전문가 모집_작성양식_PDF.pdf");
        $("#upload-button-3-0").click();
        assertEquals("파일이 업로드 되었습니다.", closeAlertAndGetItsText());

        $("#documentContainerList3\\.subContainer1\\.checkedFg").click();
        $("#documentContainerList3\\.subContainer1\\.docItemName").setValue("기타 서류 2");
        $("#file-input-3-1").setValue("/home/hanmomhanda/YS-DOC/114호_공학_트렌드_WEB_UI_개발_Part_1.pdf");
        $("#upload-button-3-1").click();
        assertEquals("파일이 업로드 되었습니다.", closeAlertAndGetItsText());

        j("scrollByLines(30)");

        // 저장
        $("#saveDocument").click();
        assertEquals("첨부 파일을 성공적으로 저장했습니다.", closeAlertAndGetItsText());
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    private static void j(String javaScriptSource) {
        Object obj = js.executeScript(javaScriptSource);
//        System.out.println(obj);
    }
}
