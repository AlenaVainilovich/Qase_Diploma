package pages;

import com.codeborne.selenide.Condition;
import dto.Suite;
import dto.TestCase;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;


@Log4j2
public class RepositoryPage extends BasePage {
    public final String CREATE_CASE_BUTTON = "#create-case-button";
    public final String CASE_NAME = "//*[@id='suitecases-container']//*[contains(text(), '%s')]";
    public final String EDIT_CASE_BUTTON = "//*[contains(text(), 'Edit')]";
    public final String DELETE_CASE_BUTTON = "//*[contains(text(), 'Delete')]";
    public final String CASE_PROPERTIES = "//button[text()='Properties']";
    public final String CREATE_SUITE_BUTTON = "#create-suite-button";
    public final String EDIT_SUITE_BUTTON = "//span[contains(text(),'%s')]/parent::*//i[contains(@class, 'fa-pencil')]";
    public final String SUITE_IN_LIST = "//span[contains(text(),'%s')]";
    public final String DELETE_SUITE_BUTTON = "//span[contains(text(),'%s')]/parent::*//i[contains(@class, 'fa-trash')]";
    public final String CONFIRM_DELETE = "//div[contains(@class,'ReactModal__Content')]/descendant::*[text()='Delete']";
    public final String SIDEBAR_SECTION = "//*[contains(text(), '%s')]";
    public final String ERROR_MESSAGE_FOR_PROJECT_CODE_CSS = "//div//input[@id='project-code']/../..//div[contains(text(), 'The code ')]";

    public final String REPOSITORY_URL = "/project/%s";

    @Step("Opening the 'Repository Page'")
    public RepositoryPage isPageOpened() {
        $(CREATE_CASE_BUTTON).shouldBe(Condition.visible);
        waitForPageLoaded();
        return this;
    }

    @Step("Clicking create Case button")
    public TestCasePage clickOnTheCreateCaseButton() {
        $(CREATE_CASE_BUTTON).click();
        return new TestCasePage();
    }

    @Step("Verification that '{testCase.title}' is existed")
    public RepositoryPage verifyIfCaseExist(TestCase testCase) {
        $x(String.format(CASE_NAME, testCase.getTitle())).shouldBe(Condition.visible);
        return this;
    }

    @Step("Clicking on the test case name")
    public RepositoryPage clickOnTheCaseName(TestCase testCase) {
        $x(String.format(CASE_NAME, testCase.getTitle())).click();
        return this;
    }

    @Step("Clicking on the edit button")
    public EditCasePage clickOnTheEditCaseButton() {
        $x(EDIT_CASE_BUTTON).click();
        return new EditCasePage();
    }

    @Step("Clicking on the delete button")
    public void clickOnTheDeleteButton() {
        $x(DELETE_CASE_BUTTON).click();
    }

    @Step("Confirmation of deletion by clicking the Delete button")
    public RepositoryPage confirmDeleting() {
        $x(CONFIRM_DELETE).click();
        return this;
    }

    @Step("Checking error message below project code field")
    public String getProjectCodeErrorMessage() {
        String errorMessage = $x(ERROR_MESSAGE_FOR_PROJECT_CODE_CSS).getText();
        log.info("Checking error message '{}', below project code field", errorMessage);
        return $x(ERROR_MESSAGE_FOR_PROJECT_CODE_CSS).getText();
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

    @Step("Click on the test suite editing button")
    public SuitesPage clickOnTheEditSuiteButton(Suite suite) {
        $x(String.format(EDIT_SUITE_BUTTON, suite.getTitle())).click();
        log.info("The test suite editing window should be open.");
        return new SuitesPage();
    }

    @Step("Click create Case button")
    public SuitesPage clickOnTheCreateSuiteButton() {
        log.info("Click on the create Suite button");
        $(CREATE_SUITE_BUTTON).click();
        return new SuitesPage();
    }

    @Step("Check that the test suite has been created")
    public RepositoryPage verifyIfSuiteExist(Suite suite) {
        $x(String.format(SUITE_IN_LIST, suite.getTitle())).shouldBe(Condition.visible);
        log.info("Suite with title {} has been created", suite.getTitle());
        return this;
    }

    @Step("Open project by code '{code}'")
    public RepositoryPage openPage(String code) {
        open(String.format(REPOSITORY_URL, code));
        log.info("Opening project by code {}", code);
        return new RepositoryPage();
    }

    @Step("Delete suite by name '{suite.title}'")
    public RepositoryPage clickOnTheDeleteSuiteButton(Suite suite) {
        $x(String.format(DELETE_SUITE_BUTTON, suite.getTitle())).click();
        log.info("Deleting suite by name {}", suite.getTitle());
        return this;
    }

    @Step("Check that the '{suite.title}' test suite has been deleted")
    public RepositoryPage verifyIfSuiteHasBeenDeleted(Suite suite) {
        log.info("Checking that the suite '{}' has been deleted", suite.getTitle());
        $x(String.format(SUITE_IN_LIST, suite.getTitle())).shouldNotBe(Condition.visible);
        return this;
    }

}

