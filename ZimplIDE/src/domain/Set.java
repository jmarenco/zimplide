package domain;

import java.util.ArrayList;

// Represents a set in the model

public class Set
{
	private String _name;
	private ArrayList<Element> _elements;
	
	public Set(String name)
	{
		_name = name;
		_elements = new ArrayList<Element>(); 
	}
	
	public String getName()
	{
		return _name;
	}
	
	public void add(Element element)
	{
		_elements.add(element);
	}
	
	public void add(String str)
	{
		_elements.add(new Element(str));
	}
	
	public boolean contains(Element element)
	{
		return _elements.contains(element);
	}
	
	@Override public String toString()
	{
		String elements = "";
		for(Element element: _elements)
			elements += (elements.length() > 0 ? ", " : "") + element;
		
		return "set " + _name + " := { " + elements + " };";
	}
}
