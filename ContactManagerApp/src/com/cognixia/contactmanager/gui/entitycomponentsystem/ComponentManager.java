package com.cognixia.contactmanager.gui.entitycomponentsystem;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.cognixia.contactmanager.gui.entitycomponentsystem.exception.InvalidComponentKeyException;

public class ComponentManager {
	private JFrame frame;
	private String activeComponent = null;
	private Map<String, Component> components = new HashMap<>();

	public ComponentManager(int width, int height) {
		frame = new JFrame();
		frame.setLayout(null);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
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
		System.out.println("Key: " + components.get(key));
		if (!components.containsKey(key)) {
			throw new InvalidComponentKeyException("Could not find component key: " + key);
		}
		
		// Remove previously active component
		// This needs to be frame.getContentPane().removeAll
		// Not frame.removeAll() otherwise it will freeze
		if (activeComponent != null) {
			frame.getContentPane().removeAll();
			activeComponent = null;
		}
		
		Component c = components.get(key);
		activeComponent = key;
		
		// Add component and re-render
		frame.add(c);
		frame.revalidate();
		frame.repaint();
		frame.setVisible(true);
	}

}
