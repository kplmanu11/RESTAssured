package handlingGoogleFacebookOauth;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class oAuthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		//Using the Authenticatio code granttype
		
		//get authorization code 
		//authorization server URL
		//this is obtained from the browser so writing the selenium script for this
		//this was removed by google so cannot be automated for the authentication part by writing the selenium script
/*		System.setProperty("webdriver.edge.driver", "D:\\Selenium-Java\\drivers\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&flowName=GeneralOAuthFlow");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("srinath19830");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("password");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		String url = driver.getCurrentUrl(); */
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
		String response = given().queryParam("access_token", accTkn).when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();

		System.out.println(response);
	}

}
