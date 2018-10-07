package framework;

import framework.helpers.PropertiesHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class Browser {

    public WebDriver driver;
    public WebDriverWait wait;
    public static Properties props;
    private OsName osName;

    private static Browser instance;

    private Browser() throws Exception {
        props = new PropertiesHelper().props;
        osName = getOs();

        setDriver();

        wait = new WebDriverWait(driver, 20);
    }

    public static Browser getCurrentInstance() {
        if (instance == null) {
            try {
                instance = new Browser();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    public static void quit() {
        instance.driver.quit();
        instance = null;
    }

    // Can be implemented to use different "How" to locate element
    public void waitAtLeastOneElementByCssSelector(By locator) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
    }

    private void setDriver() {
        switch (osName) {
            case WINDOWS:
                System.setProperty("webdriver.chrome.driver", "executable/win/chromedriver.exe");
                break;
            case LINUX:
                System.setProperty("webdriver.chrome.driver", "executable/linux/chromedriver");
                break;
            case MAC:
                System.setProperty("webdriver.chrome.driver", "executable/mac/chromedriver");
                break;
        }

        driver = new ChromeDriver();
    }

    private OsName getOs() throws Exception {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return OsName.WINDOWS;
        }
        if (os.contains("nix") || os.contains("nux")) {
            return OsName.LINUX;
        }
        if (os.contains("mac")) {
            return OsName.MAC;
        }

        throw new Exception("Not implemented for '" + os + "'!");
    }
}
