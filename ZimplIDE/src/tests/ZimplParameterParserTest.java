package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Set;
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
	}
	
	@Test
	void emptyParam()
	{
		parse("stuff param n[A*B] := 10, 1, 12; more stuff");

		// assertContains("A");
		// assertContent("A", 1);
	}
	
	private void parse(String file)
	{
		ZimplParameterParser parser = new ZimplParameterParser(_model, file);
		parser.parse();
	}	
	
	private void assertContains(String... params)
	{
		assertEquals(params.length, _model.getParameters().size());
		
		for(String name: params)
			assertNotNull(_model.getParameter(name));
	}
	
	private void assertContent(String name, int elements)
	{
		Parameter param = _model.getParameter(name);
		assertEquals(elements, param.getTuples().size());
	}
	
}
