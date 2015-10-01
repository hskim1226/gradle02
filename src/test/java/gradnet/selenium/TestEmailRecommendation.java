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
public class TestEmailRecommendation {
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

    @Test
    public void test1_추천서요청메일발송() throws Exception {
        driver.get(baseUrl + "/yonsei/application/mylist");
        driver.findElement(By.id("recommendation0")).click();
        driver.findElement(By.id("newRequest")).click();
        driver.findElement(By.id("profName")).clear();
        driver.findElement(By.id("profName")).sendKeys("Albert Einstein");
        driver.findElement(By.id("profMailAddr")).clear();
        driver.findElement(By.id("profMailAddr")).sendKeys("onetouch@apexsoft.co.kr");
        driver.findElement(By.id("profInst")).clear();
        driver.findElement(By.id("profInst")).sendKeys("Princeton University");
        driver.findElement(By.id("profMaj")).clear();
        driver.findElement(By.id("profMaj")).sendKeys("Physics");
        driver.findElement(By.id("profPhone")).clear();
        driver.findElement(By.id("profPhone")).sendKeys("213-987-569-547");
        driver.findElement(By.id("save")).click();
        assertEquals("Recommendation request is saved.", closeAlertAndGetItsText());
        driver.findElement(By.id("send")).click();
        assertTrue(closeAlertAndGetItsText().startsWith("All provided informations are correct"));
        assertEquals("Recommendation request e-mail is sent successfully.", closeAlertAndGetItsText());
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
