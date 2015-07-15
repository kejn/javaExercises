package com.capgemini.taxi;

public class User extends Position {

	public User() {
		super();
	}

	/**
	 * @param taxi
	 *            Taxi to be dispatched.
	 * @return <b>true</b> if <b>taxi</b> was successfully dispatched.
	 */
	public boolean dispatch(Taxi taxi) {
		if (taxi.dispatch(this)) {
			return true;
		}
		return false;
	}

	public boolean callOff(Taxi taxi) {
		if(taxi.callOff(this)) {
			return true;
		}
		return false;
	}

}
