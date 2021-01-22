package domain;

import java.util.ArrayList;

// Represents all the elements in a model

public class Model
{
	private ArrayList<Set> _sets;
	private ArrayList<Parameter> _parameters;
	private ArrayList<Variable> _variables;
	
	public Model()
	{
		_sets = new ArrayList<Set>();
		_parameters = new ArrayList<Parameter>();
		_variables = new ArrayList<Variable>();
	}
	
	public void add(Set set)
	{
		_sets.add(set);
	}

	public void add(Parameter parameter)
	{
		_parameters.add(parameter);
	}
	
	public void add(Variable variable)
	{
		_variables.add(variable);
	}
	
	public ArrayList<Set> getSets()
	{
		return _sets;
	}
	
	public ArrayList<Parameter> getParameters()
	{
		return _parameters;
	}
	
	public ArrayList<Variable> getVariables()
	{
		return _variables;
	}
}
