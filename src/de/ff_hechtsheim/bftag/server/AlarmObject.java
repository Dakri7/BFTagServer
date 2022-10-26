package de.ff_hechtsheim.bftag.server;

import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alarm")
public class AlarmObject {
	private String keyword;
	private String shortKeyword;
	private String street;
	private Map<String, String> vehiclesWithGroups;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getShortKeyword() {
		return shortKeyword;
	}

	public void setShortKeyword(String shortKeyword) {
		this.shortKeyword = shortKeyword;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Map<String, String> getGroupsOnVehicles() {
		return vehiclesWithGroups;
	}

	public void setGroupsOnVehicles(Map<String, String> vehiclesWithGroups) {
		this.vehiclesWithGroups = vehiclesWithGroups;
	}
	
	public String toDisplayString() {
		StringBuilder back = new StringBuilder();
		
		back.append(keyword).append("\n");
		back.append(street).append("\n");
		
		for(Entry<String, String> e: vehiclesWithGroups.entrySet()) {
			back.append(e.getValue()).append(": ").append(e.getKey()).append("\n");
		}
		
		return back.toString();
	}
	
	public String toTTSString() {
		StringBuilder back = new StringBuilder();
		
		back.append("Einsatzalarm! ");
		back.append(keyword).append(". ");
		back.append(street).append(". ");
		
		back.append("Es rücken aus: ");
		for(Entry<String, String> e: vehiclesWithGroups.entrySet()) {
			back.append(e.getValue()).append(" mit dem ").append(e.getKey()).append(", ");
		}
		
		back.append("Ende der Durchsage");
		
		return back.toString();
	}

}
