package gui;

import process.ModelFile;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.*;

public class MainForm
{
	private JFrame _frame;
	private ModelPanel _modelPanel;
	private ModelFile _zimplFile;
	
	public MainForm()
	{
		setLookAndFeel();
		
	    _frame = new JFrame();
		_frame.setBounds(100, 100, 800, 500);
		_frame.setJMenuBar(createMenuBar());
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setVisible(true);
		
    	_modelPanel = new ModelPanel();
    	_frame.getContentPane().add(_modelPanel, BorderLayout.CENTER);

    	updateTitle();
	}
	
	private void setLookAndFeel()
	{
		try
		{
			FlatIntelliJLaf.install();
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private JMenuBar createMenuBar()
	{
		// https://iconarchive.com
		JMenuBar menuBar = new JMenuBar();

		JButton openBtn = new JButton("Open");
		openBtn.setIcon(new ImageIcon("assets/open-icon.png"));
		openBtn.addActionListener(t -> open());
		openBtn.setFocusable(false);

		JButton saveBtn = new JButton("Save");
		saveBtn.setIcon(new ImageIcon("assets/save-icon.png"));
		saveBtn.addActionListener(t -> save());
		saveBtn.setFocusable(false);

		JButton saveAsBtn = new JButton("Save as");
		saveAsBtn.setIcon(new ImageIcon("assets/saveas-icon.png"));
		saveAsBtn.addActionListener(t -> saveAs());
		saveAsBtn.setFocusable(false);

		JButton solveBtn = new JButton("Solve");
		solveBtn.setIcon(new ImageIcon("assets/solve-icon.png"));
		solveBtn.addActionListener(t -> solve());
		solveBtn.setFocusable(false);
		
		// For faster testing
		JButton dietBtn = new JButton("Diet test");
		dietBtn.setIcon(new ImageIcon("assets/open-icon.png"));
		dietBtn.addActionListener(t -> dietTest());
		dietBtn.setFocusable(false);
		menuBar.add(dietBtn);

		menuBar.add(openBtn);
		menuBar.add(saveBtn);
		menuBar.add(saveAsBtn);
		menuBar.add(solveBtn);
		
		return menuBar;
	}
	
	private void dietTest()
	{
    	_zimplFile = new ModelFile("/home/jmarenco/git/zimplide/ZimplIDE/examples/diet.zpl");
    	_modelPanel.setModel(_zimplFile.getModel());

    	updateTitle();
	}
	
	private void open()
	{
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Zimpl models", "zpl");
        chooser.setFileFilter(filter);

        if( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION )
        {
        	String file = chooser.getSelectedFile().getPath();
        	
        	_zimplFile = new ModelFile(file);
        	_modelPanel.setModel(_zimplFile.getModel());

        	updateTitle();
        	_frame.invalidate();
        }
	}
	
	private void save()
	{
		if( _zimplFile != null )
			_zimplFile.write();
	}
	
	private void saveAs()
	{
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Zimpl models", "zpl");
        chooser.setFileFilter(filter);

        if( _zimplFile != null && chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION )
        {
        	_zimplFile.write(chooser.getSelectedFile().getPath());
        	updateTitle();
        }
	}
	
	private void solve()
	{
		_modelPanel.updateCurrentTab();
		
		SolverForm solverForm = new SolverForm(_zimplFile);
		solverForm.solve();
	}
	
	private void updateTitle()
	{
    	_frame.setTitle("ZimplIDE 0.01" + (_zimplFile != null ? " - " + _zimplFile.getFileName() : ""));
	}
	
	public static void main(String[] args)
	{
		new MainForm();
	}
}
