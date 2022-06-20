package libraryAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class readingStaticJSONfromExternalLocation {

	
	//most of the time the json paylod is static i.e doesnot change frequently
	//so in this case its saved in the some location and access through the Path library
	@Test
	public void readStaticJSONFile() throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";
		String resp = given().header("Content-Type", "application/json").
				body(GenerateStringFromResource("C:\\users\\kapil.neupane\\documents.addbookdetails.json")).
		when().post("/Library/Addbook.php").
		then().assertThat().statusCode(200)
		.extract().response().asString();
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
