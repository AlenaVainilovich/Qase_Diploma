package wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;

public class DropDown {
    String label;
    String dropDownLocator = ".//label[text()='%s']/following-sibling::div";
    String optionLocator = "//div[contains(@class, 'FlenM_') and text()='%s']";

    public DropDown(String label) {
        this.label = label;
    }

    public void select(String option) {
        $x(String.format(dropDownLocator, label)).shouldBe(Condition.visible).click();
        Selenide.sleep(1000);
        $x(String.format(optionLocator, option)).shouldBe(Condition.visible).click();
    }
}

