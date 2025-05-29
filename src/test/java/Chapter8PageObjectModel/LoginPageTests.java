package Chapter8PageObjectModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;


/**
 * POM(Page Object Model:) is a design patten, no such class
 * /src/main/  framework: neutrally interacts with and queries the web application
 * /src/test/  tests: call framework in a series of steps to determin if outcome is expected, tests positive or negative
 */
public class LoginPageTests extends BaseTest {
    private LoginPage loginPage;
    private static final Logger logger = LoggerFactory.getLogger(LoginPageTests.class);

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://www.selenium.dev/selenium/web/login.html");
        loginPage = new LoginPage(driver);

    }

    @Test
    public void testLoginSuccessful() {
        String message = loginPage.login("username", "password");
        Assert.assertEquals(message, "You have successfully logged in.");

    }

    @Test
    public void testLoginWithNoUsername() {
        loginPage.setPassword("password");
        String message = loginPage.clickLoginButton();
        Assert.assertEquals(message, "Please enter valid credentials");

    }


    @AfterMethod
    public void afterMethod() {

    }


}
