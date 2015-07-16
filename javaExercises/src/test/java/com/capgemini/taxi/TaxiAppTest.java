package com.capgemini.taxi;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TaxiAppTest {

	@Test
	public void shouldReturnOnlyTaxiInRadiusOf1km() {
		City city = new City();
		User user = new User();
		TaxiApp taxiApp = new TaxiApp(city, user);
		
		assertEquals(10, taxiApp.availableTaxi().size());
	}
	
	@Test
	public void taxiListIsUpdated_5SecondsTest() {
		City city = new City();
		User user = new User();
		TaxiApp taxiApp = new TaxiApp(city, user);
		List<String> list = taxiApp.availableTaxi();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(list, taxiApp.availableTaxi());
	}

}
