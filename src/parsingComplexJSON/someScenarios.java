package parsingComplexJSON;

import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class someScenarios {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath jp = new JsonPath(payload.complexJson());

		// 1. Print No of courses returned by API
		int count = jp.getInt("courses.size()");
		System.out.println("Total Count of the Course: " + count);

		// 2.Print Purchase Amount
		int purchaseAmt = jp.getInt("dashboard.purchaseAmount");
		System.out.println("Total Purchase amount: " + purchaseAmt);

		// 3. Print Title of the first course
		String title = jp.getString("courses[0].title");
		System.out.println("Title of the first course: " + title);

		// 4. Print All course titles and their respective Prices
		for (int i = 0; i < count; i++) {
			String course_title = jp.get("courses[" + i + "].title");
			int price = jp.getInt("courses[" + i + "].price");
			System.out.println(course_title + " : " + price);
		}

		// 5. Print no of copies sold by RPA Course
		for (int i = 0; i < count; i++) {
			String course_title = jp.get("courses[" + i + "].title");

			if (course_title.equalsIgnoreCase("RPA")) {
				int copies = jp.getInt("courses[" + i + "].copies");
				System.out.println("Copies of RPA : " + copies);
				break;
			}
		}

		// 6. Verify if Sum of all Course prices matches with Purchase Amount

		int sum = 0;
		for (int i = 0; i < count; i++) {
			
			int price = jp.getInt("courses[" + i + "].price");
			int copies = jp.getInt("courses[" + i + "].copies");

			int mul = price * copies;
			sum = sum + mul;

		
			if (sum == purchaseAmt) {
				System.out.println("Sum of all the course equals to the purchase amount");
			}

		}

	}

}
