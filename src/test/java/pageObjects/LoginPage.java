package pageObjects;

import commons.Commons;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

  @FindBy(xpath="//input[@name=\'email_address\']")
  private WebElement usernameField;

  @FindBy(xpath="//input[@name='password']")
  private WebElement passwordField;

  @FindBy(xpath="//button[@id='tdb5']")
  private WebElement signInButton;

  @FindBy(xpath="//td[@class='messageStackError']")
  private WebElement messageStackElement;

  @FindBy(xpath="//form[@name='login']//p[1]//a")
  private WebElement forgotPasswordLink;

  private Logger logger = Logger.getLogger(LoginPage.class);

  private WebDriver driver;

  public LoginPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  public void loginWithCredentials(String username, String password) {
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    logger.info("Logging in with username " + username);
    logger.info("Logging in with password " + password);
    Commons.clickButton(driver, signInButton, 3);
  }

  public WebElement getMessageStackElement() {
    logger.info("The message stack text: " + messageStackElement.getText());
    return messageStackElement;
  }

  public WebElement getForgotPasswordLink() {
    return forgotPasswordLink;
  }
}
