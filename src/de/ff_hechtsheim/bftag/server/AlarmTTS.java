package de.ff_hechtsheim.bftag.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlarmTTS {
	
	private BufferedWriter writer;
	private static Logger logger = LoggerFactory.getLogger(RESTHandler.class);
	
	public AlarmTTS() {
		ProcessBuilder pb = new ProcessBuilder("espeak", "-v", "mb-de2", "-s", "110", "-p", "50");
		pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
		pb.redirectError(ProcessBuilder.Redirect.INHERIT);
		try {
			Process p = pb.start();
			writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
		} catch (IOException e) {
			logger.error("Creation of TTS Process failed: " + e.getMessage());
		}
	}
	
	public void alarm(AlarmObject ao) {
		gong();
		try {
			writer.write(ao.toTTSString());
			writer.newLine();
			writer.flush();
			
		} catch (IOException e) {
			logger.error("Alarm TTS failed: " + e.getMessage());
		}
		
	}
	
	public void announcement(String text) {
		gong();
		try {
			writer.write(text);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			logger.error("Announcement TTS failed: " + e.getMessage());
		}
	}
	
	private void gong() {
		ProcessBuilder pb = new ProcessBuilder("vlc", "--qt-start-minimized", "/home/daniel/Music/gong.wav", "vlc://quit");
		pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
		pb.redirectError(ProcessBuilder.Redirect.INHERIT);
		try {
			Process p = pb.start();
			p.waitFor();
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}
}
