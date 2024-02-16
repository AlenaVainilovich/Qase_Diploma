package tests;

import dto.Project;
import dto.ProjectFactory;
import dto.TestCase;
import dto.TestCaseFactory;
import org.testng.annotations.Test;


public class TestCaseTest extends BaseTest {

    @Test
    public void createEditDeleteNewCase() {
        Project project = new ProjectFactory().newProject(5);
        TestCase newCase = new TestCaseFactory().newCase();
        TestCase editedCase = new TestCaseFactory().editedCase();
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(user, password);
        projectsPage
                .waitTillOpened();
        projectsAdapter
                .create(project);
        repositoryPage
                .openPage(project.getCode().toUpperCase())
                .clickOnTheCreateCaseButton();
        testCasePage
                .createNewTestCase(newCase)
                .clickOnSaveNewCaseButton();
        repositoryPage
                .isPageOpened()
                .verifyIfCaseExist(newCase)
                .clickOnTheCaseName(newCase)
                .clickOnTheEditCaseButton();
        editCasePage
                .isPageOpened()
                .editTestCase(editedCase)
                .clickOnSaveEditedCaseButton();
        repositoryPage
                .isPageOpened()
                .verifyIfCaseExist(newCase)
                .clickOnTheCaseName(newCase)
                .clickOnTheDeleteButton();
        repositoryPage.confirmDeleting();
        projectsAdapter.delete(project.getCode().toUpperCase());


    }
}
