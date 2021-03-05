package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Set;
import domain.Domain;
import domain.Model;
import parser.ZimplVariableParser;

class ZimplVariableParserTest
{
	private Model _model;
	
	@BeforeEach
	void initialize()
	{
		_model = new Model();
		_model.add(new Set("A"));
		_model.add(new Set("B"));
		_model.add(new Set("C"));
	}
	
	@Test
	void noDomainVariable()
	{
		parse("stuff var x; more stuff");

		assertContains("x");
		assertDomain("x");
	}
	
	@Test
	void twoVariables()
	{
		parse("stuff var x; more stuff var y; final stuff");

		assertContains("x", "y");
		assertDomain("x");
		assertDomain("y");
	}

	@Test
	void singleDomainVariable()
	{
		parse("stuff var x[A]; more stuff var y; final stuff");

		assertContains("x", "y");
		assertDomain("x", "A");
		assertDomain("y");
	}

	@Test
	void tripleDomainVariable()
	{
		parse("stuff var x[A]; more stuff var y[B*C*A]; final stuff");

		assertContains("x", "y");
		assertDomain("x", "A");
		assertDomain("y", "B", "C", "A");
	}

	private void parse(String file)
	{
		ZimplVariableParser parser = new ZimplVariableParser(_model, file);
		parser.parse();
	}

	private void assertContains(String... variables)
	{
		assertEquals(variables.length, _model.getVariables().size());
		
		for(String name: variables)
			assertNotNull(_model.getVariable(name));
	}
	
	private void assertDomain(String name, String... sets)
	{
		Domain domain = _model.getVariable(name).getDomain();
		assertEquals(sets.length, domain.size());
		
		for(int i=0; i<sets.length; ++i)
			assertEquals(sets[i], domain.get(i).getName());
	}
}
