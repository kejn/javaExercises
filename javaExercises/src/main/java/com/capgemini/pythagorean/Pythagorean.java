package com.capgemini.pythagorean;

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for
 * which, a2 + b2 = c2 For example, 32 + 42 = 9 + 16 = 25 = 52. There exists
 * exactly one Pythagorean triplet for which a + b + c = 1000. Find the product
 * abc.
 */
public class Pythagorean {
	private int a = 1;
	private int b = 2;
	private int c = 3;

	private int sumOfEdges() {
		return a + b + c;
	}

	private int productOfEdges() {
		return a * b * c;
	}

	private boolean conditionsAreMet() {
		if (sumConditionMet() && isPythagoreanTriplet())
			return true;
		return false;
	}

	private boolean sumConditionMet() {
		if (sumOfEdges() == 1000) {
			return true;
		}
		return false;
	}

	private boolean isPythagoreanTriplet() {
		if (Math.pow((double) a, 2) + Math.pow((double) b, 2) == Math.pow((double) c, 2))
			return true;
		return false;
	}

	public boolean calculateResult() {
		boolean foundResult = false;

		for (; a < b; ++a) {
			for (b = a + 1; b < c; ++b) {
				c = 1000 - b - a;
				foundResult = conditionsAreMet();
				if (foundResult) break;
			}
			if (foundResult) break;
		}

		return foundResult;
	}

	private void printResults() {
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		System.out.println("a+b+c = " + sumOfEdges());
		System.out.println("a*b*c = " + productOfEdges());
	}

	public static void main(String[] args) {
		Pythagorean pyth = new Pythagorean();
		if (pyth.calculateResult()) {
			pyth.printResults();
		} else {
			System.out.println("Result not found.");
		}
	}
}
