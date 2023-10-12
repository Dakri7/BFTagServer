package de.ff_hechtsheim.bftag.server;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@PostMapping("/alarm")
	public void alarmPost(@RequestBody AlarmObject ao) {
		System.out.println(ao.getShortKeyword());

		AlarmHandler alarmHandler = new AlarmHandler();
		logger.info("Alarm received");
		alarmHandler.alarmReceived(ao);
	}

	@GetMapping("/status")
	private @ResponseBody String getStatus(String vehicle) {
		return tracker.getFullStatusForVehicle(vehicle);
	}

	@PutMapping("/status")
	public String putStatus(@RequestParam String vehicle, @RequestParam String status) {
		tracker.setStatusForVehicle(vehicle, status);
		logger.info("Set status of "+ vehicle + " to " + status);
		return tracker.getFullStatusForVehicle(vehicle);
	}
	
	@GetMapping("/vehicles")
	public String[] getVehicleList() {
		return new String[]{"LF 8/12", "LF 20/16"};
	}
}
