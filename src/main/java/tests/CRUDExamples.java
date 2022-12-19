package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;



/**
 * CRUD
 * C = Create = POST
 * R = Read = GET
 * U = Update = PUT/PATCH
 * D = Delete = DELETE
 *
 */

public class CRUDExamples {
	
	JSONObject body;
	String id;
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://keytodorestapi.herokuapp.com/";
		body = new JSONObject();
		
		Faker fake =  new Faker();
		body.put("title", fake.cat().name());
		body.put("body", fake.chuckNorris().fact());
		
	}
	
	
	@Test(priority = 1)
	public void postATodoMessageTest() {
		
		given().
			contentType(ContentType.JSON).
			body(body.toJSONString()).	
		when().
			post("api/save").
		then().
			statusCode(200).
			body("info", equalTo("Todo saved! Nice job!")).
			body("id", anything()).
			log().all();

	}
	
	@Test(priority=2)
	public void getAllTotdos() {
		
		Response response = given().
				get("api").
				then().
				statusCode(200).
				extract().
				response();
				
		System.out.println("ID :" +response.jsonPath().getString("_id[1]"));
		id = response.jsonPath().getString("_id[1]");
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(priority=3)
	public void deleteTodo() {
		given().
		delete("api/delete/"+id).
		then().
		statusCode(200);

	}
	

}
