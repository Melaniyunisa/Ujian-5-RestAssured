import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class UserProfile {
    String baseUrl = "http://localhost:8081/api";

    @Test
    public void testGetUserProfile() {

        String endpoint = baseUrl+"/users/me";
        JSONObject request = new JSONObject();

        String tokenProfile = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzE5NzYyMjQ3LCJleHAiOjE3MTk3NjU4NDd9.ISFqKYZNbOL5FqZcFVf-jll787sqFPerdsyqTcNPDgegwczM5BV1wVdTJAiSY0kMGlYFwXezx4rjJGPKH3i3QA";

        RequestSpecification requestBody = RestAssured.given();
        requestBody.header("Authorization", "Bearer " + tokenProfile);
        requestBody.header("Content-Type", "application/json");

        System.out.println(request.toJSONString());

        Response response = requestBody.get(endpoint);
        Assert.assertEquals(response.getStatusCode(), 200);


        String id = response.getBody().jsonPath().getString("id");
        System.out.println(id);
        String firstName = response.getBody().jsonPath().getString("firstName");
        System.out.println(firstName);
        String lastName = response.getBody().jsonPath().getString("lastName");
        System.out.println(lastName);
        String username = response.getBody().jsonPath().getString("username");
        System.out.println(username);

        Assert.assertEquals(id, "2");
        Assert.assertEquals(firstName, "melani");
        Assert.assertEquals(lastName, "graham");
        Assert.assertEquals(username, "melani");
    }

}
