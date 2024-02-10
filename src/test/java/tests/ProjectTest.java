package tests;

import dto.Project;
import dto.ProjectFactory;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;

public class ProjectTest extends BaseTest {

    Project projectForCreation = new ProjectFactory().newProject(5);
    Project projectForUpdate = new ProjectFactory().updatedProject();
    Project updatedProject = new ProjectFactory().updatedProject();
    Project projectForDelete = new ProjectFactory().newProject(5);
    Project projectWithProjectCodeMoreThan10 = new ProjectFactory().newProject(11);
    Project projectWithProjectCodeLessThan2 = new ProjectFactory().newProject(1);

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
                .fillProjectFields(projectForCreation)
                .selectAccessType()
                .clickOnCreateProjectButton();
        projectsPage
                .openPage()
                .verifyIsProjectExist(projectForCreation);
/*        sleep(2000);
        projectsAdapter
                .delete(String.format(projectForCreation.getCode()).toUpperCase());*/
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
                .fillProjectFields(projectForUpdate)
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
                updatedProject.getTitle(),
                "projectName is not matched");
        assertEquals(projectSettingsPage.
                        checkProjectCode(),
                updatedProject.getCode(),
                "projectCode is not matched");
        assertEquals(projectSettingsPage.
                        checkProjectDescription(),
                updatedProject.getDescription(),
                "projectDescription is not matched");
        sleep(1000);
        projectsAdapter
                .delete(String.format(updatedProject.getCode()).toUpperCase());
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
                .fillProjectFields(projectForDelete)
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
                .verifyIfProjectDelete(projectForDelete);
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
                .fillProjectFields(projectWithProjectCodeMoreThan10)
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
                .fillProjectFields(projectWithProjectCodeLessThan2)
                .selectAccessType()
                .clickOnCreateProjectButton();
        assertEquals(repositoryPage.
                        getProjectCodeErrorMessage(),
                "The code must be at least 2 characters.",
                "Incorrect error message text");

    }
}
