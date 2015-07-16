package com.capgemini.taxi;

public class User extends Position {

	public User() {
		super();
	}
	public User(Position position) {
		super(position);
	}
	public User(Double x, Double y) {
		super(x,y);
	}

	/**
	 * @param taxi
	 *            Taxi to be dispatched.
	 * @return <b>true</b> if <b>taxi</b> was successfully dispatched.
	 */
	public boolean dispatch(Taxi taxi) {
		if (taxi.dispatchedBy(this)) {
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
