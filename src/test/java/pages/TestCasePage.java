package pages;

import com.codeborne.selenide.Condition;
import dto.TestCase;
import wrappers.DropDown;
import wrappers.ProseMirror;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestCasePage extends BasePage {

    public final String CASE_TITLE = "#title";
    public final String SAVE_BUTTON = "#save-case";
    //public static Random random = new Random();

    //public static int getRandomOption(int min, int max) {
    //   return random.nextInt((max - min) + 1) + min;
    // }

    public TestCasePage isPageOpened() {
        $(SAVE_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public TestCasePage createNewTestCase(TestCase testCase) {
        $(CASE_TITLE).clear();
        $(CASE_TITLE).setValue(testCase.getTitle());
        new DropDown("Status").select(testCase.getStatus());
        new ProseMirror("Description").write(testCase.getDescription());
        new DropDown("Severity").select(testCase.getSeverity());
        new DropDown("Priority").select(testCase.getPriority());
        new DropDown("Type").select(testCase.getType());
        new DropDown("Layer").select(testCase.getLayer());
        new DropDown("Is flaky").select(testCase.getIsflaky());
        new DropDown("Behavior").select(testCase.getBehavior());
        new DropDown("Automation status").select(testCase.getAutomation());
        new ProseMirror("Pre-conditions").write(testCase.getPreconditions());
        new ProseMirror("Post-conditions").write(testCase.getPostconditions());
        return this;
    }

    public void openTestPage() {
        open("https://app.qase.io/case/TEST/create");
    }

    public void openTestRepositoryPage() {
        open("https://app.qase.io/case/TEST");
    }

    public RepositoryPage clickOnSaveNewCaseButton() {
        $(SAVE_BUTTON).shouldBe(Condition.appear).click();
        return new RepositoryPage();
    }
}
