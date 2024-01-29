package tests;

import com.codeborne.selenide.Condition;
import dto.TestCase;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.*;


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
            .layer("E2E")
            .isFlaky("Yes")
            .behavior("Positive")
            .automationStatus("Automated")
            .preConditions(preConditions)
            .postConditions(postConditions)
            .build();

    TestCase editedCase = TestCase.builder()
            .description(faker.backToTheFuture().quote())
            .severity("Minor")
            .priority("Medium")
            .preConditions(faker.gameOfThrones().house())
            .build();

    @Test
    public void createEditDeleteNewCase() {
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

    @Test
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
