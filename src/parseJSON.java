

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import resource.payload;

public class parseJSON {

	@Test
	public void parsingTheJSONFile() { 
		
		String title;
		int price,copies, totalcopies = 0, totalprice=0;
		
		JsonPath Js= new JsonPath(payload.CoursePrice());
		
		int numberofcourses = Js.get("courses.size()");
		System.out.println("number of courses==>" + numberofcourses);
		
		int purchaseamount = Js.getInt("dashboard.purchaseAmount");
		System.out.println("purchase amount==>" + purchaseamount);
		 
		String titlefirstcourse = Js.getString("courses[0].title");
		System.out.println("title first course==>" + titlefirstcourse);
		
		for(int i=0;i<numberofcourses;i++) {
			title = Js.getString("courses["+i+"].title");
			price = Js.getInt("courses["+i+"].price");
			
			System.out.println("Title => " + title + " Price => " + price);
		}
		
		
		for(int i=0;i<numberofcourses;i++) {
			copies = Js.getInt("courses["+i+"].copies");
			totalcopies += copies;
		
		}
		
		System.out.println("Total Copies => " + totalcopies);
		
		
		for(int i=0;i<numberofcourses;i++) {
			price = Js.getInt("courses["+i+"].price");
			copies = Js.getInt("courses["+i+"].copies");
			
			totalprice += copies*price;
		}
		
		System.out.println("Total Price => " + totalprice);
	
		Assert.assertEquals(totalprice, purchaseamount);
		

	}

}
