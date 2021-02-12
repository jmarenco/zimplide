package gui;

import domain.*;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainForm
{
	public MainForm()
	{
		Model model = sampleModel();
		setLookAndFeel();
		
	    JFrame frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.getContentPane().add(new SetPanel(model.getSet("A")));
		frame.setVisible(true);
	}
	
	private void setLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		new MainForm();
	}
	
	private static Model sampleModel()
	{
		Model model = new Model();
		Set A = new Set("A");
		A.add(new Element("pepe"));
		A.add(new Element("papa"));
		A.add(new Element("pipi"));
		
		model.add(A);
		return model;
	}
}
