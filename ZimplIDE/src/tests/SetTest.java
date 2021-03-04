package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.Set;

class SetTest
{
	@Test
	void numericSetTest()
	{
		Set set = new Set("A");
		set.add("45");
		set.add("+2.");
		set.add("-0.34");
		set.add(".34");
		
		assertTrue(set.isNumeric());
	}

	@Test
	void alphanumericSetTest()
	{
		Set set = new Set("A");
		set.add("45");
		set.add("45r");
		set.add("-0.34");
		set.add(".34");
		
		assertFalse(set.isNumeric());
	}
}
