package com.capgemini.taxi;

import java.util.ArrayList;
import java.util.List;

public class City {
	public static final Double CITY_AREA = 20.0;
	public static final int MAX_NUMBER_OF_TAXI = 100000;

	public final List<Taxi> taxiList;
	
	public City() {
		taxiList = new ArrayList<Taxi>();
		for (long i = 0; i < MAX_NUMBER_OF_TAXI; ++i) {
			taxiList.add(new Taxi());
		}
	}
	
}
