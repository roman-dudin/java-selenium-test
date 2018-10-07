package uitests;

import framework.Browser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.MailPage;
import pages.MailsPage;

import java.util.Arrays;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FirstTest extends BaseTest {
    private String sender;
    private String subject;
    private String expectedBody;

    public FirstTest(String sender, String subject, String expectedBody) {
        this.sender = sender;
        this.subject = subject;
        this.expectedBody = expectedBody;
    }

    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(new Object[][]{
                {"dudin7roman@gmail.com", "Hello", "Hello world!"}
        });
    }

    @Test
    public void runFirstTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage();

        // Login:
        loginPage.Login(Browser.props.getProperty("username"), Browser.props.getProperty("password"));

        // Search for required mail:
        MailsPage mailsPage = new MailsPage();
        mailsPage.openMailBy(sender, subject);

        MailPage mailPage = new MailPage();

        // Checks:
        assertEquals(sender, mailPage.getSender());
        assertEquals(subject, mailPage.getSubject());
        assertEquals(expectedBody, mailPage.getMessageBody());
    }
}
