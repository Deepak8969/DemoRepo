import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resource.payload;

public class SpecBuilder {

		@Test
		public void NormalAddPlaceMethod() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		System.out.println("Add the new place\n\n\n");
		//Add Place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(payload.getPayload())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)");
		}
		
		@Test
		public void SpecBuilderAddPlaceMethod() {
		
		//Building specbuilder for request and response
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON)
		.addQueryParam("key", "qaclick123").build();
		
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectHeader("Server", "Apache/2.4.18 (Ubuntu)").expectContentType(ContentType.JSON).build();
		
		
		//using the request and response specbuilder

		given().log().all().spec(req).body(payload.getPayload()).when().post("maps/api/place/add/json").then().log().all().spec(res);
		
		}
		
		@Test
		public void SpecBuilderAddPlaceMethodWithSeperateRequestAndResponse() {
		
		//Building specbuilder for request and response
			RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON)
		.addQueryParam("key", "qaclick123").build();
		
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectHeader("Server", "Apache/2.4.18 (Ubuntu)").expectContentType(ContentType.JSON).build();
		

		
		//using the request and response specbuilder
		
		RequestSpecification request = given().log().all().spec(req).body(payload.getPayload());
		
		Response response = request.when().post("maps/api/place/add/json").then().log().all().spec(res).extract().response();
		
		String stringResponse = response.asString();
		
		System.out.println(stringResponse);
		
		}
		
		
		
	

}
