package com.cognixia.contactmanager.gui.component;

import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cognixia.contactmanager.gui.entitycomponentsystem.ComponentState;
import com.cognixia.contactmanager.gui.routing.Router;
import com.cognixia.contactmanager.model.User;

public class Login extends JPanel {
	private static final long serialVersionUID = 1L;
	ComponentState state;

	public Login(String componentKey) {
		state = Router.initState(componentKey);

		this.setLayout(null);
		JLabel title = new JLabel("Login");
		JTextField email = new JTextField("email");
		JTextField password = new JTextField("password");
		
		JButton button = new JButton("Sign In");
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(25, 20, 70, 30);
		backButton.addActionListener(e -> {
			Router.returnToLastRoute();
		});
		
		
		title.setBounds(190, 30, 150, 50);
		email.setBounds(175, 200, 150, 30);
		password.setBounds(175, 240, 150, 30);
		button.setBounds(175, 280, 150, 30);
		button.addActionListener(e -> {
			state.putString("email", email.getText());
			state.putString("password", password.getText());
			login();
		});
		
		

		this.setSize(500, 500);
		this.add(title);
		this.add(email);
		this.add(password);
		this.add(button);
		this.add(backButton);
	}
	
	private void login() {
		Optional<User> found = Router.accounts.stream()
				.filter(user -> {
					return user.getEmail().equals(state.getString("email")) &&
							user.getPassword().equals(state.getString("password"));
				})
				.findFirst();
		
		if (!found.isPresent()) {
			JOptionPane.showMessageDialog(null, "Invalid credentials.");
		} else {
			JOptionPane.showMessageDialog(null, "Successfully logged in");
			Router.user = found.get();
			Router.setRoute("contactMenu");
		}
	}

}