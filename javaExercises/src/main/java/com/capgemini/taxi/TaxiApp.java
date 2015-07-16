package com.capgemini.taxi;

import java.util.ArrayList;
import java.util.List;

public class TaxiApp {
	private List<Taxi> taxiInArea;
	private City city;
	private User user;
	
	private int taxiToDisplay = 10;
	private Double radiusKm = 1.0; 
	
	public TaxiApp(City whichCity, User whoLaunch) {
		taxiInArea = new ArrayList<Taxi>();
		city = whichCity;
		user = whoLaunch;
		determineAvailableTaxiInArea();
	}
	
	public void determineAvailableTaxiInArea() {
		for (Taxi taxi : city.taxiList) {
			if(!taxi.isDispatched() && user.distance(taxi) <= radiusKm) {
				putInTaxiList(taxi);
			}
		}
	}
	
	public void printAvailableTaxi() {
		for (int i = 0; i < taxiInArea.size() && i < taxiToDisplay; ++i) {
			System.out.println(taxiInArea.get(i).toString()
					+ ", distance: "
					+ user.distance(taxiInArea.get(i)) + " km");
		}
	}
	
	/**
	 * Insert taxi in list ordering them by distance to user
	 * @param taxi
	 */
	private void putInTaxiList(Taxi taxi) {
		int i = 0;
		for ( ; i < taxiInArea.size(); ++i) {
			if(taxiInArea.get(i).distance(user) >= taxi.distance(user)){
				break;
			}
		}
		taxiInArea.add(i, taxi);
	}
}
