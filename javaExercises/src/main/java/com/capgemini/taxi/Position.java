package com.capgemini.taxi;

public class Position {
	/**
	 * Time in milliseconds after which the position is updated.
	 */
	public static final int REFRESH_MILLISECONDS = 100; 
	
	protected Double maxXY; 
	protected Double x;
	protected Double y;

	public Position() {
		maxXY = 20.0;
		random();
	}
	public Position(Double newX, Double newY) {
		x = newX;
		y = newY;
	}
	public Position(Position another) {
		moveTo(another);
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	public void random() {
		x = Math.random() * maxXY;
		y = Math.random() * maxXY;
	}

	public Double distanceTo(Position other) {
		return Math.sqrt(Math.pow(other.getX() - x, 2) + Math.pow(other.getY() - y, 2));
	}
	
	public void moveTo(Position another) {
		x = another.getX();
		y = another.getY();
	}

	public Position getPosition() {
		return new Position(this);
	}
	
	@Override
	public String toString() {
		return "(" + x + "; " + y + ")";
	}
	
	@Override
	public boolean equals(Object object) {
		Position pos = (Position) object;
		if(x.equals(pos.getX()) && y.equals(pos.getY())) {
			return true;
		}
		return false;
	}
	
	
}
