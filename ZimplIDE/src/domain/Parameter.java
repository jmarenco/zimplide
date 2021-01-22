package domain;

// Represents a parameter in the model

public class Parameter
{
	private String _name;
	private Domain _domain;
	
	public Parameter(String name, Domain domain)
	{
		_name = name;
		_domain = domain;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public Domain getDomain()
	{
		return _domain;
	}
}
