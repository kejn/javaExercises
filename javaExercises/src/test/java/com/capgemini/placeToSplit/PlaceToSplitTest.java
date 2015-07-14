package com.capgemini.placeToSplit;

import static org.junit.Assert.*;
import org.junit.Test;

public class PlaceToSplitTest {
	private PlaceToSplit pts = new PlaceToSplit();

	@Test
	public void shouldBeAbleToSplit() {
		int[] array1 = { 1, 1, 1, 2, 1 };
		int[] array2 = { 10, 10 };
		assertTrue(pts.canBalance(array1));
		assertTrue(pts.canBalance(array2));
	}
	
	@Test
	public void shouldNotBeAbleToSplit() {
		int[] array1 = { 2, 1, 1, 2, 1 };
		int[] array2 = { 10, 10, 10 };
		assertFalse(pts.canBalance(array1));
		assertFalse(pts.canBalance(array2));
	}

}
