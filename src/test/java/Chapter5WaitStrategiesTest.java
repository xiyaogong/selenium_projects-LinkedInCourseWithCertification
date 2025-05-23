import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Chapter5WaitStrategiesTest {
    static protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Chapter5WaitStrategiesTest.class);


    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();

        driver.get(Statics.dynamic);
    }

    @AfterClass
    public void testDown() {
        driver.quit();
    }

    @Test(enabled = false)
    public void testImplicitWait() {


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // not increase the time, yes it is just a timeout

        WebElement reveal = driver.findElement(By.id("reveal"));
        reveal.click();
        WebElement revealed = driver.findElement(By.id("revealed"));
        revealed.sendKeys("I see you");


    }

    /**
     * not mix implicit and explicit.
     * implicit is for global,
     * and explicit applied to certain components based on their specific conditions.
     * 默认 500ms
     * ignore NoSuchElementException
     */
    @Test(enabled = true)
    public void testRevealedWithExplicitWait() {

        WebElement reveal = driver.findElement(By.id("reveal"));
        reveal.click();

        WebElement revealed = driver.findElement(By.id("revealed"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(revealed));
        revealed.sendKeys("I see you");

    }

    @Test(enabled = true)
    public void testRevealedWithExplicitWait2() {
        WebElement reveal = driver.findElement(By.id("reveal"));
        reveal.click();

        WebElement revealed = driver.findElement(By.id("revealed"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        wait.until(d -> revealed.isDisplayed());  // d is an instance of driver, pass in a function
        revealed.sendKeys("I see you");

    }



    @Test(enabled = true)
    public void testRevealedWithExplicitWaitCustomization() {

        driver.findElement(By.id("reveal")).click();
        WebElement revealed = driver.findElement(By.id("revealed"));

        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(2))
                        .pollingEvery(Duration.ofMillis(300))
                        .ignoring(ElementNotInteractableException.class);

        wait.until(
                d -> {
                    revealed.sendKeys("Displayed");
                    return true;
                });
    }





    /**
     * applied to specific elements but with custom configurations
     * in addition to total mounts of wait time, fluent wait allow you to specify:
     * a. polling interval, how often to check for condition, default 250ms
     * b. ignore specific exception（custom list of exception）
     * FluentWait = 显式等待 + 自定义轮询频率 + 忽略某些异常 + 超时控制。
     * 异常多、页面不稳定时推荐
     */
    @Test(enabled = true)
    public void testAddedWithExplicitWaitCustomization() {
       driver.findElement(By.id("adder")).click();

        Wait<WebDriver>  wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        WebElement added = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box0")));
        //presenceOfElementLocated 元素在 DOM 中就行，不管是不是可见
        //visibilityOfElementLocated 元素必须是可见的
        //enabled
    }

    @Test(enabled = true)
    public void testAddedWithExplicitWaitCustomization2() {
        driver.findElement(By.id("adder")).click();

        Wait<WebDriver>  wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        WebElement added = wait.until(d-> d.findElement(By.id("box0")));


    }

    @Test(enabled = true)
    public void testAddedWithSleep() {
        WebElement adder = driver.findElement(By.id("adder"));
        adder.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement added = driver.findElement(By.id("box0"));

    }

    @Test(enabled = true,expectedExceptions = NoSuchElementException.class)
    public void testAddedWithExplicitWait() {
        WebElement adder = driver.findElement(By.id("adder"));
        adder.click();


        WebElement added = driver.findElement(By.id("box0"));  // 在网页上added 根本没有，脚本后生成的，所以到这一步就嘎了
        // 而revealed本来就有，所以可以抓到

//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(d -> added.isDisplayed());

    }

    @Test(enabled = true)
    public void testChallenge() {
        driver.get(Statics.dynamicallyModifiedPage);
        driver.findElement(By.id("buttonDelete")).click();
        var element2d = driver.findElement(By.id("element-to-remove"));

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//        wait.until(d -> !element2d.isDisplayed());  // not working
        wait.until(ExpectedConditions.invisibilityOf(element2d));




    }




}
