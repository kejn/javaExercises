package com.capgemini.taxi;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void positionDoesntChangeInTime() {
		User user = new User();
		Position pos = user.getPosition();
		try {
			Thread.sleep(Position.REFRESH_MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(pos, user.getPosition());
	}

}
