package com.capgemini.taxi;

public class Position {
	/**
	 * Time in milliseconds after which the position is updated.
	 */
	public static final int REFRESH_MILLISECONDS = 100;

	/**
	 * The precision of describing Position's {@link #x} and {@link #y}.
	 */
	public static Units PRECISION = Units._1_METER;

	/**
	 * Maximum position for {@link #x} or {@link #y} when method
	 * {@link #random()} is called.
	 */
	protected Double maxXY;

	/**
	 * Horizontal position.
	 */
	protected Double x;

	/**
	 * Vertical position.
	 */
	protected Double y;

	public Position() {
		maxXY = City.CITY_AREA;
		random();
	}

	public Position(Double newX, Double newY) {
		x = newX;
		y = newY;
	}

	public Position(Position another) {
		jumpTo(another);
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	public void random() {
		x = adjustToSetPrecision(Math.random() * maxXY);
		y = adjustToSetPrecision(Math.random() * maxXY);
	}

	public Double distance(Position other) {
		return adjustToSetPrecision(Math.sqrt(Math.pow(other.getX() - x, 2) + Math.pow(other.getY() - y, 2)));
	}

	public Position directionTo(Position other) {
		return new Position(adjustToSetPrecision(other.getX() - x), adjustToSetPrecision(other.getY() - y));
	}

	public void jumpTo(Position another) {
		x = another.getX();
		y = another.getY();
	}

	public void shiftBy(Double x, Double y) {
		this.x += x;
		this.y += y;
	}

	public Position getPosition() {
		return this;
	}

	@Override
	public String toString() {
		return "(" + x + "; " + y + ")";
	}

	@Override
	public boolean equals(Object object) {
		Position pos = (Position) object;
		if (x.equals(pos.getX()) && y.equals(pos.getY())) {
			return true;
		}
		return false;
	}

	/**
	 * @return <b>number</b> set to precision given in {@link #PRECISION}.
	 */
	protected Double adjustToSetPrecision(Double number) {
		return Double.parseDouble(String.format("%." + PRECISION.getPrecision() + "f", number).replace(',', '.'));
	}

}
