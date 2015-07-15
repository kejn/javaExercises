package com.capgemini.pokerHands;

/**
 * A game card described by its <b>rank</b> and <b>suit</b>.
 * 
 * @author Kamil Niemczyk
 *
 */
public class GameCard implements Comparable<GameCard> {
	private GameCardRank rank; 
	private GameCardSuit suit;

	/**
	 * @param rankSuit
	 *            At least 2-character String in which first character describes
	 *            card's rank, and second card's suit.
	 */
	public GameCard(String rankSuit) {
		rank = GameCardRank.deduceRank(rankSuit.substring(0, 1));
		suit = GameCardSuit.deduceSuit(rankSuit.substring(1, 2));
	}

	public GameCardRank getRank() {
		return rank;
	}

	public GameCardSuit getSuit() {
		return suit;
	}
	
	public int compareTo(GameCard another) {
		return (another.getRank().compareTo(rank));
	}

	public boolean greater(GameCard another) {
		return (rank.compareTo(another.getRank()) > 0);
	}

	@Override
	public boolean equals(Object object) {
		GameCard another = (GameCard) object;
		return (rank.compareTo(another.getRank()) == 0);
	}

	@Override
	public String toString() {
		return (rank.getName() + suit.getName());
	}

}