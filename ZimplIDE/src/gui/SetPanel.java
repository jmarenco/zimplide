package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.Element;
import domain.Set;

public class SetPanel extends JPanel
{
	// Serial version
	private static final long serialVersionUID = 1L;
	
	// Set
	private Set _set;
	
	// Visual controls
	private JTable _table;
	private DefaultTableModel _tableModel;
	
	public SetPanel(Set set)
	{
		super(new BorderLayout());
		PanelUtils.addMargins(this);
		
		_set = set;
		_tableModel = new DefaultTableModel();
		_tableModel.addColumn(_set.getName());
		
		for(Element element: _set.getElements())
			_tableModel.addRow(new String[] { element.toString() });
		
		_table = new JTable();
		_table.setModel(_tableModel);
		_table.setBounds(30,40,200,300);
		
		JScrollPane sp = new JScrollPane(_table);    
		add(sp, BorderLayout.CENTER);
	}
}
