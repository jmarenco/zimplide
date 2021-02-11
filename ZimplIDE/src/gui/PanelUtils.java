package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelUtils
{
	public static void addMargins(JPanel panel)
	{
		JPanel leftPanel = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		leftPanel.setBounds(0, 0, 50, panel.getHeight());
		rightPanel.setBounds(0, 0, 50, panel.getHeight());
		topPanel.setBounds(0, 0, panel.getWidth(), 50);
		bottomPanel.setBounds(0, 0, panel.getWidth(), 50);
		
		panel.add(leftPanel, BorderLayout.WEST);
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(rightPanel, BorderLayout.EAST);
		panel.add(bottomPanel, BorderLayout.SOUTH);
	}

}
