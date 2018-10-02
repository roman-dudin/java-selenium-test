package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {

    public WebDriver driver;
    public WebDriverWait wait;

    private static Browser instance;

    private Browser() {
        System.setProperty("webdriver.chrome.driver", "executable/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    public static Browser getCurrentInstance() {
        if (instance == null) {
            instance = new Browser();
        }

        return instance;
    }

    // Can be implemented to use different "How" to locate element
    public void waitAtLeastOneElementByCssSelector(By locator) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
    }
}
