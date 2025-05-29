package Chapter8PageObjectModel;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


/**
 *  POM(Page Object Model:) is a design patten, no such class
 *  /src/main/  framework: neutrally interacts with and queries the web application
 *  /src/test/  tests: call framework in a series of steps to determin if outcome is expected, tests positive or negative
 */
public class BaseTest {
    protected WebDriver driver;


    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void testDown() {
        driver.quit();
    }






}
