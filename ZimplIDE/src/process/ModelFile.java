package process;

import java.util.List;

import domain.Model;
import parser.ZimplParser;

// Represents the contents of a .zpl file together with the derived Model instance
public class ModelFile
{
	private String _fileName;
	private TextFile _textFile;
	private Model _model;
	
	// Auxiliary for parsing
	private ZimplParser _parser = new ZimplParser();

	public ModelFile(String fileName)
	{
		_fileName = fileName;
		_textFile = new TextFile(fileName);
		_model = _parser.parse(_textFile.getLines());
	}
	
	public void write()
	{
		write(_fileName);
	}
	
	public void write(String fileName)
	{
		writeTemporal(fileName);
		_fileName = fileName;
	}

	public void writeTemporal(String fileName)
	{
		List<String> lines = _parser.fill(_textFile.getLines(), _model);
		
		_textFile = new TextFile(lines);
		_textFile.write(fileName);
	}
	
	public Model getModel()
	{
		return _model;
	}
	
	public String getFileName()
	{
		return _fileName;
	}
}
