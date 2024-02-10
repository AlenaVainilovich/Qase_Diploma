package pages;

import com.codeborne.selenide.Condition;
import dto.Project;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


@Log4j2
public class CreateNewProjectPage extends BasePage {
    public final String PROJECT_NAME_CSS = "#project-name";
    public final String PROJECT_CODE_CSS = "#project-code";
    public final String DESCRIPTION_CSS = "#description-area";

    public final String PUBLIC_PROJECT_ACCESS_TYPE = "//*[contains(text(), 'Public')]//ancestor::label//input";

    public final String CREATE_PROJECT_BUTTON_CSS = "[type=submit]";

    @Step("Waiting till the 'Create new project' modal window is opened")
    public CreateNewProjectPage isPageOpened() {
        $(CREATE_PROJECT_BUTTON_CSS).shouldBe(Condition.visible);
        return this;
    }

    @Step("Clicking on 'Create project' button")
    public ProjectsPage clickOnCreateProjectButton() {
        log.info("Click button 'Create new project' by locator: " + CREATE_PROJECT_BUTTON_CSS);
        $(CREATE_PROJECT_BUTTON_CSS).click();
        return new ProjectsPage();
    }

    @Step("Choosing the 'Public' Project access type")
    public CreateNewProjectPage selectAccessType() {
        log.info("Choosing the 'Public' Project access type");
        $x(PUBLIC_PROJECT_ACCESS_TYPE).click();
        return new CreateNewProjectPage();
    }

    @Step("Filling the project fields")
    public CreateNewProjectPage fillProjectFields(Project project) {
        log.info("Project fields is filling with " +
                        "Project name = '{}', Project code = '{}', Description = '{}'",
                project.getTitle(), project.getCode(), project.getDescription());
        $(PROJECT_NAME_CSS).clear();
        $(PROJECT_NAME_CSS).setValue(project.getTitle());
        $(PROJECT_CODE_CSS).clear();
        $(PROJECT_CODE_CSS).setValue(project.getCode());
        $(DESCRIPTION_CSS).clear();
        $(DESCRIPTION_CSS).setValue(project.getDescription());
        return new CreateNewProjectPage();
    }
}
