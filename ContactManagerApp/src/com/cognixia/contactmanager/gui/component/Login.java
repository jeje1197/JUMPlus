package com.cognixia.contactmanager.gui.component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login extends JPanel {
	private static final long serialVersionUID = 1L;

	public Login() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Login");
		
		JButton button1 = new JButton("Login");

		button1.addActionListener(e -> {
			System.out.println("Logged In");
		});

		this.setSize(500, 500);
		this.add(title);
		this.add(button1);
	}

}