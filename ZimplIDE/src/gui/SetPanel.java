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

import domain.Element;
import domain.Set;

public class SetPanel extends JPanel implements DataPanel
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
		_table = new JTable();
		_table.setBounds(30,40,200,300);
		
		JScrollPane sp = new JScrollPane(_table);    
		add(sp, BorderLayout.CENTER);
		
		updateControl();
		createPopup();
		
		new ExcelAdapter(_table);
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
		_set.clear();
		
		for(int i=0; i<_tableModel.getRowCount(); ++i)
			_set.add(new Element((String)_tableModel.getValueAt(i, 0)));
	}

	public void updateControl()
	{
		_tableModel = new DefaultTableModel();
		_tableModel.addColumn(_set.getName());
		
		for(Element element: _set.getElements())
			_tableModel.addRow(new String[] { element.toString() });
		
		_table.setModel(_tableModel);
	}
}
