package gradnet.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
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
public class TestSignUp {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
//        baseUrl = "http://www.gradnet.co.kr";
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 15);
        js = (JavascriptExecutor) driver;
        driver.get(baseUrl + "/yonsei");
    }

    @Test
    public void test1SignUp() throws Exception {
        js.executeScript("scroll(0, 300)");
        driver.findElement(By.id("toSignUp")).click();

        driver.findElement(By.id("terms-agree")).click();
        js.executeScript("scroll(0, 800)");
        driver.findElement(By.id("privacy-agree")).click();
        driver.findElement(By.id("sign-up-button")).click();

        driver.findElement(By.id("userId")).clear();
        driver.findElement(By.id("userId")).sendKeys("Abcd1234");
        driver.findElement(By.id("available-check-button")).click();
        assertEquals("사용 가능한 ID 입니다.", closeAlertAndGetItsText());
        driver.findElement(By.id("pswd1")).clear();
        driver.findElement(By.id("pswd1")).sendKeys("Abcd1234");
        driver.findElement(By.id("pswd2")).clear();
        driver.findElement(By.id("pswd2")).sendKeys("Abcd1234");
        driver.findElement(By.id("mailAddr")).clear();
        driver.findElement(By.id("mailAddr")).sendKeys("hanmomhanda@gmail.com");
        driver.findElement(By.id("mobiNum")).click();
        driver.findElement(By.id("mobiNum")).clear();
        driver.findElement(By.id("mobiNum")).sendKeys("01087543214");
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("셀레늄");
        js.executeScript("scroll(0, 400)");
        js.executeScript("document.getElementById('bornDay').value = '19900909'");
        driver.findElement(By.id("sign-up-button")).click();

        assertEquals("환영합니다", driver.findElement(By.xpath("//*[@id=\"globalWrapper\"]/section/div/div/div/div/div[1]/span/b")).getAttribute("innerHTML"));
    }

    @Test
    public void test2IDCheck() throws Exception {
        js.executeScript("scroll(0, 300)");
        driver.findElement(By.id("toSignUp")).click();
        driver.findElement(By.id("terms-agree")).click();
        js.executeScript("scroll(0, 800)");
        driver.findElement(By.id("privacy-agree")).click();
        driver.findElement(By.id("sign-up-button")).click();
        driver.findElement(By.id("userId")).clear();
        driver.findElement(By.id("userId")).sendKeys("Abcd1234");
        driver.findElement(By.id("available-check-button")).click();
        assertEquals("이미 사용 중인 ID 입니다.", closeAlertAndGetItsText());
    }

    @After
    public void tearDown() throws Exception {
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
