package domain;

// Represents an element within a set

public class Element
{
	private String _value;
	
	public Element(String value)
	{
		_value = value;
	}
	
	public String getValue()
	{
		return _value;
	}

	@Override
	public int hashCode()
	{
		return _value.hashCode();
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
		
		Element other = (Element) obj;
		return this.getValue().equals(other.getValue());
	}
	
	@Override
	public String toString()
	{
		return _value;
	}
}
