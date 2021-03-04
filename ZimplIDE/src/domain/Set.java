package domain;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Represents a set in the model

public class Set
{
	private String _name;
	private ArrayList<Element> _elements;
	private Boolean _isNumeric; // Cache
	
	public Set(String name)
	{
		_name = name;
		_elements = new ArrayList<Element>();
		_isNumeric = null;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public void clear()
	{
		_elements.clear();
		_isNumeric = null;
	}
	
	public void add(Element element)
	{
		_elements.add(element);
		_isNumeric = null;
	}
	
	public void add(String str)
	{
		_elements.add(new Element(str));
		_isNumeric = null;
	}
	
	public boolean contains(Element element)
	{
		return _elements.contains(element);
	}
	
	public ArrayList<Element> getElements()
	{
		return _elements;
	}
	
	public boolean isNumeric()
	{
		if( _isNumeric == null )
			_isNumeric = allNumeric();
		
		return _isNumeric;
	}
	
	private boolean allNumeric()
	{
		Pattern pattern = Pattern.compile("[+-]?\\d*(\\.\\d*)?");
		
		for(Element element: _elements)
		{
			Matcher matcher = pattern.matcher(element.toString());
			
			if( matcher.matches() == false )
				return false;
		}
		
		return true;
	}

	@Override public String toString()
	{
		String delimiter = isNumeric() ? "" : "\"";
		String elements = "";
		
		for(Element element: _elements)
			elements += (elements.length() > 0 ? ", " : "") + delimiter + element + delimiter;
		
		return "set " + _name + " := { " + elements + " };";
	}
}
