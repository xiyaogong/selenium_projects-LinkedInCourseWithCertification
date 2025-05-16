import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Main {
    static protected WebDriver driver;


    public static void main(String[] args) {
//        WebDriver driver = new ChromeDriver();
//        driver.getTitle();
//        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
//
//        WebElement message = driver.findElement(By.cssSelector("input[id=my-text-id]"));
//
//        System.out.print(message.getText());
//        System.out.print("hello world");
//        driver.quit();


        driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.getTitle();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        message.getText();

        driver.quit();
    }
}
