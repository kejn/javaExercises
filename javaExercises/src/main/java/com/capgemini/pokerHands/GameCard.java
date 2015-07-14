package com.capgemini.pokerHands;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A game card described by its <b>rank</b> and <b>suit</b>.
 * 
 * @author Kamil Niemczyk
 *
 */
public class GameCard implements Comparable<GameCard> {
	private String rank;
	private String suit;

	/**
	 * Used to define the order of possible card ranks.
	 */
	private static Map<String, Integer> ranks;

	static {
		Map<String, Integer> staticRanks = new HashMap<String, Integer>();
		for (int i = 2; i < 10; ++i) {
			staticRanks.put(Integer.toString(i), i);
		}
		staticRanks.put("T", 10);
		staticRanks.put("J", 11);
		staticRanks.put("Q", 12);
		staticRanks.put("K", 13);
		staticRanks.put("A", 14);
		ranks = Collections.unmodifiableMap(staticRanks);
	}

	/**
	 * @param rankSuit
	 *            At least 2-character String in which first character describes
	 *            card's rank, and second card's suit.
	 */
	public GameCard(String rankSuit) {
		rank = rankSuit.substring(0, 1);
		suit = rankSuit.substring(1, 2);
	}

	public String getRank() {
		return rank;
	}

	public Integer getRankValue() {
		return ranks.get(rank);
	}

	public String getSuit() {
		return suit;
	}
	
	public int compareTo(GameCard another) {
		return (another.getRankValue() - getRankValue());
	}

	public boolean greater(GameCard another) {
		return (getRankValue().compareTo(another.getRankValue()) > 0);
	}

	@Override
	public boolean equals(Object object) {
		GameCard another = (GameCard) object;
		return (getRankValue().compareTo(another.getRankValue()) == 0);
	}

	@Override
	public String toString() {
		return (rank + suit);
	}

}