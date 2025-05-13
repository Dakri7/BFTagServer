package de.ff_hechtsheim.bftag.server;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourceManager {
	
	private static String streetNamesFile = "./StreetNames.txt";
	private static String aaoFolder = "./AAO/";
	
	public static String[] getStreetNames() throws IOException {
		String allFiles = Files.readString(Path.of(streetNamesFile), StandardCharsets.UTF_8);
		return allFiles.split("\r?\n");
	}
	
	public static String[] getAAO(String category) throws IOException {
		if(!category.matches("[a-zA-Z]*")) {
			throw new IllegalArgumentException("Category may only contain letters: " + category);
		}
		String allFiles = Files.readString(Path.of(aaoFolder+category+".txt"), StandardCharsets.UTF_8);
		return allFiles.split("\r?\n");
	}
}
