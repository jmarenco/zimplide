package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import domain.Parameter;
import domain.Set;

public class DataTabs extends JPanel
{
	// Serial version
	private static final long serialVersionUID = 1L;
	
	// Tab control and selected tab
	private JTabbedPane _tabbedPane;
	private DataPanel _selectedPanel = null;
	
	// Constructor	
	public DataTabs()
	{
		super(new BorderLayout());
		PanelUtils.addMargins(this);
		
		_tabbedPane = new JTabbedPane();
		_tabbedPane.addChangeListener(e -> tabChanged(e));
		
		add(_tabbedPane, BorderLayout.CENTER);
	}
	
	// Adds a new data tab
	public void addTab(Set set)
	{
		SetPanel setPanel = new SetPanel(set);
		_tabbedPane.addTab(set.getName(), setPanel);
	}
	public void addTab(Parameter parameter)
	{
		ParameterPanel parameterPanel = new ParameterPanel(parameter);
		_tabbedPane.addTab(parameter.getName(), parameterPanel);
	}
	
	// Fires when tab is changed
	public void tabChanged(ChangeEvent e)
	{
		if (_selectedPanel != null)
			_selectedPanel.updateData();
		
		if (_tabbedPane.getSelectedComponent() instanceof DataPanel)
			_selectedPanel = ((DataPanel)_tabbedPane.getSelectedComponent());
	}
}
