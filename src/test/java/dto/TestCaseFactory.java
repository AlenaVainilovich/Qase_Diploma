package dto;

import com.github.javafaker.Faker;

public class TestCaseFactory {
    Faker faker = new Faker();
    public String title = faker.harryPotter().character();
    public String description = faker.hitchhikersGuideToTheGalaxy().marvinQuote();
    public String preConditions = faker.beer().name();
    public String postConditions = faker.animal().name();

    public TestCase newCase() {
        return TestCase.builder()
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
    }

    public TestCase editedCase() {
        return TestCase.builder()
                .description(faker.backToTheFuture().quote())
                .severity("Minor")
                .priority("Medium")
                .preconditions(faker.gameOfThrones().house())
                .build();
    }
}
