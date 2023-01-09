package utils;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder {

	public static JSONObject buildUser() {
		
		JSONObject bodyBuilder =  new JSONObject();
		Faker faker = new Faker();
		
		String email = faker.internet().emailAddress();
		bodyBuilder.put("name", faker.name().firstName());
		bodyBuilder.put("email",email );
		bodyBuilder.put("age", faker.number().numberBetween(5, 130));
		bodyBuilder.put("gender", "m");
		
		return bodyBuilder;
	}
	
	
}
