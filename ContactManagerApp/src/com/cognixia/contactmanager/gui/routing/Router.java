package com.cognixia.contactmanager.gui.routing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.cognixia.contactmanager.gui.component.ContactMenu;
import com.cognixia.contactmanager.gui.component.CreateAccount;
import com.cognixia.contactmanager.gui.component.Home;
import com.cognixia.contactmanager.gui.component.Login;
import com.cognixia.contactmanager.gui.componentsystem.ComponentManager;
import com.cognixia.contactmanager.gui.componentsystem.ComponentState;
import com.cognixia.contactmanager.model.User;
import com.cognixia.contactmanager.persistence.FileStorage;

public class Router {
	private static ComponentManager gui = new ComponentManager(500, 500);
	private static Stack<String> previousRoutes = new Stack<>();

	// Application related state
	public static List<User> accounts = new ArrayList<>();
	public static User user = null;

	static {
		gui.addComponent("home", new Home("home"));
		gui.addComponent("createAccount", new CreateAccount("createAccount"));
		gui.addComponent("login", new Login("login"));
		gui.addComponent("contactMenu", new ContactMenu("contactMenu"));

		List<User> accountsFromFile = FileStorage.parse(FileStorage.readFromFile("users"));
		if (accountsFromFile != null) {
			accounts = accountsFromFile;
		}

		System.out.println("On load: " + accounts);
		gui.addOnWindowCloseListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileStorage.writeToFile("users", FileStorage.JSONify(accounts));
				e.getWindow().dispose();
			}
		});
	}

	public static void setRoute(String key) {
		try {
			previousRoutes.push(gui.getActiveComponent());
			gui.setActiveComponent(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void returnToLastRoute() {
		setRoute(previousRoutes.pop());
	}

	public static void popPreviousRoute() {
		previousRoutes.pop();
	}

	public static ComponentState getState(String componentKey) {
		return gui.getComponentState(componentKey);
	}

	public static ComponentState initState(String componentKey) {
		return gui.initComponentState(componentKey);
	}

}
