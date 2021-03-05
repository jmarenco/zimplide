package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Domain;
import domain.Model;
import domain.Variable;

public class ZimplVariableParser
{
	private Model _model;
	private String _file;
	
	public ZimplVariableParser(Model model, String file)
	{
		_model = model;
		_file = file;
	}
	
	public void parse()
	{
		String literals = ZimplAuxiliaryParser.literals;
		
		Pattern complete = Pattern.compile("var\\s+(\\w+)(\\[(" + literals + ")\\])?((\\s*(<=|>=)\\s*\\d+) | (\\s*binary) | (\\s*integer))*;");
		Matcher matcher = complete.matcher(_file);
		
		while( matcher.find() )
		{
			Domain domain = new Domain();

			if( matcher.group(3) != null)
			{
				for(String str: ZimplAuxiliaryParser.parseLiterals(matcher.group(3)))
					domain.addSet(_model.getSet(str));
			}
			
			Variable variable = new Variable(matcher.group(1).trim(), domain);
			_model.add(variable);
		}
	}
}
