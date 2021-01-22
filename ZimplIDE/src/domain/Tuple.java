package domain;

import java.util.ArrayList;

// Represents a tuple of elements

public class Tuple
{
	private Domain _domain;
	private ArrayList<Element> _elements;
	
	public Tuple(Domain domain)
	{
		_domain = domain;
	}
	
	public void set(int index, Element element)
	{
		if( index < 0 || index >= _domain.size())
			throw new IllegalArgumentException("The index " + index + " does not correspond to the associated domain");
		
		if( _domain.get(index).contains(element) == false)
			throw new IllegalArgumentException("The element " + element + " does not belong to the set " + _domain.get(index).getName());
		
		_elements.set(index, element);
	}
	
	public Element get(int index)
	{
		if( index < 0 || index >= _domain.size())
			throw new IllegalArgumentException("The index " + index + " does not correspond to the associated domain");
		
		return _elements.get(index);
	}
}
