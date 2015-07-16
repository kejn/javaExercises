package com.capgemini.taxi;

public enum Units {
	_1_KILOMETER(0),
	_100_METRES(1),
	_10_METRES(2),
	_1_METER(3);
	
	private int precision;
	
	private Units(int newPrecision) {
		precision = newPrecision;
	}
	
	public int getPrecision() {
		return precision;
	}
}
