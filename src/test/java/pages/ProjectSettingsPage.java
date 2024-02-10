package pages;

import com.codeborne.selenide.Condition;
import dto.Project;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class ProjectSettingsPage {
    public final String PROJECT_NAME_CSS = "#project-name";
    public final String PROJECT_CODE_CSS = "#project-code";
    public final String DESCRIPTION_CSS = "#description-area";
    public final String UPDATE_SETTINGS_BUTTON_CSS = "[type='submit']";
    public final String SUCCESS_NOTIFICATION = "//*[contains(text(), 'Project settings were successfully updated!')]";
    public final String DELETE_PROJECT_BUTTON_CSS = ".fa-trash";
    public final String CONFIRM_DELETE = "//*[contains(@class, 'ReactModalPortal')]//*[contains(text(), 'Delete project')]";
    public final String MODAL_DELETE_WINDOW_CSS = ".ReactModal__Content";
    private final By PROJECT_SETTING_TITLE = By.tagName("h1");
    private final String PROJECT_ACCESS_TYPE = "//*[contains(text(), '%s')]//ancestor::label//input";

    @Step("Waiting till the 'Project settings page' is opened")
    public ProjectSettingsPage isPageOpened() {
        $(PROJECT_SETTING_TITLE).shouldBe(Condition.visible);
        return this;
    }

    @Step("Checking that the project name is updated")
    public String checkProjectName() {
        String actualProjectName = $(PROJECT_NAME_CSS).getValue();
        log.info("Checking that the project name is '{}'", actualProjectName);
        return actualProjectName;
    }

    @Step("Checking that the project code is updated")
    public String checkProjectCode() {
        String actualProjectCode = $(PROJECT_CODE_CSS).getValue();
        log.info("Checking that the project code is '{}'", actualProjectCode);
        return actualProjectCode;
    }

    @Step("Checking that the project description is updated")
    public String checkProjectDescription() {
        String actualProjectDescription = $(DESCRIPTION_CSS).getValue();
        log.info("Checking that the project description is '{}'", actualProjectDescription);
        return actualProjectDescription;
    }

    @Step("Checking that the project type is '{projectAccessType}'")
    public String checkProjectAccessType(String projectAccessType) {
        String actualProjectAccessType = $(By.xpath(String.format(PROJECT_ACCESS_TYPE, projectAccessType))).getValue();
        log.info("Checking that the project type is '{}'", actualProjectAccessType);
        return actualProjectAccessType;
    }

    @Step("Updating existing project data")
    public ProjectSettingsPage updateProjectFields(Project project) {
        log.info("Updating project with new project name: '{}', project code: '{}', description: '{}'", project.getTitle(), project.getCode(), project.getDescription());
        $(PROJECT_NAME_CSS).clear();
        $(PROJECT_NAME_CSS).setValue(project.getTitle());
        $(PROJECT_CODE_CSS).clear();
        $(PROJECT_CODE_CSS).setValue(project.getCode());
        $(DESCRIPTION_CSS).clear();
        $(DESCRIPTION_CSS).setValue(project.getDescription());
        return new ProjectSettingsPage();
    }

    @Step("Clicking on update setting button")
    public ProjectSettingsPage clickOnUpdateSettingsButton() {
        $(UPDATE_SETTINGS_BUTTON_CSS).click();
        return this;
    }

    @Step("Checking that the notification about success updated is showed")
    public ProjectSettingsPage verifyIfNotificationIsShowed() {
        String notification = $x(SUCCESS_NOTIFICATION).shouldBe(Condition.visible).getText();
        log.info("Checking that the notification '{}' is showed", notification);
        return this;
    }

    @Step("Clicking on delete project button")
    public ProjectSettingsPage clickOnDeleteProjectButton() {
        $(DELETE_PROJECT_BUTTON_CSS).click();
        return this;
    }

    @Step("Waiting till the 'Project settings page' is opened")
    public ProjectSettingsPage deleteModalWindowIsOpened() {
        log.info("Checking that the delete modal window is opened");
        $(MODAL_DELETE_WINDOW_CSS).shouldBe(Condition.visible);
        return this;
    }

    @Step("Clicking on confirm delete project button")
    public void confirmDeleting() {
        $x(CONFIRM_DELETE).click();
    }

}
