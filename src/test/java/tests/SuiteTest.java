package tests;

import dto.Project;
import dto.ProjectFactory;
import dto.Suite;
import dto.SuiteFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SuiteTest extends BaseTest {

    @Test(description = "Create Suite")
    public void createNewSuite() {
        Project project = new ProjectFactory().newProject(7);
        Suite suite = new SuiteFactory().newSuite();
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
        projectsAdapter.delete(project.getCode().toUpperCase());
    }

    @Test(description = "Update Suite")
    public void updateSuite() {
        Project project = new ProjectFactory().newProject(7);
        Suite suite = new SuiteFactory().newSuite();
        Suite updatedSuite = new SuiteFactory().updatedSuite();
        projectsAdapter
                .create(project);
        suiteAdapter
                .create(project.getCode().toUpperCase(), suite);
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        repositoryPage
                .openPage(project.getCode().toUpperCase())
                .isPageOpened()
                .verifyIfSuiteExist(suite)
                .clickOnTheEditSuiteButton(suite);
        suitesPage
                .fillInSuiteFields(updatedSuite)
                .clickOnSaveSuiteButton();
        repositoryPage
                .verifyIfSuiteExist(updatedSuite)
                .clickOnTheEditSuiteButton(updatedSuite);
        assertEquals(suitesPage.getSuiteTitle(), updatedSuite.getTitle(),
                "The expected test suite name does not match the actual one.");
        assertEquals(suitesPage.getSuiteDescription(), updatedSuite.getDescription(),
                "The expected test suite description does not match the actual one.");
        assertEquals(suitesPage.getSuitePreconditions(), updatedSuite.getPreconditions(),
                "The expected test suite preconditions does not match the actual one.");
        projectsAdapter.delete(project.getCode().toUpperCase());

    }

    @Test(description = "Delete Suite")
    public void deleteSuite() {
        Project project = new ProjectFactory().newProject(7);
        Suite suite = new SuiteFactory().newSuite();
        projectsAdapter
                .create(project);
        suiteAdapter
                .create(project.getCode().toUpperCase(), suite);
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        repositoryPage
                .openPage(project.getCode().toUpperCase())
                .isPageOpened()
                .verifyIfSuiteExist(suite)
                .clickOnTheDeleteSuiteButton(suite)
                .confirmDeleting()
                .verifyIfSuiteHasBeenDeleted(suite);
        projectsAdapter.delete(project.getCode().toUpperCase());
    }
}
