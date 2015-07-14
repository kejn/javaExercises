package com.capgemini.coins;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldrygala on 2015-02-06.
 * <p/>
 * Consider N coins aligned in a row. Each coin is showing either heads or
 * tails.
 * <p/>
 * The adjacency of these coins is the number of adjacent pairs of coins with
 * the same side facing up. It must return the maximum possible adjacency that
 * can be obtained by reversing exactly one coin (that is, one of the coins must
 * be reversed). Consecutive elements of array A represent consecutive coins in
 * the row. Array A contains only 0s and/or 1s:
 * <p/>
 * <ul>
 * <li>0 represents a coin with heads facing up;
 * <li>1 represents a coin with tails facing up.
 * </ul>
 * For example, given array A consisting of six numbers, such that:
 * <p/>
 * A[0] = 1<br>
 * A[1] = 1<br>
 * A[2] = 0<br>
 * A[3] = 1<br>
 * A[4] = 0<br>
 * A[5] = 0<br>
 * <p/>
 * the function should return 4.
 * <p/>
 * The initial adjacency is 2, as there are two pairs of adjacent coins with the
 * same side facing up, namely (0, 1) and (4, 5). After reversing the coin
 * represented by A[2], the adjacency equals 4, as there are four pairs of
 * adjacent coins with the same side facing up, namely: (0, 1), (1, 2), (2, 3)
 * and (4, 5), and it is not possible to obtain a higher adjacency. The same
 * adjacency can be obtained by reversing the coin represented by A[3].
 */
public class Coins {
	/**
	 * List of indexes in <b>coins</b> list (see {@link #solution(List) solution} )
	 * which do not make pairs with their predecessors (or consequent if index = 0).  
	 */
	private static List<Integer> nonAdjacent;

	/**
	 * 
	 * @param coins
	 *            list of integers representing either heads or tails
	 * @return maximum number of adjacent pairs after reversing one of the coins
	 * @throws Exception
	 *             if the list <b>coins</b> is empty
	 */
	public static int solution(List<Integer> coins) throws Exception {
		if (coins.isEmpty()) {
			throw new Exception("Empty list passed as an argument!");
		}
		if (coins.size() == 1) {
			return 0;
		}
		
		int numberOfPairs = 0;
		int increase = 0;
		nonAdjacent = new ArrayList<Integer>();

		// count number of adjacent pairs and create list of non-adjacent coins
		for (int i = 1, previousCoin = coins.get(0); i < coins.size(); ++i) {
			if (coins.get(i) == previousCoin) {
				++numberOfPairs;
			} else {
				nonAdjacent.add(i);
			}
			previousCoin = coins.get(i);
		}

		if (nonAdjacent.isEmpty()) { // all coins are heads/tails
			return (numberOfPairs - 1);
		}
		
		if (coins.get(0) != coins.get(1)) { // maybe will reverse first coin
			nonAdjacent.add(0);
		}
		for (int indexInCoins : nonAdjacent) {
			try {
				if (isSurroundedWithTheSameCoins(coins, indexInCoins)) {
					increase = 2;
					break;
				}
			} catch (Exception firstOrLastCoin) {
				increase = 1;
			}
		}
		return (numberOfPairs + increase);
	}

	private static boolean isSurroundedWithTheSameCoins(List<Integer> coins, int whichCoin) throws Exception {
		if (whichCoin == 0 || whichCoin == coins.size() - 1) {
			throw new Exception();
		}
		return coins.get(whichCoin - 1) == coins.get(whichCoin + 1);
	}
}
