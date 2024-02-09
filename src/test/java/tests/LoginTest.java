package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    public String invalidUser = faker.internet().emailAddress();

    @Test(description = "The user should be logged in with valid data")
    public void login() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
    }
/*
    @Test(description = "The user should not be logged in with the wrong email")
    public void invalidLogin() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .invalidLogin(invalidUser, PropertyReader.getProperty("password"));
        assertEquals(loginPage.getErrorMessage(), "These credentials do not match our records.");
    }*/
}
