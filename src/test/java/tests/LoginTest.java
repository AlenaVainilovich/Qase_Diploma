package tests;

import org.testng.annotations.Test;
import utils.PropertyReader;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    public String invalidUser = faker.internet().emailAddress();

    @Test
    public void login() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
    }

    @Test
    public void invalidLogin() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .invalidLogin(invalidUser, PropertyReader.getProperty("password"));
        assertEquals(loginPage.getErrorMessage(), "These credentials do not match our records.");
    }
}
