package taproom.model.dao;

import java.util.List;

import taproom.model.Beer;
//naming depends on you 
public interface BeerDao {
//	interface used for multiple inheritance...on Java requires that
	List<Beer> getAllBeers(String location);
	boolean addBeer(Beer beer);
	boolean removeBeer(Beer beer);
	Beer getBeerByName(String beerName);
	boolean updateBeer(Beer beer);

}
