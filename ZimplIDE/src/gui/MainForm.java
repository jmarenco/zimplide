package gui;

import domain.*;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class MainForm
{
	public MainForm()
	{
		Model model = sampleModel();
		setLookAndFeel();
		
		DataTabs tabs = new DataTabs();
		tabs.addTab(model.getSet("A"));
		tabs.addTab(model.getSet("B"));
		tabs.addTab(model.getSet("C"));
		
	    JFrame frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.getContentPane().add(tabs);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void setLookAndFeel()
	{
		try
		{
			FlatLightLaf.install();
			UIManager.setLookAndFeel(new FlatLightLaf());
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
		
		model.add(A);
		model.add(B);
		model.add(C);
		
		return model;
	}
}
