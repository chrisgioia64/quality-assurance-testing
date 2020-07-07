package commons;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {

  private WebDriver driver;
  private String osName = System.getProperty("os.name");
  private Logger logger = Logger.getLogger(WebDriverManager.class);

  public WebDriver getDriver() {
    String browserName = Commons.createEnvVariable("browser");
    if (browserName == null) {
      browserName = ReadPropertyFile.getConfigPropertyVal("browser");
    }
    return getDriver(browserName);
  }

  public WebDriver getDriver(String browser) {
    if (driver == null) {
      try {
        driver = createDriver(browser);
        logger.info("Driver initialization was successful");
      } catch (Exception ex) {
        ex.printStackTrace();
        logger.info("Couldn't initialize the driver");
      }
    } else {
      logger.info("Driver is already initialized");
    }
    return driver;
  }

  private WebDriver createDriver(String browser) {
    if (osName.toLowerCase().contains("windows")) {
      logger.info("Windows OS is detected");

      if (browser.equalsIgnoreCase("chrome")) {
        logger.info("Chrome browser detected");
        System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver.exe");
        driver = new ChromeDriver();
      }
      else if (browser.equalsIgnoreCase("firefox")) {
        logger.info("Firefox browser detected");
        System.setProperty("webdriver.gecko.driver", "src//test//resources//drivers//geckodriver.exe");
        driver = new FirefoxDriver();
      }
      else if (browser.equalsIgnoreCase("ie")) {
        logger.info("IE browser detected");
        System.setProperty("webdriver.ie.driver", "src//test//resources//drivers//ie.exe");
        driver = new InternetExplorerDriver();
      } else {
        logger.info("Default browser detected -- Chrome");
        System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver.exe");
        driver = new ChromeDriver();
      }
    }
    else if (osName.toLowerCase().contains("max")) {
      logger.info("Mac OS is detected");

      if (browser.equalsIgnoreCase("chrome")) {
        logger.info("Chrome browser detected");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
      }
      else if (browser.equalsIgnoreCase("firefox")) {
        logger.info("Firefox browser detected");
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
        driver = new FirefoxDriver();
      }
      else if (browser.equalsIgnoreCase("safari")) {
        logger.info("Safari browser detected");
        System.setProperty("webdriver.safari.driver", "src/test/resources/drivers/safari");
        driver = new SafariDriver();
      } else {
        logger.info("Default browser detected");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
      }
    } else if (osName.toLowerCase().contains("linux")) {
      // TODO
    }

    driver.manage().timeouts().implicitlyWait(2, TimeUnit.MICROSECONDS);
    return driver;
  }

  public static void main(String[] args) {
    WebDriverManager manager = new WebDriverManager();
    System.out.println(manager.osName);
    System.out.println(System.getProperty("os.name"));
    System.setProperty("os.name", "stuff");
    System.out.println(System.getProperty("os.name"));
  }

}
