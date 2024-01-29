package tests;

import dto.Project;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;

public class ProjectTest extends BaseTest {
    public String projectName = faker.witcher().character();
    public String projectCode = faker.currency().code();
    public String description = faker.hitchhikersGuideToTheGalaxy().marvinQuote();
    public String updatedProjectName = faker.witcher().character();
    public String updatedProjectCode = faker.currency().code();
    public String updatedDescription = faker.hitchhikersGuideToTheGalaxy().marvinQuote();
    public String shortProjectCode = faker.lorem().characters(1);
    public String longProjectCode = faker.lorem().characters(11);
    ;

    Project project = Project.builder()
            .projectName(projectName)
            .projectCode(projectCode)
            .description(description)
            .build();

    Project updatedProject = Project.builder()
            .projectName(updatedProjectName)
            .projectCode(updatedProjectCode)
            .description(updatedDescription)
            .build();
    Project projectWithShortProjectCode = Project.builder()
            .projectName(projectName)
            .projectCode(shortProjectCode)
            .description(description)
            .build();
    Project projectWithLongProjectCode = Project.builder()
            .projectName(projectName)
            .projectCode(longProjectCode)
            .description(description)
            .build();

// Не знаю, что за тест ты тут хотела написать.
/*    @Test
    public void projectShouldBeCreated() {
        loginPage.openLoginPage();
        loginPage.login(user, password);
        projectsPage.waitTillOpened();
    }*/

    @Test(description = "A new public project should be created with valid data")
    public void createNewProject() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        projectsPage
                .waitTillOpened()
                .clickOnCreateNewProjectButton();
        createNewProjectPage
                .isPageOpened()
                .fillProjectFields(project)
                .selectAccessType()
                .clickOnCreateProjectButton();
        projectsPage
                .openPage()
                .verifyIsProjectExist(project);
    }

    @Test(description = "Project data should be updated with valid data")
    public void updateProjectData() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        projectsPage
                .waitTillOpened()
                .clickOnCreateNewProjectButton();
        createNewProjectPage
                .isPageOpened()
                .fillProjectFields(project)
                .selectAccessType()
                .clickOnCreateProjectButton();
        repositoryPage
                .verifyIfSidebarSectionExists("Settings")
                .clickOnTheSidebarSection("Settings");
        projectSettingsPage
                .isPageOpened()
                .updateProjectFields(updatedProject)
                .clickOnUpdateSettingsButton()
                .verifyIfNotificationIsShowed();
        sleep(1000);
        assertEquals(projectSettingsPage.
                        checkProjectName(),
                updatedProjectName,
                "projectName is not matched");
        assertEquals(projectSettingsPage.
                        checkProjectCode(),
                updatedProjectCode,
                "projectCode is not matched");
        assertEquals(projectSettingsPage.
                        checkProjectDescription(),
                updatedDescription,
                "projectDescription is not matched");
    }

    @Test(description = "The project should be deleted")
    public void deleteTheProject() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        projectsPage
                .waitTillOpened()
                .clickOnCreateNewProjectButton();
        createNewProjectPage
                .isPageOpened()
                .fillProjectFields(project)
                .selectAccessType()
                .clickOnCreateProjectButton();
        repositoryPage
                .verifyIfSidebarSectionExists("Settings")
                .clickOnTheSidebarSection("Settings");
        projectSettingsPage
                .clickOnDeleteProjectButton()
                .deleteModalWindowIsOpened();
        projectSettingsPage
                .confirmDeleting();
        projectsPage
                .waitTillOpened()
                .verifyIfProjectDelete(project);
        sleep(2000);
    }

    @Test(description = "A new project should not be created with 'Project code' more than 10 characters")
    public void projectShouldNotBeCreatedWithProjectCodeMoreThanTenCharacters() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        projectsPage
                .waitTillOpened()
                .clickOnCreateNewProjectButton();
        createNewProjectPage
                .isPageOpened()
                .fillProjectFields(projectWithLongProjectCode)
                .selectAccessType()
                .clickOnCreateProjectButton();
        assertEquals(repositoryPage.
                        getProjectCodeErrorMessage(),
                "The code may not be greater than 10 characters.",
                "Incorrect error message text");
    }

    @Test(description = " A new project should not be created with 'Project code' less than 2 characters")
    public void projectShouldNotBeCreatedWithProjectCodeLessThanTwoCharacters() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        projectsPage
                .waitTillOpened()
                .clickOnCreateNewProjectButton();
        createNewProjectPage
                .isPageOpened()
                .fillProjectFields(projectWithShortProjectCode)
                .selectAccessType()
                .clickOnCreateProjectButton();
        assertEquals(repositoryPage.
                        getProjectCodeErrorMessage(),
                "The code must be at least 2 characters.",
                "Incorrect error message text");

    }
}
