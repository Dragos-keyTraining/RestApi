package tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent;
import utils.BaseComponent2;

public class JsonPathExample extends BaseComponent2{
	
	
	@Test
	public void jsonPathExamples() {
		
		Response result = doGetAllRequest();
		
		System.out.println(result.asString());
		assertTrue(result.asString().contains("Favian11"));
		
		//JsonPath
		JsonPath jsonPath = result.jsonPath();
		//citeste size-ul obiectului array
		System.out.println(jsonPath.getString("users.size()"));
		//read index based
		System.out.println(jsonPath.getString("users[0]"));
		//get filed from object
		System.out.println(jsonPath.getString("users[0].name"));
		//get all values
		System.out.println(jsonPath.getString("users.name"));
		System.out.println(jsonPath.getString("users.email"));
		System.out.println(jsonPath.getString("users._id"));
		System.out.println("Gender : -->" + jsonPath.getString("users.gender"));
		
		//condition based
		List<String> allMales = jsonPath.getList("users.findAll{it.gender == 'm'}.gender");
		System.out.println(allMales);
		
		List<String> allFemales = jsonPath.getList("users.findAll{it.gender == 'f'}.gender");
		System.out.println(allFemales);
		
		List<String> allBobo = jsonPath.getList("users.findAll{it.name == 'Bobo'}.name");
		System.out.println(allBobo);
		
		//And condition
		String user = jsonPath.getString("users.find{it.name == 'Bobo' & it.age == 12}.email");
		System.out.println(user);
		
		String user2 = jsonPath.getString("users.find{it.name == 'Bobo' & it.age > 14}.email");
		System.out.println(user2);
		
		//OR condition
		
		List<String> usersNamed = jsonPath.getList("users.findAll{it.name=='Bobo' | it.name=='Kathey'}.name");
		System.out.println(usersNamed);

		List<String> ageLessThan = jsonPath.getList("users.findAll{it.age <45}._id");
		System.out.println(ageLessThan);
		
	}

}
