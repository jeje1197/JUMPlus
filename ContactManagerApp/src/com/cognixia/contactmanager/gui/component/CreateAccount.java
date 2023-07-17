package com.cognixia.contactmanager.gui.component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cognixia.contactmanager.gui.routing.Router;

public class CreateAccount extends JPanel {
	private static final long serialVersionUID = 1L;

	public CreateAccount() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Create New Account");
		JButton button = new JButton("Create New Account");

		button.addActionListener(e -> {
			System.out.println("Create New Account");
			Router.setRoute("create account");
		});

		this.setSize(500, 500);
		this.add(title);
		this.add(button);
	}

}