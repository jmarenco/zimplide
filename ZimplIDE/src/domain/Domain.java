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
		if( set == null )
			throw new IllegalArgumentException("A null set was added to a parameter");

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
	
	public ArrayList<Set> sets()
	{
		return _sets;
	}
	
	@Override public String toString()
	{
		String elements = "";
		for(Set set: _sets)
			elements += (elements.length() > 0 ? "*" : "") + set.getName();
		
		return "[" + elements + "]";
	}	
}
