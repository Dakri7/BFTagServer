package de.ff_hechtsheim.bftag.server;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RESTHandler {

	Logger logger = LoggerFactory.getLogger(RESTHandler.class);
	StatusTracker tracker = new StatusTracker();

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/alarm")
	public void alarmPost(@RequestBody AlarmObject ao) {
		AlarmHandler alarmHandler = new AlarmHandler();
		logger.info("Alarm received");
		logger.info(ao.toDisplayString());
		alarmHandler.alarmReceived(ao);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/status")
	private @ResponseBody String getStatus(String vehicle) {
		return tracker.getFullStatusForVehicle(vehicle);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/status")
	public String putStatus(@RequestParam String vehicle, @RequestParam String status) {
		tracker.setStatusForVehicle(vehicle, status);
		logger.info("Set status of "+ vehicle + " to " + status);
		return tracker.getFullStatusForVehicle(vehicle);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/vehicles")
	public String[] getVehicleList() {
		return new String[]{"16/49", "16/48", "15/44"};
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/groups")
	public String[] getGroups() {
		return new String[]{"Gruppe 1", "Gruppe 2", "Gruppe 3"};
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/street-names")
	public String[] getStreetNames() throws IOException{
		return ResourceManager.getStreetNames();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/aao")
	public String[] getAAO(@RequestParam String category) throws IOException{
		return ResourceManager.getAAO(category);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/alarms")
	public @ResponseBody Map<String, AlarmObject> getAlarms(){
		return AlarmSerializationHandler.getAlarms();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/alarm")
	public void addAlarm(@RequestParam String name, @RequestBody AlarmObject ao){
		AlarmSerializationHandler.saveAlarm(name, ao);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/alarm")
	public void deleteAlarm(@RequestParam String name){
		AlarmSerializationHandler.deleteAlarm(name);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/announcement")
	public void triggerAnnouncement(@RequestBody String text){
		AlarmTTS alarmTTS = new AlarmTTS();
		alarmTTS.announcement(text);
	}
}
