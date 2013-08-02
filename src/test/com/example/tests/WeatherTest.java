package com.example.tests;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class WeatherTest {
  
  private Properties prop;
  private String gridHost;
  private String gridPort;
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    prop = new Properties();
    
    
    try {
      prop.load(new FileInputStream(new java.io.File("").getAbsolutePath()
          + "/era.properties"));
      gridHost = prop.getProperty("grid.host");
      gridPort = prop.getProperty("grid.port");
      
    } catch (Exception e) {
      gridHost = "http://localhost";
      gridPort = "4444";
    }
    
    DesiredCapabilities capability = DesiredCapabilities.firefox();
    driver = new RemoteWebDriver(new URL(gridHost + ":" + gridPort + "//wd/hub"), capability);
    baseUrl = "http://weather.gc.ca/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testWeather() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Edmonton")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("cityjump"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Current Conditions[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Forecast[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Edmonton City Centre Airport[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
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
}
