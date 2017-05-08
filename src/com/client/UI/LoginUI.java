package com.client.UI;

import com.serverside.Exceptions.InvalidLoginException;
import com.serverside.domain.Login;
import com.serviside.CashDispenseService.CashDispenseService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginUI extends JFrame implements ActionListener {

	private JButton loginButton;
	private JPanel panel;
	private JTextField text1, text2;
	private CashDispenseService cashDispenseService = new CashDispenseService();

	LoginUI() {
		text1 = new JTextField(15);
		text1.setText("Username");
		text1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				text1.setText("");
			}
		});

		text2 = new JPasswordField(15);
		text2.setText("Password");
		text2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				text2.setText("");
			}
		});

		loginButton = new JButton("Login");

		panel = new JPanel(new GridLayout(3, 1));
		panel.add(text1);
		panel.add(text2);
		panel.add(loginButton);
		add(panel, BorderLayout.CENTER);
		loginButton.addActionListener(this);
		setTitle("Cash Dispense");
	}

	public void actionPerformed(ActionEvent ae) {
		String username = text1.getText();
		String password = text2.getText();
		Login login = new Login(username, password);
		CashDispenseUI cashDespenser = new CashDispenseUI();
		try {
			cashDispenseService.loginUser(login);
			cashDespenser.setVisible(true);
			cashDespenser.actionPerformed(ae);
		}
		catch (InvalidLoginException e1) {
			JOptionPane.showMessageDialog(this, "Incorrect username or password",
					"Error", JOptionPane.ERROR_MESSAGE);
			text1.setText(" ");
			text2.setText(" ");
		}
		catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "Please ensure you provided corrected information", "error", JOptionPane.ERROR_MESSAGE);
			text1.setText(" ");
		}
	}
}

class LoginDemo {

	public static void main(String arg[]) {
		try {

			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			LoginUI frame = new LoginUI();
			frame.setSize(300, 300);
			frame.setVisible(true);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
