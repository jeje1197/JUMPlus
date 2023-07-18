package com.cognixia.contactmanager.gui.component;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import com.cognixia.contactmanager.gui.entitycomponentsystem.ComponentState;
import com.cognixia.contactmanager.gui.routing.Router;
import com.cognixia.contactmanager.model.Contact;

public class ContactMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	JList<Contact> contactList;
	ComponentState state;

	public ContactMenu(String componentKey) {
		state = Router.initState(componentKey);

		this.setLayout(null);
		JLabel title = new JLabel("Welcome, user!");
		JLabel contactInformation = new JLabel("Contacts: (" + 0 + ")");
		
		DefaultListModel<Contact> model = new DefaultListModel<>();
		contactList = new JList<>(model);
		contactList.setLayoutOrientation(JList.VERTICAL);
		contactList.setVisibleRowCount(7);

		JScrollPane listScroller = new JScrollPane(contactList);
		
		JButton addButton = new JButton("Add");
		JButton updateButton = new JButton("Update");
		JButton deleteButton = new JButton("Delete");
		JButton sortButton = new JButton("Sort By");
		
		JCheckBox sortAlphabetically = new JCheckBox("By Name");
        JCheckBox sortByDate = new JCheckBox("By Date");
		
		
		JButton backButton = new JButton("Log out");
		backButton.setBounds(25, 20, 80, 30);
		backButton.addActionListener(e -> {
			Router.returnToLastRoute();
			Router.popPreviousRoute();
		});

		title.setBounds(200, 30, 150, 50);
		contactInformation.setBounds(210, 60, 150, 50);
		listScroller.setBounds(125, 110, 250, 130);
		
		addButton.setBounds(80, 310, 100, 30);
		updateButton.setBounds(200, 310, 100, 30);
		deleteButton.setBounds(320, 310, 100, 30);
		sortButton.setBounds(200, 375, 100, 30);
		
		sortAlphabetically.setBounds(80, 360, 120, 30);
		sortByDate.setBounds(80, 390, 120, 30);

		addButton.addActionListener(e -> {
			System.out.println("Add Contact");
		});
		
		updateButton.addActionListener(e -> {
			System.out.println("Update Contact: " + contactList.getSelectedIndex());
			System.out.println(contactList.getSelectedIndex());
		});

		deleteButton.addActionListener(e -> {
			deleteContact();
		});

		sortButton.addActionListener(e -> {
			System.out.println("Sort Contacts");
		});

		this.setSize(500, 500);
		this.add(title);
		this.add(contactInformation);
		this.add(listScroller);
		this.add(addButton);
		this.add(updateButton);
		this.add(deleteButton);
		this.add(sortButton);
		this.add(sortAlphabetically);
		this.add(sortByDate);
		this.add(backButton);
	}

	private void addContact() {
		DefaultListModel<Contact> model = (DefaultListModel<Contact>) contactList.getModel();
		System.out.println(model.getSize());
		model.addElement(new Contact("Dolly", "", ""));
	}
	
	private void updateContact() {
		int selectedIndex = contactList.getSelectedIndex();
		if (selectedIndex  == -1) return;
	}
	
	private void deleteContact() {
		int selectedIndex = contactList.getSelectedIndex();
		if (selectedIndex  == -1) return;

		System.out.println("Delete Contact: " + selectedIndex);
		DefaultListModel<Contact> model = (DefaultListModel<Contact>) contactList.getModel();
		model.remove(selectedIndex);
	}
	
	private void sortContacts() {
		
		DefaultListModel<Contact> model = (DefaultListModel<Contact>) contactList.getModel();
	}
}