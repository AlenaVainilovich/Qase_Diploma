package tests;

import dto.Project;
import org.testng.annotations.Test;

public class ProjectTest extends BaseTest {
    public String projectName = faker.witcher().character();
    public String projectCode = faker.currency().code();
    public String description = faker.hitchhikersGuideToTheGalaxy().marvinQuote();

    Project project = Project.builder()
            .projectName(projectName)
            .projectCode(projectCode)
            .description(description)
            .build();

    @Test
    public void projectShouldBeCreated() {
        loginPage.openLoginPage();
        loginPage.login(user, password);
        projectsPage.waitTillOpened();
    }

    @Test
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

}
