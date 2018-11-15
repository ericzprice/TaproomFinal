package taproom.model.dao;

import java.util.List;

import taproom.model.InMemoryDataBase;
import taproom.model.Location;

public class LocationDaoImplementation implements LocationDao{

	InMemoryDataBase inMemoryDataBase=new InMemoryDataBase();
	@Override
	public List<Location> getAllLocations() {
		return InMemoryDataBase.getLocationsList();
		
	}

	@Override
	public boolean addLocation(Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeLocation(Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Location getLocationByName(String locationName) {
		// TODO Auto-generated method stub
		return null;
	}

}
