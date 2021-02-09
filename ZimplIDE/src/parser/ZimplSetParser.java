package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Element;
import domain.Model;
import domain.Set;

public class ZimplSetParser
{
	private Model _model;
	private String _file;
	
	public ZimplSetParser(Model model, String file)
	{
		_model = model;
		_file = file;
	}
	
	public void parse()
	{
		String literal = "(\"(\\w+)\"|(\\w+))";
		String literals = "(" + literal + "(\\s*,\\s*" + literal + ")*)?";
		
		Pattern complete = Pattern.compile("set\\s+(\\w+)(\\s*:=\\s*\\{\\s*" + literals + "\\s*\\})?\\s*;");
		Pattern elements = Pattern.compile(literal);
		
		Matcher matcher = complete.matcher(_file);
		
		while( matcher.find() )
		{
			Set set = new Set(matcher.group(1).trim());
			if( matcher.group(3) != null )
			{
				Matcher rematcher = elements.matcher(matcher.group(3));
				
				while( rematcher.find() )
				{
					if( rematcher.group(2) != null)
						set.add(new Element(rematcher.group(2).trim()));
					else if( rematcher.group(3) != null)
						set.add(new Element(rematcher.group(3).trim()));
				}
			}
			
			_model.add(set);
		}
	}
}
