package com.cognixia.contactmanager.gui.component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cognixia.contactmanager.gui.componentsystem.ComponentState;
import com.cognixia.contactmanager.gui.routing.Router;

public class Home extends JPanel implements Mountable {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private ComponentState state;

	public Home(String componentKey) {
		state = Router.initState(componentKey);

		this.setLayout(null);

		JLabel title = new JLabel("Contact Manager");

		JButton createAccountButton = new JButton("Create New Account");
		JButton loginButton = new JButton("Login");
		
		JLabel creator = new JLabel("Created by Joseph Evans");

		createAccountButton.addActionListener(e -> {
			Router.setRoute("createAccount");
		});

		loginButton.addActionListener(e -> {
			Router.setRoute("login");
		});
		
		title.setBounds(195, 30, 150, 50);
		createAccountButton.setBounds(175, 200, 150, 30);
		loginButton.setBounds(175, 240, 150, 30);
		creator.setBounds(179, 365, 150, 50);

		this.setSize(500, 500);
		this.add(title);
		this.add(createAccountButton);
		this.add(loginButton);
		this.add(creator);
	}

	@Override
	public void onMount() {
		// TODO Auto-generated method stub
		
	}
}
