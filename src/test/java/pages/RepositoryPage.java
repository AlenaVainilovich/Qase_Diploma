package pages;

import com.codeborne.selenide.Condition;
import dto.TestCase;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class RepositoryPage {
    public final String CREATE_CASE_BUTTON = "#create-case-button";
    public final String CASE_NAME = ".wq7uNh";
    public final String EDIT_CASE_BUTTON = ".fa-pencil";
    public final String DELETE_CASE_BUTTON = ".fa-trash";
    public final String CONFIRM_DELETE = ".b_jd28";
    public final String SIDEBAR_SECTION = "//*[contains(text(), '%s')]";

    public final String ERROR_MESSAGE_FOR_PROJECT_CODE_CSS = ".fkMMG8";


    public RepositoryPage isPageOpened() {
        $(CREATE_CASE_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public RepositoryPage verifyIfCaseExist(TestCase testCase) {
        $(String.format(CASE_NAME, testCase.getTitle())).shouldBe(Condition.visible);
        return this;
    }

    public RepositoryPage clickOnTheCaseName(TestCase testCase) {
        $(String.format(CASE_NAME, testCase.getTitle())).click();
        return this;
    }

    public EditCasePage clickOnTheEditCaseButton() {
        $(EDIT_CASE_BUTTON).click();
        return new EditCasePage();
    }

    public void clickOnTheDeleteButton() {
        $(DELETE_CASE_BUTTON).click();
    }

    public void confirmDeleting() {
        $(CONFIRM_DELETE).click();
    }

    @Step("Checking error message below project code field")
    public String getProjectCodeErrorMessage() {
        String errorMessage = $(ERROR_MESSAGE_FOR_PROJECT_CODE_CSS).getText();
        log.info("Checking error message '{}', below project code field", errorMessage);
        return $(ERROR_MESSAGE_FOR_PROJECT_CODE_CSS).getText();
    }

    @Step("Verification if the sidebar is displayed")
    public RepositoryPage verifyIfSidebarSectionExists(String section) {
        $(By.xpath(String.format(SIDEBAR_SECTION, section))).shouldBe(Condition.visible);
        return new RepositoryPage();
    }

    @Step("Clicking to the '{section}' section in the sidebar")
    public RepositoryPage clickOnTheSidebarSection(String section) {
        log.info("Clicking to the {} section in the sidebar", section);
        $x(String.format(SIDEBAR_SECTION, section)).click();
        return new RepositoryPage();
    }
}

