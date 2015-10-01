package gradnet.selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestForeign {
    private static WebDriver driver;
    private static String baseUrl;
    private boolean acceptNextAlert = true;
    private static StringBuffer verificationErrors = new StringBuffer();
    private static WebDriverWait wait;
    private static JavascriptExecutor js;
    private static String userId = "Abc888";
    private static String password = "Abc88888";

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080";
        driver.manage().window().setSize(new Dimension(1600, 1000));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
        js = (JavascriptExecutor) driver;

        driver.get(baseUrl + "/yonsei/user/login");

        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(userId);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();
    }

    @Test
    public void test0_언어전환() throws Exception {
        driver.get(baseUrl + "/yonsei");
        driver.findElement(By.id("toAdmsList")).click();
        driver.findElement(By.id("nav-transLang")).click();
        driver.findElement(By.id("english")).click();
        assertEquals("English", driver.findElement(By.id("english")).getText());
    }

//    @Test
//    public void test1_기본정보생성() throws Exception {
//        driver.get(baseUrl + "/yonsei");
//        driver.findElement(By.id("toAdmsList")).click();
//        driver.findElement(By.id("toForeignApply")).click();
//        js.executeScript("scroll(0, 3000)");
//        driver.findElement(By.id("checkAll")).click();
//        driver.findElement(By.id("composePaper")).click();
//        new Select(driver.findElement(By.id("applAttrCode"))).selectByVisibleText("Foreigner Applicants");
//        new Select(driver.findElement(By.id("campCode"))).selectByVisibleText("International");
//        new Select(driver.findElement(By.id("collCode"))).selectByVisibleText("College of Pharmacy");
//        driver.findElement(By.cssSelector("option[value=\"1202\"]")).click();
//        new Select(driver.findElement(By.id("deptCode"))).selectByVisibleText("Pharmacy");
//        driver.findElement(By.cssSelector("option[value=\"10520\"]")).click();
//        new Select(driver.findElement(By.id("corsTypeCode"))).selectByVisibleText("Ph.D. Degree");
//        driver.findElement(By.cssSelector("option[value=\"02\"]")).click();
//        new Select(driver.findElement(By.id("detlMajCode"))).selectByVisibleText("N/A");
//        driver.findElement(By.id("btnBaseSave")).click();
//        assertEquals("All selections are correct?", closeAlertAndGetItsText());
//        js.executeScript("scrollByLines(10)");
////        driver.findElement(By.id("application.korName")).clear();
////        driver.findElement(By.id("application.korName")).sendKeys("");
//        driver.findElement(By.id("application.engSur")).clear();
//        driver.findElement(By.id("application.engSur")).sendKeys("Marlon");
//        driver.findElement(By.id("application.engName")).clear();
//        driver.findElement(By.id("application.engName")).sendKeys("De Yong");
//        js.executeScript("$('#citzCntrCode').val('227')");
//        js.executeScript("$('#citzCntrCode').change()");
//        driver.findElement(By.id("application.rgstBornDate")).clear();
//        driver.findElement(By.id("application.rgstBornDate")).sendKeys("900909");
//        driver.findElement(By.id("application.gend1")).click();
//
//        js.executeScript("scrollByLines(10)");
//        WebElement selectionTypeOfForeigner = driver.findElement(By.id("fornTypeCode"));
//        selectionTypeOfForeigner.click();
//        Select selectTypeOfForeigner = new Select(selectionTypeOfForeigner);
//        selectTypeOfForeigner.selectByVisibleText("Foreigner");
//        driver.findElement(By.id("applicationForeigner.homeAddr")).clear();
//        driver.findElement(By.id("applicationForeigner.homeAddr")).sendKeys("2700 S. Grand Ave., Los Angeles, CA 90007-3301 United States");
//        driver.findElement(By.id("applicationForeigner.homeTel")).clear();
//        driver.findElement(By.id("applicationForeigner.homeTel")).sendKeys("213-748-5118");
//
//        Point pointPaspNo = driver.findElement(By.id("applicationForeigner.paspNo")).getLocation();
//        driver.manage().window().setPosition(pointPaspNo);
//        driver.findElement(By.id("applicationForeigner.paspNo")).clear();
//        driver.findElement(By.id("applicationForeigner.paspNo")).sendKeys("asd093as890nf");
//        WebElement selectionVisaTypeCode = driver.findElement(By.id("applicationForeigner.visaTypeCode"));
//        selectionVisaTypeCode.click();
//        Select selectVisaTypeCode = new Select(selectionVisaTypeCode);
//        selectVisaTypeCode.selectByVisibleText("ETC");
////        selectVisaTypeCode.selectByValue("00099");
//        js.executeScript("scrollByLines(-10)");
//        driver.findElement(By.tagName("body")).click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("applicationForeigner.visaTypeEtc"))));
//        driver.findElement(By.id("applicationForeigner.visaTypeEtc")).click();
//        driver.findElement(By.id("applicationForeigner.visaTypeEtc")).clear();
//        driver.findElement(By.id("applicationForeigner.visaTypeEtc")).sendKeys("D-4");
//        driver.findElement(By.id("applicationForeigner.visaNo")).clear();
//        driver.findElement(By.id("applicationForeigner.visaNo")).sendKeys("us-visa-3ac48r");
//        js.executeScript("document.getElementById('applicationForeigner.visaExprDay').value = '20181231'");
//
//        js.executeScript("scrollByLines(10)");
//        js.executeScript("$('#zipCode').val('143815')");
////        driver.findElement(By.id("address")).clear();
////        driver.findElement(By.id("address")).sendKeys("서울특별시 광진구 강변북로 423");
//        js.executeScript("$('#address').val('서울특별시 광진구 강변북로 423')");
//        driver.findElement(By.id("addressDetail")).clear();
//        driver.findElement(By.id("addressDetail")).sendKeys("777");
//
//        driver.findElement(By.id("application.telNum")).clear();
//        driver.findElement(By.id("application.telNum")).sendKeys("01095147896");
//        driver.findElement(By.id("application.mobiNum")).clear();
//        driver.findElement(By.id("application.mobiNum")).sendKeys("01095147896");
//        driver.findElement(By.id("application.mailAddr")).clear();
//        driver.findElement(By.id("application.mailAddr")).sendKeys("hanmomhanda@gmail.com");
//
//        js.executeScript("scrollByLines(10)");
//        driver.findElement(By.id("applicationForeigner.korEmrgName")).clear();
//        driver.findElement(By.id("applicationForeigner.korEmrgName")).sendKeys("장수왕");
//        new Select(driver.findElement(By.id("applicationForeigner.korEmrgRela"))).selectByVisibleText("Acquaintance");
//        driver.findElement(By.id("applicationForeigner.korEmrgTel")).clear();
//        driver.findElement(By.id("applicationForeigner.korEmrgTel")).sendKeys("01032145698");
//
//        js.executeScript("scrollByLines(10)");
//        driver.findElement(By.id("applicationForeigner.homeEmrgName")).clear();
//        driver.findElement(By.id("applicationForeigner.homeEmrgName")).sendKeys("Marlon De Dragon");
//        new Select(driver.findElement(By.id("applicationForeigner.homeEmrgRela"))).selectByVisibleText("Father");
//        driver.findElement(By.id("applicationForeigner.homeEmrgTel")).clear();
//        driver.findElement(By.id("applicationForeigner.homeEmrgTel")).sendKeys("01021458796");
//
//        driver.findElement(By.id("saveBasis")).click();
//        assertEquals("기본 정보를 성공적으로 저장했습니다.", closeAlertAndGetItsText());
//    }
//
//    @Test
//    public void test2_학력정보생성() throws Exception {
////        driver.get(baseUrl + "/yonsei/application/mylist");
////        driver.findElement(By.id("modify1")).click();
////        js.executeScript("scrollTo(0, 10)");
//        driver.findElement(By.linkText("2. Educational Background")).click();
//
//        js.executeScript("scrollByLines(10)");
//        js.executeScript("document.getElementById('collegeList0.schlCntrCode').value = '227'");
//        js.executeScript("document.getElementById('collegeList0.korCntrName').value = 'USA'");
//
//        js.executeScript("document.getElementById('collegeList0.entrDay').value = '20080301'");
//        js.executeScript("document.getElementById('collegeList0.grdaDay').value = '20120228'");
//        WebElement selectElement = driver.findElement(By.id("collegeList0.grdaTypeCode"));
//        selectElement.click();
//        new Select(selectElement).selectByVisibleText("Graduated");
////        new Select(driver.findElement(By.id("collegeList0.grdaTypeCode"))).selectByVisibleText("Graduated");
//        js.executeScript("document.getElementById('collegeList0.degrNo').value = 'grad-874569'");
//
//        js.executeScript("$('#collegeList0\\\\.schlCode').val('999')");
//        js.executeScript("$('#collegeList0\\\\.schlName').val('University of Pennsylvania')");
//        driver.findElement(By.id("college-radio-0")).click();
//        driver.findElement(By.id("collegeList0.collName")).clear();
//        driver.findElement(By.id("collegeList0.collName")).sendKeys("College of Science");
//        driver.findElement(By.id("collegeList0.majName")).clear();
//        driver.findElement(By.id("collegeList0.majName")).sendKeys("Biology");
////        js.executeScript("scrollBy(0, -300)");
////        WebElement selectElement1 = driver.findElement(By.id("collegeList0.gradFormCode"));
////        selectElement1.click();
////        js.executeScript("scrollBy(0, -300)");
////        new Select(selectElement1).selectByVisibleText("ETC");
//        new Select(driver.findElement(By.id("collegeList0.gradFormCode"))).selectByVisibleText("ETC");
//        driver.findElement(By.id("collegeList0.gradAvr")).clear();
//        driver.findElement(By.id("collegeList0.gradAvr")).sendKeys("A-");
//        driver.findElement(By.id("collegeList0.gradFull")).clear();
//        driver.findElement(By.id("collegeList0.gradFull")).sendKeys("A+");
//
//
//        js.executeScript("scrollByLines(10)");
//        js.executeScript("document.getElementById('graduateList0.schlCntrCode').value = '227'");
//        js.executeScript("document.getElementById('graduateList0.korCntrName').value = 'USA'");
//
//        js.executeScript("document.getElementById('graduateList0.entrDay').value = '20120301'");
//        js.executeScript("document.getElementById('graduateList0.grdaDay').value = '20140228'");
////        WebElement selectElement2 = driver.findElement(By.id("graduateList0.grdaTypeCode"));
////        selectElement2.click();
////        new Select(selectElement2).selectByVisibleText("Graduated");
//        new Select(driver.findElement(By.id("graduateList0.grdaTypeCode"))).selectByVisibleText("Graduated");
//        js.executeScript("document.getElementById('graduateList0.degrNo').value = 'grad-254786622'");
//
//        js.executeScript("$('#graduateList0\\\\.schlCode').val('999')");
//        js.executeScript("$('#graduateList0\\\\.schlName').val('University of Pennsylvania')");
//        driver.findElement(By.id("graduate-radio-0")).click();
//        driver.findElement(By.id("graduateList0.collName")).clear();
//        driver.findElement(By.id("graduateList0.collName")).sendKeys("College of Pharmacy");
//        driver.findElement(By.id("graduateList0.majName")).clear();
//        driver.findElement(By.id("graduateList0.majName")).sendKeys("Pharmacy");
////        WebElement selectElement3 = driver.findElement(By.id("graduateList0.gradFormCode"));
////        selectElement3.click();
////        new Select(selectElement3).selectByVisibleText("ETC");
//////        js.executeScript("scrollBy(0, -300)");
//        new Select(driver.findElement(By.id("graduateList0.gradFormCode"))).selectByVisibleText("ETC");
//        driver.findElement(By.id("graduateList0.gradAvr")).clear();
//        driver.findElement(By.id("graduateList0.gradAvr")).sendKeys("A");
//        driver.findElement(By.id("graduateList0.gradFull")).clear();
//        driver.findElement(By.id("graduateList0.gradFull")).sendKeys("A+");
//
//        js.executeScript("scrollByLines(5)");
//        driver.findElement(By.id("saveAcademy")).click();
//        assertEquals("Academy background information is saved successfully.", closeAlertAndGetItsText());
//    }
//
//    @Test
//    public void test3_어학경력정보생성() throws Exception {
////        driver.get(baseUrl + "/yonsei/application/mylist");
////        driver.findElement(By.id("modify1")).click();
//
//        driver.findElement(By.linkText("3. Language Test and Career")).click();
//
//        js.executeScript("scrollByLines(15)");
//        driver.findElement(By.id("checkForlExmp-0")).click();
//        assertTrue(closeAlertAndGetItsText().startsWith("If you choose Exemption"));
////        new Select(driver.findElement(By.id("forlExmpCode-0"))).selectByVisibleText("Proof of Proficiency");
//        WebElement select0 = driver.findElement(By.id("forlExmpCode-0"));
//        select0.click();
//        new Select(select0).selectByVisibleText("Proof of Proficiency");
//
//        js.executeScript("scrollByLines(30)");
//        driver.findElement(By.id("checkForlExmp-1")).click();
//        assertTrue(closeAlertAndGetItsText().startsWith("If you choose Exemption"));
//        new Select(driver.findElement(By.id("forlExmpCode-1"))).selectByVisibleText("Proof of Proficiency");
//
//        js.executeScript("scrollByLines(10)");
//        js.executeScript("document.getElementById('applicationExperienceList0.joinDay').value = '20140407'");
//        js.executeScript("document.getElementById('applicationExperienceList0.retrDay').value = '20141231'");
//        driver.findElement(By.id("applicationExperienceList0.corpName")).clear();
//        driver.findElement(By.id("applicationExperienceList0.corpName")).sendKeys("Goodness Corporation");
//        driver.findElement(By.id("applicationExperienceList0.exprDesc")).clear();
//        driver.findElement(By.id("applicationExperienceList0.exprDesc")).sendKeys("Pharmacy R&D");
//        driver.findElement(By.id("saveLangCareer")).click();
//        assertEquals("Language test result and career information is saved successfully.", closeAlertAndGetItsText());
//    }
//
//    @Test
//    public void test3_1_어학정보수정() throws Exception {
////        driver.get(baseUrl + "/yonsei/application/mylist");
////        driver.findElement(By.id("modify1")).click();
//
//        driver.findElement(By.linkText("3. Language Test and Career")).click();
//
//        js.executeScript("scrollTo(0, 400)");
//        driver.findElement(By.id("checkForlExmp-0")).click(); // uncheck
//        driver.findElement(By.id("checkLang-0-0-1")).click();
////        WebElement selectElement = driver.findElement(By.id("languageGroupList0.langList0.subContainer1.subCode"));
////        selectElement.click();
////        new Select(selectElement).selectByVisibleText("CBT");
//        js.executeScript("document.getElementById('languageGroupList0.langList0.subContainer1.examDay').value = '20141103'");
//        js.executeScript("scrollBy(0, -300)");
//        driver.findElement(By.id("languageGroupList0.langList0.subContainer1.langGrad")).clear();
//        driver.findElement(By.id("languageGroupList0.langList0.subContainer1.langGrad")).sendKeys("999");
//        js.executeScript("document.getElementById('languageGroupList0.langList0.subContainer1.langGrad').blur()");
//        assertEquals("990 is the upper limit.", closeAlertAndGetItsText());
//        driver.findElement(By.id("languageGroupList0.langList0.subContainer1.langGrad")).clear();
//        driver.findElement(By.id("languageGroupList0.langList0.subContainer1.langGrad")).sendKeys("880");
//
//        js.executeScript("scrollByLines(30)");
//        driver.findElement(By.id("checkForlExmp-1")).click(); // uncheck
//        driver.findElement(By.id("checkLang-1-0-0")).click();
//        js.executeScript("document.getElementById('languageGroupList1.langList0.subContainer0.examDay').value = '20150303'");
//        new Select(driver.findElement(By.id("languageGroupList1.langList0.subContainer0.langGrad"))).selectByVisibleText("Level-6");
//
//        js.executeScript("scrollByLines(10)");
//        js.executeScript("document.getElementById('applicationExperienceList0.joinDay').value = '20140408'");
//        js.executeScript("document.getElementById('applicationExperienceList0.retrDay').value = '20141231'");
//        driver.findElement(By.id("applicationExperienceList0.corpName")).clear();
//        driver.findElement(By.id("applicationExperienceList0.corpName")).sendKeys("Wellness Corporation");
//        driver.findElement(By.id("applicationExperienceList0.exprDesc")).clear();
//        driver.findElement(By.id("applicationExperienceList0.exprDesc")).sendKeys("Pharmacy R&D");
//        driver.findElement(By.id("saveLangCareer")).click();
//        assertEquals("Language test result and career information is saved successfully.", closeAlertAndGetItsText());
//    }

    @Test
    public void test4_첨부파일정보생성() throws Exception {
        driver.get(baseUrl + "/yonsei/application/mylist");
        driver.findElement(By.id("modify0")).click();

        driver.findElement(By.linkText("4. File Submission and Submit")).click();

        js.executeScript("scrollBy(0, 5000)");
        js.executeScript("scrollTo(0, 1000)");
        driver.findElement(By.id("docChckYn")).click();

        driver.findElement(By.id("file-input-0-0-0")).clear();
        driver.findElement(By.id("file-input-0-0-0")).sendKeys("/home/hanmomhanda/YS-DOC/공 백/LG_트롬 모델로_발탁된_배우_이민정_(20).jpg");
        driver.findElement(By.id("upload-button-0-0-0")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        driver.findElement(By.id("file-input-0-0-1")).clear();
        driver.findElement(By.id("file-input-0-0-1")).sendKeys("/home/hanmomhanda/YS-DOC/축변환매트릭스(Axis Transformation Matrices).pdf");
        driver.findElement(By.id("upload-button-0-0-1")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        driver.findElement(By.id("file-input-0-0-2")).clear();
        driver.findElement(By.id("file-input-0-0-2")).sendKeys("/home/hanmomhanda/YS-DOC/제62차 SW 테스트 전문가 양성 교육(일반).pdf");
        driver.findElement(By.id("upload-button-0-0-2")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        js.executeScript("scrollByLines(10)");

        driver.findElement(By.id("file-input-1-0-0-0")).clear();
        driver.findElement(By.id("file-input-1-0-0-0")).sendKeys("/home/hanmomhanda/YS-DOC/83호_공학_트렌드_GIT_Flow를_활용한_효과적인_소스_형상_관리_Part_2.pdf");
        driver.findElement(By.id("upload-button-1-0-0-0")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        driver.findElement(By.id("file-input-1-0-0-1")).clear();
        driver.findElement(By.id("file-input-1-0-0-1")).sendKeys("/home/hanmomhanda/YS-DOC/79호_공학_트렌드_웹성능테스트-part_2.pdf");
        driver.findElement(By.id("upload-button-1-0-0-1")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        js.executeScript("scrollByLines(30)");
        driver.findElement(By.id("file-input-1-0-1-0")).clear();
        driver.findElement(By.id("file-input-1-0-1-0")).sendKeys("/home/hanmomhanda/YS-DOC/2014-국가직무능력표준 개발전문가 모집_작성양식_PDF.pdf");
        driver.findElement(By.id("upload-button-1-0-1-0")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());


        driver.findElement(By.id("file-input-1-1-0-0")).clear();
        driver.findElement(By.id("file-input-1-1-0-0")).sendKeys("/home/hanmomhanda/YS-DOC/83호_공학_트렌드_GIT_Flow를_활용한_효과적인_소스_형상_관리_Part_2.pdf");
        driver.findElement(By.id("upload-button-1-1-0-0")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        driver.findElement(By.id("file-input-1-1-0-1")).clear();
        driver.findElement(By.id("file-input-1-1-0-1")).sendKeys("/home/hanmomhanda/YS-DOC/79호_공학_트렌드_웹성능테스트-part_2.pdf");
        driver.findElement(By.id("upload-button-1-1-0-1")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        js.executeScript("scrollByLines(30)");

        driver.findElement(By.id("file-input-1-1-1-0")).clear();
        driver.findElement(By.id("file-input-1-1-1-0")).sendKeys("/home/hanmomhanda/YS-DOC/2014-국가직무능력표준 개발전문가 모집_작성양식_PDF.pdf");
        driver.findElement(By.id("upload-button-1-1-1-0")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());


        driver.findElement(By.id("file-input-2-0")).clear();
        driver.findElement(By.id("file-input-2-0")).sendKeys("/home/hanmomhanda/YS-DOC/83호_공학_트렌드_GIT_Flow를_활용한_효과적인_소스_형상_관리_Part_2.pdf");
        driver.findElement(By.id("upload-button-2-0")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        driver.findElement(By.id("file-input-2-1")).clear();
        driver.findElement(By.id("file-input-2-1")).sendKeys("/home/hanmomhanda/YS-DOC/79호_공학_트렌드_웹성능테스트-part_2.pdf");
        driver.findElement(By.id("upload-button-2-1")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        js.executeScript("scrollByLines(30)");

        driver.findElement(By.id("file-input-3-0-0")).clear();
        driver.findElement(By.id("file-input-3-0-0")).sendKeys("/home/hanmomhanda/YS-DOC/2014-국가직무능력표준 개발전문가 모집_작성양식_PDF.pdf");
        driver.findElement(By.id("upload-button-3-0-0")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        driver.findElement(By.id("file-input-3-0-1")).clear();
        driver.findElement(By.id("file-input-3-0-1")).sendKeys("/home/hanmomhanda/YS-DOC/제62차 SW 테스트 전문가 양성 교육(일반).pdf");
        driver.findElement(By.id("upload-button-3-0-1")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        driver.findElement(By.id("file-input-3-0-3")).clear();
        driver.findElement(By.id("file-input-3-0-3")).sendKeys("/home/hanmomhanda/YS-DOC/114호_공학_트렌드_WEB_UI_개발_Part_1.pdf");
        driver.findElement(By.id("upload-button-3-0-3")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        js.executeScript("scrollByLines(30)");

        driver.findElement(By.id("documentContainerList4.subContainer0.checkedFg")).click();
        driver.findElement(By.id("documentContainerList4.subContainer0.docItemName")).clear();
        driver.findElement(By.id("documentContainerList4.subContainer0.docItemName")).sendKeys("기타 서류 1");
        driver.findElement(By.id("file-input-4-0")).clear();
        driver.findElement(By.id("file-input-4-0")).sendKeys("/home/hanmomhanda/YS-DOC/2014-국가직무능력표준 개발전문가 모집_작성양식_PDF.pdf");
        driver.findElement(By.id("upload-button-4-0")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        driver.findElement(By.id("documentContainerList4.subContainer1.checkedFg")).click();
        driver.findElement(By.id("documentContainerList4.subContainer1.docItemName")).clear();
        driver.findElement(By.id("documentContainerList4.subContainer1.docItemName")).sendKeys("기타 서류 2");
        driver.findElement(By.id("file-input-4-1")).clear();
        driver.findElement(By.id("file-input-4-1")).sendKeys("/home/hanmomhanda/YS-DOC/114호_공학_트렌드_WEB_UI_개발_Part_1.pdf");
        driver.findElement(By.id("upload-button-4-1")).click();
        assertEquals("File is uploaded successfully.", closeAlertAndGetItsText());

        js.executeScript("scrollByLines(30)");
        driver.findElement(By.id("saveDocument")).click();
        assertEquals("File submission information is saved successfully.", closeAlertAndGetItsText());
    }

//    @Test
//    public void test1UpdateFornBasis() throws Exception {
//        js.executeScript("scroll(0, 300)");
//        driver.findElement(By.id("toMyList")).click();
//
//        driver.findElement(By.id("username")).clear();
//        driver.findElement(By.id("username")).sendKeys("Abc333");
//        driver.findElement(By.id("password")).clear();
//        driver.findElement(By.id("password")).sendKeys("Abc33333");
//        driver.findElement(By.xpath("//div[@id='login-form-container']/div[7]/div/button")).click();
//
//        driver.findElement(By.xpath("(//button[@id='modify0'])")).click();
//        js.executeScript("scroll(0, 1000)");
//        driver.findElement(By.id("applicationForeigner.homeTel")).clear();
//        driver.findElement(By.id("applicationForeigner.homeTel")).sendKeys("8765412365");
////        driver.findElement(By.id("saveBasis")).click();
//        js.executeScript("$('#saveBasis').click()");
//        assertEquals("기본 정보를 성공적으로 수정했습니다.", closeAlertAndGetItsText());
//        assertEquals("8765412365", driver.findElement(By.id("applicationForeigner.homeTel")).getAttribute("value"));
//    }
//
//    @Test
//    public void test1UpdateFornAcademy() throws Exception {
//        driver.get(baseUrl + "/yonsei/application/mylist");
//        driver.findElement(By.id("modify0")).click();
//        driver.findElement(By.linkText("2. 학력 정보")).click();
//        WebElement selectElement = driver.findElement(By.id("collegeList0.grdaTypeCode"));
//        selectElement.click();
//        Select select = new Select(selectElement);
//        select.selectByVisibleText("졸업예정");
////        List<WebElement> options = select.getOptions();
////        for (WebElement option : options) {
////            if ("00002".equals(option.getAttribute("value"))) {
////                System.err.println("======================");
////                System.err.println(option.getText());
////                System.err.println(option.getAttribute("value"));
////                System.err.println(option.isSelected());
////                System.err.println(option.isEnabled());
////            }
////        }
//
//        js.executeScript("$('#saveAcademy').click()");
//        assertEquals("학력 정보를 성공적으로 저장했습니다.", closeAlertAndGetItsText());
//        assertEquals("00002", driver.findElement(By.id("collegeList0.grdaTypeCode")).getAttribute("value"));
//    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
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

    private void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(pageLoadCondition);
    }
}
