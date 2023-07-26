package com.cognixia.contactmanager.gui.componentsystem;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.cognixia.contactmanager.gui.component.Mountable;
import com.cognixia.contactmanager.gui.entitycomponentsystem.exception.InvalidComponentKeyException;

public class ComponentManager {
	private JFrame frame;
	private String activeComponent = null;
	private Map<String, Component> components = new HashMap<>();
	private Map<String, ComponentState> componentStates = new HashMap<>();

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
		initComponentState(key);
	}

	public void removeComponent(String key) {
		components.remove(key);
		if (activeComponent != null && activeComponent.equals(key)) {
			frame.remove(0);
			activeComponent = null;
		}
	}

	public String getActiveComponent() {
		return activeComponent;
	}

	public void setActiveComponent(String key) throws InvalidComponentKeyException {
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

		if (c instanceof Mountable m) {
			m.onMount();
		}

		// Add component and re-render
		frame.add(c);
		frame.revalidate();
		frame.repaint();
		frame.setVisible(true);
	}

	public ComponentState getComponentState(String componentKey) {
		return componentStates.get(componentKey);
	}

	public ComponentState initComponentState(String componentKey) {
		if (!componentStates.containsKey(componentKey)) {
			componentStates.put(componentKey, new ComponentState());
		}

		return componentStates.get(componentKey);
	}

	public void addOnWindowCloseListener(WindowAdapter listener) {
		frame.addWindowListener(listener);
	}
}
