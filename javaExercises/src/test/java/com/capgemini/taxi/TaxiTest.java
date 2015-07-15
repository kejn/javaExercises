package com.capgemini.taxi;

import static org.junit.Assert.*;

import org.junit.Test;

public class TaxiTest {
	private static int sleepTimeMs = 2 * Position.REFRESH_MILLISECONDS;

	@Test
	public void positionMayChangeInTime() {
		Taxi taxi = new Taxi();
		Position pos = new Position(taxi.getPosition());

		try {
			Thread.sleep(sleepTimeMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(pos, taxi.getPosition());

		new User().dispatch(taxi);
		try {
			Thread.sleep(sleepTimeMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertNotEquals(pos, taxi.getPosition());
	}
	
	@Test
	public void mayBeDispatched() {
		Taxi taxi = new Taxi();
		assertFalse(taxi.isDispatched());
		
		User user = new User();
		user.dispatch(taxi);
		assertTrue(taxi.isDispatched());
	}
	
	@Test
	public void canBeCalledOffOnlyByTheClientWhoDispatchedIt() {
		Taxi taxi = new Taxi();
		User user = new User();
		
		user.dispatch(taxi);

		new User().callOff(taxi);
		assertTrue(taxi.isDispatched());
		
		user.callOff(taxi);
		assertFalse(taxi.isDispatched());
	}
	
//	@Test
//	public void dispatchedTaxiDrivesToClient() {
//		User user = new User();
//		Taxi taxi = new Taxi();
//		
//		user.dispatch(taxi);
//		
//	}

}
