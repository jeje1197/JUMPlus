package com.cognixia.contactmanager.gui.component;

import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cognixia.contactmanager.gui.componentsystem.ComponentState;
import com.cognixia.contactmanager.gui.routing.Router;
import com.cognixia.contactmanager.model.User;
import com.cognixia.contactmanager.validation.Validation;

public class CreateAccount extends JPanel implements Mountable {
	private static final long serialVersionUID = 1L;
	private ComponentState state;

	public CreateAccount(String componentKey) {
		state = Router.initState(componentKey);

		this.setLayout(null);
		JLabel title = new JLabel("Create New Account");

		JTextField email = new JTextField("email");
		JTextField password = new JTextField("password");

		JButton button = new JButton("Create");
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
			if (Validation.validateEmail(email.getText())) {
				JOptionPane.showMessageDialog(null, "Enter a valid email address.");
			}
			state.put("email", email.getText());
			state.put("password", password.getText());
			createAccount();
		});

		this.setSize(500, 500);
		this.add(title);
		this.add(email);
		this.add(password);
		this.add(button);
		this.add(backButton);
	}
	
	private void createAccount() {
		Optional<User> found = Router.accounts.stream()
				.filter(user -> user.getEmail().equals(state.getString("email")))
				.findFirst();

		if (found.isPresent()) {
			JOptionPane.showMessageDialog(null, "Email already exists.");
			return;	
		}
		
		User created = new User(state.getString("email"), state.getString("password"));
		Router.accounts.add(created);
		JOptionPane.showMessageDialog(null, "Account successfully created!");
		Router.returnToLastRoute();
	}

	@Override
	public void onMount() {}
}