package pages;

import com.codeborne.selenide.Condition;
import dto.TestCase;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.DropDown;
import wrappers.ProseMirror;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCasePage extends BasePage {

    public final String CASE_TITLE = "#title";
    public final String SAVE_BUTTON = "#save-case";

    @Step("Opening the 'Test case' page")
    public TestCasePage isPageOpened() {
        $(SAVE_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    @Step("Creating a new test case")
    public TestCasePage createNewTestCase(TestCase testCase) {
        $(CASE_TITLE).clear();
        $(CASE_TITLE).setValue(testCase.getTitle());
        log.info("The '{}' test case is created", testCase.getTitle());
        new DropDown("Status").select(testCase.getStatus());
        new ProseMirror("Description").writeCase(testCase.getDescription());
        new DropDown("Severity").select(testCase.getSeverity());
        new DropDown("Priority").select(testCase.getPriority());
        new DropDown("Type").select(testCase.getType());
        new DropDown("Layer").select(testCase.getLayer());
        new DropDown("Is flaky").select(testCase.getIsFlaky());
        new DropDown("Behavior").select(testCase.getBehavior());
        new DropDown("Automation status").select(testCase.getAutomation());
        new ProseMirror("Pre-conditions").writeCase(testCase.getPreconditions());
        new ProseMirror("Post-conditions").writeCase(testCase.getPostconditions());
        return this;
    }

    @Step("Clicking on the save button ")
    public RepositoryPage clickOnSaveNewCaseButton() {
        $(SAVE_BUTTON).shouldBe(Condition.appear).click();
        return new RepositoryPage();
    }
}
