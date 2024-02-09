package tests;

import adapters.ProjectsAdapter;
import dto.Project;
import dto.ProjectFactory;
import dto.TestCase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class TestCaseTest extends BaseTest {

    public String title = faker.harryPotter().character();
    public String description = faker.hitchhikersGuideToTheGalaxy().marvinQuote();
    public String preConditions = faker.beer().name();
    public String postConditions = faker.animal().name();
    Project project = new ProjectFactory().newProject(5);
    TestCase newCase = TestCase.builder()
            .title(title)
            .status("Actual")
            .description(description)
            .severity("Major")
            .priority("High")
            .type("Smoke")
            .layer("E2E")
            .isFlaky("Yes")
            .behavior("Positive")
            .automation("Automated")
            .preconditions(preConditions)
            .postconditions(postConditions)
            .build();

    TestCase editedCase = TestCase.builder()
            .description(faker.backToTheFuture().quote())
            .severity("Minor")
            .priority("Medium")
            .preconditions(faker.gameOfThrones().house())
            .build();

    @AfterClass
    public void deleteProject() {
        new ProjectsAdapter().delete(project.getCode().toUpperCase());
    }

    @Test
    public void createEditDeleteNewCase() {
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


    }
}
