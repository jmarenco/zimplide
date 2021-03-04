package gui;

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
}
