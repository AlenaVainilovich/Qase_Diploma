package tests;

import dto.TestCase;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.sleep;


public class TestCaseTest extends BaseTest {
    public String title = faker.harryPotter().character();
    public String description = faker.hitchhikersGuideToTheGalaxy().marvinQuote();
    public String preConditions = faker.beer().name();
    public String postConditions = faker.animal().name();

    TestCase newCase = TestCase.builder()
            .title(title)
            .status("Actual")
            .description(description)
            .severity("Major")
            .priority("High")
            .type("Smoke")
            .behavior("Positive")
            .automationStatus("Automated")
            .preConditions(preConditions)
            .postConditions(postConditions)
            .build();

    @Test(description = "A New test case should be created with valid data")
    public void createNewCase() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(PropertyReader.getProperty("user"), PropertyReader.getProperty("password"));
        projectsPage
                .waitTillOpened();
        testCasePage.openTestPage();
        testCasePage.createNewTestCase(newCase);
        testCasePage.clickOnSaveNewCaseButton();
        repositoryPage
                .isPageOpened()
                .verifyIfCaseExist(newCase)
                .clickOnTheCaseName(newCase)
                .clickOnTheEditCaseButton();
        editCasePage
                .isPageOpened();


    }

    @Test(description = "A test case should be deleted")
    public void deleteCase() {
        loginPage
                .openLoginPage()
                .isPageOpened()
                .login(PropertyReader.getProperty("user"), PropertyReader.getProperty("password"));
        projectsPage
                .waitTillOpened();
        testCasePage.openTestPage();
        testCasePage.createNewTestCase(newCase);
        testCasePage.clickOnSaveNewCaseButton();
        repositoryPage
                .isPageOpened()
                .verifyIfCaseExist(newCase)
                .clickOnTheCaseName(newCase);
        sleep(2000);
        repositoryPage
                .clickOnTheDeleteButton();
        sleep(2000);
        repositoryPage.confirmDeleting();


    }
}
