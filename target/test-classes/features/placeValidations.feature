Feature: Validating Place API's

Scenario Outline: Verify if Place is being 	Successfully added using AddPlaceAPI
		Given Add Place Payload with "<name>" "<language>" "<address>"
		When user calls "AddPlaceAPI" with POST http request
		Then the API Call got success with status code of 200
		And "status" response body is "OK"
		And "scope" response body is "APP"
		
Examples: 
	| name 					 | language | address 				  |
	| Sa aming bahay | English  | sa Binondo 			  |
	| Diwata Pares   | Bisaya   | sa gilid ng pasay |