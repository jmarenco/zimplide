package gui;

import domain.*;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.formdev.flatlaf.*;

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
		tabs.addTab(model.getParameter("cost"));
		
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
			FlatIntelliJLaf.install();
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
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
		
		Domain domain = new Domain();
		domain.addSet(A);
		domain.addSet(B);
		
		Parameter parameter = new Parameter("cost", domain);
		parameter.setValue(new Tuple(domain, "pepe", "cat"), 4.5);
		parameter.setValue(new Tuple(domain, "pepe", "hat"), 4.6);
		parameter.setValue(new Tuple(domain, "pipi", "cat"), 4.7);
		
		model.add(A);
		model.add(B);
		model.add(C);
		model.add(parameter);
		
		return model;
	}
}
