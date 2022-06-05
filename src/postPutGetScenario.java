import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;

public class postPutGetScenario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// POST automation
		String responseAPI = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").body(payload.AddPayload()).when()
				.post("maps/api/place/add/json").then().assertThat().statusCode(200).
				// adding some more validation for the assertion
				body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response()
				.asString();

		System.out.println(responseAPI);

		// Parsing json file
		JsonPath jp = new JsonPath(responseAPI);

		String placeID = jp.getString("place_id");
		System.out.println("placeID :" + placeID);

		// PUT Automation
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeID + "\",\r\n" + "\"address\":\"TEST Address,USA\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// GET Automation

		String GETResponsemsg = given().queryParam("key", "qaclick123").queryParam("place_id", placeID)
				.header("Content-Type", "application/json").when().get("maps/api/place/get/json").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath jj = new JsonPath(GETResponsemsg);
		String address = jj.getString("address");
		System.out.println(address);
		Assert.assertEquals(address, "TEST Address,USA");

	}

}
