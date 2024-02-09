package tests;

import adapters.ProjectsAdapter;
import dto.Project;
import dto.ProjectFactory;
import dto.Suite;
import dto.SuiteFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SuiteTest extends BaseTest {
    Project project = new ProjectFactory().newProject(7);
    Suite suite = new SuiteFactory().newSuite();

    @AfterClass
    public void deleteProject() {
        new ProjectsAdapter().delete(project.getCode().toUpperCase());
    }

    @Test
    public void createNewSuite() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        projectsAdapter
                .create(project);
        suiteAdapter
                .create(project.getCode().toUpperCase(), suite);
    }
}
