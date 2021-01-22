package parser;

import java.util.List;

import domain.Model;

public interface Parser
{
	Model parse(List<String> fileContent);
}
