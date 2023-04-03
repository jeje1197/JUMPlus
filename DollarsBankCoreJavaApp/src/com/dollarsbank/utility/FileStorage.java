package com.dollarsbank.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.controller.DollarsBankController;
import com.dollarsbank.model.Account;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileStorage {
	private static Gson gson = new Gson();
	
	/* Methods that load data from a file into the controller
	 * and write data from the controller to a file*/
	public static void loadAccountListFromFile(DollarsBankController controller, String filePath) {
		String fileContent = readFromFile(filePath);
		List<Account> accounts = null;
		try {
			accounts = accountListFromJSON(fileContent);
		} catch (Exception e) {
			System.err.println("Could not load account data from file.");
		}
		controller.setAccounts(accounts);
	}
	
	public static void saveAccountListToFile(DollarsBankController controller, String filePath) {
		List<Account> accounts = controller.getAccounts();
		String json = accountListToJSON(accounts);
		writeToFile(filePath, json);
	}
	
	
	/* Methods associated with serialization/deserialization
	 * and reading/writing to files 
	 */
	private static String accountListToJSON(List<Account> accounts) {
		return gson.toJson(accounts);
	}
	
	private static List<Account> accountListFromJSON(String JSON) {
		Type listType = new TypeToken<ArrayList<Account>>(){}.getType();
		return gson.fromJson(JSON, listType);
	}
	
	private static String readFromFile(String filePath) {
		try {
			return new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static boolean writeToFile(String filePath, String text) {
		try (FileWriter fw = new FileWriter(filePath); 
				BufferedWriter writer = new BufferedWriter(fw)) {
			writer.append(text);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
