package pages;

import com.codeborne.selenide.Condition;
import dto.Suite;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.ProseMirror;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class SuitesPage {

    public final String SUITE_HEADER = "//*[text()='Create suite']";
    public final String SUITE_FIELD = "//label[text()='%s']/ancestor::div[2]//p[@class]";
    private final String SUITE_TITLE = "#title";
    private final String SAVE_BUTTON = "button[type='submit']";

    @Step("Fill in the fields of the test suite")
    public SuitesPage fillInSuiteFields(Suite suite) {
        $(SUITE_TITLE).clear();
        $(SUITE_TITLE).setValue(suite.getTitle());
        new ProseMirror("Description").writeSuite(suite.getDescription());
        new ProseMirror("Preconditions").writeSuite(suite.getPreconditions());
        log.info("Filling in the test suite fields with random values");
        return this;
    }

    @Step("Get suite title")
    public String getSuiteTitle() {
        log.info("Getting the name of the test suite");
        return $(SUITE_TITLE).getValue();
    }

    @Step("Get suite description")
    public String getSuiteDescription() {
        log.info("Getting the description of the test suite");
        return $x(String.format(SUITE_FIELD, "Description")).getText();
    }

    @Step("Get suite preconditions")
    public String getSuitePreconditions() {
        log.info("Getting the preconditions of the test suite");
        return $x(String.format(SUITE_FIELD, "Preconditions")).getText();
    }

    @Step("Click on the Save button")
    public RepositoryPage clickOnSaveSuiteButton() {
        $(SAVE_BUTTON).shouldBe(Condition.appear).click();
        return new RepositoryPage();
    }

    @Step("Check that the test suite creation window is open")
    public boolean isCreateSuitePageOpened() {
        log.info("Checking if the Suite page is opened");
        return $x(SUITE_HEADER).isDisplayed();
    }

}
