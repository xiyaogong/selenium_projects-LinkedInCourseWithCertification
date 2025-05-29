package Chapter8PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * dynamic modified page
 */
public class ChallengePage {

    // elements


    private final By byButtonDelete = By.id("buttonDelete");
    private final By byElementToRemove = By.id("element-to-remove");


    // methods
    private final WebDriver driver;

    public ChallengePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonDelete() {
        driver.findElement(byButtonDelete).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofMillis(501));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byElementToRemove));
    }

    public boolean isElementToRemovePresented() {
        try {
            driver.findElement(byElementToRemove);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
