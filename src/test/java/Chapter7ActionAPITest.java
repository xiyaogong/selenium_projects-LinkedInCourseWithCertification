import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Chapter7ActionAPITest {
    static protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Chapter7ActionAPITest.class);


    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();

    }

    @AfterClass
    public void testDown() {
        driver.quit();
    }

    @Test(enabled = true)
    public void testHover() {
        driver.get("https://www.selenium.dev/selenium/web/mouseOver.html");

        var redBox = driver.findElement(By.id("redbox"));
        System.out.println(redBox.getCssValue("background-color"));

        new Actions(driver) // requires precise control over mouse and keyboard
                .moveToElement(redBox) // like a builder pattern set values
                .perform();  // like a build of builder pattern, It executes the defined action.
        System.out.println(redBox.getCssValue("background-color"));

    }

    /**
     * Selenium 提供 Actions 类来模拟这类更复杂的操作（如双击、拖拽、右键点击、键盘组合键等）。
     */
    @Test(enabled = true)
    public void testDoubleClick() {
        driver.get("https://selenium.dev/selenium/web/mouse_interaction.html");

        var input = driver.findElement(By.id("clickable"));
        new Actions(driver).doubleClick(input).perform();

        System.out.println(driver.findElement(By.id("click-status")).getText());


    }

    /**
     * mouse right click, provides additional options
     */
    @Test(enabled = true)
    public void testContextClick() {
        driver.get("https://selenium.dev/selenium/web/mouse_interaction.html");

        var input = driver.findElement(By.id("clickable"));
        new Actions(driver).contextClick(input).perform();

        System.out.println(driver.findElement(By.id("click-status")).getText());


    }

    /**
     * for most of the interactions is not required, since from DOM can reach this line 9 without scroll.
     * However, for constant heavy web pages, use infinite scrolling to load more elements as the user scrolls the page.
     * <p>
     * scroll of element
     */
    @Test(enabled = true)
    public void testScrollToElement() {
        driver.get("https://www.selenium.dev/selenium/web/scroll.html");
        var line9 = driver.findElement(By.id("line9"));
        System.out.println(line9.isDisplayed()); //true
        new Actions(driver).scrollToElement(line9).perform();
        System.out.println(line9.isDisplayed()); //true

    }

    /**
     * Scroll by amount
     */
    @Test(enabled = true)
    public void testScrollByAmount() {
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/page_with_tall_frame.html");
        var height = driver.findElement(By.tagName("body")).getSize().getHeight(); // vertical axis of the body in pixels
        // getSize() only width and height, getRect() width and height + position x, y
        new Actions(driver).scrollByAmount(0, height).perform();

        System.out.println("height is " + height);
    }

    /**
     * rearrange items on the page
     */
    @Test(enabled = true)
    public void testDragAndDrop() {
        driver.get("https://www.selenium.dev/selenium/web/draggableLists.html");

        WebElement leftItem = driver.findElement(By.id("leftitem-1"));
        WebElement rightItem = driver.findElement(By.id("rightitem-1"));
        new Actions(driver).dragAndDrop(leftItem, rightItem).perform(); // leftItem to drag , rightItem to drop on

        var rightItems = driver.findElements(By.cssSelector("#sortable2 li")); // gives the right list of items，
        //在 id 为 sortable2 的元素里面，找到所有的 <li> 元素
        //标签	名称	用途
        //<ul>	unordered list	无序列表（前面是小圆点）
        //<li>	list item	列表项，必须放在 <ul> 或 <ol> 里面
        rightItems.forEach(e -> System.out.println(e.getText()));
    }

    /**
     * use keyboard
     * 总结对比
     * 特点	WebElement.click()	Actions.click()
     * 调用方式	元素对象直接调用	需要先 new Actions，再 perform()
     * 交互方式	模拟 DOM 点击事件	模拟鼠标动作（移动 + 点击）
     * 适用场景	普通点击	复杂交互或普通点击失败时
     * 代码复杂度	简单	较复杂
     * 可能遇到的问题	遮挡、不可见时报错	较少，但不排除
     * <p>
     * <p>
     * 方面	WebElement.sendKeys()	Actions.sendKeys()
     * 发送目标	具体某个元素	当前焦点或整个页面
     * 使用复杂度	简单	可以实现复杂键盘动作
     * 需要执行操作	立即执行	需要调用 .perform() 执行
     * 适用场景	输入框、文本区域输入	发送快捷键组合，全局按键操作
     */
    @Test(enabled = true)
    public void testCopyAndPaste() {
        driver.get(Statics.formPage);

        var field1 = driver.findElement(By.id("withText"));
        var field2 = driver.findElement(By.id("emptyTextArea"));

        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

        Actions actions = new Actions(driver);

        // Copy
        actions
                .click(field1) // 模拟鼠标行为，先移动鼠标到目标元素上，再执行点击（类似真实鼠标操作）, this is just a first step in the whole actions
                .keyDown(cmdCtrl) //模拟按住某个键不松开 holding keypress
                .sendKeys("a") //模拟按下并松开具体的字符键，这里是字母 a， simultaneously press ctrl/cmd + a
                .sendKeys("c") // simultaneously press ctrl/cmd + c
                .keyUp(cmdCtrl)
                .perform();

        // Paste
        actions
                .click(field2)
                .keyDown(cmdCtrl)
                .sendKeys("v")
                .keyUp(cmdCtrl)
                .perform();
    }

    @Test
    public void testChapterChallenge() {
        driver.get("https://www.selenium.dev/selenium/web/keyboard_shortcut.html");

        Keys cmdCtrl = Keys.CONTROL;
        Keys cmdShift = Keys.SHIFT;

        new Actions(driver)
                .keyDown(cmdCtrl)
                .keyDown(cmdShift)
                .sendKeys("1")
                .keyUp(cmdCtrl)// in general first come last go，otherwise may cause issues
                .keyUp(cmdShift)
                .perform();


        WebElement body = driver.findElement(By.tagName("body"));
        String bgColor = body.getCssValue("background-color");
        System.out.println("background color is: " + bgColor);
    }


}
