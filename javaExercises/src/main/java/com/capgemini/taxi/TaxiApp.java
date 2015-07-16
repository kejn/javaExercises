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
	}

	/**
	 * Fills the list with {@link #taxiToDisplay} number of Taxi. See
	 * {@link #putInTaxiList(Taxi)} method for more details.
	 */
	private void determineAvailableTaxiInArea() {
		for (Taxi taxi : city.taxiList) {
			if (!taxi.isDispatched() && user.distance(taxi) <= radiusKm) {
				putInTaxiList(taxi);
			}
		}
	}

	/**
	 * @return Ready to print List of nearest Taxi in the area.
	 */
	public List<String> availableTaxi() {
		determineAvailableTaxiInArea();
		List<String> taxiList = new ArrayList<String>();

		for (int i = 0; i < taxiInArea.size(); ++i) {
			String newLine = String.format("%s\nDistance: %3.0f meters\n", taxiInArea.get(i).toString(),
					user.distance(taxiInArea.get(i)) * 1000);
			taxiList.add(newLine);
		}
		return taxiList;
	}

	/**
	 * Insert taxi in list ordering them by distance to {@link #user}. If the
	 * list is full, another <b>taxi</b> can be added to list only if its
	 * distance to user is smaller than the farthest Taxi in the list.
	 * 
	 * @param taxi
	 */
	private void putInTaxiList(Taxi taxi) {
		if (taxiListIsFull()) {
			if (taxiCanBeAdded(taxi)) {
				taxiInArea.remove(taxiToDisplay - 1);
				putInTaxiList(taxi);
			}
		} else {
			int i = 0;
			for (; i < taxiInArea.size(); ++i) {
				if (taxiInArea.get(i).distance(user) >= taxi.distance(user)) {
					break;
				}
			}
			taxiInArea.add(i, taxi);
		}
	}

	private boolean taxiListIsFull() {
		return taxiInArea.size() == taxiToDisplay;
	}

	/**
	 * @return <b>true</b> if <b>taxi</b> distance to {@link #user} is smaller
	 *         than the farthest Taxi in the list.
	 */
	private boolean taxiCanBeAdded(Taxi taxi) {
		return taxi.distance(user) < lastTaxiInTheList().distance(user);
	}

	private Taxi lastTaxiInTheList() {
		return taxiInArea.get(taxiInArea.size() - 1);
	}
}