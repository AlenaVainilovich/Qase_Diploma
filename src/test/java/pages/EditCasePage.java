package pages;

import com.codeborne.selenide.Condition;
import dto.TestCase;
import wrappers.DropDown;
import wrappers.ProseMirror;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class EditCasePage {
    public final String EDIT_CASE_PAGE_HEADER = "//h1[text()='Edit test case']";
    public final String SAVE_EDITED_CASE_BUTTON = "#save-case";

    public EditCasePage isPageOpened() {
        $x(EDIT_CASE_PAGE_HEADER).shouldBe(Condition.visible);
        return this;
    }

    public EditCasePage editTestCase(TestCase editedCase) {
        new ProseMirror("Description").write(editedCase.getDescription());
        new DropDown("Severity").select(editedCase.getSeverity());
        new DropDown("Priority").select(editedCase.getPriority());
        new ProseMirror("Pre-conditions").write(editedCase.getPreConditions());
        return this;
    }

    public RepositoryPage clickOnSaveEditedCaseButton() {
        $(SAVE_EDITED_CASE_BUTTON).shouldBe(Condition.appear).click();
        return new RepositoryPage();
    }
}
