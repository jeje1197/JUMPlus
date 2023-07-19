package com.cognixia.contactmanager.gui.component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.cognixia.contactmanager.gui.entitycomponentsystem.ComponentState;
import com.cognixia.contactmanager.gui.routing.Router;
import com.cognixia.contactmanager.model.Contact;
import com.cognixia.contactmanager.validation.Validation;

public class ContactMenu extends JPanel implements Mountable {
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
			Router.user = null;
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
			showEditor("add");
		});

		updateButton.addActionListener(e -> {
			showEditor("update");
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
	
	@Override
	public void onMount() {
		if (Router.user == null) return;
		DefaultListModel<Contact> model = (DefaultListModel<Contact>) contactList.getModel();
		model.addAll(Router.user.getContacts());
	}

	private void showEditor(String mode) {
		int selectedIndex = contactList.getSelectedIndex();
		if (mode.equals("update") && selectedIndex == -1) {
			JOptionPane.showMessageDialog(null, "No contact selected.");
			return;
		}

		JTextField nameField = new JTextField(5);
		JTextField phoneField = new JTextField(5);
		JTextField emailField = new JTextField(5);
		
		if (mode.equals("update")) {
			Contact contactToUpdate = contactList.getModel().getElementAt(selectedIndex);
			nameField.setText(contactToUpdate.getName());
			phoneField.setText(contactToUpdate.getPhone());
			emailField.setText(contactToUpdate.getEmail());
		}

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Name:"));
		myPanel.add(nameField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Phone:"));
		myPanel.add(phoneField);
		myPanel.add(new JLabel("Email:"));
		myPanel.add(emailField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				mode.equals("add") ? "Create Contact" : "Update Contact" ,
						JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if (mode.equals("add")) {
				addContact(new Contact(nameField.getText(), phoneField.getText(), emailField.getText()));
			} else if (mode.equals("update")) {
				updateContact(new Contact(nameField.getText(), phoneField.getText(), emailField.getText()));
			}
		}
	}
	
	private boolean validateChanges(Contact c) {
		if (c.getName().isBlank()) {
			JOptionPane.showMessageDialog(null, "Please enter a valid name.");
			return false;
		} else if (!Validation.validatePhone(c.getPhone())) {
			JOptionPane.showMessageDialog(null, "Please enter a valid phone.");
			return false;
		} else if (!Validation.validateEmail(c.getEmail())) {
			JOptionPane.showMessageDialog(null, "Please enter a valid email.");
			return false;
		}
		return true;
	}

	private void addContact(Contact c) {
		if (!validateChanges(c)) return;
		
		DefaultListModel<Contact> model = (DefaultListModel<Contact>) contactList.getModel();
		model.addElement(c);
		Router.user.getContacts().add(c);
	}

	private void updateContact(Contact c) {
		if (!validateChanges(c)) return;
		
		int selectedIndex = contactList.getSelectedIndex();
		DefaultListModel<Contact> model = (DefaultListModel<Contact>) contactList.getModel();
		model.set(selectedIndex, c);
		Router.user.getContacts().set(selectedIndex, c);
	}

	private void deleteContact() {
		int selectedIndex = contactList.getSelectedIndex();
		if (selectedIndex  == -1) {
			JOptionPane.showMessageDialog(null, "No contact selected.");
			return;
		}

		DefaultListModel<Contact> model = (DefaultListModel<Contact>) contactList.getModel();
		model.remove(selectedIndex);
		Router.user.getContacts().remove(selectedIndex);
	}

	private void sortContacts() {
		DefaultListModel<Contact> model = (DefaultListModel<Contact>) contactList.getModel();
		if (model.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No contacts found.");
			return;
		}
		
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