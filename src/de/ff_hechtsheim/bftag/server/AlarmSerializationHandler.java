package de.ff_hechtsheim.bftag.server;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AlarmSerializationHandler {
	
	private static Logger logger = LoggerFactory.getLogger(RESTHandler.class);
	
	private static String folder = "./PredefinedAlarms/";
	
	public static void saveAlarm(String name, AlarmObject ao) {
		File file = new File(folder + name + ".json");
		ObjectMapper objMapper = new ObjectMapper();
		try {
			objMapper.writeValue(file, ao);
		} catch (IOException e) {
			logger.error("Writing alarm " + name + "to file failed: " + e.getMessage());
		}
	}
	
	public static Map<String, AlarmObject> getAlarms(){
		ObjectMapper objectMapper = new ObjectMapper();
		File folderFile = new File(folder);
		File[] jsons = folderFile.listFiles(f -> f.getName().endsWith(".json"));
		HashMap<String, AlarmObject> back = new HashMap<>();
		for(File f: jsons) {
			try {
				AlarmObject ao = objectMapper.readValue(f, AlarmObject.class);
				String name = f.getName().replaceAll("\\.json$", "");
				back.put(name, ao);
			} catch (IOException e) {
				logger.error("Cannot read alarm file + " + f.getName() + ": " + e.getMessage());
			}
		}
		return back;
	}

	public static void deleteAlarm(String name) {
		File file = new File(folder + name + ".json");
		if(file.delete()) {
			logger.info("Deleted alarm: " + name);
		} else {
			logger.warn("Could not delete: " + name);
		}
	}
}
