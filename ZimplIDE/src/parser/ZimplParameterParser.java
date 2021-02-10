package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Domain;
import domain.Element;
import domain.Model;
import domain.Parameter;

public class ZimplParameterParser
{
	private Model _model;
	private String _file;
	
	public ZimplParameterParser(Model model, String file)
	{
		_model = model;
		_file = file;
	}
	
	public void parse()
	{
		String literals = ZimplAuxiliaryParser.literals;
		String value = "(<" + literals + ">)?\\s*(\\d+)";
		String values = "(" + value + "(\\s*,\\s*" + value + ")*)?";
		
		Pattern complete = Pattern.compile("param\\s+(\\w+)(\\[(" + literals + ")\\])?(\\s*:=\\s*" + values + "\\s*)?\\s*;");
		Matcher matcher = complete.matcher(_file);
		
		while( matcher.find() )
		{
			String name = matcher.group(1).trim();
			Domain domain = new Domain();
			
			for(int i=0; i<=matcher.groupCount(); ++i)
				System.out.println(i + " - " + matcher.group(i));
			
			Parameter param = new Parameter(name, domain);
//			if( matcher.group(3) != null )
//			{
//				Matcher rematcher = elements.matcher(matcher.group(3));
//				
//				while( rematcher.find() )
//				{
//					if( rematcher.group(2) != null)
//						set.add(new Element(rematcher.group(2).trim()));
//					else if( rematcher.group(3) != null)
//						set.add(new Element(rematcher.group(3).trim()));
//				}
//			}
			
			_model.add(param);
		}
	}}
