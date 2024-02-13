package pages;

import com.codeborne.selenide.Condition;
import dto.TestCase;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.DropDown;
import wrappers.ProseMirror;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2

public class EditCasePage {
    public final String EDIT_CASE_PAGE_HEADER = "//h1[text()='Edit test case']";
    public final String SAVE_EDITED_CASE_BUTTON = "#save-case";

    @Step("Opening the 'Edit test case' page")
    public EditCasePage isPageOpened() {
        $x(EDIT_CASE_PAGE_HEADER).shouldBe(Condition.visible);
        return this;
    }

    @Step("Editing the '{editedCase.title}' test case")
    public EditCasePage editTestCase(TestCase editedCase) {
        log.info("The '{}' test case is edited", editedCase.getTitle());
        new ProseMirror("Description").writeCase(editedCase.getDescription());
        new DropDown("Severity").select(editedCase.getSeverity());
        new DropDown("Priority").select(editedCase.getPriority());
        new ProseMirror("Pre-conditions").writeCase(editedCase.getPreconditions());
        return this;
    }

    @Step("Clicking the save edited case button")
    public RepositoryPage clickOnSaveEditedCaseButton() {
        $(SAVE_EDITED_CASE_BUTTON).shouldBe(Condition.appear).click();
        return new RepositoryPage();
    }
}
