package com.capgemini.pokerHands;

/**
 * Defines possible hand ranks in poker game valued in order.
 * 
 * @author Kamil Niemczyk
 *
 */
public enum HandRanks {
	HIGH_CARD(1),
	PAIR(2),
	TWO_PAIRS(3),
	THREE_OF_A_KIND(4),
	STRAIGHT(5),
	FLUSH(6),
	FULL_HOUSE(7),
	FOUR_OF_A_KIND(8),
	STRAIGHT_FLUSH(9);

	private int value;

	private HandRanks(int initValue) {
		value = initValue;
	}

	public int getValue() {
		return value;
	}
}