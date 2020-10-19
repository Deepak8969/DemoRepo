import io.restassured.RestAssured;
import resource.payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LibraryAPI {

	@Test(dataProvider = "MobileData")
	public void sendMultipleRequest(String Mobilenumber) throws IOException {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		System.out.println("Add the new place\n\n\n");
		//Add Place
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(payload.getPayload(Mobilenumber))
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println("response ==> "+ response);
		
		
		
	}
	
	@Test
	public void usePayloadThroughFile() throws IOException {
		
		//Extracting Payload from outside
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
				String Secondresponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(new String(Files.readAllBytes(Paths.get("D:\\API Notes\\Payload.JSON"))))
						.when().post("maps/api/place/add/json")
						.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
						
						System.out.println("Secondresponse ==> "+ Secondresponse);
		
	}
	
	@DataProvider(name = "MobileData")
	public  Object[] provideMobileNumber() {
		
		return new Object[] {"1111111111","222222222222","33333333333","444444444444"};
	}

}
