package taproom.model.dao;

import java.util.List;

import taproom.model.Beer;
import taproom.model.InMemoryDataBase;

public class BeerDaoInMemoryImp implements BeerDao{

	@Override
	public List<Beer> getAllBeers(String location) {
		return InMemoryDataBase.getBeersList(location);
		
	}

	@Override
	public boolean addBeer(Beer beer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBeer(Beer beer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Beer getBeerByName(String beerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBeer(Beer beer) {
		
		List<Beer> beers=InMemoryDataBase.getBeersList(beer.getLocation().getName());
		System.out.println("bef rem beers list size"+ beers.size());
		for(int i=0;i<beers.size();i++)
		{
			if(beers.get(i).getStyle().equalsIgnoreCase(beer.getStyle()) && beers.get(i).getLocation().getName().equalsIgnoreCase(beer.getLocation().getName()))
			{
				beer.setABV(beers.get(i).getABV());
				beer.setIBU(beers.get(i).getIBU());
				beer.setPrice1(beers.get(i).getPrice1());
				beer.setPrice2(beers.get(i).getPrice2());
				beer.setTotalOunes(beers.get(i).getTotalOunes()-beer.getTotalOunes());
				if(beer.getTotalOunes()<=0)
			    {
			    	System.out.println(beer.getStyle()+ " is kicked");
			    	beer.setKicked(true);
			    }
				beer.setLocation(beers.get(i).getLocation());
				beer.setSize1(beers.get(i).getSize1());
				beer.setSize2(beers.get(i).getSize2());
				
				beers.remove(beers.get(i));
				System.out.println("afte rem beers list size"+ beers.size());
				beers.add(i,beer);
				System.out.println(beers.get(i).getTotalOunes());
				System.out.println(beers.get(i).isKicked());
				return true;
			}
		}
		return false;
	}

}
