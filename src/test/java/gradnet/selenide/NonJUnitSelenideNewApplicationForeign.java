/**
 * 여러개의 브라우저를 동시에 띄워서 Selenide 테스트를 실행한다.
 * 사용법 : SelenideMultiUserTest 파일 참고
 */
package gradnet.selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.fail;

public class NonJUnitSelenideNewApplicationForeign extends NewApplicationForeign {

    public static String baseUrl = "https://www.gradnet.co.kr/yonsei";
    public static String userId;
    public static String password;
    public static String docRoot;

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("아이디, 비밀번호, 테스터 개수, 첨부파일Root(예: /home/hanmomhanda/) 를 입력해야 합니다.");
        }
        System.setProperty("selenide.timeout", "10000");
        userId = args[0];
        password = args[1];
        int numOfRunners = Integer.parseInt(args[2]);
        docRoot = args[3];



        class TestRunner implements Runnable {

            int runnerId;

            public TestRunner(int runnerId) {
                this.runnerId = runnerId;
            }

            @Override
            public void run() {
                NonJUnitSelenideNewApplicationForeign test = new NonJUnitSelenideNewApplicationForeign();
                try {
                    test.setUpConnection();
                    test.t01_기본정보입력_BASIS();
                    test.t02_학력정보입력_ACADEMY();
                    test.t03_어학경력정보입력();
                    test.t04_파일첨부(runnerId);
//                    test.t04_파일첨부();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0 ; i < numOfRunners ; i++) {
            Thread t = new Thread(new TestRunner(i));
            t.sleep(3000);
            t.start();

        }
    }

    public void setUpConnection() {

        open(baseUrl + "/user/login?lang=en");
        $("#username").setValue(userId);
        $("#password").setValue(password);
        $("#btnLogin").click();

        // 특정 페이지만 테스트 할 때는 아래 내용 주석 처리
        open(baseUrl + "/index-en.html");
        $("#toAdmsList").click();
        $("#toForeignApply").click();
        $("#checkAll").scrollTo();
        $("#checkAll").click();
        J("$('#composePaper').click()");
    }


    public void t04_파일첨부(int num) throws Exception {
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
        $("#file-input-0-0-0").setValue(docRoot + "YS-DOC/공 백/뚱석이.jpg");
        $("#upload-button-0-0-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-0-0-1").isDisplayed()) {
            $("#file-delete-link-0-0-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-0-0-1").setValue(docRoot + "YS-DOC/축변환매트릭스(Axis Transformation Matrices).pdf");
        $("#upload-button-0-0-1").click();
        confirmAlert("File is uploaded successfully.");

    $("#file-input-0-0-1").scrollTo();

        if ($("#file-delete-link-0-0-2").isDisplayed()) {
            $("#file-delete-link-0-0-2").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-0-0-2").setValue(docRoot + "YS-DOC/79호_공학_트렌드_웹성능테스트-part_2.pdf");
        $("#upload-button-0-0-2").click();
        confirmAlert("File is uploaded successfully.");

    $("#file-input-0-0-2").scrollTo();

        if ($("#file-delete-link-1-0-0-0").isDisplayed()) {
            $("#file-delete-link-1-0-0-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
//        $("#file-input-1-0-0-0").setValue(docRoot + "YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#file-input-1-0-0-0").setValue(docRoot + "YS-DOC/" + num + "/축변환매트릭스{Axis Transformation Matrices}`~,!@#[$%^]-_=+&(').pdf");
        $("#upload-button-1-0-0-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-1-0-0-1").isDisplayed()) {
            $("#file-delete-link-1-0-0-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-1-0-0-1").setValue(docRoot + "YS-DOC/Modellipse_소개.pdf");
        $("#upload-button-1-0-0-1").click();
        confirmAlert("File is uploaded successfully.");

    $("#file-input-1-0-0-1").scrollTo();

        if ($("#file-delete-link-1-0-1-0").isDisplayed()) {
            $("#file-delete-link-1-0-1-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
//        $("#file-input-1-0-1-0").setValue(docRoot + "YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#file-input-1-0-1-0").setValue(docRoot + "YS-DOC/" + num + "/축변환매트릭스{Axis Transformation Matrices}`~,!@#[$%^]-_=+&(').pdf");
        $("#upload-button-1-0-1-0").click();
        confirmAlert("File is uploaded successfully.");

    $("#file-input-1-0-1-0").scrollTo();

        if ($("#file-delete-link-2-0").isDisplayed()) {
            $("#file-delete-link-2-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-2-0").setValue(docRoot + "YS-DOC/제62차 SW 테스트 전문가 양성 교육(일반).pdf");
        $("#upload-button-2-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-3-0-0").isDisplayed()) {
            $("#file-delete-link-3-0-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
//        $("#file-input-3-0-0").setValue(docRoot + "YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#file-input-3-0-0").setValue(docRoot + "YS-DOC/" + num + "/제62차 SW 테스트 전문가 양성 교육(일반).pdf");
        $("#upload-button-3-0-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-3-0-1").isDisplayed()) {
            $("#file-delete-link-3-0-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-3-0-1").setValue(docRoot + "YS-DOC/83호_공학_트렌드_GIT_Flow를_활용한_효과적인_소스_형상_관리_Part_2.pdf");
        $("#upload-button-3-0-1").click();
        confirmAlert("File is uploaded successfully.");

    $("#file-input-3-0-1").scrollTo();

        if ($("#file-delete-link-3-0-3").isDisplayed()) {
            $("#file-delete-link-3-0-3").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
//        $("#file-input-3-0-3").setValue(docRoot + "YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#file-input-3-0-3").setValue(docRoot + "YS-DOC/" + num + "/114호_공학_트렌드_WEB_UI_개발_Part_1.pdf");
        $("#upload-button-3-0-3").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-4-0").isDisplayed()) {
            $("#file-delete-link-4-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#documentContainerList4\\.subContainer0\\.checkedFg").click();
        $("#documentContainerList4\\.subContainer0\\.docItemName").setValue("Additional Paper 1");
        $("#file-input-4-0").setValue(docRoot + "YS-DOC/위변조방지/암호화-ETSN.pdf");
        $("#upload-button-4-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-4-1").isDisplayed()) {
            $("#file-delete-link-4-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#documentContainerList4\\.subContainer1\\.checkedFg").click();
        $("#documentContainerList4\\.subContainer1\\.docItemName").setValue("Additional Paper 2");
        $("#file-input-4-1").setValue(docRoot + "YS-DOC/위변조방지/위변조방지-홍익대학교_[국]성적증명서.pdf");
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



}