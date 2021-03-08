package process;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFile
{
	private String _fileName;
	private List<String> _lines;
	
	public TextFile(String fileName)
	{
		read(fileName);
	}
	
	public TextFile(List<String> lines)
	{
		_lines = lines;
	}
	
	public void read(String fileName)
	{
		_fileName = fileName;
		
		try
		{
			FileInputStream fis = new FileInputStream(fileName);
			Scanner scanner = new Scanner(fis);

			_lines = new ArrayList<String>();
			
			while( scanner.hasNextLine() )
				_lines.add(scanner.nextLine());
			
			scanner.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void write()
	{
		write(_fileName);
	}
	
	public void write(String fileName)
	{
		_fileName = fileName;
		
		try
		{
			FileOutputStream fos = new FileOutputStream(fileName);
			OutputStreamWriter out = new OutputStreamWriter(fos);
			
			for(String str: _lines)
				out.write(str + "\r\n");
			
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String> getLines()
	{
		return _lines;
	}
}
