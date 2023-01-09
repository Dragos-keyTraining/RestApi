package utils;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers.*;
import org.hamcrest.core.AnyOf;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseComponent2 {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	
	@BeforeClass
	public static void createRequestSpecification() {
		
		requestSpec =  new RequestSpecBuilder()
				.setBaseUri("https://keytrcrud.herokuapp.com/")
				.setBasePath("api/users")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
	}
	
	@BeforeClass
	public static void createResponseSpecification() {
		
		responseSpec =  new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();	
	}
	
public static Response doPostRequest(String body, int statusCode) {
		
		Response result = 
				given().
					spec(requestSpec).
					body(body).
				when().
					post().
				then().
					statusCode(statusCode).
					extract().response();
			
		return result;
		
	}
	
	public static Response doPutRequest(String id, String body) {
		
		Response result = 
				given().
					spec(requestSpec).
					body(body).
				when().
					put(id).
				then().
					spec(responseSpec).
					extract().response();
			
		return result;
		
	}
	
	
	public static Response doGetOneRequest(String id) {
		
		Response result = 
				given().
					spec(requestSpec).
				when().
					get(id).
				then().
					spec(responseSpec).
					extract().response();
			
		return result;
	}
	
	public static Response doGetAllRequest() {
		
		Response result = 
				given().
					spec(requestSpec).
				when().
					get().
				then().
					spec(responseSpec).
					extract().response();
			
		return result;
	}
	
	
	public static Response doDeleteRequest(String id) {
		
		Response result = 
				given().
					spec(requestSpec).
				when().
					delete(id).
				then().
					spec(responseSpec).
					extract().response();
			
		return result;
	}
	

}
