package parser;

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
}
