package parser;

import java.util.Arrays;
import java.util.List;

import domain.Model;

public class ZimplParser implements Parser
{
	public Model parse(List<String> fileContent)
	{
		Model ret = new Model();
		String file = String.join("\n", fileContent);
		
		ZimplSetParser setParser = new ZimplSetParser(ret, file);
		setParser.parse();
		
		ZimplParameterParser parameterParser = new ZimplParameterParser(ret, file);
		parameterParser.parse();
		
		ZimplVariableParser variableParser = new ZimplVariableParser(ret, file);
		variableParser.parse();

		return ret;
	}
	
	public List<String> fill(List<String> fileContent, Model model)
	{
		String file = String.join("\n", fileContent);
		
		ZimplSetParser setParser = new ZimplSetParser(model, file);
		file = setParser.fill();		
		
		return Arrays.asList(file.split("\n"));
	}	
}
