package gradnet.selenium;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by hanmomhanda on 15. 3. 31.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserSignUp {

    private static WebDriver driver;
    private static String baseUrl;
    private boolean acceptNextAlert = true;
    private static StringBuffer verificationErrors = new StringBuffer();
    private static WebDriverWait wait;
    private static JavascriptExecutor js;
    private static String userId = "Real333";
    private static String password = "Real33333";

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
//        System.setProperty("webdriver.chrome.driver", "/home/hanmomhanda/chromedriver");
//        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600, 1000));
        baseUrl = "http://www.gradnet.co.kr";
//        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 15);
        js = (JavascriptExecutor) driver;
//        driver.get(baseUrl + "/yonsei/user/login");
//
//        driver.findElement(By.id("username")).clear();
//        driver.findElement(By.id("username")).sendKeys(userId);
//        driver.findElement(By.id("password")).clear();
//        driver.findElement(By.id("password")).sendKeys(password);
//        driver.findElement(By.id("btnLogin")).click();
    }

    @Test
    public void test1_회원가입() throws Exception {
        // Given : 회원가입 페이지 이동
        driver.get(baseUrl + "/yonsei");
        js.executeScript("scroll(0, 300)");
        driver.findElement(By.id("toSignUp")).click();
//        js.executeScript("$('#toSignUp').click()");
//        driver.get(baseUrl + "/yonsei/user/agreement");
//        driver.findElement(By.id("terms-agree")).click();
        js.executeScript("$('#terms-agree').click()");
//        driver.findElement(By.id("privacy-agree")).click();
        js.executeScript("scroll(0, 1000)");
        js.executeScript("$('#privacy-agree').click()");
//        driver.findElement(By.xpath("//form[@id='sign-up-form']/div/div/div[4]/div/label")).click();

        driver.findElement(By.id("sign-up-button")).click();
//        js.executeScript("$('#sign-up-button').click()");
//        driver.findElement(By.id("userId")).clear();

        // When : 회원가입 정보 입력 및 가입 실행
        driver.findElement(By.id("userId")).sendKeys(userId);
        driver.findElement(By.id("available-check-button")).click();
        wait.withTimeout(500, TimeUnit.MILLISECONDS);
        assertEquals("사용 가능한 ID 입니다.", closeAlertAndGetItsText());
        driver.findElement(By.id("pswd1")).clear();
        driver.findElement(By.id("pswd1")).sendKeys(password);
        driver.findElement(By.id("pswd2")).clear();
        driver.findElement(By.id("pswd2")).sendKeys(password);
        driver.findElement(By.id("mailAddr")).clear();
        driver.findElement(By.id("mailAddr")).sendKeys("hanmomhanda@gmail.com");
        driver.findElement(By.id("mobiNum")).clear();
        driver.findElement(By.id("mobiNum")).sendKeys("01082982369");
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("오명운");
        js.executeScript("scroll(0, 400)");
        js.executeScript("document.getElementById('bornDay').value = '19900909'");
        driver.findElement(By.id("sign-up-button")).click();

        // Then : 회원가입 성공 메시지 확인
        assertEquals("환영합니다", driver.findElement(By.id("welcome")).getText());
    }

    @Test
    public void test2_ID중복체크() throws Exception {
        // Given : 회원 가입 페이지로 이동
        driver.get(baseUrl + "/yonsei");
        js.executeScript("scroll(0, 300)");
        driver.findElement(By.id("toSignUp")).click();
        js.executeScript("$('#terms-agree').click()");
        js.executeScript("scroll(0, 1000)");
        js.executeScript("$('#privacy-agree').click()");
        driver.findElement(By.id("sign-up-button")).click();

        // When : 방금 전에 가입한 ID로 중복검사
        driver.findElement(By.id("userId")).clear();
        driver.findElement(By.id("userId")).sendKeys(userId);
        driver.findElement(By.id("available-check-button")).click();

        // Then : ID 중복 안내 확인
        assertEquals("이미 사용 중인 ID 입니다.", closeAlertAndGetItsText());
    }

    @Test
    public void test3_회원정보수정() throws Exception {
        // Given : 회원정보 수정 화면 진입
        driver.get(baseUrl + "/yonsei/user/login");

        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(userId);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();

        driver.get(baseUrl + "/yonsei/application/mylist");
        driver.findElement(By.cssSelector("i.fa.fa-info-circle")).click();
        driver.findElement(By.id("pswd")).clear();
        driver.findElement(By.id("pswd")).sendKeys(password);
        driver.findElement(By.xpath("//form[@id='user']/div/div/div[4]/div/div/button")).click();

        // When : 회원정보 수정
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("팔팔이");
        driver.findElement(By.id("mobiNum")).clear();
        driver.findElement(By.id("mobiNum")).sendKeys("01077778888");
        driver.findElement(By.id("modify")).click();

        // Then : 정보 수정 성공 확인
        assertEquals("사용자 정보가 수정되었습니다.", closeAlertAndGetItsText());
    }

    @Test
    public void test4_비밀번호수정() throws Exception {
        // Given : 비밀번호 수정 화면 진입
        driver.get(baseUrl + "/yonsei/application/mylist");
        driver.findElement(By.cssSelector("i.fa.fa-info-circle")).click();
        driver.findElement(By.id("pswd")).clear();
        driver.findElement(By.id("pswd")).sendKeys(password);
        driver.findElement(By.xpath("//form[@id='user']/div/div/div[4]/div/div/button")).click();
        driver.findElement(By.id("change-pwd")).click();
        driver.findElement(By.id("pswd")).clear();
        driver.findElement(By.id("pswd")).sendKeys(password);
        driver.findElement(By.xpath("//form[@id='user']/div/div/div[4]/div/div/button")).click();

        // When : 비밀번호 수정
        driver.findElement(By.id("pswd")).clear();
        driver.findElement(By.id("pswd")).sendKeys(password);
        driver.findElement(By.id("pswd2")).clear();
        driver.findElement(By.id("pswd2")).sendKeys(password);
        driver.findElement(By.xpath("//form[@id='user']/div/div/div[5]/div/button")).click();

        // Then : 비밀번호 수정 성공 확인
        assertEquals("비밀번호 재설정에 성공했습니다. 로그인 화면으로 이동합니다.", closeAlertAndGetItsText());
    }

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
