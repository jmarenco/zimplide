package parser;

import java.util.List;

import domain.Model;

public class ZimplParser implements Parser
{
	public Model parse(List<String> fileContent)
	{
		Model ret = new Model();
		String file = String.join("\n", fileContent);
		
		parseSets(ret, file);
		parseParameters(ret, file);
		parseVariables(ret, file);
		
		return ret;
	}
	
	private void parseSets(Model model, String file)
	{
		
	}

	private void parseParameters(Model model, String file)
	{
		
	}

	private void parseVariables(Model model, String file)
	{
		
	}
}
