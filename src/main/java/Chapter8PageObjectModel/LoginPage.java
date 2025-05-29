package Chapter8PageObjectModel;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    // elements

    // And instead of using web elements as the type we'll use By.
    // When you define objects as web element,they are located and initialized immediately
    // when the page object class is instantiated.
    // If the page hasn't fully loaded or if the elements aren't immediately present in the dom,
    // this can lead to an exception.

    // By defining the elements as By objects,the actual search for the element happens
    // when the method interacts with it, allowing the page to fully load
    // and ensuring that the element is present and ready.

    //By 是定位器（Locator），WebElement 是已经找到的元素实例
    //延迟查找（Lazy loading）
    //页面元素在页面加载或操作过程中可能会动态变化，如果一开始就定义成 WebElement 并立即查找，元素可能不存在或状态不对，导致异常。用 By 定义，可以等到真正调用操作方法时，再去查找元素，这样更灵活稳定。
    //
    //避免页面加载问题
    //比如页面刷新、元素重新渲染等，之前的 WebElement 可能已经失效（StaleElementReferenceException）。用 By 定位，每次操作时重新定位元素，避免了这个问题。
    //
    //代码可读性和维护性更好
    //把定位策略集中管理，方便统一修改定位方式，而不是代码里到处是具体元素实例。
    private final By usernameField = By.id("username-field");
    private final By passwordField = By.id("password-field");
    private final By loginButton = By.id("login-form-submit");



    // methods
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public String clickLoginButton() {
        driver.findElement(loginButton).click();
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        alert.accept();
        return message;
    }

    public String login(String username, String password) {
        setUsername(username);
        setPassword(password);
        return clickLoginButton();
        //Similarly, if a method within your page object class causes navigation to a different page
        //within the application,your method should return an instance to the new page object class
        // so that the test can continue interacting with the elements on that page.
        // In the next lesson, we'll write tests that utilize this page object class.
    }
}
