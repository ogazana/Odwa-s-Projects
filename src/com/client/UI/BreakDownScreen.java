package com.client.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BreakDownScreen extends JFrame implements ActionListener {

	private JLabel label1, label;
	private JButton reset;
	private JPanel panel;

	BreakDownScreen(String printInformation) {
		label1 = new JLabel();
		label1.setText("Break down of cash to dispense is:");
		String x[] = printInformation.split(",");
		panel = new JPanel(new GridLayout(8, 1));
		panel.add(label1);
		for (int y = 0; y < x.length; y++) {
			label = new JLabel();
			label.setText(x[y]);
			panel.add(label);
		}
		reset = new JButton("Reset");
		panel.add(reset);
		add(panel, BorderLayout.CENTER);
		reset.addActionListener(this);
		this.setTitle("Cash Dispense");
		this.setSize(300, 300);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
	}

	public JButton getReset() {
		return reset;
	}

	public void setReset(JButton reset) {
		this.reset = reset;
	}

}
