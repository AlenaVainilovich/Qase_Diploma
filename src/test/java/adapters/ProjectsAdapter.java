package adapters;

import dto.Project;

public class ProjectsAdapter extends BaseAdapter {

    public void create(Project project) {
        request.
                body(project).
        when().
                post(BASE_API_URL + "project").
        then().
                log().body().
                statusCode(200);
    }
}
