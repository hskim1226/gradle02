package gradnet.selenium;

import java.util.Date;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RetrieveOneApplicationTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private WebDriverWait wait;
  private JavascriptExecutor js;

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.gradnet.co.kr";
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 15);
  }

  @Test
  public void loginABC333() throws Exception {
    js = (JavascriptExecutor) driver;
    driver.get(baseUrl + "/yonsei");

    waitForLoad(driver);
//    driver.findElement(By.xpath("//*[@id='toMyList']")).click();
//    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toList")));
//
//    WebElement toMyList = driver.findElement(By.id("toList"));

//    js.executeScript("return document");
    js.executeScript("$('#toMyList').click()");
//    toMyList.click();
    waitForLoad(driver);
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("Abc333");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("Abc333");
    driver.findElement(By.xpath("//div[@id='login-form-container']/div[7]/div/button")).click();
    waitForLoad(driver);
//System.out.println("지원 내역".equals(driver.findElement(By.xpath("//*[@id=\"LGD_PAYINFO\"]/div/div/div/div[1]/span/b")).getAttribute("innerHTML")));

//    assertEquals("지원 내역", driver.findElement(By.xpath("//*[@id=\"LGD_PAYINFO\"]/div/div/div/div[1]/span/b")).getAttribute("innerHTML"));
//  }
//
//  @Test
//  public void updateHomeTel() throws Exception {

    driver.findElement(By.xpath("(//button[@id='modify'])[5]")).click();
    driver.findElement(By.id("applicationForeigner.homeTel")).clear();
    driver.findElement(By.id("applicationForeigner.homeTel")).sendKeys("8765412365");
//    driver.findElement(By.id("saveBasis")).click();
    js.executeScript("$('#saveBasis').click()");
    waitForLoad(driver);
    assertEquals("8765412365", driver.findElement(By.id("applicationForeigner.homeTel")).getAttribute("value"));
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
    ExpectedCondition<Boolean> pageLoadCondition = new
            ExpectedCondition<Boolean>() {
              public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
              }
            };
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(pageLoadCondition);
  }
}
