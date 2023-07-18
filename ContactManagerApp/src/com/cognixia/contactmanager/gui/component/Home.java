package com.cognixia.contactmanager.gui.component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cognixia.contactmanager.gui.entitycomponentsystem.ComponentState;
import com.cognixia.contactmanager.gui.routing.Router;

public class Home extends JPanel {
	private static final long serialVersionUID = 1L;
	private ComponentState state;

	public Home(String componentKey) {
		state = Router.initState(componentKey);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("Contact Manager");

		JButton button1 = new JButton("Create New Account");
		JButton button2 = new JButton("Login");

		button1.addActionListener(e -> {
			Router.setRoute("createAccount");
		});

		button2.addActionListener(e -> {
			Router.setRoute("login");
		});

		this.setSize(500, 500);
		this.add(title);
		this.add(button1);
		this.add(button2);
	}
}
