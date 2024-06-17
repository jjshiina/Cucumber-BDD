package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deletePlace")
	public void beforeScenario() throws IOException {
		
		stepDefinitions stepDef = new stepDefinitions();
		
		if(stepDefinitions.place_Id==null) 
		{		
		stepDef.add_place_payload_with("Raul", "Ilonggo", "Marawi");
		stepDef.user_calls_with_http_request("AddPlaceAPI", "POST");
		stepDef.verify_place_id_created_maps_to_using("Raul", "getPlaceAPI");
		}
	}
}
