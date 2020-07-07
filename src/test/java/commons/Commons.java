package commons;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.lang.Thread.currentThread;
import static org.junit.Assert.assertTrue;

/**
 * This is the class that I created in class with Ghadi on 6/20/2020
 */
public class Commons {

  private static Logger logger = Logger.getLogger(Commons.class);

  public static String createEnvVariable(String envVariableName) {
    String value = System.getProperty(envVariableName);
    logger.info("Environment value for " + envVariableName + " is " + value);
    return value;
  }

  // 1st option for implementing explicit wait
  public static boolean waitForElement(WebDriver driver, WebElement el, int seconds) {
    WebDriverWait wait = new WebDriverWait(driver, seconds);
    try {
      wait.until(ExpectedConditions.visibilityOf(el));
      // this is risky to add; not all elements have a text
      String s = el.getText().length() > 100 ? el.getText().substring(0,100) : el.getText();
      logger.info("Element  " + s + " is visible");
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("Element is NOT visible");
      return false;
    }
  }

  // 2nd option for implementing explicit wait
  public static boolean isElementVisible(WebDriver driver, WebElement el, int seconds) {
//    WebDriverWait wait = new WebDriverWait(driver, seconds);
    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(seconds, TimeUnit.SECONDS)
        .pollingEvery(10, TimeUnit.MILLISECONDS);

    Function<WebDriver, Boolean> function = arg -> {
      try {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MICROSECONDS);
        el.getSize(); // if element does not exist, this will throw an exception
        return true;
      } catch (NoSuchElementException e) {
        e.printStackTrace();
        return false;
      }
    };

    try {
      wait.until(function);
      // this is risky to add; not all elements have a text
      logger.info("Element  " + el.getText() + " is visible");
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("Element is NOT visible");
      return false;
    }
  }

  public static String getElementText(WebDriver driver, WebElement el, int seconds) {
    if (Commons.waitForElement(driver, el, seconds)) {
      return el.getText();
    } else {
      logger.info("Couldn't find the element text");
      return "";
    }
  }

  public static void clickButton1(WebDriver driver, WebElement el, int seconds) {
    if (waitForElement(driver, el, seconds)) {
      logger.info("Clicking on button " + el.getText());
      el.click();
    } else {
      logger.info("Couldn't click on the button ");
    }
  }


  public static void clickButton2(WebDriver driver, WebElement el, int seconds) {
    check(waitForElement(driver, el, seconds), driver, "Couldn't click the button");
    el.click();
    logger.info("Clicking the button");
  }

  public static void clickButton(WebDriver driver, WebElement el, int seconds) {
    if (waitForElement(driver, el, seconds)) {
      logger.info("Clicking on button " + el.getText());
      el.click();
    } else {
      logger.info("Couldn't click on the button ");
      check(false, driver, "Couldn't click the button");
    }
  }

  public static void sendKeys(WebDriver driver, WebElement el, String keys, int seconds) {
    if (waitForElement(driver, el, seconds)) {
      logger.info("The textbox " + el.getText() + " has the following value entered: " + keys);
      el.sendKeys(keys);
    } else {
      logger.info("Couldn't locate the textbox ");
      check(false, driver, "Couldn't locate the textbox");
    }
  }

  public static void selectRadioOption(WebDriver driver, WebElement el, String selectedValue, int seconds) {
    if (waitForElement(driver, el, seconds)) {
      for ( WebElement option : el.findElements(By.tagName("input")) ) {
        String value = option.getAttribute("value");
        if (value != null && value.equalsIgnoreCase(selectedValue)) {
          option.click();
          logger.info("Successfully clicked on the radio option with value=" + selectedValue);
          return;
        }
      }
      check(false, driver, "Couldn't locate the radio option with value=" + selectedValue);
    } else {
      logger.info("Couldn't locate the enclosing element for the radio buttons");
      check(false, driver, "Couldn't locate the enclosing element for the radio button");
    }
  }

  public static void selectDropdownOption(WebDriver driver, WebElement el,
                                          String selectedTextValue, int seconds) {
    if (waitForElement(driver, el, seconds)) {
      for ( WebElement option : el.findElements(By.tagName("option"))) {
        String value = option.getText();
        if (value != null && value.equalsIgnoreCase(selectedTextValue)) {
          option.click();
          logger.info("Successfully clicked on dropdown with text=" + selectedTextValue);
          return;
        }
      }
      check(false, driver,
          "Couldn't locate the dropdown with text=" + selectedTextValue);
    } else {
      logger.info("Could not locate the dropdown box");
      check(false, driver, "Couldn't locate the dropdown box");
    }
  }



  private static void check(boolean condition, WebDriver driver, String failMessage, boolean stopExecution) {
    if (condition) {
      logger.info("check condition is true");
      assertTrue(true);
    } else {
      logger.info(failMessage);
      String clazzName = currentThread().getStackTrace()[2].getClassName();
      String methodName = currentThread().getStackTrace()[2].getMethodName();
      screenshot(driver, clazzName, methodName);
      if (stopExecution) {
        assertTrue(false);
//        fail();
      }
    }
  }

  public static void check(boolean condition, WebDriver driver, String failMessage) {
    check(condition, driver, failMessage, true);
  }

  public static void softCheck(boolean condition, WebDriver driver, String failMessage) {
    check(condition, driver, failMessage, false);
  }


  private static void screenshot(WebDriver driver, String className, String methodName) {
    String timestamp = new SimpleDateFormat("yyyy-MM-dd.mm.ss._").format(new Date());
    String filename = timestamp + className + "_" + methodName + ".png";
    File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(ss, new File("./screenshots/" + filename));
      logger.info("The following screenshot was taken: " + filename);
    } catch (IOException e) {
      logger.info("Unable to take screenshot " + filename);
      e.printStackTrace();
    }
  }


}
