package de.ff_hechtsheim.bftag.server;

import java.io.IOException;

public class AlarmHandler {
	
	private final int disposeTime = 180; //Time for the window to be open in seconds
	private final AlarmTTS alarmTTS = new AlarmTTS();
	
	public static final String POWER_OFF_COMMAND = "xset dpms force off";
	public static final String POWER_ON_COMMAND = "xset dpms force on";
	
	
	public void alarmReceived(AlarmObject ao) {
		final AlarmWindow window = new AlarmWindow(ao.toDisplayString(), ao.getKeyword(), 10);
		window.setVisible(true);
		
		new Thread() {
			@Override
			public void run() {
				super.run();
				try {
					Thread.sleep(disposeTime * 1000);
					Runtime.getRuntime().exec(POWER_ON_COMMAND);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (IOException e) {
					e.printStackTrace();
				}
				window.dispose();
			}
		}.start(); //Start a dispose timer
		
		try {
			Runtime.getRuntime().exec(POWER_ON_COMMAND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		alarmTTS.alarm(ao);
	}
}
