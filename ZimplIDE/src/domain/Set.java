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
}
