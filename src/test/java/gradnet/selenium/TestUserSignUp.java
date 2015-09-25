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
    private static String userId = "Abc777";
    private static String password = "Abc77777";

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
//        System.setProperty("webdriver.chrome.driver", "/home/hanmomhanda/chromedriver");
//        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 800));
//        baseUrl = "http://www.gradnet.co.kr";
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 15);
        js = (JavascriptExecutor) driver;
        driver.get(baseUrl + "/yonsei");
    }

    @Test
    public void test1_회원가입() throws Exception {
        // Given : 회원가입 페이지 이동
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
