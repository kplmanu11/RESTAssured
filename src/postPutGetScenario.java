import io.restassured.RestAssured;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class postPutGetScenario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String responseAPI = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
						.body(payload.AddPayload()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).
						//adding some more validation for the assertion 
					      body("scope",equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

		System.out.println(responseAPI);
		
		

	}

}
