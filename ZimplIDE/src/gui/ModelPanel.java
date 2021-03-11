package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import domain.Model;
import domain.Parameter;
import domain.Set;
import domain.Variable;

public class ModelPanel  extends JPanel
{
	// Serial version
	private static final long serialVersionUID = 1L;
	
	// Model
	private Model _model;
	
	// Tab control
	private DataTabs _dataTabs;

	// Constructor
	public ModelPanel()
	{
		super(new BorderLayout());
		PanelUtils.addMargins(this);
		
		_dataTabs = new DataTabs();
		add(_dataTabs, BorderLayout.CENTER);
	}
	
	public void setModel(Model model)
	{
		_model = model;
		_dataTabs.clear();
		
		for(Set set: _model.getSets())
			_dataTabs.addTab(set);

		for(Parameter param: _model.getParameters())
			_dataTabs.addTab(param);

		for(Variable variable: _model.getVariables())
			_dataTabs.addTab(variable);
	}
	
	public void updateCurrentTab()
	{
		_dataTabs.updateCurrentTab();
	}
}
