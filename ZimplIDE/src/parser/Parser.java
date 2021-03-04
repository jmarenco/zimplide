package parser;

import java.util.List;

import domain.Model;

public interface Parser
{
	// Parse sets, parameters, and variable definitions from the file
	Model parse(List<String> fileContent);
	
	// Replace sets and parameters in the file with the values from the model
	List<String> fill(List<String> fileContent, Model model);
}
