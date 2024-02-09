package dto;

import com.github.javafaker.Faker;

public class ProjectFactory {
    Faker faker = new Faker();
    public String projectName = faker.witcher().character();

    public String description = faker.hitchhikersGuideToTheGalaxy().marvinQuote();
    public String updatedProjectName = faker.witcher().character();
    public String updatedProjectCode = faker.currency().code();
    public String updatedDescription = faker.hitchhikersGuideToTheGalaxy().marvinQuote();

    public Project newProject(int codeLength) {
        String projectCode = faker.lorem().characters(codeLength);
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
}
