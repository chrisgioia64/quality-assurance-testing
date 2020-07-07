package pageObjects;

import commons.Commons;
import commons.ReadPropertyFile;
import commons.Utilities;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

  @FindBy(xpath="//body//div[@id='bodyContent']//div//div[1]//a[1]")
  private WebElement loginLink;

  @FindBy(xpath="//body//div[@id='bodyContent']//div//div[1]//a[2]")
  private WebElement createAccountLink;

  @FindBy(xpath="//body//div[@id='bodyContent']//h1")
  private WebElement pageTitle;

  @FindBy(xpath="//a[@id='tdb4']")
  private WebElement logoffButton;

  @FindBy(xpath="//select[@name='manufacturers_id']")
  private WebElement manufacturingDropdown;

  private WebDriver driver;

  private Logger logger = Logger.getLogger(HomePage.class);


  public HomePage(WebDriver driver) {
    PageFactory.initElements(driver,this);
    this.driver = driver;
  }

  public void openHomePage() {
    String URL = ReadPropertyFile.getConfigPropertyVal("homePageURL");
    driver.get(URL);
  }

  public String getHomePageTitle() {
    // Option 1
//    Utilities.check(Commons.waitForElement(driver, pageTitle, 3), driver,
//        "Couldn't find the page title");
//    logger.info("Home Page Title text is: " + pageTitle.getText());
//    return pageTitle.getText();

    // Option 2
    String s = Commons.getElementText(driver, this.pageTitle, 3);
    logger.info("Home Page Title: " + s);
    return s;
  }

  public WebElement getLoginLink() {
    return loginLink;
  }

  public WebElement getCreateAccountLink() {
    return createAccountLink;
  }

  public WebElement getPageTitle() {
    return pageTitle;
  }

  public WebElement getManufacturingDropdown() {
    return manufacturingDropdown;
  }

  public void logoff() {
    logoffButton.click();
    logger.info("Logging off");
  }
}
