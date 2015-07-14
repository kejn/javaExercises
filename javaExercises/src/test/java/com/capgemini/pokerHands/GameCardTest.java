package com.capgemini.pokerHands;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameCardTest {
	private String[] cardRanks = { "A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2" };
	
	@Test
	public void rankTest() {
		for (int i = 0; i < cardRanks.length; ++i) {
			GameCard card1 = new GameCard(cardRanks[i]+"H");
			for (int j = 0; j < cardRanks.length; ++j) {
				GameCard card2 = new GameCard(cardRanks[j]+"H");
				if(i < j) {
					assertTrue(card1.greater(card2));
				} else if(i == j) {
					assertTrue(card1.equals(card2));
				} else {
					assertFalse(card1.greater(card2) || card1.equals(card2));
				}
			}
		}
	}

}
