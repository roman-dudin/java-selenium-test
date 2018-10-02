package uitests;

import org.junit.Test;
import pages.LoginPage;
import pages.MailPage;
import pages.MailsPage;
import static org.junit.Assert.*;

public class FirstTest extends BaseTest {

    @Test
    public void runFirstTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage();

        // Login:
        loginPage.Login("javaselenium", "sj123test");

        // Search for required mail:
        MailsPage mailsPage = new MailsPage();
        mailsPage.openMailBy("dudin7roman", "Hello");

        MailPage mailPage = new MailPage();

        // Checks:
        assertEquals("dudin7roman@gmail.com", mailPage.getSender());
        assertEquals("Hello", mailPage.getSubject());
        assertEquals("Hello world!", mailPage.getMessageBody());
    }
}
