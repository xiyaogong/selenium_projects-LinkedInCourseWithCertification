import org.openqa.selenium.*;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;

public class Chapter6BrowserInteractionTest {
    static protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Chapter6BrowserInteractionTest.class);


    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();

    }

    @AfterClass
    public void testDown() {
        driver.quit();
    }

    @Test(enabled = false)
    public void testGetTitleAndUrl() {
        driver.get(Statics.webForm);
        logger.info("title is : {}, url is : {}", driver.getTitle(), driver.getCurrentUrl());
    }

    @Test(enabled = true)
    public void testBackAndForward() {
        driver.get("https://www.selenium.dev/selenium/web/clicks.html");
        logger.info("on page: " + driver.getTitle());

        driver.findElement(By.id("twoClientRects")).click();
        logger.info("clicked link. now on page: " + driver.getTitle());

        driver.navigate().back();
        logger.info("go back link. now on page: " + driver.getTitle());

        driver.navigate().forward();
        logger.info("go forward link. now on page: " + driver.getTitle());
    }

    /*
    tab and window in selenium handles in the same way.
     */
    @Test
    public void testTabs() {
        driver.get("https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html");

        driver.findElement(By.id("a-link-that-opens-a-new-window")).click();
        var windows = driver.getWindowHandles();
        logger.info("all windows or tabs" + windows);

        var currentWindow = driver.getWindowHandle();

        for (var window : windows) {
            if (!currentWindow.equals(window)) {
                driver.switchTo().window(window);
                break;
            }
        }
        logger.info("new tab title:" + driver.getTitle());
    }

    /*
    Feels like a alert but is part of  DOM
     */
    @Test
    public void testModals() {
        driver.get("https://www.selenium.dev/selenium/web/modal_dialogs/modern_modal.html");
        driver.findElement(By.id("trigger-modal-btn")).click();

        WebElement modal = driver.findElement(By.id("modalContent"));
        if (modal.isDisplayed()) {
            System.out.println("modal is displayed");
            driver.findElement(By.id("modal-input")).sendKeys("hey modal");
            driver.findElement(By.id("modal-close")).click();
        } else
            System.out.println("modal is not displayed");

    }


    @Test
    public static void testAlerts() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html");
        driver.findElement(By.id("alert")).click();

        Alert alert = driver.switchTo().alert();
        String message = alert.getText();

        System.out.println(message);

        alert.accept();
    }


    @Test
    public static void TestAlertsConfirmations() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html");
        driver.findElement(By.id("confirm")).click();

        Alert confirmation = driver.switchTo().alert();
        // confirmation.accept(); // OK
        confirmation.dismiss(); // Cancel
    }

    @Test
    public static void testAlertsPrompts() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html");
        driver.findElement(By.id("prompt")).click();

        Alert prompt = driver.switchTo().alert();
        prompt.sendKeys("something");
        prompt.accept();
    }

    @Test
    public static void testAlertsWaits() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html");
        driver.findElement(By.id("slow-alert")).click();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    /**
     * if name or id doesnt existed can use index, the main is 0 and iframe is 1
     * or locator strategy
     */
    @Test
    public static void testIframe() {
        driver.get("https://www.selenium.dev/selenium/web/click_tests/click_in_iframe.html");

        driver.switchTo().frame("ifr");
        driver.findElement(By.id("link")).click();
        // switch back to main frame
        driver.switchTo().defaultContent();
    }

    /**
     * developers tools
     * inspection -> application -> cookies
     */
    @Test
    public static void testCookies() {
        driver.get(Statics.webForm);

        Cookie cookie = new Cookie("theme", "light");
        driver.manage().addCookie(cookie);

        Cookie themeCookie = driver.manage().getCookieNamed("theme");
        System.out.println(themeCookie.getDomain());

        driver.manage().deleteCookie(themeCookie);
        System.out.println(driver.manage().getCookies().size());
    }

    @Test
    public static void chapterChallenge() {
        driver.get("https://www.selenium.dev/selenium/web/alerts.html");
        driver.findElement(By.id("open-page-with-onload-alert")).click();

        var tabs = driver.getWindowHandles();
        logger.info("all windows or tabs" + tabs);

        var currentTab = driver.getWindowHandle();

        for (var tab : tabs) {
            if (!currentTab.equals(tab)) {
                driver.switchTo().window(tab);
                break;
            }
        }


        Alert alert = driver.switchTo().alert();
        String message = alert.getText();

        logger.info(message);

        alert.accept();
    }








}
