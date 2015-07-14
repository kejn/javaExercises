package com.capgemini.pokerHands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Defines hand of game cards in poker game.
 * 
 * @author Kamil Niemczyk
 * 
 */
public class PokerHand {
	/**
	 * List of game cards the player is given.
	 */
	private List<GameCard> hand;

	/**
	 * @param cardsGiven
	 *            A string representing set of game cards separated with a
	 *            space.
	 */
	public PokerHand(String cardsGiven) {
		hand = new ArrayList<GameCard>();
		for (String rankSuit : cardsGiven.split("\\s")) {
			hand.add(new GameCard(rankSuit));
		}
	}

	/**
	 * @param index
	 *            Index of a game card in hand.
	 * @return i <sup>th</sup> game card from hand (counting from 0).
	 */
	private GameCard getCard(int index) {
		return hand.get(index);
	}

	@Override
	public String toString() {
		String handString = new String();
		for (GameCard gameCard : hand) {
			handString += (gameCard.getRank() + gameCard.getSuit() + " ");
		}
		return handString.substring(0, handString.length() - 1);
	}

	/**
	 * Declares a winner in poker game.
	 * 
	 * @return The player's PokerHand which score is better.
	 */
	public static PokerHand winner(PokerHand player1, PokerHand player2) {
		if (player2.scoreGreater(player1)
				|| (player2.scoreEquals(player1) && player2.firstNotEqualCardIsGreater(player1))) {
			return player2;
		}
		return player1;
	}

	private boolean firstNotEqualCardIsGreater(PokerHand toCompare) {
		for (int i = 0; i < 5; ++i) {
			if (getCard(i).greater(toCompare.getCard(i))) {
				return true;
			} else if (toCompare.getCard(i).greater(getCard(i))) {
				break;
			}
		}
		return false;
	}

	public boolean scoreGreater(PokerHand toCompare) {
		if (score().getValue() > toCompare.score().getValue()) {
			return true;
		}
		return false;
	}

	public boolean scoreEquals(PokerHand toCompare) {
		if (score().getValue() == toCompare.score().getValue()) {
			return true;
		}
		return false;
	}

	/**
	 * @return One of scores defined by HandRanks.
	 */
	public HandRanks score() {
		HandRanks score = HandRanks.HIGH_CARD;
		sortByRank();

		if (isOfTheSameSuit()) {
			score = HandRanks.FLUSH;
		}

		if (isStraight()) {
			if (score == HandRanks.FLUSH) {
				score = HandRanks.STRAIGHT_FLUSH;
			} else {
				score = HandRanks.STRAIGHT;
			}
		} else if (hasDuplicates(0)) {
			putSingleCardsAtEnd();
			score = HandRanks.PAIR;

			if (hand.get(2).equals(hand.get(1))) {
				score = HandRanks.THREE_OF_A_KIND;
			}
			if (hasDuplicates(2)) {
				if (hand.get(1).equals(hand.get(2))) {
					score = HandRanks.FOUR_OF_A_KIND;
				} else {
					score = HandRanks.TWO_PAIRS;
				}
			}
			if (hasDuplicates(3)) {
				moveThreeOfAKindToFront();
				score = HandRanks.FULL_HOUSE;
			}
		}
		return score;
	}

	private void sortByRank() {
		Collections.sort(hand);
	}

	/**
	 * @return <b>true</b> if all game cards in hand are of the same suit.
	 */
	private boolean isOfTheSameSuit() {
		String flush = hand.get(0).getSuit();
		for (int i = 1; i < hand.size(); ++i) {
			if (!hand.get(i).getSuit().equals(flush)) {
				return false;
			}
		}
		return true;
	}

	private boolean isStraight() {
		for (int i = 0; i < hand.size() - 1; ++i) {
			if (hand.get(i + 1).compareTo(hand.get(i)) != 1) {
				return false;
			}
		}
		return true;
	}

	private void moveThreeOfAKindToFront() {
		if (!hand.get(2).equals(hand.get(1))) {
			hand.add(hand.remove(0));
			hand.add(hand.remove(0));
		}
	}

	/**
	 * @param checkFrom
	 *            Index in hand to start checking from.
	 * @return <b>true</b> if in sorted (by rank) adjacent card ranks are the
	 *         same.
	 */
	private boolean hasDuplicates(int checkFrom) {
		for (int i = checkFrom; i < hand.size() - 1; ++i) {
			if (getCard(i).equals(getCard(i + 1))) {
				return true;
			}
		}
		return false;
	}

	private void putSingleCardsAtEnd() {
		int moved = 0;
		boolean sequence = false;

		for (int i = 0; i < hand.size() - moved; ++i) {
			// prevent indexOutOfBounds for [i+1]
			if (moved == 0 && i == hand.size() - 2) {
				break;
			}
			if (getCard(i).equals(getCard(i + 1))) {
				sequence = true;
				continue;
			}
			if (!sequence) {
				hand.add(hand.remove(i));
				++moved;
				--i;
			}
			sequence = false;
		}
	}

}
