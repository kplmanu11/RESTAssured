import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import files.payload;

public class PostMethodFromFilesPackage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Payload is used from the files package

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-type", "application/json").body(payload.AddPayload())  
				.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).header("server", "Apache/2.4.41 (Ubuntu)");
				
		
	}

}
