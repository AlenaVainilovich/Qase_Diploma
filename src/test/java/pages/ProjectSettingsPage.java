package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ProjectSettingsPage {
    public final String PROJECT_NAME_CSS = "#project-name";
    public final String PROJECT_CODE_CSS = "#project-code";
    public final String DESCRIPTION_CSS = "#description-area";
    private final By PROJECT_SETTING_TITLE = By.tagName("h1");

    @Step("Waiting till the 'Project settings page' is opened")
    public ProjectSettingsPage isPageOpened() {
        $(PROJECT_SETTING_TITLE).shouldBe(Condition.visible);
        return this;
    }
}
