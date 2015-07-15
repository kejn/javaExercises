package com.capgemini.pokerHands;

public enum GameCardSuit {
	HEARTS("H"),
	DIAMONDS("D"),
	SPADES("S"),
	CLUBS("C");
	
	private String name;
	
	private GameCardSuit(String suit) {
		name = suit;
	}
	
	/**
	 * @param suitToDeduce String equivalent to searched suit name.
	 * @return one of game card suits</p>
	 * <b>null</b> if <b>suitToDeduce</b> was invalid.
	 */
	public static GameCardSuit deduceSuit(String suitToDeduce) {
		for (GameCardSuit suit : GameCardSuit.values()) {
			if (suit.getName().equals(suitToDeduce)) {
				return suit;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
}
