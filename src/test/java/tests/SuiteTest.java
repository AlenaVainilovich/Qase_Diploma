package tests;

import adapters.ProjectsAdapter;
import dto.Project;
import dto.ProjectFactory;
import dto.Suite;
import dto.SuiteFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SuiteTest extends BaseTest {
    Project project = new ProjectFactory().newProject();
    Suite suite = new SuiteFactory().newSuite();

    @AfterClass
    public void deleteProject() {
        new ProjectsAdapter().delete(project.getCode());
    }

    @Test
    public void createNewSuite() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        projectsAdapter
                .create(project);
        repositoryPage
                .openPage(project.getCode());
        suiteAdapter
                .create(project.getCode(), suite);
    }
}
