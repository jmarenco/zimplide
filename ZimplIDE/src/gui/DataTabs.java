package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import domain.Set;

public class DataTabs extends JPanel
{
	// Serial version
	private static final long serialVersionUID = 1L;
	
	// Tab control
	JTabbedPane _tabbedPane;
	
	// Constructor	
	public DataTabs()
	{
		super(new BorderLayout());
		PanelUtils.addMargins(this);
		
		_tabbedPane = new JTabbedPane();
		add(_tabbedPane, BorderLayout.CENTER);
	}
	
	// Adds a new data tab
	public void addTab(Set set)
	{
		SetPanel setPanel = new SetPanel(set);
		_tabbedPane.addTab(set.getName(), setPanel);
	}
}
