package de.ff_hechtsheim.bftag.server;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public String getShortKeyword() {
		Pattern pattern = Pattern.compile("(^[BHWSUG]\\d+\\.\\d+)");
		Matcher matcher = pattern.matcher(keyword);
		if (matcher.find()){
		    return matcher.group(1);
		} else {
			return "";
		}
	}

	public String toDisplayString() {
		StringBuilder back = new StringBuilder();
		
		if (concreteKeyword != null && !"".equals(concreteKeyword)) {
			back.append(getShortKeyword()).append(" ").append(concreteKeyword).append("\n");
		} else {
			back.append(keyword).append("\n");
		}
		back.append(street);
		if (houseNumber != null && !"".equals(houseNumber)) {
			back.append(" ").append(houseNumber);
		}
		back.append("\n");
		if (additionalInfo != null && !"".equals(additionalInfo)) {
			back.append(additionalInfo).append("\n");
		}
		
		
		for(Entry<String, String> e: vehiclesWithGroups.entrySet()) {
			if(e.getValue() != null && e.getValue() != "null") {
				back.append(e.getValue()).append(": ").append(e.getKey()).append("\n");
			}
		}
		
		
		return back.toString();
	}
	
	public String toTTSString() {
		StringBuilder back = new StringBuilder();
		
		back.append("Einsatzalarm! ");
		if (concreteKeyword != null && !"".equals(concreteKeyword)) {
			back.append(getShortKeyword()).append(" ").append(concreteKeyword).append(". ");
		} else {
			back.append(keyword).append(". ");
		}
		back.append(street);
		if (houseNumber != null && !"".equals(houseNumber)) {
			back.append(" ").append(houseNumber);
		}
		back.append(additionalInfo).append(". ");
		
		back.append("Es rücken aus: ");
		for(Entry<String, String> e: vehiclesWithGroups.entrySet()) {
			back.append(e.getValue()).append(" mit dem ").append(e.getKey()).append(", ");
		}
		
		back.append("Ende der Durchsage");
		
		return back.toString();
	}
}
