package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    public String invalidUser = faker.internet().emailAddress();
    public String invalidPassword = faker.internet().password(5, 12, true, true);

    @Test(description = "Successful login")
    public void login() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        projectsPage
                .waitTillOpened();
        assertTrue(projectsPage.isProjectsPageOpened(), "User is not logged in.");
    }

    @Test(description = "Log in with invalid email")
    public void invalidLoginWithWrongEmail() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(invalidUser, password);
        assertEquals(loginPage.getErrorMessageInAlert(), "These credentials do not match our records.",
                "The message about the incorrectly entered email is not displayed.");
    }

    @Test(description = "Log in with invalid password")
    public void invalidLoginWithWrongPassword() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, invalidPassword);
        assertEquals(loginPage.getErrorMessageInAlert(), "These credentials do not match our records.",
                "The message about the incorrectly entered password is not displayed.");
    }

    @Test(description = "Log in with not security password")
    public void invalidLoginWithNotSecurityPassword() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, "qwerty");
        assertEquals(loginPage.getErrorMessageInAlert(), "Security notice: " +
                        "The password entered has been found in a public data leak. " +
                        "Please reset your password to ensure the safety of your account",
                "The security notice is not displayed..");
    }

    @Test(description = "Unsuccessful log in with empty email")
    public void invalidLoginWithEmptyEmail() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login("", password);
        assertEquals(loginPage.getErrorMessageAboutRequiredFields(), "This field is required",
                "The message about the required field completion is not displayed.");
    }

    @Test(description = "Unsuccessful log in with empty password")
    public void invalidLoginWithEmptyPassword() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, "");
        assertEquals(loginPage.getErrorMessageAboutRequiredFields(), "This field is required",
                "The message about the required field completion is not displayed.");
    }
}
