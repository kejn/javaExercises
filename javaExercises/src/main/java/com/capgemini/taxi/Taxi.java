package com.capgemini.taxi;

public class Taxi extends Position implements Runnable {
	private static long id = 0;

	private final Thread thread;
	private User client;

	public Taxi() {
		super();
		client = null;
		thread = new Thread(this, "Taxi Thread, ID: " + (++id));
	}

	/**
	 * Starts thread which implements taxi's motion
	 * 
	 * @return Taxi which called this method
	 */
	private void driveToClient() {
		thread.start();
	}

	/**
	 * Changes taxi position until the thread is terminated.
	 */
	public void run() {
		while (isDispatched()) {
			try {
				Thread.sleep(REFRESH_MILLISECONDS);
				move();
			} catch (InterruptedException e) {
				callOff(client);
				e.printStackTrace();
			}
		}
	}

	public boolean isDispatched() {
		if (client == null) {
			return false;
		}
		return true;
	}

	private void move() {
		super.random();
	}

	/**
	 * Check if was not dispatched by another client before and if so drives to
	 * the client who has dispatched it.
	 * 
	 * @param newClient
	 *            the User who dispatched the Taxi
	 * @return <b>true</b> if was not dispatched by another client before.
	 */
	public boolean dispatch(User newClient) {
		if (!isDispatched()) {
			client = newClient;
			driveToClient();
			return true;
		}
		return false;
	}

	/**
	 * Calls of the Taxi so that it is no longer dispatched. The method is
	 * successful only if called by the client, who dispatched this Taxi before.
	 * @return <b>true</b> if the call off was successful.
	 */
	public boolean callOff(User clientCallingOff) {
		if (client.equals(clientCallingOff)) {
			client = null;
			return true;
		}
		return false;
	}

}
