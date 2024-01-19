package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import utils.PropertyReader;
import utils.TestListener;

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
    String user;
    String password;

    @BeforeMethod(description = "Setup browser")
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
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
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        closeWebDriver();
    }
}
