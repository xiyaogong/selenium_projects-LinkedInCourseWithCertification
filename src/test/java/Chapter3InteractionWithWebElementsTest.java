import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Chapter3InteractionWithWebElementsTest {
    static protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Chapter3InteractionWithWebElementsTest.class);


    @BeforeClass
    public void setUp() {
        var options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public void testDown() {
        driver.quit();
    }

    @Test(enabled = true)
    public void testReadWriteClearTextBox() {
        driver.getTitle();
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");

        WebElement textBox = driver.findElement(By.name("id-name1"));
        // textBox.getText() doesnt work
        logger.info("textBox text: {} domProperty value: {}", textBox.getText(), textBox.getDomProperty("value"));
        textBox.clear();
        textBox.sendKeys("Xiyao");


        logger.info(" textBox text: {} domProperty value: {}", textBox.getText(), textBox.getDomProperty("value"));

    }

    @Test(enabled = false)
    public void testXPathAndClickableTextBox() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

//        driver.findElement(
//                        By.cssSelector("button[type='submit']"))
//                .click();
        driver.findElement(
                        By.xpath("//button[text()='Submit']")) // according to text
                .click();


    }

    @Test(enabled = false)
    public void testXPathAndClickableLink() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.findElement(
                        By.xpath("//a[normalize-space(text())='Return to index']")) // according to trimmed text
                .click();


    }

    @Test(enabled = false)
    public void testCheckBox() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement checkBox = driver.findElement(By.id("my-check-1"));
        boolean isSelected = checkBox.isSelected();

        if (!isSelected) {
            checkBox.click();
        }

    }

    @Test(enabled = false)
    public void testDropDown() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        Select dropDown = new Select(driver.findElement(By.name("my-select"))); // select can be single options or multi,this one is a single
        dropDown.selectByValue("2");
        dropDown.selectByVisibleText("Three");
        dropDown.selectByIndex(0);

        WebElement selectedOption = dropDown.getFirstSelectedOption(); // 第一个被选中 maybe this is single select, so it show the last setting value of 0
        logger.info(selectedOption.getText());
        List<WebElement> selectedOptions = dropDown.getAllSelectedOptions(); //所有被选中
        logger.info("options size:{}", selectedOptions.size());

//        dropDown.selectByValue("2");
//        dropDown.selectByVisibleText("One");
//        dropDown.selectByIndex(1);

    }

    @Test(enabled = false)
    public void testFileUpload() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String filePath = "/Users/xiyaogong/my_projects/selenium_projects/my_first_try/src/test/resources/aFile.txt";
        WebElement file = driver.findElement(By.name("my-file"));
        file.sendKeys(filePath);

        logger.info("end");
    }

    @Test(enabled = true)
    public void chapter3Challenge() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");


        WebElement name = driver.findElement(By.id("my-text-id"));
        name.sendKeys("username");

        WebElement pass = driver.findElement(By.name("my-password"));
        name.sendKeys("password");

        WebElement textArea = driver.findElement(By.name("my-textarea"));
        name.sendKeys("I am studying Selenium webDriver");

        Select dropDown = new Select(driver.findElement(By.name("my-select"))); // select can be single options or multi,this one is a single
        dropDown.selectByValue("3");


//        WebElement checkBox = driver.findElement(By.cssSelector("input[type='checkbox']"));
//        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
//        for(WebElement checkbox: checkBoxes){
//            checkbox.click();
//        }

        WebElement checkBox1 = driver.findElement(By.id("my-check-1"));
        if (checkBox1.isSelected()) {
            checkBox1.click();
        }
        WebElement checkBox2 = driver.findElement(By.id("my-check-2"));
        if (!checkBox2.isSelected()) {
            checkBox2.click();
        }

        driver.findElement(By.id("my-radio-2")).click();


        driver.findElement(By.xpath("//button[text()='Submit']")) // according to text
                .click();

        logger.info("end");
    }




}
