package com.cognixia.contactmanager.gui.routing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.cognixia.contactmanager.gui.component.CreateAccount;
import com.cognixia.contactmanager.gui.component.Home;
import com.cognixia.contactmanager.gui.component.Login;
import com.cognixia.contactmanager.gui.entitycomponentsystem.ComponentManager;
import com.cognixia.contactmanager.gui.entitycomponentsystem.ComponentState;
import com.cognixia.contactmanager.model.User;

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
	
	public static ComponentState getState(String componentKey) {
		return gui.getComponentState(componentKey);
	}
	
	public static ComponentState initState(String componentKey) {
		return gui.initComponentState(componentKey);
	}

}
