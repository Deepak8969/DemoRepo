import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import POJO.Googleplace;
import POJO.location;

public class Serialization {
	
	public static void main(String[] args) {
		
		//creating a JSON using the JAVA POJO classes and then procide it as a input
		//we use only setter method which provide values as we are creating a JSON
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		
		Googleplace obj = new Googleplace();
		obj.setAccuracy(50);
		obj.setAddress("29, side layout, cohen 09");
		obj.setLanguage("French-IN");
		
		obj.setName("Frontline house");
		obj.setPhone_number("(+91) 983 893 3937");
		obj.setWebsite("http://google.com");
		
		ArrayList<String> array = new ArrayList<String>();
		array.add("shoe park");
		array.add("shop");
		array.add("park");
		
		obj.setTypes(array);
		
		location locat = new location();
		locat.setLat(-38);
		locat.setLng(30);
		
		obj.setLocation(locat);
		
		String Response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(obj).
		when().post("maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(Response);

	}

}
