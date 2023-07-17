package com.cognixia.contactmanager.gui.routing;

import com.cognixia.contactmanager.gui.component.CreateAccount;
import com.cognixia.contactmanager.gui.component.Home;
import com.cognixia.contactmanager.gui.component.Login;
import com.cognixia.contactmanager.gui.entitycomponentsystem.ComponentManager;

public class Router {
	private static ComponentManager gui = new ComponentManager(500, 500);

	static {
		gui.addComponent("home", new Home());
		gui.addComponent("createAccount", new CreateAccount());
		gui.addComponent("login", new Login());
	}

	public static void setRoute(String key) {
		try {
			gui.setActiveComponent(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
