package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import process.ModelFile;
import process.Solver;

public class SolverForm implements Solver.Logger
{
	private ModelFile _modelFile;
	private JTextArea _textArea;
	
	public SolverForm(ModelFile modelFile)
	{
		_modelFile = modelFile;
		_textArea = new JTextArea();
		_textArea.setFont(new Font("LM Mono 8", 0, 12));
		
	    JFrame frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setTitle("Solving " + modelFile.getFileName());
		
		JPanel panel = new JPanel(new BorderLayout());
		PanelUtils.addMargins(panel);
		
		JScrollPane sp = new JScrollPane(_textArea);    
		panel.add(sp, BorderLayout.CENTER);

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public void solve()
	{
		Solver solver = new Solver(_modelFile, this);
		SwingUtilities.invokeLater(solver);
	}

	@Override
	public void log(String str)
	{
		_textArea.setText(_textArea.getText() + "\r\n" + str);
	}
}
