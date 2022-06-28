package serializationAndDesireliazationPOJO.DeserialzeOnly;

import io.restassured.RestAssured;


import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class DeaseriliazeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// Serialize
		// Pojo class object is created and send it to the body

		PojoClass pc = new PojoClass();
		pc.setAccuracy(50);
		pc.setName("ram");
		pc.setPhone_number("111-222-333");
		pc.setAddress("USA");
		pc.setWebsite("https://www.google.com");
		pc.setLanguage("French_IN");

		List<String> myTypes = new ArrayList<String>();
		myTypes.add("shoe-park");
		myTypes.add("shop");
		pc.setTypes(myTypes);

		Direction d = new Direction();
		d.setLat(-31.3213);
		d.setLng(324.323);
		pc.setLocation(d);

		 String response = given().queryParam("key",
		"qaclick123").body(pc).when().post("/maps/api/place/add/json").then().assertThat()
		 .statusCode(200).extract().response().asString();

	
	}

}
