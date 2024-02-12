package adapters;

import dto.Project;

public class ProjectsAdapter extends BaseAdapter {

    public void create(Project project) {
        request.
                body(project).
                when().
                post(BASE_API_URL + "project").
                then().
                log().ifValidationFails().
                statusCode(200);
    }

    public void delete(String code) {
        request.
                when().
                delete(BASE_API_URL + "project/" + code).
                then().
                log().ifValidationFails().
                statusCode(200);
    }

}
