package com.cognixia.contactmanager.gui.routing;

import com.cognixia.contactmanager.gui.component.Home;
import com.cognixia.contactmanager.gui.componentmanager.ComponentManager;

public class Router {
	private static ComponentManager gui = new ComponentManager(500, 500);

	static {
		gui.addComponent("home", new Home());
	}

	public static void setRoute(String key) {
		try {
			gui.setActiveComponent(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
