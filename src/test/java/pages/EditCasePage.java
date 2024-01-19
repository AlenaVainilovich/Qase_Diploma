package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class EditCasePage {
    public final String EDIT_CASE_PAGE_HEADER = "//h1[text()='Edit test case']";

    public EditCasePage isPageOpened() {
        $x(EDIT_CASE_PAGE_HEADER).shouldBe(Condition.visible);
        return this;
    }


}
