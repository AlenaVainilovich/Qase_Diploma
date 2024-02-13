# Check list for: https://app.qase.io/

#### Descriptions:

✅ - test case **is automated**

❌ - test case **is not automated**

## **E2E tests:**

### Log in page:

- Successful Log in ✅
- Log in with wrong email ✅

### Projects page:

- Create a project ✅
- Check that the 'Project code' field has validation: 'The code may not be greater than 10 characters.' ✅
- Check that the 'Project code' field has validation: 'The code must be at least 2 characters.' ✅

### Project settings page:

- Update the data of the current project ✅
- Delete a project ✅

### Test case page:

- Create a test case ✅
- Delete a test case ✅

## **API tests:**

### Log in page:

- Log in with wrong password ❌
- Log in with empty email ❌
- Log in with empty password ❌

### Projects page:

#### 'GET', Get All Projects:

1) 200 code. Get a list of all projects. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 429 code. Too Many Requests. ❌

#### 'POST', Create new project:

1) 200 code. Get a result of project creation. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 429 code. Too Many Requests. ❌

#### 'GET', Get Project by code:

1) 200 code. Get the project. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 429 code. Too Many Requests. ❌

#### 'DELETE', Delete Project by code:

1) 200 code. Get a result of project removal. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 429 code. Too Many Requests. ❌

#### 'POST', Grant access to project by code:

1) 200 code. Get a result of operation. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 422 code. Unprocessable Entity.❌
7) 429 code. Too Many Requests. ❌

#### 'DELETE', Revoke access to project by code

1) 200 code. Get a result of operation. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 422 code. Unprocessable Entity.❌
7) 429 code. Too Many Requests. ❌

### Test case page:

#### 'GET', Get all test cases:

1) 200 code. Get a list of all cases. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 429 code. Too Many Requests. ❌

#### 'POST', Create a new test case:

1) 200 code. Get a result of case creation. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 422 code. Unprocessable Entity.❌
7) 429 code. Too Many Requests. ❌

#### 'GET', Get a specific test case:

1) 200 code. Get a specific test case. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 422 code. Unprocessable Entity.❌
7) 429 code. Too Many Requests. ❌

#### 'DELETE', Delete test case:

1) 200 code. Get a result of operation. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 422 code. Unprocessable Entity.❌
7) 429 code. Too Many Requests. ❌

#### 'PATCH', Update test case:

1) 200 code. Get a result of operation. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 422 code. Unprocessable Entity.❌
7) 429 code. Too Many Requests. ❌

#### 'POST', Create test cases in bulk:

1) 200 code. Get a list of IDs of the created cases. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 422 code. Unprocessable Entity.❌
7) 429 code. Too Many Requests. ❌

#### 'POST', Attach the external issues to the test cases:

1) 200 code. OK. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 422 code. Unprocessable Entity.❌
7) 429 code. Too Many Requests. ❌

#### 'POST', Detach the external issues from the test cases:

1) 200 code. OK. ❌
2) 400 code. Bad Request. ❌
3) 401 code. Unauthorized. ❌
4) 403 code. Forbidden. ❌
5) 404 code. Not Found. ❌
6) 422 code. Unprocessable Entity.❌
7) 429 code. Too Many Requests. ❌
