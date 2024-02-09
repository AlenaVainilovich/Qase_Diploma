package tests;

import adapters.BaseAdapter;
import adapters.ProjectsAdapter;
import adapters.SuiteAdapter;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;
import pages.*;
import tests.base.TestListener;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;


@Listeners(TestListener.class)
public class BaseTest {
    Faker faker = new Faker();
    LoginPage loginPage;
    ProjectsPage projectsPage;
    CreateNewProjectPage createNewProjectPage;
    TestCasePage testCasePage;
    RepositoryPage repositoryPage;
    EditCasePage editCasePage;
    ProjectSettingsPage projectSettingsPage;
    SuitesPage suitesPage;
    BaseAdapter baseAdapter;
    ProjectsAdapter projectsAdapter;
    SuiteAdapter suiteAdapter;
    String user;
    String password;

    @Parameters({"browser"})
    @BeforeMethod(description = "Setup browser")
    public void setup(@Optional("chrome") String browser) {

        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.clickViaJs = false;
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.browserSize = "1920x1080";

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        user = System.getenv().getOrDefault("user", PropertyReader.getProperty("user"));
        password = System.getenv().getOrDefault("password", PropertyReader.getProperty("password"));

        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
        createNewProjectPage = new CreateNewProjectPage();
        testCasePage = new TestCasePage();
        repositoryPage = new RepositoryPage();
        editCasePage = new EditCasePage();
        projectSettingsPage = new ProjectSettingsPage();
        suitesPage = new SuitesPage();
        baseAdapter = new BaseAdapter();
        projectsAdapter = new ProjectsAdapter();
        suiteAdapter = new SuiteAdapter();
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        closeWebDriver();
    }
}
