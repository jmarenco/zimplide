package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Element;
import domain.Model;
import domain.Set;
import parser.ZimplSetParser;

class ZimplSetParserTest
{
	private Model _model;
	
	@BeforeEach
	void initialize()
	{
		_model = new Model();
	}
	
	@Test
	void emptySet()
	{
		parse("stuff set  A; more stuff");

		assertContains("A");
		assertContent("A");
	}
	
	@Test
	void emptySetWithBrackets()
	{
		parse("stuff set  A := {}; more stuff");

		assertContains("A");
		assertContent("A");
	}
	
	@Test
	void numericSingletonSet()
	{
		parse("stuff set  A := { 72 }; more stuff");

		assertContains("A");
		assertContent("A", "72");
	}
	
	@Test
	void numericThreeElementSet()
	{
		parse("stuff set  A := { 72, 12, 56 }; more stuff");

		assertContains("A");
		assertContent("A", "12", "56", "72");
	}
	
	@Test
	void stringSingletonSet()
	{
		parse("stuff set  A := { \"shrek\" }; more stuff");

		assertContains("A");
		assertContent("A", "shrek");
	}
	
	@Test
	void stringTwoElementSet()
	{
		parse("stuff set  A := { \"shrek\", \"fiona\" }; more stuff");

		assertContains("A");
		assertContent("A", "shrek", "fiona");
	}
	
	@Test
	void numericNoSpacesSet()
	{
		parse("stuff set A:={45,23,12}; more stuff");

		assertContains("A");
		assertContent("A", "45", "23", "12");
	}
	
	@Test
	void numericManySpacesSet()
	{
		parse("stuff set A    :=        {   45,    23,  12    }   ; more stuff");

		assertContains("A");
		assertContent("A", "45", "23", "12");
	}
	
	@Test
	void differentSeparatorsSet()
	{
		parse("stuff set A := \t { \n 45,\t23,\n12}; more stuff");

		assertContains("A");
		assertContent("A", "45", "23", "12");
	}
	
	@Test
	void largeSetName()
	{
		parse("stuff set TheLargeSet :={45,23,12}; more stuff");

		assertContains("TheLargeSet");
		assertContent("TheLargeSet", "45", "23", "12");
	}
	
	@Test
	void twoSetsWithComments()
	{
		parse("# comment set A := { 1, 2, 3 }; # comment set B := {4,5}; more stuff");

		assertContains("A", "B");
		assertContent("A", "1", "2", "3");
		assertContent("B", "4", "5");
	}
	
	@Test
	void fillEmptySet()
	{
		_model.add(new Set("A", "3", "4", "7"));
		String result = fill("stuff set A; more stuff");
		assertEquals("stuff set A := { 3, 4, 7 }; more stuff", result);
	}

	private void parse(String file)
	{
		ZimplSetParser parser = new ZimplSetParser(_model, file);
		parser.parse();
	}
	
	private String fill(String file)
	{
		ZimplSetParser parser = new ZimplSetParser(_model, file);
		return parser.fill();
	}

	private void assertContains(String... sets)
	{
		assertEquals(sets.length, _model.getSets().size());
		
		for(String name: sets)
			assertNotNull(_model.getSet(name));
	}
	
	private void assertContent(String name, String... elements)
	{
		Set set = _model.getSet(name);
		
		for(String element: elements)
			assertTrue(set.contains(new Element(element)));
	}
}
