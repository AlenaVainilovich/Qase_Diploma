package pages;

import com.codeborne.selenide.Condition;
import dto.TestCase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertEquals;


public class RepositoryPage {
    public final String CREATE_CASE_BUTTON = "#create-case-button";
    public final String CASE_NAME = "//*[@id='suitecases-container']//*[contains(text(), '%s')]";
    public final String EDIT_CASE_BUTTON = "//*[contains(text(), 'Edit')]";
    public final String DELETE_CASE_BUTTON = "//*[contains(text(), 'Delete')]";
    public final String CASE_PROPERTIES = "//button[text()='Properties']";
    //public final String EDIT_SUITE_BUTTON = ".fa-pencil";
    //public final String DELETE_SUITE_BUTTON = ".fa-trash";
    public final String CONFIRM_DELETE = "//div[contains(@class,'ReactModal__Content')]/descendant::*[text()='Delete']";

    public RepositoryPage isPageOpened() {
        $(CREATE_CASE_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public RepositoryPage verifyIfCaseExist(TestCase testCase) {
        $x(String.format(CASE_NAME, testCase.getTitle())).shouldBe(Condition.visible);
        return this;
    }

    public RepositoryPage clickOnTheCaseName(TestCase testCase) {
        $x(String.format(CASE_NAME, testCase.getTitle())).click();
        return this;
    }

    public EditCasePage clickOnTheEditCaseButton() {
        $x(EDIT_CASE_BUTTON).click();
        return new EditCasePage();
    }

    public void clickOnTheDeleteButton() {
        $x(DELETE_CASE_BUTTON).click();
    }

    public void confirmDeleting() {
        $x(CONFIRM_DELETE).click();
    }
}
