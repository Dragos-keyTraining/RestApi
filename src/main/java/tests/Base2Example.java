package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent;
import utils.BaseComponent2;
import utils.DataBuilder;

public class Base2Example extends BaseComponent2{
	
	
	public String id;
	public String email;
	
	@Test(priority=1)
	public void postUser() {
		
		Response result = doPostRequest(DataBuilder.buildUser().toJSONString(), 201);
		
		System.out.println(result.asPrettyString());
		System.out.println(result.asString());
		id = result.jsonPath().getString("result._id");
		email = result.jsonPath().getString("result.email");
		assertNotNull(result);
		
	}
	
	@Test(priority = 2)
	public void getUser() {
		Response result = doGetOneRequest(id);
		assertEquals(email, result.jsonPath().getString("result.email"));
	}
	

}
