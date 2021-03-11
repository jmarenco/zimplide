package process;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;

public class Solver implements Runnable
{
	private ModelFile _modelFile;
	private ArrayList<String> _output;
	private ArrayList<String> _errors;
	
	// Temporal file for model
	private static String _temporal = "modeltobesolved.zpl";
	
	// Path to SCIP executable
	private static String _scip = "/home/jmarenco/scipopt/bin/scip";
	
	// Interface for object containing logging method
	public static interface Logger
	{
		void log(String str);
	}
	
	// Logger
	private Logger _logger;
	
	public Solver(ModelFile modelFile, Logger logger)
	{
		_modelFile = modelFile;
		_logger = logger;
	}
	
	@Override
	public void run()
	{
		try
		{
			writeFile();
			runScip();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void writeFile() throws Exception
	{
		File file = new File(_temporal);
		Files.deleteIfExists(file.toPath());

		_modelFile.writeTemporal(_temporal);
	}
	
	private void runScip() throws Exception
	{
		Process p = Runtime.getRuntime().exec(_scip + " -f " + _temporal);

		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader errors = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
		_output = new ArrayList<String>();
		_errors = new ArrayList<String>();

		String line = "";
		while ((line = input.readLine()) != null)
		{
			_output.add(line);
			_logger.log(line);
		}
			
		while ((line = errors.readLine()) != null)
		{
			_errors.add(line);
			_logger.log(line);
		}

		input.close();
		errors.close();
	}
	
	public ArrayList<String> getOutput()
	{
		return _output;
	}
	public ArrayList<String> getErrors()
	{
		return _errors;
	}
}
