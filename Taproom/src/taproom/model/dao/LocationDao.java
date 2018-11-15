package taproom.model.dao;

import java.util.List;


import taproom.model.Location;

public interface LocationDao {
	 List<Location> getAllLocations();
	boolean addLocation(Location location);
	boolean removeLocation(Location location);
	Location getLocationByName(String locationName);
}
