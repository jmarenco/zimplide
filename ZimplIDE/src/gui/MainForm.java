package gui;

import domain.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

import com.formdev.flatlaf.*;

public class MainForm
{
	public MainForm()
	{
		setLookAndFeel();
		
	    JFrame frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setJMenuBar(createMenuBar());
		frame.getContentPane().add(new ModelPanel(sampleModel()));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
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

		menuBar.add(openBtn);
		menuBar.add(saveBtn);
		menuBar.add(saveAsBtn);
		menuBar.add(solveBtn);
		
		return menuBar;
	}
	
	private void open()
	{
		
	}
	
	private void save()
	{
		
	}
	
	private void saveAs()
	{
		
	}
	
	private void solve()
	{
		
	}
	
	public static void main(String[] args)
	{
		new MainForm();
	}
	
	private static Model sampleModel()
	{
		Model model = new Model();
		
		Set A = new Set("A");
		Set B = new Set("B");
		Set C = new Set("C");

		A.add(new Element("pepe"));
		A.add(new Element("papa"));
		A.add(new Element("pipi"));
		
		B.add(new Element("cat"));
		B.add(new Element("bat"));
		B.add(new Element("rat"));
		B.add(new Element("hat"));

		C.add(new Element("calvin"));
		C.add(new Element("hobbes"));
		
		Domain domain = new Domain();
		domain.addSet(A);
		domain.addSet(B);
		
		Parameter parameter = new Parameter("cost", domain);
		parameter.setValue(new Tuple(domain, "pepe", "cat"), 4.5);
		parameter.setValue(new Tuple(domain, "pepe", "hat"), 4.6);
		parameter.setValue(new Tuple(domain, "pipi", "cat"), 4.7);
		
		Variable variable = new Variable("x", domain);
		
		model.add(A);
		model.add(B);
		model.add(C);
		model.add(parameter);
		model.add(variable);
		
		return model;
	}
}
