import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;

public class e2eScenario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// POST Automation
		String responeAPI = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").body(payload.AddPayload()).when()
				.post("maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();

		System.out.println(responeAPI);
		// Parsing the json to get the place_id
		//JsonPath jp = new JsonPath(responeAPI);
		JsonPath jp = ResuableComponent.JsonParseMethod(responeAPI);
		String placeID = jp.getString("place_id");

		// PUT Automation

		// Update address
		String newAddress = "TEST Address,USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeID + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// GET Automation

		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID).when()
				.get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		//JsonPath jpp = new JsonPath(getResponse);
		JsonPath jpp = ResuableComponent.JsonParseMethod(getResponse);
		String actualAddress = jpp.getString("address");
		Assert.assertEquals(actualAddress, newAddress);
	}

}
