package serializationAndDesireliazationPOJO;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class DeserialzeTheJSONResponse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";
		String partialCode = url.split("code=")[1];
		String actualCode = partialCode.split("&scope")[0];
		System.out.println(actualCode);
		//parse the url and split it to get the code
		
		

		// to obtain the accesstoken
		//Get the accesstokenrequest
		//access token url
		String accessTokenResponse = given().urlEncodingEnabled(false).queryParams("code", actualCode)  //urlencoding set to false because the code may contains the special characters and those are converted to the number and the code is invalid
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath jsp = new JsonPath(accessTokenResponse);
		String accTkn = jsp.getString("access-token");
		

		//using the access token 
		//Insted of return the string its done using the class object
		//Deserialization
//		String response = given().queryParam("access_token", accTkn).when().log().all()
				//.get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		MainJsonPayload gc = given().queryParam("access_token", accTkn).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(MainJsonPayload.class);
		//afte the class object is created all the methods are accessible
		gc.getLinkedin();
		gc.getInstructor();

		
		//get the specific course and find the title and find the price
		// there might be more index so iterate the loop 
		
		List<Api> apiCourses = gc.getCourse().getApi();
		for(int i = 0; i<apiCourses.size();i++) {
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SOAP UI Testing")) {
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		
		//find all the title of the courses of webautomation
		//Excepted courses
		//deserialiaze-- converting to the java objects and using the getters method
		String[] courseTitle = {"Selenium","Cypress","Protractor"};
		
		ArrayList<String> a = new ArrayList<String>();
		List<WebAutomation> webAutomtionCourseTitle = gc.getCourse().getWebAutomation();
		for(int i= 0; i<webAutomtionCourseTitle.size();i++) {
			//System.out.println(webAutomtionCourseTitle.get(i).getCourseTitle());
			a.add(webAutomtionCourseTitle.get(i).getCourseTitle());
		}
		List<String> expectedCourseTitle = Arrays.asList(courseTitle);
		Assert.assertTrue(a.equals(expectedCourseTitle));
		
		
	}

}
