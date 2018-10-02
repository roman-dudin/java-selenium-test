package pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailsPage extends BasePage {

    private final String searchInputLocator = "input.textinput__control";

    @FindBy(css = searchInputLocator)
    private WebElement searchInput;

    @FindBy(css = "div.ns-view-messages-item-wrap")
    private List<WebElement> mails;

    public MailsPage() {
        // Wait page to be loaded
        browser.waitAtLeastOneElementByCssSelector(By.cssSelector(searchInputLocator));
        PageFactory.initElements(browser.driver, this);
    }

    public void openMailBy(String sender, String subject) throws InterruptedException {
        // TODO: implement case with "more" button
        searchMailBySender(sender);

        WebElement mail = getMailBySubject(subject);
        browser.wait.until(ExpectedConditions.elementToBeClickable(mail));
        mail.click();
    }

    public void searchMailBySender(String sender) {
        searchInput.sendKeys(sender);
        searchInput.sendKeys(Keys.RETURN);
    }

    public WebElement getMailBySubject(String subject) throws InterruptedException {
        // TODO avoid sleep by using wait for some event like completion of search 
        Thread.sleep(3000);

        browser.waitAtLeastOneElementByCssSelector(By.cssSelector("div.ns-view-messages-item-wrap"));
        List<WebElement> res = mails
                .stream()
                .filter(m -> m.findElement(By.cssSelector("span[title='" + subject + "']")) != null)
                .collect(Collectors.toList());
        return res.get((0));
    }
}
