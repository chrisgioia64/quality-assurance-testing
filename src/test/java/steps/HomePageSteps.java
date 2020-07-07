package steps;

import commons.Commons;
import commons.WebDriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class HomePageSteps {
    HomePage homePage;
    LoginPage loginPage;
    WebDriver driver;
    WebDriverManager webDriverManager;

    private Logger logger;

    @Before
    public void setup() {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Given ("^I open the home page$")
    public void openHomePage() {
        homePage.openHomePage();
        Commons.check(homePage.getHomePageTitle().equals("Welcome to iBusiness"),
            driver, "incorrect home page title");
    }

    @Then("^verify the home page title$")
    public void verifyHomePageTitle() {
        boolean b = homePage.getPageTitle().getText().equals("Welcome to iBusiness");
        Commons.check(b,
            driver, "incorrect");
    }

    @And("^I click on the account button$")
    public void clickAccountButton() {
        Commons.clickButton(driver, homePage.getLoginLink(), 3);
    }

    @When("^I enter invalid credentials$")
    public void enterInvalidCredentials() {
        loginPage.loginWithCredentials("abc", "abc");
    }

    @Then("^verify the login error message appears$")
    public void verifyErrorMessage() {
        String text = loginPage.getMessageStackElement().getText();
        boolean b = text.contains(" Error: No match for E-Mail Address and/or Password.");
        Commons.check(b, driver, "Error message is incorrect: \"" + text + "\"");
    }

    @When("^I enter invalid username \"(.*)\" and password \"(.*)\"$")
    public void loginWithUsernamePassword(String username, String password) {
        loginPage.loginWithCredentials(username, password);
    }
}
