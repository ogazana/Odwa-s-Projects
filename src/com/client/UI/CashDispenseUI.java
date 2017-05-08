package com.client.UI;

import com.serverside.CashDispenseDao.CashDepenserDao;
import com.serverside.Exceptions.IncorrectRandValeException;
import com.serviside.CashDispenseService.CashDispenseService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CashDispenseUI extends JFrame implements ActionListener {

	private JLabel label1, label2;
	private JTextField text1;
	private JButton SUBMIT;
	private JPanel panel;
	private final float AMOUNTDUE = 25.00f;
	private String printInformation = new String();

	CashDispenseUI() {
		label1 = new JLabel();
		label1.setText("Amount Due: " + AMOUNTDUE);
		label2 = new JLabel();
		label2.setText("Capture rand note denomination");
		text1 = new JTextField(15);
		text1.setPreferredSize(new Dimension(200, 24));
		SUBMIT = new JButton("Submit");

		panel = new JPanel(new GridLayout(4, 1));
		panel.add(label1);
		panel.add(text1);
		panel.add(label2);
		panel.add(text1);
		panel.add(SUBMIT);
		SUBMIT.setPreferredSize(new Dimension(40, 40));
		add(panel, BorderLayout.CENTER);
		SUBMIT.addActionListener(this);
		setTitle("Cash Dispense");
		this.setSize(300, 300);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder sb = new StringBuilder();
		try {
			if (!text1.getText().equals("")) {
				String amount = text1.getText();
				float captureAmount = Float.parseFloat(amount);
				CashDispenseService cashDispenseService = new CashDispenseService();
				if (captureAmount >= AMOUNTDUE) {
				sb = cashDispenseService.calculateDispanseAmount(captureAmount, AMOUNTDUE);
				} else {
					JOptionPane.showMessageDialog(this, "The amount you entered is less that the amount due, please enter"
							+ " an amount greater or equal to the due amount",
					"Error", JOptionPane.ERROR_MESSAGE);
					text1.setText(" ");
				}
			}

		}
		catch (IncorrectRandValeException e1) {
			JOptionPane.showMessageDialog(this, "The rand note value is incorrect",
					"Error", JOptionPane.ERROR_MESSAGE);
			text1.setText(" ");
		}
		catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(this, "Please enter numbers only", "error", JOptionPane.ERROR_MESSAGE);
			text1.setText(" ");
		}
		catch (Exception e3) {
			JOptionPane.showMessageDialog(this, "Please ensure you provided corrected information", "error", JOptionPane.ERROR_MESSAGE);
			text1.setText(" ");
		}
		printInformation = sb.toString();
		if (sb.length() != 0) {
			BreakDownScreen bd = new BreakDownScreen(printInformation);
			bd.setVisible(true);
		}
	}

	public StringBuilder calculateDispanseAmount() {

		StringBuilder sb = new StringBuilder();
		if (!text1.getText().equals("")) {
			String amount = text1.getText();
			float captureAmount = Float.parseFloat(amount);
			CashDepenserDao dao = new CashDepenserDao();

			try {
				sb = dao.calculateDispanseAmount(captureAmount, AMOUNTDUE);
			}
			catch (IncorrectRandValeException e1) {
				JOptionPane.showMessageDialog(this, "The rand note value is incorrect",
						"Error", JOptionPane.ERROR_MESSAGE);
				text1.setText(" ");
			}
		}

		return sb;
	}

}
