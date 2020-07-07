package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {

  private WebDriver driver;
  private Logger logger;

  @FindBy(xpath="//div[@id='bodyWrapper']//div[2]//table[1]//tbody[1]//tr[1]//td[2]")
  private WebElement genderRadioGroup;

  @FindBy(xpath = "//input[@name='firstname']")
  private WebElement firstName;

  @FindBy(xpath= "//input[@name='lastname']")
  private WebElement lastName;

  @FindBy(xpath= "//input[@name='dob']")
  private WebElement dob;

  @FindBy(xpath= "//input[@name='email_address']")
  private WebElement email_address;

  @FindBy(xpath = "//input[@name='company']")
  private WebElement company;

  @FindBy(xpath = "//input[@name='street_address']")
  private WebElement streetAddress;

  @FindBy(xpath = "//input[@name='suburb']")
  private WebElement suburb;

  @FindBy(xpath = "//input[@name='postcode']")
  private WebElement postcode;

  @FindBy(xpath = "//input[@name='city']")
  private WebElement city;

  @FindBy(xpath = "//input[@name='state']")
  private WebElement state;

  @FindBy(xpath = "//select[@name='country']")
  private WebElement country;

  @FindBy(xpath = "//input[@name='telephone']")
  private WebElement telephone;

  @FindBy(xpath = "//input[@name='newsletter']")
  private WebElement newsletterCheckbox;

  @FindBy(xpath = "//input[@name='password']")
  private WebElement password;

  @FindBy(xpath = "//input[@name='confirmation']")
  private WebElement confirmation;

  @FindBy(xpath = "//button[@id='tdb4']")
  private WebElement continueButton;

  public CreateAccountPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public WebElement getGenderRadioGroup() {
    return genderRadioGroup;
  }

  public WebElement getFirstName() {
    return firstName;
  }

  public WebElement getLastName() {
    return lastName;
  }

  public WebElement getDob() {
    return dob;
  }

  public WebElement getEmail_address() {
    return email_address;
  }

  public WebElement getCompany() {
    return company;
  }

  public WebElement getStreetAddress() {
    return streetAddress;
  }

  public WebElement getSuburb() {
    return suburb;
  }

  public WebElement getPostcode() {
    return postcode;
  }

  public WebElement getCity() {
    return city;
  }

  public WebElement getState() {
    return state;
  }

  public WebElement getCountry() {
    return country;
  }

  public WebElement getTelephone() {
    return telephone;
  }

  public WebElement getNewsletterCheckbox() {
    return newsletterCheckbox;
  }

  public WebElement getPassword() {
    return password;
  }

  public WebElement getConfirmation() {
    return confirmation;
  }

  public WebElement getContinueButton() {
    return continueButton;
  }
}
