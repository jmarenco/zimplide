package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Set;
import domain.Tuple;
import domain.Domain;
import domain.Element;
import domain.Model;
import domain.Parameter;
import parser.ZimplParameterParser;

class ZimplParameterParserTest
{
	private Model _model;
	
	@BeforeEach
	void initialize()
	{
		_model = new Model();
		_model.add(new Set("A"));
		_model.add(new Set("B"));
		_model.add(new Set("C"));
		
		_model.getSet("A").add(new Element("shrek"));
		_model.getSet("A").add(new Element("fiona"));
		_model.getSet("A").add(new Element("donkey"));

		_model.getSet("B").add(new Element("10"));
		_model.getSet("B").add(new Element("11"));
		_model.getSet("B").add(new Element("12"));

		_model.getSet("C").add(new Element("100"));
	}
	
	@Test
	void noDomainEmptyParam()
	{
		parse("stuff param n; more stuff");

		assertContains("n");
		assertDomain("n");
		assertValues("n", 0);
	}
	
	@Test
	void noDomainParam()
	{
		parse("stuff param n := 11; more stuff");

		assertContains("n");
		assertDomain("n");
		assertValues("n", 1);
		assertValue("n", 11);
	}
	
	@Test
	void emptySingleDomainParam()
	{
		parse("stuff param x[A]; more stuff");

		assertContains("x");
		assertDomain("x", "A");
		assertValues("x", 0);
	}
	
	@Test
	void singleDomainParam()
	{
		parse("stuff param x[A] := <shrek> 10, <fiona> 1; more stuff");

		assertContains("x");
		assertDomain("x", "A");
		assertValues("x", 2);
		assertValue("x", 10, "shrek");
		assertValue("x", 1, "fiona");
	}
	
	@Test
	void twoDomainNoSpacedParam()
	{
		parse("stuff param x[A*B]:=<shrek,10> 13, <fiona,10> 1,<shrek,11>12; more stuff");

		assertContains("x");
		assertDomain("x", "A", "B");
		assertValues("x", 3);
		assertValue("x", 13, "shrek", "10");
		assertValue("x", 1, "fiona", "10");
		assertValue("x", 12, "shrek", "11");
	}
	
	@Test
	void fillEmptyParam()
	{
		Domain domain = new Domain();
		domain.addSet(_model.getSet("A"));
		
		Parameter param = new Parameter("cost", domain);
		param.setValue(new Tuple(domain, "shrek"), 11);
		param.setValue(new Tuple(domain, "fiona"), 12);
		
		_model.add(param);
		
		String result = fill("stuff param cost[A]; more stuff");
		assertEquals("stuff param cost[A] := <\"shrek\"> 11.0, <\"fiona\"> 12.0; more stuff", result);
	}

	private void parse(String file)
	{
		ZimplParameterParser parser = new ZimplParameterParser(_model, file);
		parser.parse();
	}
	
	private String fill(String file)
	{
		ZimplParameterParser parser = new ZimplParameterParser(_model, file);
		return parser.fill();
	}

	private void assertContains(String... params)
	{
		assertEquals(params.length, _model.getParameters().size());
		
		for(String name: params)
			assertNotNull(_model.getParameter(name));
	}
	
	private void assertDomain(String name, String... sets)
	{
		Domain domain = _model.getParameter(name).getDomain();
		assertEquals(sets.length, domain.size());
		
		for(int i=0; i<sets.length; ++i)
			assertEquals(sets[i], domain.get(i).getName());
	}
	
	private void assertValues(String name, int elements)
	{
		Parameter param = _model.getParameter(name);
		assertEquals(elements, param.getDomainTuples().size());
	}
	
	private void assertValue(String name, double value, String... domainValues)
	{
		Parameter param = _model.getParameter(name);
		
		Tuple tuple = new Tuple(param.getDomain());
		for(int i=0; i<domainValues.length; ++i)
			tuple.set(i, new Element(domainValues[i]));
		
		assertEquals(value, param.getValue(tuple), 1e-5);
	}
}
