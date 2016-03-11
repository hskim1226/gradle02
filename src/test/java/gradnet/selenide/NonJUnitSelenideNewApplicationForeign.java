package gradnet.selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.fail;

public class NonJUnitSelenideNewApplicationForeign extends NewApplicationForeign {

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
        $("#file-input-0-0-0").setValue("/home/hanmomhanda/YS-DOC/공 백/뚱석이.jpg");
        $("#upload-button-0-0-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-0-0-1").isDisplayed()) {
            $("#file-delete-link-0-0-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-0-0-1").setValue("/home/hanmomhanda/YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#upload-button-0-0-1").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-0-0-2").isDisplayed()) {
            $("#file-delete-link-0-0-2").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-0-0-2").setValue("/home/hanmomhanda/YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#upload-button-0-0-2").click();
        confirmAlert("File is uploaded successfully.");

        $("#file-input-0-0-2").scrollTo();

        if ($("#file-delete-link-1-0-0-0").isDisplayed()) {
            $("#file-delete-link-1-0-0-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-1-0-0-0").setValue("/home/hanmomhanda/YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#upload-button-1-0-0-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-1-0-0-1").isDisplayed()) {
            $("#file-delete-link-1-0-0-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-1-0-0-1").setValue("/home/hanmomhanda/YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#upload-button-1-0-0-1").click();
        confirmAlert("File is uploaded successfully.");

        $("#file-input-1-0-0-1").scrollTo();

        if ($("#file-delete-link-1-0-1-0").isDisplayed()) {
            $("#file-delete-link-1-0-1-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-1-0-1-0").setValue("/home/hanmomhanda/YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#upload-button-1-0-1-0").click();
        confirmAlert("File is uploaded successfully.");

        $("#file-input-1-0-1-0").scrollTo();

        if ($("#file-delete-link-2-0").isDisplayed()) {
            $("#file-delete-link-2-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-2-0").setValue("/home/hanmomhanda/YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#upload-button-2-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-3-0-0").isDisplayed()) {
            $("#file-delete-link-3-0-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-3-0-0").setValue("/home/hanmomhanda/YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#upload-button-3-0-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-3-0-1").isDisplayed()) {
            $("#file-delete-link-3-0-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-3-0-1").setValue("/home/hanmomhanda/YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#upload-button-3-0-1").click();
        confirmAlert("File is uploaded successfully.");

        $("#file-input-3-0-1").scrollTo();

        if ($("#file-delete-link-3-0-3").isDisplayed()) {
            $("#file-delete-link-3-0-3").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#file-input-3-0-3").setValue("/home/hanmomhanda/YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#upload-button-3-0-3").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-4-0").isDisplayed()) {
            $("#file-delete-link-4-0").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#documentContainerList4\\.subContainer0\\.checkedFg").click();
        $("#documentContainerList4\\.subContainer0\\.docItemName").setValue("Additional Paper 1");
        $("#file-input-4-0").setValue("/home/hanmomhanda/YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
        $("#upload-button-4-0").click();
        confirmAlert("File is uploaded successfully.");

        if ($("#file-delete-link-4-1").isDisplayed()) {
            $("#file-delete-link-4-1").click();
            confirmAlert("Do you want to delete uploaded file?");
            confirmAlert("File is deleted.");
        }
        $("#documentContainerList4\\.subContainer1\\.checkedFg").click();
        $("#documentContainerList4\\.subContainer1\\.docItemName").setValue("Additional Paper 2");
        $("#file-input-4-1").setValue("/home/hanmomhanda/YS-DOC/" + num + "/3메가이상-253페이지-progit.ko.pdf");
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

    public static void main(String[] args) throws Exception {
        System.setProperty("selenide.timeout", "10000");
//        NewApplicationForeign.baseUrl = "http://52.79.125.54:8080/yonsei";
        NewApplicationForeign.baseUrl = "http://localhost:8080/yonsei";
        NewApplicationForeign.userId = "Eng333";
        NewApplicationForeign.password = "Eng33333";
        int numOfRunners;

        if (args.length == 0) {
            numOfRunners = 1;
        }
        else if (args.length == 1) {
            numOfRunners = Integer.parseInt(args[0]);
        } else {
            throw new IllegalArgumentException("인수는 0 또는 1개여야 합니다.");
        }

        class TestRunner implements Runnable {

            int runnerId;

            public TestRunner(int runnerId) {
                this.runnerId = runnerId;
            }

            @Override
            public void run() {
                NonJUnitSelenideNewApplicationForeign test = new NonJUnitSelenideNewApplicationForeign();
                try {
                    NewApplicationForeign.로그인();
                    test.t01_기본정보입력_BASIS();
                    test.t02_학력정보입력_ACADEMY();
                    test.t03_어학경력정보입력();
//                    test.t04_파일첨부(runnerId);
                    test.t04_파일첨부();
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


}