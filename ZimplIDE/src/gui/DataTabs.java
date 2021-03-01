package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

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
	
	// Fires when tab is changed
	public void tabChanged(ChangeEvent e)
	{
		if (_selectedPanel != null)
			_selectedPanel.updateData();
		
		if (_tabbedPane.getSelectedComponent() instanceof SetPanel)
			_selectedPanel = ((SetPanel)_tabbedPane.getSelectedComponent());
	}
}
