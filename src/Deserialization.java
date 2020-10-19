import static io.restassured.RestAssured.*;

import POJO.AddPlaceAPIResponse;
import io.restassured.RestAssured;
import resource.payload;

public class Deserialization {

	public static void main(String[] args) {
		
		//converting the JSON into java object and parsing the JSON response to get values
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		AddPlaceAPIResponse Response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(payload.getPayload()).
		when().post("maps/api/place/add/json").as(AddPlaceAPIResponse.class);
		
		System.out.println(Response.getId());
		System.out.println(Response.getPlace_id());
		System.out.println(Response.getScope());
		
		

	}

}
