import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;


import files.ReusableM;
import files.payload;






public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//validate if Add place API is Working as expected
		    //given - all input details
		   // when - submit the API
		   // Then- Validate the response
		 RestAssured.baseURI= "https://rahulshettyacademy.com";
		 String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		 .body(payload.AddPlace()).when().post("maps/api/place/add/json")
		 .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
	     .header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		 
		System.out.println(response);
		JsonPath js=new JsonPath(response); //forparsing Json
		String placeId=js.getString("place_id");
		System.out.println(placeId);
		//Update Place
		String newAddress ="Summer Walk Africa";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "").
		when().put("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
		//Get place
	String getPlaceResponse=  given().log().all().queryParam("key","qaclick123")
		.queryParam("place_Id", "placeId")
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	  
          JsonPath Js1= ReusableM.rawToJson(getPlaceResponse);
          String actualAddress= Js1.getString("address");
          System.out.println(actualAddress);
		  Assert.assertEquals(actualAddress, newAddress);
	}

}
