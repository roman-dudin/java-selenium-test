package uitests;

import framework.Browser;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

abstract public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setup() {
        driver = Browser.getCurrentInstance().driver;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
