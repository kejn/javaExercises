package com.capgemini.taxi;

import org.junit.Test;

public class TaxiAppTest {

	@Test
	public void shouldReturnOnlyTaxiInRadiusOf1km() {
		City city = new City();
		User user = new User();
		TaxiApp taxiApp = new TaxiApp(city, user);
		taxiApp.printAvailableTaxi();
	}

}
