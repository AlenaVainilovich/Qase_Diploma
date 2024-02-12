package dto;

import com.github.javafaker.Faker;

public class SuiteFactory {
    Faker faker = new Faker();
    private String title = faker.lordOfTheRings().character();
    private String description = faker.gameOfThrones().quote();
    private String preconditions = faker.harryPotter().quote();

    public Suite newSuite() {
        return Suite.builder()
                .title(title)
                .description(description)
                .preconditions(preconditions)
                .build();
    }

    public Suite updatedSuite() {
        return Suite.builder()
                .title(title)
                .description(description)
                .preconditions(preconditions)
                .build();
    }
}
