package parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZimplAuxiliaryParser
{
	public static String literal = "(\"(\\w+)\"|(\\w+))";
	public static String literals = "(" + literal + "(\\s*[,\\*]\\s*" + literal + ")*)?";
	
	private static Pattern literalMatcher = null;
	
	public static ArrayList<String> parseCommaSeparatedLiterals(String text)
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
}
