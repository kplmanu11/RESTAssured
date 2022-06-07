package parsingComplexJSON;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class Scenario6 {

	@Test
	public void SC6() {

		JsonPath jp = new JsonPath(payload.complexJson());

		int count = jp.getInt("courses.size()");
		int purchasedAmt = jp.getInt("dashboard.purchaseAmount");
		int sum = 0;

		for (int i = 0; i < count; i++) {

			int price = jp.getInt("courses[" + i + "].price");
			int copies = jp.getInt("courses[" + i + "].copies");
			
			int priceCopy = price*copies;
			
			sum = sum+priceCopy;
			
		}
		System.out.println(sum);
		System.out.println(purchasedAmt);
		Assert.assertEquals(sum, purchasedAmt);

	}

}
