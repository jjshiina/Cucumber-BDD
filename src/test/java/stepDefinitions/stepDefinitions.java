package stepDefinitions;

import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinitions extends Utils{
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_Id;
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		resspec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		res = given().spec(requestSpecs()).body(data.AddPlacePayload(name, language, address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		//constructor will be called with value of resource
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		{
		response = res.when().post(resourceAPI.getResource())
				.then().spec(resspec).extract().response();
		}
		else if(method.equalsIgnoreCase("GET")) 
		{
		response = res.when().get(resourceAPI.getResource())
				.then().spec(resspec).extract().response();	
		}
		
	}
	@Then("the API Call got success with status code of {int}")
	public void the_api_call_got_success_with_status_code_of(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
		
		
	}
	@Then("{string} response body is {string}")
	public void response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
    
	    assertEquals(getJsonPath(response, keyValue).toString(), expectedValue);    
	    
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_Id = getJsonPath(response, "place_id");
	    res = given().spec(requestSpecs()).queryParam("place_id", place_Id);
	    
	    user_calls_with_http_request(resource, "GET");
	    
	    String actualname = getJsonPath(response, "name");
	    
	    assertEquals(actualname, name);
	    
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecs()).body(data.deletePlacePayload(place_Id));
	}

}
