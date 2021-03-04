package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import domain.Domain;
import domain.Element;
import domain.Parameter;
import domain.Set;
import domain.Tuple;

public class ParameterPanel extends JPanel implements DataPanel
{
	// Serial version
	private static final long serialVersionUID = 1L;
	
	// Parameter and its domain
	private Parameter _parameter;
	private Domain _domain;
	
	// Visual controls
	private JTable _table;
	private DefaultTableModel _tableModel;
	
	public ParameterPanel(Parameter parameter)
	{
		super(new BorderLayout());
		PanelUtils.addMargins(this);
		
		_parameter = parameter;
		_domain = parameter.getDomain();
		_tableModel = new DefaultTableModel();
		
		for(Set set: _domain.sets())
			_tableModel.addColumn(set.getName());
		
		_tableModel.addColumn(parameter.getName());
		
		for(Tuple tuple: _parameter.getDomainTuples())
			_tableModel.addRow(stringArray(tuple, _parameter.getValue(tuple)));
		
		_table = new JTable();
		_table.setModel(_tableModel);
		_table.setBounds(30,40,200,300);
		
		JScrollPane sp = new JScrollPane(_table);    
		add(sp, BorderLayout.CENTER);
		
		createPopup();
		
		new ExcelAdapter(_table);
	}
	
	private String[] stringArray(Tuple tuple, double value)
	{
		String[] ret = new String[tuple.size() + 1];
		
		for(int i=0; i<tuple.size(); ++i)
			ret[i] = tuple.get(i).toString();
		
		ret[tuple.size()] = Double.toString(value);
		return ret;
	}
	
	private void createPopup()
	{
		JPopupMenu editMenu = new JPopupMenu();
		JMenuItem addRowMenuItem = new JMenuItem("Add row");
		JMenuItem deleteRowMenuItem = new JMenuItem("Delete row");
		
		addRowMenuItem.addActionListener(e -> addRow());
		deleteRowMenuItem.addActionListener(e -> deleteRow());

		editMenu.add(addRowMenuItem);
		editMenu.add(deleteRowMenuItem);
		
		_table.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if( SwingUtilities.isRightMouseButton(e) )
					editMenu.show(_table, e.getX(), e.getY());
			}
		});
	}
	
	private void addRow()
	{
		_tableModel.addRow(new String[] { });
	}
	
	private void deleteRow()
	{
		_tableModel.removeRow(_table.getSelectedRow());
	}
	
	public void updateData()
	{
		_parameter.clear();
		
		for(int i=0; i<_tableModel.getRowCount(); ++i)
		{
			Tuple tuple = new Tuple(_domain);
			
			for(int j=0; j<_domain.size(); ++j)
				tuple.set(j, new Element((String)_tableModel.getValueAt(i, j)));
			
			_parameter.setValue(tuple, Aux.toDouble((String)_tableModel.getValueAt(i, _domain.size())));
		}
		
		System.out.println(_parameter);
	}
}
