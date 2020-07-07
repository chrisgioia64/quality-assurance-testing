package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ForgotPasswordPage {

  @FindBy(xpath="//input[@name='email_address']")
  private WebElement emailAddressElement;

  @FindBy(xpath="//button[@id='tdb4']")
  private WebElement continueButton;

  @FindBy(xpath="//td[@class='messageStackError']")
  private WebElement errorMessage;

  private WebDriver driver;

  public ForgotPasswordPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  public WebElement getEmailAddressElement() {
    return emailAddressElement;
  }

  public WebElement getContinueButton() {
    return continueButton;
  }

  public WebElement getErrorMessage() {
    return errorMessage;
  }
}
