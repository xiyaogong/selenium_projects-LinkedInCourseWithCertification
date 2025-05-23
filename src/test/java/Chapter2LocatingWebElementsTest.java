import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Chapter2LocatingWebElementsTest {
    static protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Chapter2LocatingWebElementsTest.class);


    @BeforeClass
    public void setUp() {
        var options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // option is for the code run on github codeSpaces, not needed in normal case
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public void testDown() {
        driver.quit();
    }

    @Test(enabled = false)
    public void test1() {
        driver.getTitle();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement message = driver.findElement(By.cssSelector("input[id=my-text-id]"));

        System.out.print(message.getText());
        System.out.print("hello world");

    }

    @Test(enabled = false)
    public void test2() {

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.getTitle();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        message.getText();

    }



    @Test(enabled = false)
    public void testId() {
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");
        WebElement emailTextbox = driver.findElement(By.id("email"));
        logger.info("emailTextbox tagName: {}, text: {}", emailTextbox.getTagName(), emailTextbox.getText());
    }


    /**
     * input[type='radio'] cando multi selection
     * input[type='submit'][name='save']
     */
    @Test(enabled = false)
    public void testRadios() {
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");
        List<WebElement> radios = driver.findElements(By.cssSelector("input[type='radio']"));
        logger.info("radios size:{} , {}", radios.size(), radios);

        List<WebElement> radios2 = driver.findElements(By.xpath("//input[@type='radio']"));
        logger.info("radios size:{} , {}", radios.size(), radios2);
    }

    /**
     * based on pixels. not recommended. due to the phone, screen size.
     * above, below, toLeftOf, ToRightOf, near 50 pixels(configurable)
     * of a specified element
     * By submitLocator = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("cancel")); whole chain
     */
    @Test(enabled = false)
    public void testRelativeLocators() {
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");

        WebElement secondSubmit = driver.findElement(RelativeLocator.with(By.name("submit")).below(By.id("submit")));
        logger.info("second submit value: {}", secondSubmit.getAttribute("value"));
    }

    @Test(enabled = false)
    public void chapter2Challenge() {
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");
        WebElement imageButton = driver.findElement(By.id("imageButton"));
        logger.info("imageButton value: {}", imageButton.getAttribute("src"));


//        WebElement submitClick =driver.findElement(By.cssSelector("input[type='submit'][value='Click!']"));
        WebElement submitClick = driver.findElement(By.cssSelector("input[value='Click!']"));
        logger.info("submitClick value: {}", submitClick.getAttribute("value"));


//        WebElement firstNameTextBox =driver.findElement(By.cssSelector("input[type='text'][name='id-name1']"));
        WebElement firstNameTextBox = driver.findElement(By.name("id-name1"));
        logger.info("firstNameTextBox name: {}", firstNameTextBox.getAttribute("name"));

    }


}
