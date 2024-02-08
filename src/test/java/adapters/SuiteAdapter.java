package adapters;

import dto.Suite;

public class SuiteAdapter extends BaseAdapter {
    public void create(String code, Suite suite) {
        request.
                body(suite).
                when().
                post(BASE_API_URL + "suite/" + code).
                then().
                log().body().
                statusCode(200);
    }

    public void delete(String code, Integer id) {
        request.
                when().
                delete(BASE_API_URL + "suite/" + code + "/" + id).
                then().
                log().all().
                statusCode(200);
    }
}
