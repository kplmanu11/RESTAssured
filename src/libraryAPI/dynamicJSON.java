package libraryAPI;

import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class dynamicJSON {
	
	@Test
	public void addBook() {
		RestAssured.baseURI="http://216.10.245.166";
		
		String resp = given().header("Content-Type","application/json").
		body(payload.Addbook()).
		when()
		.post("/Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ResuableComp.JsonParseMethod(resp);
		String id = js.get("ID");
		System.out.println(id);
			
	}
	

}
