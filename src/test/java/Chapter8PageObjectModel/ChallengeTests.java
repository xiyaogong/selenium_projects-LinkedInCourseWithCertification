package Chapter8PageObjectModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Dynamic modified page
 */
public class ChallengeTests extends BaseTest {
    private ChallengePage page;
    private static final Logger logger = LoggerFactory.getLogger(ChallengeTests.class);

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://www.selenium.dev/selenium/web/dynamicallyModifiedPage.html");
        page = new ChallengePage(driver);

    }

    @Test
    public void testNoClick() {
        Assert.assertTrue(page.isElementToRemovePresented());

    }

    @Test
    public void testClick() {
        page.clickButtonDelete();
        Assert.assertFalse(page.isElementToRemovePresented());

    }

}
