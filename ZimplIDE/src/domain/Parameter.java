package domain;

import java.util.HashMap;
import java.util.Map;

// Represents a parameter in the model

public class Parameter
{
	private String _name;
	private Domain _domain;
	private Map<Tuple, Double> _values;
	
	public Parameter(String name, Domain domain)
	{
		_name = name;
		_domain = domain;
		_values = new HashMap<Tuple, Double>();
	}

	public Parameter(String name, Domain domain, Map<Tuple, Double> values)
	{
		_name = name;
		_domain = domain;
		_values = values;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public Domain getDomain()
	{
		return _domain;
	}
	
	public void clear()
	{
		_values.clear();
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
	
	@Override public String toString()
	{
		String elements = "";
		for(Tuple tuple: getDomainTuples())
			elements += (elements.length() > 0 ? ", " : "") + tuple + " " + getValue(tuple);
		
		return "param " + _name + _domain + " := " + elements + ";";
	}
}
