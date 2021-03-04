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
		Pattern complete = Pattern.compile("set\\s+(\\w+)(\\s*:=\\s*\\{\\s*" + ZimplAuxiliaryParser.literals + "\\s*\\})?\\s*;");
		Matcher matcher = complete.matcher(_file);
		
		while( matcher.find() )
		{
			Set set = new Set(matcher.group(1).trim());
			if( matcher.group(3) != null )
			{
				for(String str: ZimplAuxiliaryParser.parseLiterals(matcher.group(3)))
					set.add(new Element(str));
			}
			
			_model.add(set);
		}
	}
	
	public String fill()
	{
		String ret = _file;
		for(Set set: _model.getSets())
		{
			String regex = "set\\s+" + set.getName() + "(\\s*:=\\s*\\{\\s*" + ZimplAuxiliaryParser.literals + "\\s*\\})?\\s*;";
			ret = ret.replaceAll(regex, set.toString());
		}
		
		return ret;
	}
}
