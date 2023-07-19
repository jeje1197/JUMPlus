package com.cognixia.contactmanager.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileStorage {
	
	public static void writeToFile(String path, String text) {
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
	
	public static void JSONify(Object o) {
		
	}
	
	public static Object parse(String JSON) {
		return null;
	}
	
	
}
