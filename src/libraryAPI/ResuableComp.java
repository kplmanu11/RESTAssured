package libraryAPI;

import io.restassured.path.json.JsonPath;

public class ResuableComp {

	public static JsonPath JsonParseMethod(String response) {
		JsonPath js = new JsonPath(response);
		return js;

	}
}
