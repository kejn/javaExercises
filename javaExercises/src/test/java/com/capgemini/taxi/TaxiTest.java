package com.capgemini.taxi;

import static org.junit.Assert.*;

import org.junit.Test;

public class TaxiTest {
	private static int sleepTimeMs = 1000;

	@Test
	public synchronized void positionMayChangeInTime() {
		Taxi taxi = new Taxi();
		Position pos = new Position(taxi.getPosition());

		try {
			Thread.sleep(sleepTimeMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(pos, taxi.getPosition());

		new User(taxi.getX()+1,taxi.getY()).dispatch(taxi);
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
		User user2 = new User();
		
		user.dispatch(taxi);
		user2.callOff(taxi);
		assertTrue(taxi.isDispatched());
		
		user.callOff(taxi);
		assertFalse(taxi.isDispatched());
	}
	
	@Test
	public void dispatchedTaxiDrivesCloserToClient1MeterPrecision() {
		Units prec = Position.PRECISION;
		Position.PRECISION = Units._1_METER;
		
		Taxi taxi = new Taxi();
		User user = new User(taxi.getX()+2,taxi.getY()-4);
		Double distanceBeforeDispatch = taxi.distance(user);
		Double distanceAfterDispatch;
		user.dispatch(taxi);
		try {
			Thread.sleep(sleepTimeMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		distanceAfterDispatch = taxi.distance(user);
		Position.PRECISION = prec;
		
		assertTrue(distanceAfterDispatch < distanceBeforeDispatch);
	}
	
	@Test
	public void dispatchedTaxiDrivesToClientIn6Seconds() {
		Taxi taxi = new Taxi();
		User user = new User(taxi.getX()+0.01, taxi.getY()+0.04);
		
		user.dispatch(taxi);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(user.getPosition(), taxi.getPosition());
		assertEquals(new Double(0), taxi.distance(user));
	}
	

}
