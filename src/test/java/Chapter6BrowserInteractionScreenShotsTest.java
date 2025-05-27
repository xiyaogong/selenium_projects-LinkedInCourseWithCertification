import org.openqa.selenium.*;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class Chapter6BrowserInteractionScreenShotsTest {
    protected static  WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Chapter6BrowserInteractionScreenShotsTest.class);
    private static  BrowsingContext browsingContext;

    @BeforeClass
    public void setUp() {
        var options = new ChromeOptions();
        options.enableBiDi(); // bi direction
        driver = new ChromeDriver(options);

        driver.get(Statics.webForm);
        browsingContext = new BrowsingContext(driver, driver.getWindowHandle()); // get current window or tab context where it browsed on
    }

    @AfterClass
    public void testDown() {
        driver.quit();
    }


    @Test
    public static void testScreenShotsFullPage() {


        // FULL PAGE
        String fullScreenshot = browsingContext.captureScreenshot(); // return Base64 encoded string of the binary image data
        //是一种把二进制数据（比如图片、文件、音频、视频、PDF等）转换成 只包含 ASCII 字符 的字符串格式。常用于网络传输、嵌入图像、数据存储等场景。
//        logger.info(fullScreenshot);
        saveScreenshot(fullScreenshot, "full_screenshot.png");


    }

    @Test
    public static void testScreenShotsElement() {

        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        String internalElementId = ((RemoteWebElement) colorPicker).getId(); // to get the internal id of the element, which != element ID attribute
        String elementScreenshot = browsingContext.captureElementScreenshot(internalElementId);
        saveScreenshot(elementScreenshot, "element_screenshot.png");

    }

    @Test
    public static void testScreenShotsViewPort() {


        driver.findElement(By.name("my-date")).click(); // after click the calender(datapicker shows)
        var datePickerRect = driver.findElement(By.className("datepicker")).getRect();
        String viewportScreenshot = browsingContext.captureBoxScreenshot(
                datePickerRect.getX(),
                datePickerRect.getY(),
                datePickerRect.getWidth(),
                datePickerRect.getHeight());
        saveScreenshot(viewportScreenshot, "viewport_screenshot.png");


    }

    private static void saveScreenshot(String screenshot, String filename) {
        var decodedScreenshot = Base64.getDecoder().decode(screenshot);// decode base 64 string to binary
        try {
            String path = "/Users/xiyaogong/my_projects/selenium_projects/my_first_try/screenshots";
            Files.write(Paths.get(path, filename), decodedScreenshot);
        } catch (IOException e) {
            logger.error("error", e);
        }
    }


}
