package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LoginPage {

    public final String EMAIL_CSS = "[name=email]";
    public final String PASSWORD_CSS = "[name=password]";
    public final String SUBMIT_CSS = "[type=submit]";
    public final String ERROR_MESSAGE = "//div[@role='alert']//span";
    public final String REQUIRED_ERROR_MESSAGE = "//div/small";

    @Step("Open Login page")
    public LoginPage openLoginPage() {
        log.info("Open 'Login page' by link: " + baseUrl + "/login");
        open("/login");
        return this;
    }

    @Step("Wait till the login page is opened")
    public LoginPage isPageOpened() {
        $(SUBMIT_CSS).shouldBe(Condition.visible);
        return this;
    }

    @Step("Log in by '{user}' and '{password}'")
    public ProjectsPage login(String user, String password) {
        log.info("{} with {} password is logging in", user, password);
        $(EMAIL_CSS).sendKeys(user);
        $(PASSWORD_CSS).sendKeys(password);
        $(SUBMIT_CSS).click();
        return new ProjectsPage();
    }

    @Step("Get an error message in alert")
    public String getErrorMessageInAlert() {
        String errorMessage = $x(ERROR_MESSAGE).getText();
        log.info("Getting error message '{}'", errorMessage);
        return errorMessage;
    }

    @Step("Get an error message about required fields.")
    public String getErrorMessageAboutRequiredFields() {
        String requiredErrorMessage = $x(REQUIRED_ERROR_MESSAGE).getText();
        log.info("Getting error message '{}'", requiredErrorMessage);
        return requiredErrorMessage;
    }

}
