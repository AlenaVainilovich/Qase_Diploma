package pages;

import com.codeborne.selenide.Condition;
import dto.Suite;
import io.qameta.allure.Step;
import wrappers.ProseMirror;

import static com.codeborne.selenide.Selenide.$;

public class SuitesPage {

    private final String SUITE_TITLE = "#title";
    private final String SAVE_BUTTON = "button[type='submit']";

    public SuitesPage fillInSuiteFields(Suite suite) {
        $(SUITE_TITLE).clear();
        $(SUITE_TITLE).setValue(suite.getTitle());
        new ProseMirror("Description").write(suite.getDescription());
        new ProseMirror("Preconditions").write(suite.getPreconditions());
        return this;
    }

    public SuitesPage checkSuiteData(Suite suite) {
        $(SUITE_TITLE).shouldBe(Condition.value(suite.getTitle()));
        return this;
    }

    @Step("Click on the Save button")
    public RepositoryPage clickOnSaveSuiteButton() {
        $(SAVE_BUTTON).shouldBe(Condition.appear).click();
        return new RepositoryPage();
    }

}
