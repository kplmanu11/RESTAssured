package jiraAPIAutomation;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://localhost:8080";

		// login the API

		// to parse the json response
		// other way is by using the JSONPath class
		SessionFilter session = new SessionFilter();
		given().header("Content-Type", "application/json")
				.body("{ \"username\": \"myuser\", \"password\": \"mypassword\" }").filter(session).when()
				.post("/rest/auth/1/session").then();
		
		String expectedMessage = "Hi, How are you??";

		// Added the comment in the jira ticket
		String addResponse = given().relaxedHTTPSValidation().log().all().pathParam("key", "10101").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \""+expectedMessage+"\",\r\n"
						+ "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n"
						+ "        \"value\": \"Administrators\"\r\n" + "    }\r\n" + "}")
				.filter(session).when().post("rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201).extract().response().asString();

		JsonPath jsp = new JsonPath(addResponse);
		String commentId = jsp.getString("id");
		System.out.print(commentId);
		// add attachment to the jira ticket
		// url -D- -u admin:admin -X POST -H "X-Atlassian-Token: nocheck" -F
		// "file=@myfile.txt" http://myhost/rest/api/2/issue/TEST-123/attachments
		given().pathParam("key", "10101").header("X-Atlassian-Token", "nocheck").filter(session)
				.header("Content-Type", "multipart/form-data").multiPart("file", new File("testfile.txt")).when()
				.post("/rest/api/2/issue/{key}/attachments").then().assertThat().statusCode(200);

		// getIssue
		// and limiting the fields using the query parameters
		String getIssue = given().pathParam("key", "10101").queryParam("fields", "comment").filter(session).when()
				.get("/rest/api/2/issue/{key}").then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js1 = new JsonPath(getIssue);
		int commentCount = js1.getInt("fields.comment.comments.size()");
		for(int i = 0; i<commentCount;i++) {
			String commentID = js1.get("fields.comment.comments["+i+"].id").toString();
			if(commentID.equalsIgnoreCase(commentId)) {
				String commentBody = js1.get("field.comment.comments["+i+"].body").toString();
				
				Assert.assertEquals(commentBody, expectedMessage);
				System.out.println(commentBody);
			}
		}
		System.out.println(getIssue);
	}

}
