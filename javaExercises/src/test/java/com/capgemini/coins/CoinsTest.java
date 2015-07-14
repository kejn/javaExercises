package com.capgemini.coins;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class CoinsTest {
	private List<Integer> coins;

	@Test
	public void oneCoinInListReturn0() {
		coins = new ArrayList<Integer>(Arrays.asList(1)); // 0 pairs
		try {
			assertEquals(0, Coins.solution(coins));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void twoCoinsInListTheSameReturn0() {
		coins = new ArrayList<Integer>(Arrays.asList(1, 1)); // 1 pair
		try {
			assertEquals(0, Coins.solution(coins));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void moreThan3CoinsSortedIncreaseBy0() {
		coins = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 0, 0, 0)); // 4 pairs
		try {
			assertEquals(4, Coins.solution(coins));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void oddCoinsAllTheSameSortedDecreaseBy1() {
		coins = new ArrayList<Integer>(Arrays.asList(1, 1, 1)); // 2 pairs
		try {
			assertEquals(1, Coins.solution(coins));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void coinsSortedEdgeElementDifferentIncreaseBy1() {
		coins = new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 1)); // 4 pairs
		try {
			assertEquals(5, Coins.solution(coins));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void coinSurroundedByTheSameIncreaseBy2() {
		coins = new ArrayList<Integer>(Arrays.asList(1, 1, 0, 1, 0, 0)); // 2 pairs
		try {
			assertEquals(4, Coins.solution(coins));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
