package gui;

import domain.Tuple;

public class Aux
{
	public static double toDouble(String str)
	{
		double ret = 0;
		
		try
		{
			ret = Double.parseDouble(str);
		}
		catch (Exception e)
		{
		}
		
		return ret;
	}
	
	public static String[] stringArray(Tuple tuple, double value)
	{
		String[] ret = new String[tuple.size() + 1];
		
		for(int i=0; i<tuple.size(); ++i)
			ret[i] = tuple.get(i).toString();
		
		ret[tuple.size()] = Double.toString(value);
		return ret;
	}	
}
