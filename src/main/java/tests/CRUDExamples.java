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
	
	JSONObject body, body2;
	String id;
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://keytodorestapi.herokuapp.com/";
		body = new JSONObject();
		
		//am pus in interiorul jsonului cele 2 campuri de care am nevoie
		Faker fake = new Faker();
		body.put("title", fake.cat().name());
		body.put("body", fake.chuckNorris().fact());
		
		body2 = new JSONObject();

		body2.put("title", fake.cat().name());
		body2.put("body", fake.chuckNorris().fact());
		
	}
	
	@Test(priority=1)
	public void postATodoMessageTest() {
		
		Response obj = given().
			contentType(ContentType.JSON).
			body(body.toJSONString()).
			
			when().
				post("api/save").
			then().
				statusCode(200).
				body("info",equalTo("Todo saved! Nice job!")).
				body("id",anything()).
				log().all().  //logam in interiorul consolei raspunsul intors
				extract().response();
		id=obj.jsonPath().getString("id");
	}
	
	@Test(priority=2)
	public void getAllTodos() {
		
		Response response = given().
				get("api/"+id). 
				then().
				statusCode(200). 
				extract().
				response();
		
		System.out.println(response.jsonPath().getString("_id"));
		System.out.println(response.asPrettyString());
	}
	
	@Test(priority=3)
	public void updateTodo() {
		
		Response response=given(). 
				body(body2.toJSONString()).
				when(). 
					put("api/todos/" + id). 
				then(). 
				extract().response();
		
		System.out.println(response.asPrettyString());
		System.out.println(body2.toJSONString());
	}

}
