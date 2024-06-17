Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is being 	Successfully added using AddPlaceAPI
		Given Add Place Payload with "<name>" "<language>" "<address>"
		When user calls "AddPlaceAPI" with "POST" http request
		Then the API Call got success with status code of 200
		And "status" response body is "OK"
		And "scope" response body is "APP"
		And verify place_Id created maps to "<name>" using "getPlaceAPI"
		
Examples: 
	| name 					 | language | address 				  |
	| Sa aming bahay | English  | sa Binondo 			  |
#	| Diwata Pares   | Bisaya   | sa gilid ng pasay |
	
@deletePlace
Scenario: Verify if Delete Place functionality is working

		Given Delete Place Payload
		When user calls "deletePlaceAPI" with "POST" http request
		Then the API Call got success with status code of 200
		And "status" response body is "OK"


		
	
	