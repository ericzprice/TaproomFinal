 package taproom.model;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataBase {
	
	static List<Beer> beersList1;
	static List<Beer> beersList2;
	static List<Beer> beersList3;
	
	static List<Location> locationsList;
	static List<Tap> tapsList;
	static List<Order> orders;
	static
	{
		
		Location location1=new Location(null, 0, "taproom1");
		Location location2=new Location(null, 0, "taproom2");
		Location location3=new Location(null, 0, "taproom3");
		Beer saison, stout, ipa, consoleLager;
		
		saison=new Beer("Sass Saison","Notes of tangerine, lemon, ginger and black pepper",5.0,10.0,35,8,6,16,1984,location1);
		stout=new Beer("Java Stout","An English stout with heavily roasted malts that characterize this darkest of traditional brews, with an addition of dark chocolate covered espresso beans.",4.0,7.0,40,5,6,16,1984,location1);
		ipa=new Beer("Geek IPA","A New England IPA with modest bitterness and intense hop character.",4.0,7.0,70,8,6,16,1984,location1);
		consoleLager=new Beer("Console.Lager","This object is a special European dark lager to console your taste buds.",3.0,6.0,42,4,6,16,1984,location1);
		
		Beer saison2=new Beer("Sass Saison","Notes of tangerine, lemon, ginger and black pepper",5.0,10.0,35,8,6,16,1984,location2);
		Beer stout2=new Beer("Java Stout","An English stout with heavily roasted malts that characterize this darkest of traditional brews, with an addition of dark chocolate covered espresso beans.",4.0,7.0,40,5,6,16,1984,location2);
		Beer ipa2=new Beer("Geek IPA","A New England IPA with modest bitterness and intense hop character.",4.0,7.0,70,8,6,16,1984,location2);
		Beer consoleLager2=new Beer("Console.Lager","This object is a special European dark lager to console your taste buds.",3.0,6.0,42,4,6,16,1984,location2);
		
		Beer saison3=new Beer("Sass Saison","Notes of tangerine, lemon, ginger and black pepper",5.0,10.0,35,8,6,16,1984,location3);
		Beer stout3=new Beer("Java Stout","An English stout with heavily roasted malts that characterize this darkest of traditional brews, with an addition of dark chocolate covered espresso beans.",4.0,7.0,40,5,6,16,1984,location3);
		Beer ipa3=new Beer("Geek IPA","A New England IPA with modest bitterness and intense hop character.",7.0,8.0,70,8,6,16,1984,location3);
		Beer consoleLager3=new Beer("Console.Lager","This object is a special European dark lager to console your taste buds.",3.0,6.0,42,4,6,16,1984,location3);
		
		beersList1=new ArrayList<>();
		beersList1.add(saison);
		beersList1.add(stout);
		beersList1.add(ipa);
		beersList1.add(consoleLager);
		
		locationsList=new ArrayList<>();
		
		locationsList.add(location1);
		locationsList.add(location2);
		locationsList.add(location3);
		
		locationsList.get(0).setListOfBeers(beersList1);
		
		beersList2=new ArrayList<>();
		beersList2.add(saison2);
		beersList2.add(stout2);
		beersList2.add(ipa2);
		beersList2.add(consoleLager2);
		
		locationsList.get(1).setListOfBeers(beersList2);
		
		beersList3=new ArrayList<>();
		beersList3.add(saison3);
		beersList3.add(stout3);
		beersList3.add(ipa3);
		beersList3.add(consoleLager3);
		
		locationsList.get(2).setListOfBeers(beersList3);
		
		
		
		
		
		
		Tap tap1=new Tap(true,saison);
		Tap tap2=new Tap(true,stout);
		Tap tap3=new Tap(true,ipa);
		Tap tap4=new Tap(true,consoleLager);
		tapsList=new ArrayList<>();
		tapsList.add(tap1);
		tapsList.add(tap2);
		tapsList.add(tap3);
		tapsList.add(tap4);
		
		locationsList.get(0).setTaps(tapsList);
		
		Tap tap5=new Tap(true,saison2);
		Tap tap6=new Tap(true,stout2);
		Tap tap7=new Tap(true,ipa2);
		Tap tap8=new Tap(true,consoleLager2);
		List<Tap> tapsList2=new ArrayList<>();
		tapsList.add(tap5);
		tapsList.add(tap6);
		tapsList.add(tap7);
		tapsList.add(tap8);
		
		locationsList.get(0).setTaps(tapsList2);
		
		Tap tap9=new Tap(true,saison3);
		Tap tap10=new Tap(true,stout3);
		Tap tap11=new Tap(true,ipa3);
		Tap tap12=new Tap(true,consoleLager3);
		List<Tap> tapsList3=new ArrayList<>();
		tapsList.add(tap9);
		tapsList.add(tap10);
		tapsList.add(tap11);
		tapsList.add(tap12);
		
		locationsList.get(0).setTaps(tapsList3);
		orders=new ArrayList<>();
		System.out.println("hello from static block");
		
	}

	public static List<Beer> getBeersList(String location) {
		switch(location)
		{
		case "taproom1":
			return beersList1;

		case "taproom2":
			return beersList2;
		
		case "taproom3":
			return beersList3;
		
			
				
		}
		return null;
	}

	public static void setBeersList(List<Beer> beersList) {
		InMemoryDataBase.beersList1 = beersList;
	}

	public static List<Beer> getBeersList2() {
		return beersList2;
	}

	public static void setBeersList2(List<Beer> beersList2) {
		InMemoryDataBase.beersList2 = beersList2;
	}

	public static List<Beer> getBeersList3() {
		return beersList3;
	}

	public static void setBeersList3(List<Beer> beersList3) {
		InMemoryDataBase.beersList3 = beersList3;
	}

	public static List<Location> getLocationsList() {
		return locationsList;
	}

	public static void setLocationsList(List<Location> locationsList) {
		InMemoryDataBase.locationsList = locationsList;
	}

	public static List<Tap> getTapsList() {
		return tapsList;
	}

	public static void setTapsList(List<Tap> tapsList) {
		InMemoryDataBase.tapsList = tapsList;
	}

	public static List<Beer> getBeersList1() {
		return beersList1;
	}

	public static void setBeersList1(List<Beer> beersList1) {
		InMemoryDataBase.beersList1 = beersList1;
	}

	public static List<Order> getOrders() {
		return orders;
	}

	public static void setOrders(List<Order> orders) {
		InMemoryDataBase.orders = orders;
	}
	
	

}