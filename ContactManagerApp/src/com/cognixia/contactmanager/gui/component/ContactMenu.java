package com.cognixia.contactmanager.gui.component;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.cognixia.contactmanager.gui.entitycomponentsystem.ComponentState;
import com.cognixia.contactmanager.gui.routing.Router;
import com.cognixia.contactmanager.model.Contact;

public class ContactMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	JList<Contact> contactList;
	JCheckBox sortAlphabetically, sortByDate;
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

		sortAlphabetically = new JCheckBox("By Name");
		sortByDate = new JCheckBox("By Date");


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
			addContact();
		});

		updateButton.addActionListener(e -> {
			updateContact();
		});

		deleteButton.addActionListener(e -> {
			deleteContact();
		});

		sortButton.addActionListener(e -> {
			// Implementing sorting algorithm with Lambda and Java MultiThreading 
			Runnable runnable = () -> {
				sortContacts();
			};
			new Thread(runnable).start();
		});

		sortAlphabetically.addActionListener(e -> {
			sortByDate.setSelected(false);
		});

		sortByDate.addActionListener(e -> {
			sortAlphabetically.setSelected(false);
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
		model.addElement(new Contact("Dolly" + (model.getSize() % 3), "", ""));
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
		List<Contact> list = Arrays.stream(model.toArray())
				.map(o -> (Contact) o).collect(Collectors.toList());

		// Sorting list using custom comparators
		if (sortAlphabetically.isSelected()) {
			Collections.sort(list, (c1, c2) -> c1.getName().compareTo(c2.getName()));
		} 

		if (sortByDate.isSelected()) {
			Collections.sort(list, (c1, c2) -> c1.getDateCreated().compareTo(c2.getDateCreated()));;
		}

		DefaultListModel<Contact> newModel = new DefaultListModel<>();
		newModel.addAll(list);

		contactList.setModel(newModel);
	}
}