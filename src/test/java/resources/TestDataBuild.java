package resources;

import java.util.ArrayList;
import java.util.List;

import Pojo.Location;
import Pojo.addPlace;

public class TestDataBuild {
	
	public addPlace AddPlacePayload(String name, String language, String address){
		
		addPlace p = new addPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("093312344 imy");
		p.setAddress(address);
		p.setWebsite("www.po**hub.com");
		p.setLanguage(language);
		
		List<String> myList = new ArrayList<String>();
		myList.add("shab*");
		myList.add("tindahan");
		
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-30.444);
		l.setLng(30.4444);
		p.setLocation(l);
		
		return p;
	}
	
	public String deletePlacePayload(String place_id){
		return "{\"place_id\":\"" + place_id + "\"}";
	}

}
