package com.cognixia.contactmanager.gui.componentsystem;

import java.util.HashMap;
import java.util.Map;

public class ComponentState {
	private Map<String, Object> state = new HashMap<>();
	
	public Object get(String key) {
		return state.getOrDefault(key, null);
	}
	
	public String getString(String key) {
		return (String) state.getOrDefault(key, null);
	}
	
	public Integer getInteger(String key) {
		return (Integer) state.getOrDefault(key, null);
	}
	
	public Double getDouble(String key) {
		return (Double) state.getOrDefault(key, null);
	}
	
	public boolean hasKey(String key) {
		return state.containsKey(key);
	}
	
	public boolean put(String key, Object object) {
		state.put(key, object);
		return true;
	}
	
	public boolean putString(String key, String string) {
		state.put(key, string);
		return true;
	}
	
	public boolean putInteger(String key, Integer integer) {
		state.put(key, integer);
		return true;
	}
	
	public boolean putDouble(String key, Double d) {
		state.put(key, d);
		return true;
	}
}
