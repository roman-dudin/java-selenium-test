package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    private final String url = "https://passport.yandex.ru/auth?from=mail&origin=hostroot_homer_auth_ru&retpath=https%3A%2F%2Fmail.yandex.ru%2F&backpath=https%3A%2F%2Fmail.yandex.ru%3Fnoretpath%3D1";
    private final String loginButtonLocator = "button.passport-Button[type='submit']";

    @FindBy(css = "input[name='login']")
    private WebElement loginInput;

    @FindBy(css = "input[name='passwd']")
    private WebElement passInput;

    @FindBy(css = loginButtonLocator)
    private WebElement loginButton;

    public LoginPage() {
        browser.driver.get(url);
        // Wait page to be loaded
        browser.waitAtLeastOneElementByCssSelector(By.cssSelector(loginButtonLocator));
        PageFactory.initElements(browser.driver, this);
    }

    public void Login(String username, String password) {
        loginInput.sendKeys(username);
        passInput.sendKeys(password);
        loginButton.click();
    }
}
