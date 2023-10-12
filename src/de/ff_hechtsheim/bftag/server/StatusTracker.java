package de.ff_hechtsheim.bftag.server;

import java.util.HashMap;
import java.util.Map;

public class StatusTracker {
	private Map<String, String> vehicleStatus  = new HashMap<>();
	private static final String SPECIAL_STATUS_PATTERN = "[50J]";
	
	public String getFullStatusForVehicle(String vehicle) {
		String status = vehicleStatus.get(vehicle);
		if(status == null) {
			return "2";
		} else {
			return status;
		}
	}
	
	public String getRealStatusforVehicle(String vehicle) {
		String full = getFullStatusForVehicle(vehicle);
		
		if(full.length() == 1) {
			return full;
		}
		String replaced = full.replaceAll(SPECIAL_STATUS_PATTERN, "");
		if(replaced.length() == 1) {
			return replaced;
		}  else {
			throw new IllegalStateException("Invalid full status: " + full);
		}
	}
	
	public void setStatusForVehicle(String vehicle, String status) {
		String full = status.toUpperCase();
		if(status.matches(SPECIAL_STATUS_PATTERN)) {
			full = full + getRealStatusforVehicle(vehicle);
		}
		vehicleStatus.put(vehicle, full);
	}
}
