package com.capgemini.taxi;

public class Taxi extends Position implements Runnable {
	private static long id = 0;

	public static final Double MAX_MOVE_DISTANCE_PER_REFRESH = 0.001; // [km]

	private final Thread thread;
	private User client = null;

	public Taxi() {
		super();
		++id;
		thread = new Thread(this);
		thread.setName("Taxi " + String.format("%" + (int) Math.log10(City.MAX_NUMBER_OF_TAXI) + "d", id));
	}

	/**
	 * Starts thread which implements taxi's motion
	 * 
	 * @return Taxi which called this method
	 */
	private void drive() {
		thread.start();
	}

	/**
	 * Changes taxi position until the thread is terminated.
	 */
	public synchronized void run() {
		while (isDispatched()) {
			try {
				Thread.sleep(REFRESH_MILLISECONDS);
				move();
			} catch (InterruptedException e) {
				if (isDispatched()) {
					callOff(client);
				}
				e.printStackTrace();
				break;
			}
		}
	}

	public boolean isDispatched() {
		if (client == null) {
			return false;
		}
		return true;
	}

	public synchronized void move() {
		if (isDispatched()) {
			Double dist = distance(client);
			Position dir = direction(client);

			if (dist < Math.sqrt(2) * MAX_MOVE_DISTANCE_PER_REFRESH) {
				super.jumpTo(client);
			} else {
				if (dir.getX() > 0) {
					shiftBy(MAX_MOVE_DISTANCE_PER_REFRESH * Math.signum(dir.getX()), 0.0);
				} else if (dir.getY() > 0) {
					shiftBy(0.0, MAX_MOVE_DISTANCE_PER_REFRESH * Math.signum(dir.getY()));
				}
			}
		}
	}

	/**
	 * Check if was not dispatched by another client before and if so drives to
	 * the client who has dispatched it.
	 * 
	 * @param newClient
	 *            the User who dispatched the Taxi
	 * @return <b>true</b> if was not dispatched by another client before.
	 */
	public synchronized boolean dispatchedBy(User newClient) {
		if (!isDispatched()) {
			client = newClient;
			drive();
			return true;
		}
		return false;
	}

	/**
	 * Calls of the Taxi so that it is no longer dispatched. The method is
	 * successful only if called by the client, who dispatched this Taxi before.
	 * 
	 * @return <b>true</b> if the call off was successful.
	 */
	public synchronized boolean callOff(User clientCallingOff) {
		if (client.equals(clientCallingOff)) {
			client = null;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return thread.getName() + " " + super.toString();
	}

}
