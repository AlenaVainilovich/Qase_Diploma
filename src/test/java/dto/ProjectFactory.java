package dto;

import com.github.javafaker.Faker;

public class ProjectFactory {
    Faker faker = new Faker();
    public String projectName = faker.witcher().character();
    public String projectCode = faker.currency().code();
    public String description = faker.hitchhikersGuideToTheGalaxy().marvinQuote();
    public String updatedProjectName = faker.witcher().character();
    public String updatedProjectCode = faker.currency().code();
    public String updatedDescription = faker.hitchhikersGuideToTheGalaxy().marvinQuote();
    public String shortProjectCode = faker.lorem().characters(1);
    public String longProjectCode = faker.lorem().characters(11);

    public Project newProject() {
        return Project.builder()
                .title(projectName)
                .code(projectCode)
                .description(description)
                .build();
    }

    public Project updatedProject() {

        return Project.builder()
                .title(updatedProjectName)
                .code(updatedProjectCode)
                .description(updatedDescription)
                .build();
    }

    public Project projectWithShortProjectCode() {
        return Project.builder()
                .title(projectName)
                .code(shortProjectCode)
                .description(description)
                .build();
    }

    public Project projectWithLongProjectCode() {
        return Project.builder()
                .title(projectName)
                .code(longProjectCode)
                .description(description)
                .build();
    }

}
