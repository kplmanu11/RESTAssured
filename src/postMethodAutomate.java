import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class postMethodAutomate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//three terms are used for the for the rest assured automation
		//given : all the input details are provided, like query parameters
		//when: submit the api resourses and the httpmethod is provided here
		//then: validate api response, like the status code
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	String responseApI = 	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "").when().post("maps/api/place/add/json").then().assertThat().statusCode(200).
					//adding some more validation for the assertion 
		      body("scope",equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
	
	
	 	System.out.println(responseApI);
	}
	

}
