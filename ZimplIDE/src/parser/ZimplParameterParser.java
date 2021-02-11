package parser;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Domain;
import domain.Model;
import domain.Parameter;
import domain.Tuple;

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
		String values = ZimplAuxiliaryParser.values;
		
		Pattern complete = Pattern.compile("param\\s+(\\w+)(\\[(" + literals + ")\\])?(\\s*:=\\s*" + values + "\\s*)?\\s*;");
		Matcher matcher = complete.matcher(_file);
		
		while( matcher.find() )
		{
			Domain domain = new Domain();

			if( matcher.group(3) != null)
			{
				for(String str: ZimplAuxiliaryParser.parseLiterals(matcher.group(3)))
					domain.addSet(_model.getSet(str));
			}
			
			if( matcher.group(13) != null )
			{
				Map<Tuple, Double> valueMap = ZimplAuxiliaryParser.parseValues(domain, matcher.group(13));
				Parameter param = new Parameter(matcher.group(1).trim(), domain, valueMap);
				_model.add(param);
			}
			else
			{
				Parameter param = new Parameter(matcher.group(1).trim(), domain);
				_model.add(param);
			}
			
		}
	}}
