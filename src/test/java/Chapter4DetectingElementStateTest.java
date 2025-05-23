import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Chapter4DetectingElementStateTest {
    static protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Chapter4DetectingElementStateTest.class);



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

    @Test(enabled = false)
    public void testIsDisplayedAndIsEnabled() {
        driver.getTitle();
        driver.get(Statics.webForm);

        boolean isDisplayed  = driver.findElement(By.name("my-hidden")).isDisplayed();

        logger.info("my-hidden isDisplayed: {} ", isDisplayed);

        boolean isEnabled  = driver.findElement(By.name("my-disabled")).isEnabled();

        logger.info("my-disabled isEnabled: {} ", isEnabled);


    }

    @Test(enabled = true)
    public void testChapter4Challenge() {
        driver.getTitle();
        driver.get(Statics.pageWithHiddenElement);

        WebElement box = driver.findElement(By.id("box"));

       if(box.isDisplayed()){
           box.click();
       }else{
           logger.info("box is not visible ");
       }




    }





}
