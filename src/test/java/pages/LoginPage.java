package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage {

    public final String EMAIL_CSS = "[name=email]";
    public final String PASSWORD_CSS = "[name=password]";
    public final String SUBMIT_CSS = "[type=submit]";
    public final String ERROR_MESSAGE = "//div[@role='alert']//span[@class='ic9QAx']";

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

    @Step("Log in by '{user}' and redirect on Projects Page")
    public ProjectsPage login(String user, String password) {
        log.info("{} with {} password is logging in", user, password);
        $(EMAIL_CSS).sendKeys(user);
        $(PASSWORD_CSS).sendKeys(password);
        $(SUBMIT_CSS).click();
        return new ProjectsPage();
    }

    @Step("Log in with invalid data")
    public LoginPage invalidLogin(String invalidUser, String password) {
        $(EMAIL_CSS).sendKeys(invalidUser);
        $(PASSWORD_CSS).sendKeys(password);
        $(SUBMIT_CSS).click();
        return this;
    }

    @Step("Get error message")
    public String getErrorMessage() {
        String errorMessage = $(ERROR_MESSAGE).getText();
        return errorMessage;
    }


}
