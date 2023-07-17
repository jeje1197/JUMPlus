package com.cognixia.contactmanager.gui.componentmanager;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.cognixia.contactmanager.gui.componentmanager.exception.InvalidComponentKeyException;

public class ComponentManager {
	private JFrame frame;
	private String activeComponent = null;
	private Map<String, Component> components = new HashMap<>();

	public ComponentManager(int width, int height) {
		frame = new JFrame();
		frame.setLayout(null);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setSize(int width, int height) {
		frame.setSize(width, height);
	}

	public void addComponent(String key, Component component) {
		components.put(key, component);
	}
	
	public void removeComponent(String key) {
		components.remove(key);
		if (activeComponent != null && activeComponent.equals(key)) {
			frame.remove(0);
			activeComponent = null;
		}
	}

	public void setActiveComponent(String key) throws InvalidComponentKeyException {
		if (!components.containsKey(key)) {
			throw new InvalidComponentKeyException("Could not find component key: " + key);
		}
		
		// Remove previously active component
		if (activeComponent != null) {
			frame.remove(0);
			activeComponent = null;
		}
		
		Component c = components.get(key);
		activeComponent = key;
		
		// Add component and re-render
		frame.add(c);
		frame.setVisible(true);
	}

}
