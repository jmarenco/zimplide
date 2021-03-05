package domain;

import java.util.HashMap;
import java.util.Map;

// Represents a variable in the model

public class Variable
{
	private String _name;
	private Domain _domain;
	private Map<Tuple, Double> _values;
	
	public Variable(String name, Domain domain)
	{
		_name = name;
		_domain = domain;
		_values = new HashMap<Tuple, Double>();
	}
	
	public String getName()
	{
		return _name;
	}
	
	public Domain getDomain()
	{
		return _domain;
	}
	
	public void setValue(Tuple tuple, double value)
	{
		_values.put(tuple, value);
	}

	public double getValue(Tuple tuple)
	{
		return _values.get(tuple);
	}
	
	public java.util.Set<Tuple> getDomainTuples()
	{
		return _values.keySet();
	}
}