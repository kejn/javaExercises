package com.capgemini.taxi;

import java.util.Comparator;

public class TaxiCompartor implements Comparator<Taxi>{
	private Position user;

	public TaxiCompartor(Position userToCompare) {
		user = userToCompare;
	}

	public int compare(Taxi o1, Taxi o2) {
		return (int) (user.distance(o1) - user.distance(o2));
	}
}
