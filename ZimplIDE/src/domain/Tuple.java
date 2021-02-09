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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_domain == null) ? 0 : _domain.hashCode());
		result = prime * result + ((_elements == null) ? 0 : _elements.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;

		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Tuple other = (Tuple) obj;
		
		if (_domain == null)
		{
			if (other._domain != null)
				return false;
		}
		else if (!_domain.equals(other._domain))
			return false;
		
		if (_elements == null)
		{
			if (other._elements != null)
				return false;
		}
		else if (!_elements.equals(other._elements))
			return false;
		
		return true;
	}
}
