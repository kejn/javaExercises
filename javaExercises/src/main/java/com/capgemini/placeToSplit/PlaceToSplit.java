package com.capgemini.placeToSplit;

/**
 * Given a non-empty array, return true if there is a place to split the array
 * so that the sum of the numbers on one side is equal to the sum of the numbers
 * on the other side. Example:<br>
 * {{{ canBalance({1, 1, 1, 2, 1}) → true<br>
 * canBalance({2, 1, 1, 2, 1}) → false<br>
 * canBalance({10, 10}) → true<br>
 * }}}
 */
public final class PlaceToSplit {
	/**
	 * Current sum of elements to the left of split point.
	 */
	private int leftSideSum;

	/**
	 * Current sum of elements to the right of split point.
	 */
	private int rightSideSum;

	/**
	 * Checks if there is a place to split the array so that the sum of the
	 * numbers on one side is equal to the sum of the numbers on the other side.
	 * 
	 * @param nums
	 *            an array of numbers to check.
	 * @return <b>true</b> if the condition is met.
	 *         </p>
	 *         <b>false</b> otherwise.
	 */
	public boolean canBalance(int[] nums) {
		int i = 0;
		int j = nums.length - 1;

		leftSideSum = 0;
		rightSideSum = 0;

		while (i <= j) {
			if (leftSideSum <= rightSideSum) {
				leftSideSum += nums[i];
				++i;
			} else {
				rightSideSum += nums[j];
				--j;
			}
		}

		return (leftSideSum == rightSideSum);
	}

	public static void main(String[] args) {
		int[] testOne = { 1, 1, 1, 2, 1 };
		int[] testTwo = { 2, 1, 1, 2, 1 };
		int[] testThree = { 10, 10 };
		int[] testFour = { 10 };

		PlaceToSplit pts = new PlaceToSplit();

		System.out.println("test 1 : " + pts.canBalance(testOne));
		System.out.println("test 2 : " + pts.canBalance(testTwo));
		System.out.println("test 3 : " + pts.canBalance(testThree));
		System.out.println("test 4 : " + pts.canBalance(testFour));
	}
}
