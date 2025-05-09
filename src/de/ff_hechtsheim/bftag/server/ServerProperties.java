package de.ff_hechtsheim.bftag.server;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bftag-server")
public class ServerProperties {
	
	private List<String> vehicles;
	private List<String> groups;
	
	public List<String> getVehicles() {
		return vehicles;
	}
	
	public List<String> getGroups() {
		return groups;
	}
	
	public void setVehicles(List<String> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
}
