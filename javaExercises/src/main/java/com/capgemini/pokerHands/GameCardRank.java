package com.capgemini.pokerHands;

/**
 * Used to define the order of possible card ranks.
 * 
 * @author Kamil Niemczyk
 * 
 */
public enum GameCardRank {
	_2("2"),
	_3("3"),
	_4("4"),
	_5("5"),
	_6("6"),
	_7("7"),
	_8("8"),
	_9("9"),
	_10("T"),
	JACK("J"),
	QUEEN("Q"),
	KING("K"),
	ACE("A");
	
	private String name;
	
	private GameCardRank(String rank) {
		name = rank;
	}
	
	/**
	 * @param rankToDeduce String equivalent to searched rank name.
	 * @return one of game card ranks</p>
	 * <b>null</b> if <b>rankToDeduce</b> was invalid.
	 */
	public static GameCardRank deduceRank(String rankToDeduce) {
		for (GameCardRank rank : GameCardRank.values()) {
			if (rank.getName().equals(rankToDeduce)) {
				return rank;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
}
