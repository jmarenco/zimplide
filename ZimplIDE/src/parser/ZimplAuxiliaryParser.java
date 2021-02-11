package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Domain;
import domain.Element;
import domain.Tuple;

public class ZimplAuxiliaryParser
{
	public static String literal = "(\"(\\w+)\"|(\\w+))";
	public static String literals = "(" + literal + "(\\s*[,\\*]\\s*" + literal + ")*)?";
	public static String value = "(<(" + literals + ")>)?\\s*(\\d+)";
	public static String values = "(" + value + "(\\s*,\\s*" + value + ")*)?";
	
	private static Pattern literalMatcher = null;
	private static Pattern valueMatcher = null;
	
	// Parses a comma- or asterisk-separated list of literals into an array list
	public static ArrayList<String> parseLiterals(String text)
	{
		if( literalMatcher == null )
			literalMatcher = Pattern.compile(literal);
		
		ArrayList<String> ret = new ArrayList<String>();
		Matcher matcher = literalMatcher.matcher(text);
		
		while( matcher.find() )
		{
			if( matcher.group(2) != null )
				ret.add(matcher.group(2).trim());
			else if( matcher.group(3) != null)
				ret.add(matcher.group(3).trim());
		}
		
		return ret;
	}
	
	// Parses a comma-separated list of parameter values into a map
	public static Map<Tuple, Double> parseValues(Domain domain, String text)
	{
		if( valueMatcher == null )
			valueMatcher = Pattern.compile(value);
		
		Map<Tuple, Double> ret = new HashMap<Tuple, Double>();
		Matcher matcher = valueMatcher.matcher(text);
		
		while( matcher.find() )
		{
			Tuple tuple = new Tuple(domain);
			double value = Double.parseDouble(matcher.group(11));
			
			if( matcher.group(2) != null )
			{
				ArrayList<String> domainValues = parseLiterals(matcher.group(2));
				
				for(int i=0; i<domainValues.size(); ++i)
					tuple.set(i, new Element(domainValues.get(i)));
			}
			
			ret.put(tuple, value);
		}
		
		return ret;
	}
}
