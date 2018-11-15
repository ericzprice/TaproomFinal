package taproom.model.service;

import java.util.List;

import taproom.model.Location;

public interface LocationService {

	List<Location> getAllLocations();
	boolean addLocation(Location location);
	boolean removeLocation(Location location);
	Location getLocationByName(String locationName);
}

//implements 
//the Beer entities will be persisted into corresponding beer table in the database
//A simple DAO interface to handle the database operation required to manipulate the beer entity 
//like the above interface we saw 