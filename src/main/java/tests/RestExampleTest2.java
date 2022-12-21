package tests;

import static org.testng.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import utils.BaseComponent;

public class RestExampleTest2 extends BaseComponent{

	String id;
	
	@Test(priority = 1)
	public void postUser() {
		
		JSONObject body =  new JSONObject();
		Faker faker = new Faker();
		
		String email = faker.internet().emailAddress();
		body.put("name", faker.name().firstName());
		body.put("email",email );
		body.put("age", faker.number().numberBetween(5, 130));
		body.put("gender", "m");
		
		Response respose = doPostRequest("api/users", body.toJSONString(), HttpStatus.SC_CREATED);
		assertEquals(respose.jsonPath().getString("result.email"), email);
		id = respose.jsonPath().getString("result._id");
		
	}
	
	@Test(priority = 2)
	public void getUser() {
		
		Response response = doGetRequest("api/users/", id, 200);
		assertEquals(response.jsonPath().get("result._id"), id);
	}
	@Test(priority = 3)
	public void deleteUser() {
		
		Response response = doDeleteRequest("api/users/", id, 200);
		assertEquals(response.jsonPath().get("result._id"), id);
		assertEquals(response.jsonPath().getString("msg"), "It has been deleted.");
	}
	
}
