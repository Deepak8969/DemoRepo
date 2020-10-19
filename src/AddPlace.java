import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import resource.payload;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class AddPlace {

	public static void main(String[] args) {
		
	String placeid,address;
	String newAddress = "70 winter walk, USA";
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		System.out.println("Add the new place\n\n\n");
		//Add Place
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(payload.getPayload())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println("Response===>" + response);
		
		JsonPath js = new JsonPath(response);
		placeid = js.getString("place_id");
		
		System.out.println("placeid===>" + placeid);
		
		
		System.out.println("\n\n\n Update the new Address\n\n\n");
		//update Place
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).header("Content-Type", "application/json").body("{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		System.out.println("\n\n\n Get the Updated Address\n\n\n");
		//Get Place
		
		String newAddressResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid)
				.when().get("maps/api/place/get/json")
				.then().log().all().statusCode(200).extract().response().asString();
		
		System.out.println("New Address Response===>" + newAddressResponse);
		
		
		JsonPath js1 = new JsonPath(newAddressResponse);
		address = js1.getString("address");
		
		System.out.println("address===>" + address);

	}

	

}
