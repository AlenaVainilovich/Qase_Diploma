package tests;

import adapters.ProjectsAdapter;
import dto.Project;
import dto.ProjectFactory;
import dto.Suite;
import dto.SuiteFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SuiteTest extends BaseTest {
    Project project = new ProjectFactory().newProject(7);
    Suite suite = new SuiteFactory().newSuite();

    @AfterClass
    public void deleteProject() {
        new ProjectsAdapter().delete(project.getCode().toUpperCase());
    }

    @Test(description = "Create Suite")
    public void createNewSuite() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        projectsAdapter
                .create(project);
        projectsPage
                .waitTillOpened()
                .chooseProjectByName(project);
        repositoryPage.clickOnTheCreateSuiteButton();
        assertTrue(suitesPage.isCreateSuitePageOpened(), "The create suite window isn't opened");
        suitesPage
                .fillInSuiteFields(suite)
                .clickOnSaveSuiteButton();
        repositoryPage
                .verifyIfSuiteExist(suite)
                .clickOnTheEditSuiteButton(suite);
        assertEquals(suitesPage.getSuiteTitle(), suite.getTitle(),
                "The expected test suite name does not match the actual one.");
        assertEquals(suitesPage.getSuiteDescription(), suite.getDescription(),
                "The expected test suite description does not match the actual one.");
        assertEquals(suitesPage.getSuitePreconditions(), suite.getPreconditions(),
                "The expected test suite preconditions does not match the actual one.");
    }

    @Test(description = "Update Suite")
    public void updateSuite() {
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
