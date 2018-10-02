package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailPage extends BasePage {

    private final String messageBodyLocator = "div.mail-Message-Body-Content";

    @FindBy(css = "span.mail-Message-Sender-Email")
    private WebElement senderLabel;

    @FindBy(css = "div.mail-Message-Toolbar-Subject")
    private WebElement subjectLabel;

    @FindBy(css = messageBodyLocator)
    private WebElement messageBody;

    public MailPage() {
        // Wait page to be loaded
        browser.waitAtLeastOneElementByCssSelector(By.cssSelector(messageBodyLocator));
        PageFactory.initElements(browser.driver, this);
    }

    public String getSender() throws InterruptedException {
        Thread.sleep(3000);
        return senderLabel.getText();
    }

    public String getSubject() {
        return subjectLabel.getText();
    }

    public String getMessageBody() {
        return messageBody.getText();
    }
}
