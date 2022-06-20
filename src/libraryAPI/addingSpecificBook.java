package libraryAPI;

import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class addingSpecificBook {

	@Test
	public void AddingSpecificBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		
		String resp = given().header("Content-Type","application-json").
		body(payload.AddSpecificBook("BBN","2131")).when().
		post("/Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ResuableComp.JsonParseMethod(resp);
		String getID = js.get("ID");
		
		System.out.println(getID);
		
	
	}

}
