package domain;

import java.util.ArrayList;

// Represents the domain of a parameter or a variable in the model

public class Domain
{
	private ArrayList<Set> _sets;
	
	public Domain()
	{
		_sets = new ArrayList<Set>();
	}

	public void addSet(Set set)
	{
		_sets.add(set);
	}
	
	public int size()
	{
		return _sets.size();
	}
	
	public Set get(int index)
	{
		if( index < 0 || index >= this.size())
			throw new IllegalArgumentException("The index " + index + " does not correspond to the associated domain");
		
		return _sets.get(index);
	}
}
