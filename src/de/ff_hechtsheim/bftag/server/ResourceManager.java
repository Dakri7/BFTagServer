package de.ff_hechtsheim.bftag.server;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		String allFiles = Files.readString(Path.of(getFileForCategory(category).getAbsolutePath()), StandardCharsets.UTF_8);
		return allFiles.split("\r?\n");
	}
	
	public static String[] getAAOCategories() throws IOException {
		File aaoFolderFile = new File(aaoFolder);
		File[] categoryFiles = aaoFolderFile.listFiles();
		Stream<File> s = Arrays.stream(categoryFiles);
		
		List<String> back = s.map(f -> f.getName())
				.filter(fn -> fn.matches("^\\d\\d[a-zA-Z]+\\.txt$"))				//Only txt Files with index in front
				.sorted((a, b) -> Integer.compare(Integer.parseInt(a.substring(0, 2)), Integer.parseInt(b.substring(0, 2))))	// Sort by first digits
				.map(fn -> fn.substring(0, fn.length() - 4))	//Strip the .txt
				.map(fn -> fn.substring(2, fn.length()))		//Strip the .txt
				.collect(Collectors.toList());					//To List
		return back.toArray(new String[back.size()]);
	}
	
	private static File getFileForCategory(String category) {
		File aaoFolderFile = new File(aaoFolder);
		File[] categoryFiles = aaoFolderFile.listFiles();
		for(File f: categoryFiles) {
			if(f.getName().matches("^\\d\\d" + category + "\\.txt$")) {
				return f;
			}
		}
		throw new IllegalArgumentException("Category not found: " + category);
	}
}
