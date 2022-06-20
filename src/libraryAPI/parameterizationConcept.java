package libraryAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class parameterizationConcept {
	
	//if we gave multiples values then its better to use the data provider of testng
	// and based on that the loop will be running
	@Test(dataProvider = "booksProvider")
	
	public void Parametirization(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		
			String resp=	given().header("Content-Type","application/json")
				.body(payload.AddSpecificBook(isbn,aisle)).
				when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
		
			JsonPath jsp = ResuableComp.JsonParseMethod(resp);
			String getId = jsp.get("ID");
			System.out.println(getId);
	}
	
	@DataProvider(name="booksProvider")
	public Object[][] getData(){
		//creating the multi dimensional array
		return new Object[][]  {{"dasa","212"},{"fsf","321"},{"ewr","321"}};
	}

}
