package domain;

// Represents a variable in the model

public class Variable
{
	private String _name;
	private Domain _domain;
	
	public Variable(String name, Domain domain)
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