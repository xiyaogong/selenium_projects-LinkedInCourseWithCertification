import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.time.Duration;

public class MyTest {
    static protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(MyTest.class);


    @BeforeClass
    public void setUp() {
        var options= new ChromeOptions();
        options.addArguments("--no-sandbox");
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


    // ID <button id="login_btn">
    // Name <button name="login_btn">
    // class Name <button class="btn"> classes added for styling with CSS, change frequently
    // Tag Name <button> by html tag, no attribute, lots of them
    // Link Text <a href="/">Click me</a> a for anchor, use text than link. link changed often
    // CSS Selector <input type="password">. not only for input, input[type='password'] based on (CSS?) properties
    // Xpath
    // ############ html tags
    // input:  text, email, password, tel, number, search, url, file, hidden
    //         button(simple only show value), reset, submit, radio, checkbox, range, image, color, month
    //         date, time, week
    // button  rich in styles
    // a
    // div
    // ul
    // table
    // span
    // ####### choose right locator
    // 1.use id whenever possible
    // 2.use name when no id
    // 3.use class when no id or name
    // 4.use CSS selector and/or Xpath for advanced locators.(has attribute at least, use the tagName+ attribute to search)
    // 5.use tag name to locate multiple elements, or when no unique identifiers(attribute) are available.
    // 6.use link text for anchor elements with no other attributes.
    // 5 and 6 is the similar, no attribute
    @Test(enabled = true)
    public void testId() {
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");
        WebElement emailTextbox=driver.findElement(By.id("email"));
        logger.info("emailTextbox tagName: {}, text: {}", emailTextbox.getTagName(), emailTextbox.getText());
    }

}
