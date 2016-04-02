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

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by hanmomhanda on 16. 1. 27.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewApplication {

    public static String baseUrl = "http://localhost:8080/yonsei";
    public static String userId = "Abc333";
    public static String password = "Abc33333";

    @BeforeClass
    public static void 로그인() throws Exception {

        System.setProperty("webdriver.chrome.driver", "/home/hanmomhanda/chromedriver");

        open(baseUrl + "/user/login");
        $("#username").setValue(userId);
        $("#password").setValue(password);
        $("#btnLogin").click();

        // 특정 페이지만 테스트 할 때는 아래 내용 주석 처리
        open(baseUrl);
        J("window.scrollBy(0, 300)");
        J("$('#toAdmsList').click()");
        $("#toGeneralApply").click();
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
        // 특정 페이지 단독 테스트 시 주석 해제
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
        confirmAlert("지원 사항을 정확히 지정 하셨습니까?");

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
        confirmAlert("기본 정보를 성공적으로 저장했습니다.");
    }

    @Test
    public void t02_학력정보입력_ACADEMY() throws Exception {
        // 특정 페이지 단독 테스트 시 주석 해제
//        $("#modify3").click();

        $(By.linkText("2. 학력 정보")).click();

        // 대학 입력
//        $("#collegeList0.schlCntrCode").setValue("118");    // hidden 필드는 Selenide API로 세팅 불가
        J("$('#collegeList0\\\\.schlCntrCode').val('118')");
        J("document.getElementById('collegeList0.korCntrName').value = '대한민국'");
        J("document.getElementById('collegeList0.entrDay').value = '20100301'");
        J("document.querySelector('#collegeList0\\\\.grdaDay').value = '20140228'");    // readonly 는 API나 jQuery로 변경 가능
//        $(By.linkText("2. 학력 정보")).scrollTo();
//        $("#collegeList0\\.grdaTypeCode").selectOption("졸업");
        J("document.getElementById('collegeList0\\.grdaTypeCode').selectedIndex='1'");
        J("$('#collegeList0\\\\.grdaTypeCode').change()");
        $("#collegeList0\\.degrNo").setValue("grad-874569");
        J("$('#collegeList0\\\\.schlCode').val('219')");    // hidden 필드는 DOM API나 jQuery로 변경 가능
//        $("#collegeList0\\.schlName").setValue("연세대학교");    // readonly 필드는 Selenide API로 세팅 불가
        J("document.getElementById('collegeList0\\.schlName').value = '연세대학교'");    // readonly 는 API나 jQuery로 변경 가능
        $("#collegeList0\\.schlCntrCode").scrollTo();
        $("#college-radio-0").click();
        $("#collegeList0\\.collName").setValue("경영대학");
        $("#collegeList0\\.majName").setValue("경영학과");
        $("#collegeList0\\.gradFormCode").selectOption("평량평균");
        $("#collegeList0\\.gradAvr").setValue("4.25");
        $("#collegeList0\\.gradFull").setValue("4.50");

        // 저장
        $("#saveAcademy").scrollTo();
        $("#saveAcademy").click();
        confirmAlert("학력 정보를 성공적으로 저장했습니다.");
    }

    @Test
    public void test03_어학경력정보입력() throws Exception {
        // 특정 페이지 단독 테스트 시 주석 해제
//        $("#modify2").click();

        $(By.linkText("3. 어학/경력 정보")).click();

        // TOEFL - CBT
        if (!$("#checkLang-0-0-0").isSelected())
            $("#checkLang-0-0-0").click();
//        $("#languageGroupList0\\.langList0\\.subContainer0\\.subCode").selectOption("CBT");
        J("document.getElementById('languageGroupList0\\.langList0\\.subContainer0\\.subCode').selectedIndex='2'");
        J("$('#languageGroupList0\\\\.langList0\\\\.subContainer0\\\\.subCode').change()");

        $("#stepStatusTitle").scrollTo();
        J("document.getElementById('languageGroupList0.langList0.subContainer0.examDay').value = '20141103'"); // readonly는 DOM API나 jQuery로
        $("#languageGroupList0\\.langList0\\.subContainer0\\.langGrad").setValue("280");

        // TOEFL - IELTS
        if (!$("#checkLang-0-0-2").isSelected())
            $("#checkLang-0-0-2").click();
        J("document.getElementById('languageGroupList0.langList0.subContainer2.examDay').value = '20141108'"); // readonly는 DOM API나 jQuery로
        $("#languageGroupList0\\.langList0\\.subContainer2\\.langGrad").selectOption("8.0");

        // 경력 사항
        $("#saveLangCareer").scrollTo();
        J("document.getElementById('applicationExperienceList0.joinDay').value = '20100407'"); // readonly는 DOM API나 jQuery로
        J("document.getElementById('applicationExperienceList0.retrDay').value = '20140831'"); // readonly는 DOM API나 jQuery로
        $("#applicationExperienceList0\\.corpName").setValue("좋은 회사");
        $("#applicationExperienceList0\\.exprDesc").setValue("마케팅 기획 총괄");

        // 저장
        $("#saveLangCareer").click();
        confirmAlert("어학 및 경력 정보를 성공적으로 저장했습니다.");
    }

    @Test
    public void test04_파일첨부() throws Exception {
        // 특정 페이지 단독 테스트 시 주석 해제
//        $("#modify2").click();

        $(By.linkText("4. 파일 첨부 및 제출")).click();

        $("#docChckYn").scrollTo();
    J("window.scrollBy(0, -200)");
        if ("off".equals($("#docChckYn").val()))
            $("#docChckYn").click();

        if ($("#file-delete-link-0-0-0").isDisplayed()) {
            $("#file-delete-link-0-0-0").click();
            confirmAlert("첨부한 파일을 삭제하시겠습니까?");
            confirmAlert("첨부 파일을 삭제했습니다.");
        }
        $("#file-input-0-0-0").setValue("/home/hanmomhanda/YS-DOC/공 백/뚱석이.jpg");
        $("#upload-button-0-0-0").click();
        confirmAlert("파일이 업로드 되었습니다.");

        if ($("#file-delete-link-0-0-1").isDisplayed()) {
            $("#file-delete-link-0-0-1").click();
            confirmAlert("첨부한 파일을 삭제하시겠습니까?");
            confirmAlert("첨부 파일을 삭제했습니다.");
        }
        $("#file-input-0-0-1").setValue("/home/hanmomhanda/YS-DOC/축변환매트릭스(Axis Transformation Matrices).pdf");
        $("#upload-button-0-0-1").click();
        confirmAlert("파일이 업로드 되었습니다.");

        if ($("#file-delete-link-0-1-0").isDisplayed()) {
            $("#file-delete-link-0-1-0").click();
            confirmAlert("첨부한 파일을 삭제하시겠습니까?");
            confirmAlert("첨부 파일을 삭제했습니다.");
        }
        $("#file-input-0-1-0").setValue("/home/hanmomhanda/YS-DOC/2014-국가직무능력표준 개발전문가 모집_작성양식_PDF.pdf");
        $("#upload-button-0-1-0").click();
        confirmAlert("파일이 업로드 되었습니다.");

    $("#file-input-0-1-0").scrollTo();

        if ($("#file-delete-link-1-0-0-0").isDisplayed()) {
            $("#file-delete-link-1-0-0-0").click();
            confirmAlert("첨부한 파일을 삭제하시겠습니까?");
            confirmAlert("첨부 파일을 삭제했습니다.");
        }
        $("#file-input-1-0-0-0").setValue("/home/hanmomhanda/YS-DOC/Modellipse_소개.pdf");
        $("#upload-button-1-0-0-0").click();
        confirmAlert("파일이 업로드 되었습니다.");

        if ($("#file-delete-link-1-0-0-1").isDisplayed()) {
            $("#file-delete-link-1-0-0-1").click();
            confirmAlert("첨부한 파일을 삭제하시겠습니까?");
            confirmAlert("첨부 파일을 삭제했습니다.");
        }
        $("#file-input-1-0-0-1").setValue("/home/hanmomhanda/YS-DOC/제62차 SW 테스트 전문가 양성 교육(일반).pdf");
        $("#upload-button-1-0-0-1").click();
        confirmAlert("파일이 업로드 되었습니다.");

    $("#file-input-1-0-0-1").scrollTo();

        if ($("#file-delete-link-2-0").isDisplayed()) {
            $("#file-delete-link-2-0").click();
            confirmAlert("첨부한 파일을 삭제하시겠습니까?");
            confirmAlert("첨부 파일을 삭제했습니다.");
        }
        $("#file-input-2-0").setValue("/home/hanmomhanda/YS-DOC/83호_공학_트렌드_GIT_Flow를_활용한_효과적인_소스_형상_관리_Part_2.pdf");
        $("#upload-button-2-0").click();
        confirmAlert("파일이 업로드 되었습니다.");

        if ($("#file-delete-link-2-1").isDisplayed()) {
            $("#file-delete-link-2-1").click();
            confirmAlert("첨부한 파일을 삭제하시겠습니까?");
            confirmAlert("첨부 파일을 삭제했습니다.");
        }
//        $("#file-input-2-1").setValue("/home/hanmomhanda/YS-DOC/3메가이상-253페이지-progit.ko.pdf");
        $("#file-input-2-1").setValue("/home/hanmomhanda/YS-DOC/2014-동해물과백두산이마르고닳도록하느님이보우하사우리나라만세무궁화삼천리화려강산대한사람대한으로길이보전하세.pdf");
        $("#upload-button-2-1").click();
        confirmAlert("파일이 업로드 되었습니다.");

    $("#file-input-2-1").scrollTo();

        if ($("#file-delete-link-3-0").isDisplayed()) {
            $("#file-delete-link-3-0").click();
            confirmAlert("첨부한 파일을 삭제하시겠습니까?");
            confirmAlert("첨부 파일을 삭제했습니다.");
        }
        $("#documentContainerList3\\.subContainer0\\.checkedFg").click();
        $("#documentContainerList3\\.subContainer0\\.docItemName").setValue("암호화");
        $("#file-input-3-0").setValue("/home/hanmomhanda/YS-DOC/위변조방지/암호화-ETSN.pdf");
        $("#upload-button-3-0").click();
        confirmAlert("파일이 업로드 되었습니다.");

        if ($("#file-delete-link-3-1").isDisplayed()) {
            $("#file-delete-link-3-1").click();
            confirmAlert("첨부한 파일을 삭제하시겠습니까?");
            confirmAlert("첨부 파일을 삭제했습니다.");
        }
        $("#documentContainerList3\\.subContainer1\\.checkedFg").click();
        $("#documentContainerList3\\.subContainer1\\.docItemName").setValue("위변조방지");
        $("#file-input-3-1").setValue("/home/hanmomhanda/YS-DOC/위변조방지/위변조방지-홍익대학교_[국]성적증명서.pdf");
        $("#upload-button-3-1").click();
        confirmAlert("파일이 업로드 되었습니다.");

    $("#saveDocument").scrollTo();

        // 저장
        $("#saveDocument").click();
        confirmAlert("첨부 파일을 성공적으로 저장했습니다.");

    $("#saveDocument").scrollTo();

        // 원서 미리보기 생성
        $("#generateApplication").click();
        $("#previewApplication").waitUntil(Condition.appear, 1000*120);
        $("#previewApplication").click();


        // 원서 제출
        if($("#submitApplication").is(Condition.enabled)) {
            $("#submitApplication").click();
            confirmAlert("원서 제출 후에는 원서 내용을 수정할 수 없습니다.\n\n계속하시겠습니까?");
            confirmAlert("원서가 제출 되었습니다.");
        } else {
            fail("원서 제출 실행 안됨");
        }
    }

    private void confirmAlert(String msg) {
        confirm(msg);
    }

    private static void J(String javaScriptSource) {
        Object obj = executeJavaScript(javaScriptSource);
//        System.out.println(obj);
    }
}
