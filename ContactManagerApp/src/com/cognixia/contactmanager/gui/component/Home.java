package com.cognixia.contactmanager.gui.component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cognixia.contactmanager.gui.routing.Router;

public class Home extends JPanel {
	private static final long serialVersionUID = 1L;

	public Home() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Contact Manager");
		
		JButton button1 = new JButton("Create New Account");
		JButton button2 = new JButton("Login");
		
//		title.setLocation(150, 50);
//		button1.setBounds(150,200,200,40);
//		button2.setBounds(150,275,200,40);
		
		button1.addActionListener(e -> {
			System.out.println("Create New Account");
			Router.setRoute("create account");
		});
		
		button2.addActionListener(e -> {
			System.out.println("Login");
			Router.setRoute("login");
		});

		this.setSize(500, 500);
		this.add(title);
		this.add(button1);
		this.add(button2);
	}

}
