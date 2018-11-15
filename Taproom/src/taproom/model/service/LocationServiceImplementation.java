package taproom.model.service;

import java.util.List;

import taproom.model.Location;
import taproom.model.dao.LocationDao;
import taproom.model.dao.LocationDaoImplementation;

public class LocationServiceImplementation implements LocationService{
	LocationDao locationDao=new LocationDaoImplementation();

	@Override
	public List<Location> getAllLocations() {
		return locationDao.getAllLocations();
		
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
