package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.Domain;
import domain.Set;
import domain.Tuple;
import domain.Variable;

public class VariablePanel extends JPanel implements DataPanel
{
	// Serial version
	private static final long serialVersionUID = 1L;
	
	// Parameter and its domain
	private Variable _variable;
	private Domain _domain;
	
	// Visual controls
	private JTable _table;
	private DefaultTableModel _tableModel;
	
	public VariablePanel(Variable variable)
	{
		super(new BorderLayout());
		PanelUtils.addMargins(this);
		
		_variable = variable;
		_domain = variable.getDomain();

		_table = new JTable();
		_table.setBounds(30,40,200,300);

		JScrollPane sp = new JScrollPane(_table);    
		add(sp, BorderLayout.CENTER);
		
		updateControl();
	}
	
	public void updateData()
	{
	}

	public void updateControl()
	{
		_tableModel = new DefaultTableModel();
		
		for(Set set: _domain.sets())
			_tableModel.addColumn(set.getName());
		
		_tableModel.addColumn(_variable.getName());
		
		for(Tuple tuple: _variable.getDomainTuples())
			_tableModel.addRow(Aux.stringArray(tuple, _variable.getValue(tuple)));
		
		_table.setModel(_tableModel);
	}
}
