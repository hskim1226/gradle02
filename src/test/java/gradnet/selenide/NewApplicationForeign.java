package gradnet.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.hamcrest.core.StringStartsWith;
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
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by hanmomhanda on 16. 1. 27.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewApplicationForeign {

    private static final String baseUrl = "http://localhost:8080/yonsei";
    private static final String userId = "Eng333";
    private static final String password = "Eng33333";
    private static final WebDriver driver = WebDriverRunner.getWebDriver();
    private static final JavascriptExecutor js = (JavascriptExecutor)driver;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public static void 로그인() throws Exception {
        open(baseUrl + "/user/login?lang=en");
        $("#username").setValue(userId);
        $("#password").setValue(password);
        $("#btnLogin").click();
    }
        // 특정 페이지만 테스트 할 때는 아래 내용 주석 처리
//        open(baseUrl);
//        $("#toAdmsList").click();
//        $("#toGeneralApply").click();
//        $("#checkAll").scrollTo();
//        $("#checkAll").click();
//        J("$('#composePaper').click()");
//    }

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void t01_기본정보입력_BASIS() throws Exception {
        // 특정 페이지 단독 테스트 시 사용
//        $("#modify3").click();

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
        J("$('#citzCntrCode').val('118')");
        J("$('#citzCntrCode').change()");
        $("#application\\.rgstBornDate").scrollTo();
        $("#application\\.rgstBornDate").setValue("900909");
        $("#application\\.rgstEncr").setValue("1111118");
        $("#application\\.gend1").click();

        // 지원자 연락처
        J("$('#zipCode').val('143815')");    // hidden 필드는 selenide setValue()로 변경 불가, jQuery로 변경
        J("$('#address').val('서울특별시 광진구 강변북로 423')");    // hidden 필드는 jQuery로 변경
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
        // 특정 페이지 단독 테스트 시 주석 해제
        $("#modify0").click();

        driver.findElement(By.linkText("2. Educational Background")).click();

        // 대학 입력
        J("$('#collegeList0\\\\.schlCntrCode').val('111')");    // hidden 필드는 Selenide API로 세팅 불가
        J("document.getElementById('collegeList0.korCntrName').value = 'Indonesia'");
        J("document.getElementById('collegeList0.entrDay').value = '20100104'");
        J("document.querySelector('#collegeList0\\\\.grdaDay').value = '20131227'");    // readonly 는 API나 jQuery로 변경 가능
        J("document.getElementById('collegeList0\\.grdaTypeCode').selectedIndex='1'");
        J("$('#collegeList0\\\\.grdaTypeCode').change()");
        $("#collegeList0\\.degrNo").setValue("grad-874569");
        J("$('#collegeList0\\\\.schlCode').val('999')");    // hidden 필드는 DOM API나 jQuery로 변경 가능
        J("document.getElementById('collegeList0\\.schlName').value = 'Indonesian National University'");    // readonly 는 DOM API나 jQuery로 변경 가능
        J("document.getElementById('college-radio-0').scrollTo()");
        $("#college-radio-0").click();
        $("#collegeList0\\.collName").setValue("Education College");
        $("#collegeList0\\.majName").setValue("Children Education");
        $("#collegeList0\\.gradFormCode").selectOption("Percentage");
        $("#collegeList0\\.gradAvr").setValue("89");
        $("#collegeList0\\.gradFull").setValue("100");

        // 저장
        $("#saveAcademy").scrollTo();
        $("#saveAcademy").click();
        assertEquals("Educational background information is saved successfully.", closeAlertAndGetItsText());
    }

    @Test
    public void test03_어학경력정보입력() throws Exception {
        // 특정 페이지 단독 테스트 시 주석 해제
        $("#modify0").click();

        driver.findElement(By.linkText("3. Language Test and Career")).click();

        // 영어 면제
        $("#checkForlExmp-0").scrollTo();
        if (!$("#checkForlExmp-0").isSelected())
            $("#checkForlExmp-0").click();
        J("document.getElementById('forlExmpCode-0').selectedIndex='1'");
        J("$('#forlExmpCode-0').change()");

        // TOPIK
        $("#languageGroupList1\\.langList0\\.list").scrollTo();
        if (!$("#checkLang-1-0-0").isSelected())
            $("#checkLang-1-0-0").click();
        J("document.getElementById('languageGroupList1.langList0.subContainer0.examDay').value = '20150915'"); // readonly는 DOM API나 jQuery로
        $("#languageGroupList1\\.langList0\\.subContainer0\\.langGrad").selectOption("Level-5");

        // 경력 사항
        $("#saveLangCareer").scrollTo();
        J("document.getElementById('applicationExperienceList0.joinDay').value = '20100407'"); // readonly는 DOM API나 jQuery로
        J("document.getElementById('applicationExperienceList0.retrDay').value = '20140831'"); // readonly는 DOM API나 jQuery로
        $("#applicationExperienceList0\\.corpName").setValue("Good Company");
        $("#applicationExperienceList0\\.exprDesc").setValue("Inhouse Education");

        // 저장
        $("#saveLangCareer").click();
        assertEquals("Language test result and career information is saved successfully.", closeAlertAndGetItsText());
    }

    @Test
    public void test04_파일첨부() throws Exception {
        // 특정 페이지 단독 테스트 시 주석 해제
        $("#modify0").click();

        driver.findElement(By.linkText("4. File Submission and Submit")).click();

        $("#docChckYn").scrollTo();
        J("scrollByLines(-8)");
        if ("off".equals($("#docChckYn").val()))
            $("#docChckYn").click();

        J("scrollByLines(10)");

        if ($("#file-delete-link-0-0-0").isDisplayed()) {
            $("#file-delete-link-0-0-0").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#file-input-0-0-0").setValue("/home/hanmomhanda/YS-DOC/공 백/뚱석이.jpg");
        $("#upload-button-0-0-0").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        if ($("#file-delete-link-0-0-1").isDisplayed()) {
            $("#file-delete-link-0-0-1").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#file-input-0-0-1").setValue("/home/hanmomhanda/YS-DOC/축변환매트릭스(Axis Transformation Matrices).pdf");
        $("#upload-button-0-0-1").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        J("scrollByLines(10)");

        if ($("#file-delete-link-0-0-2").isDisplayed()) {
            $("#file-delete-link-0-0-2").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#file-input-0-0-2").setValue("/home/hanmomhanda/YS-DOC/79호_공학_트렌드_웹성능테스트-part_2.pdf");
        $("#upload-button-0-0-2").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        J("scrollByLines(20)");

        if ($("#file-delete-link-1-0-0-0").isDisplayed()) {
            $("#file-delete-link-1-0-0-0").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#file-input-1-0-0-0").setValue("/home/hanmomhanda/YS-DOC/Modellipse_소개.pdf");
        $("#upload-button-1-0-0-0").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        if ($("#file-delete-link-1-0-0-1").isDisplayed()) {
            $("#file-delete-link-1-0-0-1").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#file-input-1-0-0-1").setValue("/home/hanmomhanda/YS-DOC/제62차 SW 테스트 전문가 양성 교육(일반).pdf");
        $("#upload-button-1-0-0-1").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        J("scrollByLines(10)");

        if ($("#file-delete-link-1-0-1-0").isDisplayed()) {
            $("#file-delete-link-1-0-1-0").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#file-input-1-0-1-0").setValue("/home/hanmomhanda/YS-DOC/83호_공학_트렌드_GIT_Flow를_활용한_효과적인_소스_형상_관리_Part_2.pdf");
        $("#upload-button-1-0-1-0").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        J("scrollByLines(10)");

        if ($("#file-delete-link-2-0").isDisplayed()) {
            $("#file-delete-link-2-0").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#file-input-2-0").setValue("/home/hanmomhanda/YS-DOC/114호_공학_트렌드_WEB_UI_개발_Part_1.pdf");
        $("#upload-button-2-0").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        J("scrollByLines(10)");

        if ($("#file-delete-link-3-0-0").isDisplayed()) {
            $("#file-delete-link-3-0-0").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#file-input-3-0-0").setValue("/home/hanmomhanda/YS-DOC/83호_공학_트렌드_GIT_Flow를_활용한_효과적인_소스_형상_관리_Part_2.pdf");
        $("#upload-button-3-0-0").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        J("scrollByLines(5)");

        if ($("#file-delete-link-3-0-1").isDisplayed()) {
            $("#file-delete-link-3-0-1").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#file-input-3-0-1").setValue("/home/hanmomhanda/YS-DOC/114호_공학_트렌드_WEB_UI_개발_Part_1.pdf");
        $("#upload-button-3-0-1").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        J("scrollByLines(5)");

        if ($("#file-delete-link-3-0-3").isDisplayed()) {
            $("#file-delete-link-3-0-3").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#file-input-3-0-3").setValue("/home/hanmomhanda/YS-DOC/83호_공학_트렌드_GIT_Flow를_활용한_효과적인_소스_형상_관리_Part_2.pdf");
        $("#upload-button-3-0-3").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        J("scrollByLines(10)");

        if ($("#file-delete-link-4-0").isDisplayed()) {
            $("#file-delete-link-4-0").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#documentContainerList4\\.subContainer0\\.checkedFg").click();
        $("#documentContainerList4\\.subContainer0\\.docItemName").setValue("Additional Paper 1");
        $("#file-input-4-0").setValue("/home/hanmomhanda/YS-DOC/2014-국가직무능력표준 개발전문가 모집_작성양식_PDF.pdf");
        $("#upload-button-4-0").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        J("scrollByLines(10)");

        if ($("#file-delete-link-4-1").isDisplayed()) {
            $("#file-delete-link-4-1").click();
            assertEquals("Do you want to delete uploaded file?", closeAlertAndGetItsText());
        }
        $("#documentContainerList4\\.subContainer1\\.checkedFg").click();
        $("#documentContainerList4\\.subContainer1\\.docItemName").setValue("Additional Paper 2");
        $("#file-input-4-1").setValue("/home/hanmomhanda/YS-DOC/114호_공학_트렌드_WEB_UI_개발_Part_1.pdf");
        $("#upload-button-4-1").click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        J("scrollByLines(40)");

        // 저장
        $("#saveDocument").click();
        assertEquals("File submission information is saved successfully.", closeAlertAndGetItsText());

        J("scrollByLines(200)");

        // 원서 미리보기 생성
        $("#generateApplication").click();
        $("#previewApplication").waitUntil(Condition.appear, 1000*120);
        $("#previewApplication").click();


        // 원서 제출
        if($("#submitApplication").is(Condition.enabled)) {
            $("#submitApplication").click();
            String submitNotice = closeAlertAndGetItsText();
            assertThat(submitNotice, StringStartsWith.startsWith("You can NOT modify application form after submission"));
            assertEquals("Application is submitted.", closeAlertAndGetItsText());
        } else {
            fail("원서 제출 실행 안됨");
        }
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

    private static void J(String javaScriptSource) {
        Object obj = js.executeScript(javaScriptSource);
//        System.out.println(obj);
    }
}
