package com.cognixia.contactmanager.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.cognixia.contactmanager.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileStorage {
	
	public static void writeToFile(String path, String text) {
		
		// Create file if doesn't exist
		try {
			File yourFile = new File(path);
			yourFile.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (FileWriter fw = new FileWriter(path);
				BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readFromFile(String path) {
		try (FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr)) {
			return String.join("\n", br.readLine()).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String JSONify(List<User> users) {
		Gson gson = new Gson();
		return gson.toJson(users);
	}
	
	public static List<User> parse(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<List<User>>(){}.getType());
	}
	
}
