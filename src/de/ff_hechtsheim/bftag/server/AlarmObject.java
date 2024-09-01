package de.ff_hechtsheim.bftag.server;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

@Repository
public class AlarmObject {
	private String category;
	private String keyword;
	private String concreteKeyword;
	private String street;
	private String houseNumber;
	private Map<String, String> vehiclesWithGroups;
	private String reportant;
	private String additionalInfo;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getConcreteKeyword() {
		return concreteKeyword;
	}

	public void setConcreteKeyword(String concreteKeyword) {
		this.concreteKeyword = concreteKeyword;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Map<String, String> getVehiclesWithGroups() {
		return vehiclesWithGroups;
	}

	public void setVehiclesWithGroups(Map<String, String> vehiclesWithGroups) {
		this.vehiclesWithGroups = vehiclesWithGroups;
	}

	public String getReportant() {
		return reportant;
	}

	public void setReportant(String reportant) {
		this.reportant = reportant;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
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
