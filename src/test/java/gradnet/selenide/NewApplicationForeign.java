package gradnet.selenide;

import com.codeborne.selenide.Condition;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.fail;

/**
 * Created by hanmomhanda on 16. 1. 27.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewApplicationForeign {

//    public static String baseUrl = "http://localhost:8080/yonsei";
//    public static String baseUrl = "https://www.gradnet.co.kr/yonsei";
    public static String baseUrl = "http://52.79.125.54:8080/yonsei";
    public static String userId = "Eng333";
    public static String password = "Eng33333";

    @BeforeClass
    public static void 로그인() throws Exception {
        // 아래는 Mozilla 드라이버로 쓸 때의 default 사용법
//        driver = WebDriverRunner.getWebDriver(); // private static WebDriver driver 필드 추가 필요

        // 실행 시 VM 옵션에  -Dselenide.browser=chrome 추가해야함
        System.setProperty("webdriver.chrome.driver", "/home/hanmomhanda/chromedriver");
//        driver = new ChromeDriver(); // driver는 명시 해주지 않아도 됨

        open(baseUrl + "/user/login?lang=en");
        $("#username").setValue(userId);
        $("#password").setValue(password);
        $("#btnLogin").click();

        // 특정 페이지만 테스트 할 때는 아래 내용 주석 처리
        open(baseUrl + "/index-en.html");
        J("window.scrollBy(0, 300)");
        J("$('#toAdmsList').click()");
        $("#toForeignApply").click();
        $("#checkAll").scrollTo();
        J("window.scrollBy(0, -200)");
        J("$('#checkAll').click()");
        $("#composePaper").scrollTo();
        J("$('#composePaper').click()");
    }

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void t01_기본정보입력_BASIS() throws Exception {
        // 특정 페이지 단독 테스트 시 사용
//        $("#modify3").click();

        // 지원 과정 선택
        $("#applAttrCode").selectOption("Foreigner Applicants");
        $("#campCode").selectOption("Seoul");
        $("#collCode").selectOption("Business");
        $("#deptCode").selectOption("Business Administration");
        $("#corsTypeCode").selectOption("Master's Degree");
        $("#detlMajCode").selectOption("Marketing");
        $("#partTimeYn").selectOption("Full-time");
        $("#btnBaseSave").click();
        confirmAlert("All selections are correct?");

        // 지원자 정보
        $("#application\\.korName").scrollTo();
        $("#application\\.korName").setValue("장보고");
        $("#application\\.engSur").setValue("jang");
        $("#application\\.engName").setValue("bogo");
        J("$('#citzCntrCode').val('111')");
        J("$('#citzCntrCode').change()");
        $("#application\\.rgstBornDate").scrollTo();
        $("#application\\.rgstBornDate").setValue("900909");
        $("#application\\.engSur").scrollTo();
        $("#application\\.gend1").click();

        // 지원자 상세 정보
        $("#application\\.engSur").scrollTo();
        J("document.getElementById('fornTypeCode').selectedIndex='1'");
        J("$('#fornTypeCode').change()");
        $("#applicationForeigner\\.homeAddr").setValue("704 Kingshill PL. #KC12219");
        $("#applicationForeigner\\.homeTel").setValue("213-748-5118");

        // 체류 정보
        $("#applicationForeigner\\.homeAddr").scrollTo();
        $("#applicationForeigner\\.paspNo").setValue("6549812317");
        $("#applicationForeigner\\.homeAddr").scrollTo();
        J("document.getElementById('applicationForeigner\\.visaTypeCode').selectedIndex='3'");
        J("$('#applicationForeigner\\\\.visaTypeCode').change()");
        $("#applicationForeigner\\.homeAddr").scrollTo();
        $("#applicationForeigner\\.visaNo").setValue("5498949651117887");
        J("document.querySelector('#applicationForeigner\\\\.visaExprDay').value = '20181227'");
        $("#applicationForeigner\\.fornRgstNo").setValue("4824121425645");

        // 지원자 연락처
        $("#applicationForeigner\\.fornRgstNo").scrollTo();
        J("$('#zipCode').val('143815')");    // hidden 필드는 selenide setValue()로 변경 불가, jQuery로 변경
        J("$('#address').val('서울특별시 광진구 강변북로 423')");    // hidden 필드는 jQuery로 변경
        $("#addressDetail").setValue("777");
        $("#application\\.telNum").setValue("01095147896");
        $("#application\\.mobiNum").setValue("01095147896");
        $("#application\\.mailAddr").setValue("hanmomhanda@gmail.com");

        // 비상연락처
        $("#application\\.mailAddr").scrollTo();
        $("#applicationForeigner\\.korEmrgName").setValue("장수왕");
        $("#applicationForeigner\\.korEmrgRela").selectOption("Acquaintance");
        $("#applicationForeigner\\.korEmrgTel").setValue("01032145698");

        $("#applicationForeigner\\.korEmrgRela").scrollTo();
        $("#applicationForeigner\\.homeEmrgName").setValue("Michael Jordan");
        $("#applicationForeigner\\.homeEmrgRela").selectOption("Relative");
        $("#applicationForeigner\\.homeEmrgTel").setValue("0154785698874");

        // 저장
        $("#saveBasis").click();
        confirmAlert("Basic information is saved successfully.\n\nYou can send an E-mail to request a recommendation letter in \'My List > Recommendation\'.");
    }

    @Test
    public void t02_학력정보입력_ACADEMY() throws Exception {
        // 특정 페이지 단독 테스트 시 주석 해제
//        $("#modify16").click();

        $(By.linkText("2. Educational Background")).click();

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
        $("#collegeList0\\.schlCntrCode").scrollTo();
        $("#college-radio-0").click();
        $("#collegeList0\\.grdaTypeCode").scrollTo();
        $("#collegeList0\\.collName").setValue("Education College");
        $("#collegeList0\\.majName").setValue("Children Education");
        $("#collegeList0\\.gradFormCode").selectOption("Percentage");
        $("#collegeList0\\.gradAvr").setValue("89");
        $("#collegeList0\\.gradFull").setValue("100");

        // 저장
        $("#saveAcademy").scrollTo();
        $("#saveAcademy").click();
        confirmAlert("Educational background information is saved successfully.");
    }

    @Test
    public void t03_어학경력정보입력() throws Exception {
        // 특정 페이지 단독 테스트 시 주석 해제
//        $("#modify0").click();

        $(By.linkText("3. Language Test and Career")).click();

        // 영어 면제
        $("#checkLang-0-0-1").scrollTo();
        if (!$("#checkForlExmp-0").isSelected())
            $("#checkForlExmp-0").click();
        confirm();
        J("document.getElementById('forlExmpCode-0').selectedIndex='1'");
        J("$('#forlExmpCode-0').change()");

        // TOPIK
//        $("#languageGroupList1\\.langList0\\.list").scrollTo();
        $("#checkForlExmp-0").scrollTo();
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
        confirmAlert("Language test result and career information is saved successfully.");
    }

    @Test
    public void t04_파일첨부() throws Exception {
        // 특정 페이지 단독 테스트 시 주석 해제
//        $("#modify1").click();

        $(By.linkText("4. File Submission and Submit")).click();

        $("#docChckYn").scrollTo();
    J("window.scrollBy(0, -200)");
        if ("off".equals($("#docChckYn").val()))
            $("#docChckYn").click();

        if ($("#file-delete-link-0-0-0").isDisplayed()) {
            $("#file-delete-link-0-0-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-0-0-0").setValue("/home/hanmomhanda/YS-DOC/공 백/뚱석이.jpg");
        $("#upload-button-0-0-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-0-0-1").isDisplayed()) {
            $("#file-delete-link-0-0-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-0-0-1").setValue("/home/hanmomhanda/YS-DOC/축변환매트릭스(Axis Transformation Matrices).pdf");
        $("#upload-button-0-0-1").click();
        confirmAlert("File is uploaded successfully.");

    $("#file-input-0-0-1").scrollTo();

        if ($("#file-delete-link-0-0-2").isDisplayed()) {
            $("#file-delete-link-0-0-2").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-0-0-2").setValue("/home/hanmomhanda/YS-DOC/79호_공학_트렌드_웹성능테스트-part_2.pdf");
        $("#upload-button-0-0-2").click();
        confirmAlert("File is uploaded successfully.");

    $("#file-input-0-0-2").scrollTo();

        if ($("#file-delete-link-1-0-0-0").isDisplayed()) {
            $("#file-delete-link-1-0-0-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-1-0-0-0").setValue("/home/hanmomhanda/YS-DOC/Modellipse_소개.pdf");
        $("#upload-button-1-0-0-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-1-0-0-1").isDisplayed()) {
            $("#file-delete-link-1-0-0-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-1-0-0-1").setValue("/home/hanmomhanda/YS-DOC/제62차 SW 테스트 전문가 양성 교육(일반).pdf");
        $("#upload-button-1-0-0-1").click();
        confirmAlert("File is uploaded successfully.");

    $("#file-input-1-0-0-1").scrollTo();

        if ($("#file-delete-link-1-0-1-0").isDisplayed()) {
            $("#file-delete-link-1-0-1-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-1-0-1-0").setValue("/home/hanmomhanda/YS-DOC/83호_공학_트렌드_GIT_Flow를_활용한_효과적인_소스_형상_관리_Part_2.pdf");
        $("#upload-button-1-0-1-0").click();
        confirmAlert("File is uploaded successfully.");

    $("#file-input-1-0-1-0").scrollTo();

        if ($("#file-delete-link-2-0").isDisplayed()) {
            $("#file-delete-link-2-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-2-0").setValue("/home/hanmomhanda/YS-DOC/114호_공학_트렌드_WEB_UI_개발_Part_1.pdf");
        $("#upload-button-2-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-3-0-0").isDisplayed()) {
            $("#file-delete-link-3-0-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-3-0-0").setValue("/home/hanmomhanda/YS-DOC/83호_공학_트렌드_GIT_Flow를_활용한_효과적인_소스_형상_관리_Part_2.pdf");
        $("#upload-button-3-0-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-3-0-1").isDisplayed()) {
            $("#file-delete-link-3-0-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-3-0-1").setValue("/home/hanmomhanda/YS-DOC/축변환매트릭스{Axis Transformation Matrices}`~,!@#[$%^]-_=+&(').pdf");
        $("#upload-button-3-0-1").click();
        confirmAlert("File is uploaded successfully.");

    $("#file-input-3-0-1").scrollTo();

        if ($("#file-delete-link-3-0-3").isDisplayed()) {
            $("#file-delete-link-3-0-3").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
//        $("#file-input-3-0-3").setValue("/home/hanmomhanda/YS-DOC/3메가이상-253페이지-progit.ko.pdf");
        $("#file-input-3-0-3").setValue("/home/hanmomhanda/YS-DOC/2014-동해물과백두산이마르고닳도록하느님이보우하사우리나라만세무궁화삼천리화려강산대한사람대한으로길이보전하세.pdf");
        $("#upload-button-3-0-3").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-4-0").isDisplayed()) {
            $("#file-delete-link-4-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#documentContainerList4\\.subContainer0\\.checkedFg").click();
        $("#documentContainerList4\\.subContainer0\\.docItemName").setValue("Encrypted PDF");
        $("#file-input-4-0").setValue("/home/hanmomhanda/YS-DOC/위변조방지/암호화-ETSN.pdf");
        $("#upload-button-4-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-4-1").isDisplayed()) {
            $("#file-delete-link-4-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#documentContainerList4\\.subContainer1\\.checkedFg").click();
        $("#documentContainerList4\\.subContainer1\\.docItemName").setValue("Anti-Forgery PDF");
        $("#file-input-4-1").setValue("/home/hanmomhanda/YS-DOC/위변조방지/위변조방지-홍익대학교_[국]성적증명서.pdf");
        $("#upload-button-4-1").click();
        confirmAlert("File is uploaded successfully.");

    $("#saveDocument").scrollTo();

        // 저장
        $("#saveDocument").click();
        confirmAlert("File submission information is saved successfully.");

    $("#saveDocument").scrollTo();

        // 원서 미리보기 생성
        $("#generateApplication").click();
        $("#previewApplication").waitUntil(Condition.appear, 1000*120);
        $("#previewApplication").click();


        // 원서 제출
        if($("#submitApplication").is(Condition.enabled)) {
            $("#submitApplication").click();
            confirmAlert("You can NOT modify application form after submission.\n\nDo you want to continue?");
            confirmAlert("Application is submitted.");
        } else {
            fail("원서 제출 실행 안됨");
        }
    }

    protected void confirmAlert(String msg) {
        confirm(msg);
    }

    protected static void J(String javaScriptSource) {
        Object obj = executeJavaScript(javaScriptSource);
//        System.out.println(obj);
    }
}
