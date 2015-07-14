package com.capgemini.pokerHands;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

public class PokerHandTest {
	private PokerHand player1;
	private PokerHand player2;

	@After
	public void testCase() {
		assertEquals(player2, PokerHand.winner(player1,player2));
		assertEquals(player2, PokerHand.winner(player2,player1));
	}
	
	/*
	 * High card
	 */
	@Test
	public void highCardTieAceBetterThanKing() {
		player1 = new PokerHand("KS 2C 4D TH 3H");
		player2 = new PokerHand("KS AC 4D TH 3H");
		assertEquals(HandRanks.HIGH_CARD, player1.score());
		assertEquals(HandRanks.HIGH_CARD, player2.score());
	}

	/*
	 * Pair
	 */
	@Test
	public void pairBetterThanHighCard() {
		player1 = new PokerHand("KS 2C 4D TH 3H");
		player2 = new PokerHand("KS 2C 2H 4D 3H");
		assertEquals(HandRanks.HIGH_CARD, player1.score());
		assertEquals(HandRanks.PAIR, player2.score());
	}

	@Test
	public void pairTieKingBetterThanJack() {
		player1 = new PokerHand("KS JC JD TH 3H");
		player2 = new PokerHand("KS 2C KH 4D 3H");
		assertEquals(HandRanks.PAIR, player1.score());
		assertEquals(HandRanks.PAIR, player2.score());
	}

	/*
	 * Two pairs
	 */
	@Test
	public void twoPairsBetterThanHighCard() {
		player1 = new PokerHand("KS 2C 4D TH 3H");
		player2 = new PokerHand("4S 2C 2H 4D 3H");
		assertEquals(HandRanks.HIGH_CARD, player1.score());
		assertEquals(HandRanks.TWO_PAIRS, player2.score());
	}
	
	@Test
	public void twoPairsBetterThanPair() {
		player1 = new PokerHand("KS JC JD TH 3H");
		player2 = new PokerHand("4S 2C 2H 4D 3H");
		assertEquals(HandRanks.PAIR, player1.score());
		assertEquals(HandRanks.TWO_PAIRS, player2.score());
	}
	
	@Test
	public void twoPairsTie9BetterThan6() {
		player1 = new PokerHand("4S 2C 2H 6D 6H");
		player2 = new PokerHand("KS 9C 9D 3H 3H");
		assertEquals(HandRanks.TWO_PAIRS, player1.score());
		assertEquals(HandRanks.TWO_PAIRS, player2.score());
	}
	
	/*
	 * Three of a kind
	 */
	@Test
	public void threeOfAKindBetterThanHighCard() {
		player1 = new PokerHand("KS 2C 4D TH 3H");
		player2 = new PokerHand("4S 2C 2H 2D 3H");
		assertEquals(HandRanks.HIGH_CARD, player1.score());
		assertEquals(HandRanks.THREE_OF_A_KIND, player2.score());
	}
	
	@Test
	public void threeOfAKindBetterThanPair() {
		player1 = new PokerHand("KS JC JD TH 3H");
		player2 = new PokerHand("4S 2C 2H 2D 3H");
		assertEquals(HandRanks.PAIR, player1.score());
		assertEquals(HandRanks.THREE_OF_A_KIND, player2.score());
	}
	
	@Test
	public void threeOfAKindBetterThantwoPairs() {
		player1 = new PokerHand("4S 2C 2H 6D 6H");
		player2 = new PokerHand("KS 9C 3D 3H 3H");
		assertEquals(HandRanks.TWO_PAIRS, player1.score());
		assertEquals(HandRanks.THREE_OF_A_KIND, player2.score());
	}

	@Test
	public void threeOfAKindTie3BetterThan2() {
		player1 = new PokerHand("4S 2C 2H 2D 6H");
		player2 = new PokerHand("KS 9C 3D 3H 3H");
		assertEquals(HandRanks.THREE_OF_A_KIND, player1.score());
		assertEquals(HandRanks.THREE_OF_A_KIND, player2.score());
	}
	
	/*
	 * Straight
	 */
	@Test
	public void straightBetterThanHighCard() {
		player1 = new PokerHand("KS 2C 4D TH 3H");
		player2 = new PokerHand("4S 2C 6H 5D 3H");
		assertEquals(HandRanks.HIGH_CARD, player1.score());
		assertEquals(HandRanks.STRAIGHT, player2.score());
	}

	@Test
	public void straightBetterThanPair() {
		player1 = new PokerHand("KS 2C KD TH 3H");
		player2 = new PokerHand("4S 2C 6H 5D 3H");
		assertEquals(HandRanks.PAIR, player1.score());
		assertEquals(HandRanks.STRAIGHT, player2.score());
	}

	@Test
	public void straightBetterThanTwoPairs() {
		player1 = new PokerHand("KS 2C KD TH 2H");
		player2 = new PokerHand("4S 2C 6H 5D 3H");
		assertEquals(HandRanks.TWO_PAIRS, player1.score());
		assertEquals(HandRanks.STRAIGHT, player2.score());
	}

	@Test
	public void straightBetterThanThreeOfAKind() {
		player1 = new PokerHand("KS KC KD TH 2H");
		player2 = new PokerHand("4S 2C 6H 5D 3H");
		assertEquals(HandRanks.THREE_OF_A_KIND, player1.score());
		assertEquals(HandRanks.STRAIGHT, player2.score());
	}
	
	@Test
	public void straightTieAceTo10BetterThanQueenTo8() {
		player1 = new PokerHand("8S TC JD QH 9H");
		player2 = new PokerHand("QS JC KH TD AH");
		assertEquals(HandRanks.STRAIGHT, player1.score());
		assertEquals(HandRanks.STRAIGHT, player2.score());
	}
	
	/*
	 * Flush
	 */
	@Test
	public void flushBetterThanHighCard() {
		player1 = new PokerHand("KS 2C 4D TH 3H");
		player2 = new PokerHand("TC 2C 6C AC 7C");
		assertEquals(HandRanks.HIGH_CARD, player1.score());
		assertEquals(HandRanks.FLUSH, player2.score());
	}

	@Test
	public void flushBetterThanPair() {
		player1 = new PokerHand("KS 2C KD TH 3H");
		player2 = new PokerHand("TC 2C 6C AC 7C");
		assertEquals(HandRanks.PAIR, player1.score());
		assertEquals(HandRanks.FLUSH, player2.score());
	}

	@Test
	public void flushBetterThanTwoPairs() {
		player1 = new PokerHand("KS 2C TD TH KH");
		player2 = new PokerHand("TC 2C 6C AC 7C");
		assertEquals(HandRanks.TWO_PAIRS, player1.score());
		assertEquals(HandRanks.FLUSH, player2.score());
	}

	@Test
	public void flushBetterThanThreeOfAKind() {
		player1 = new PokerHand("TS 2C TD TH KH");
		player2 = new PokerHand("TC 2C 6C AC 7C");
		assertEquals(HandRanks.THREE_OF_A_KIND, player1.score());
		assertEquals(HandRanks.FLUSH, player2.score());
	}

	@Test
	public void flushBetterThanStraight() {
		player1 = new PokerHand("TS QC AD JH KH");
		player2 = new PokerHand("TC 2C 6C AC 7C");
		assertEquals(HandRanks.STRAIGHT, player1.score());
		assertEquals(HandRanks.FLUSH, player2.score());
	}

	@Test
	public void flushTieAceBetterThanJack() {
		player1 = new PokerHand("TH 2H 9H JH KH");
		player2 = new PokerHand("TC 2C 6C AC 7C");
		assertEquals(HandRanks.FLUSH, player1.score());
		assertEquals(HandRanks.FLUSH, player2.score());
	}

	/*
	 * Full house
	 */
	@Test
	public void fullHouseBetterThanHighCard() {
		player1 = new PokerHand("TH 2D 9C JH KS");
		player2 = new PokerHand("2S 2C 2D 3C 3H");
		assertEquals(HandRanks.HIGH_CARD, player1.score());
		assertEquals(HandRanks.FULL_HOUSE, player2.score());
	}

	@Test
	public void fullHouseBetterThanPair() {
		player1 = new PokerHand("TH KD 9C JH KS");
		player2 = new PokerHand("2S 2C 2D 3C 3H");
		assertEquals(HandRanks.PAIR, player1.score());
		assertEquals(HandRanks.FULL_HOUSE, player2.score());
	}
	
	@Test
	public void fullHouseBetterThanTwoPairs() {
		player1 = new PokerHand("TH KD JC JH KS");
		player2 = new PokerHand("2S 2C 2D 3C 3H");
		assertEquals(HandRanks.TWO_PAIRS, player1.score());
		assertEquals(HandRanks.FULL_HOUSE, player2.score());
	}
	
	@Test
	public void fullHouseBetterThanThreeOfAKind() {
		player1 = new PokerHand("TH KD JC JH JS");
		player2 = new PokerHand("2S 2C 2D 3C 3H");
		assertEquals(HandRanks.THREE_OF_A_KIND, player1.score());
		assertEquals(HandRanks.FULL_HOUSE, player2.score());
	}
	
	@Test
	public void fullHouseBetterThanStraight() {
		player1 = new PokerHand("TH KD JC QH 9D");
		player2 = new PokerHand("2S 2C 2D 3C 3H");
		assertEquals(HandRanks.STRAIGHT, player1.score());
		assertEquals(HandRanks.FULL_HOUSE, player2.score());
	}
	
	@Test
	public void fullHouseBetterThanFlush() {
		player1 = new PokerHand("4C 2C JC QC 9C");
		player2 = new PokerHand("2S 2C 2D 3C 3H");
		assertEquals(HandRanks.FLUSH, player1.score());
		assertEquals(HandRanks.FULL_HOUSE, player2.score());
	}
	
	@Test
	public void fullHouseTieThree4BetterThanThree2() {
		player1 = new PokerHand("2H 2C 9D 2S 9C");
		player2 = new PokerHand("4S 4C 4D 3C 3H");
		assertEquals(HandRanks.FULL_HOUSE, player1.score());
		assertEquals(HandRanks.FULL_HOUSE, player2.score());
	}

	/*
	 * Four of a kind
	 */
	@Test
	public void fourOfAKindBetterThanHighCard() {
		player1 = new PokerHand("TH 2D 9C JH KS");
		player2 = new PokerHand("2S 2C 2D 3C 2H");
		assertEquals(HandRanks.HIGH_CARD, player1.score());
		assertEquals(HandRanks.FOUR_OF_A_KIND, player2.score());
	}

	@Test
	public void fourOfAKindBetterThanPair() {
		player1 = new PokerHand("TH KD 9C JH KS");
		player2 = new PokerHand("2S 2C 2D 3C 2H");
		assertEquals(HandRanks.PAIR, player1.score());
		assertEquals(HandRanks.FOUR_OF_A_KIND, player2.score());
	}
	
	@Test
	public void fourOfAKindBetterThanTwoPairs() {
		player1 = new PokerHand("TH KD JC JH KS");
		player2 = new PokerHand("2S 2C 2D 3C 2H");
		assertEquals(HandRanks.TWO_PAIRS, player1.score());
		assertEquals(HandRanks.FOUR_OF_A_KIND, player2.score());
	}
	
	@Test
	public void fourOfAKindBetterThanThreeOfAKind() {
		player1 = new PokerHand("TH KD JC JH JS");
		player2 = new PokerHand("2S 2C 2D 3C 2H");
		assertEquals(HandRanks.THREE_OF_A_KIND, player1.score());
		assertEquals(HandRanks.FOUR_OF_A_KIND, player2.score());
	}
	
	@Test
	public void fourOfAKindBetterThanStraight() {
		player1 = new PokerHand("TH KD JC QH 9D");
		player2 = new PokerHand("2S 2C 2D 3C 2H");
		assertEquals(HandRanks.STRAIGHT, player1.score());
		assertEquals(HandRanks.FOUR_OF_A_KIND, player2.score());
	}
	
	@Test
	public void fourOfAKindBetterThanFlush() {
		player1 = new PokerHand("4C 2C JC QC 9C");
		player2 = new PokerHand("2S 2C 2D 3C 2H");
		assertEquals(HandRanks.FLUSH, player1.score());
		assertEquals(HandRanks.FOUR_OF_A_KIND, player2.score());
	}
	
	@Test
	public void fourOfAKindBetterThanfullHouse() {
		player1 = new PokerHand("2H 2C 9D 9S 9C");
		player2 = new PokerHand("4S 4C 4D 3C 4H");
		assertEquals(HandRanks.FULL_HOUSE, player1.score());
		assertEquals(HandRanks.FOUR_OF_A_KIND, player2.score());
	}

	@Test
	public void fourOfAKindTie9BetterThan4() {
		player1 = new PokerHand("4S 4C 4D 3C 4H");
		player2 = new PokerHand("9H 2C 9D 9S 9C");
		assertEquals(HandRanks.FOUR_OF_A_KIND, player1.score());
		assertEquals(HandRanks.FOUR_OF_A_KIND, player2.score());
	}
	
	/*
	 * Flush straight
	 */
	@Test
	public void straightFlushBetterThanHighCard() {
		player1 = new PokerHand("TH 2D 9C JH KS");
		player2 = new PokerHand("4C 2C 5C 3C 6C");
		assertEquals(HandRanks.HIGH_CARD, player1.score());
		assertEquals(HandRanks.STRAIGHT_FLUSH, player2.score());
	}

	@Test
	public void straightFlushBetterThanPair() {
		player1 = new PokerHand("TH KD 9C JH KS");
		player2 = new PokerHand("4C 2C 5C 3C 6C");
		assertEquals(HandRanks.PAIR, player1.score());
		assertEquals(HandRanks.STRAIGHT_FLUSH, player2.score());
	}
	
	@Test
	public void straightFlushBetterThanTwoPairs() {
		player1 = new PokerHand("TH KD JC JH KS");
		player2 = new PokerHand("4C 2C 5C 3C 6C");
		assertEquals(HandRanks.TWO_PAIRS, player1.score());
		assertEquals(HandRanks.STRAIGHT_FLUSH, player2.score());
	}
	
	@Test
	public void straightFlushBetterThanThreeOfAKind() {
		player1 = new PokerHand("TH KD JC JH JS");
		player2 = new PokerHand("4C 2C 5C 3C 6C");
		assertEquals(HandRanks.THREE_OF_A_KIND, player1.score());
		assertEquals(HandRanks.STRAIGHT_FLUSH, player2.score());
	}
	
	@Test
	public void straightFlushBetterThanStraight() {
		player1 = new PokerHand("TH KD JC QH 9D");
		player2 = new PokerHand("4C 2C 5C 3C 6C");
		assertEquals(HandRanks.STRAIGHT, player1.score());
		assertEquals(HandRanks.STRAIGHT_FLUSH, player2.score());
	}
	
	@Test
	public void straightFlushBetterThanFlush() {
		player1 = new PokerHand("4C 2C JC QC 9C");
		player2 = new PokerHand("4C 2C 5C 3C 6C");
		assertEquals(HandRanks.FLUSH, player1.score());
		assertEquals(HandRanks.STRAIGHT_FLUSH, player2.score());
	}
	
	@Test
	public void straightFlushBetterThanfullHouse() {
		player1 = new PokerHand("2H 2C 9D 9S 9C");
		player2 = new PokerHand("4C 2C 5C 3C 6C");
		assertEquals(HandRanks.FULL_HOUSE, player1.score());
		assertEquals(HandRanks.STRAIGHT_FLUSH, player2.score());
	}

	@Test
	public void straightFlushBetterThanfourOfAKind() {
		player1 = new PokerHand("4S 4C 4D 3C 4H");
		player2 = new PokerHand("4C 2C 5C 3C 6C");
		assertEquals(HandRanks.FOUR_OF_A_KIND, player1.score());
		assertEquals(HandRanks.STRAIGHT_FLUSH, player2.score());
	}	

	@Test
	public void straightFlushTieKingBetterThan6() {
		player1 = new PokerHand("4C 2C 5C 3C 6C");
		player2 = new PokerHand("JC TC QC 9C KC");
		assertEquals(HandRanks.STRAIGHT_FLUSH, player1.score());
		assertEquals(HandRanks.STRAIGHT_FLUSH, player2.score());
	}
	
	/*
	 * Royal flush
	 */

	@Test
	public void royalFlushBetterThanStraightFlush() {
		player1 = new PokerHand("4C 2C 5C 3C 6C");
		player2 = new PokerHand("JC KC AC TC QC");
		assertEquals(HandRanks.STRAIGHT_FLUSH, player1.score());
		assertEquals(HandRanks.STRAIGHT_FLUSH, player2.score());
	}
}
