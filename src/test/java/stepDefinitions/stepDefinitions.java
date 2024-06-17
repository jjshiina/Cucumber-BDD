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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinitions extends Utils{
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		resspec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		res = given().spec(requestSpecs()).body(data.AddPlacePayload(name, language, address));
	}
	
	@When("user calls {string} with POST http request")
	public void user_calls_with_post_http_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
		response = res.when().post("maps/api/place/add/json")
				.then().spec(resspec).extract().response();
		
	}
	@Then("the API Call got success with status code of {int}")
	public void the_api_call_got_success_with_status_code_of(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
		
		
	}
	@Then("{string} response body is {string}")
	public void response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
	    String resp = response.asString();
	    JsonPath js = new JsonPath(resp);
	    assertEquals(js.get(keyValue).toString(), expectedValue);
	    
	    
	}
}
