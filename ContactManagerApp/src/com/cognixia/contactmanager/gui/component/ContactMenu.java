package com.cognixia.contactmanager.gui.component;

import java.awt.Dimension;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.cognixia.contactmanager.gui.entitycomponentsystem.ComponentState;
import com.cognixia.contactmanager.gui.routing.Router;
import com.cognixia.contactmanager.model.Contact;
import com.cognixia.contactmanager.model.User;

public class ContactMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	ComponentState state;

	public ContactMenu(String componentKey) {
		state = Router.initState(componentKey);

		this.setLayout(null);
		JLabel title = new JLabel("Welcome, user!");
		JLabel contactInformation = new JLabel("Contacts: (" + 0 + ")");
		
		JList<Contact> contactList = new JList<Contact>(new Contact[]{});
		contactList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		contactList.setLayoutOrientation(JList.VERTICAL);
		contactList.setVisibleRowCount(-1);

		JScrollPane listScroller = new JScrollPane(contactList);
		listScroller.setPreferredSize(new Dimension(250, 80));

		JButton addButton = new JButton("Add");
		JButton sortButton = new JButton("Sort By");
		JButton backButton = new JButton("Log out");
		backButton.setBounds(25, 20, 80, 30);
		backButton.addActionListener(e -> {
			Router.returnToLastRoute();
			Router.popPreviousRoute();
		});
		
		
		title.setBounds(190, 30, 150, 50);
		contactInformation.setBounds(190, 60, 150, 50);
		addButton.setBounds(175, 240, 150, 30);
		sortButton.setBounds(175, 280, 150, 30);

		addButton.addActionListener(e -> {
			System.out.println("Add Contact");
		});

		sortButton.addActionListener(e -> {
			System.out.println("Sort Contacts");
		});
		
		

		this.setSize(500, 500);
		this.add(title);
		this.add(contactInformation);
		this.add(contactList);
		this.add(addButton);
		this.add(sortButton);
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