package de.ff_hechtsheim.bftag.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AlarmTTS {
	
	private BufferedWriter writer;
	
	public AlarmTTS() {
		ProcessBuilder pb = new ProcessBuilder("wsl",  "ssh",  "192.168.178.175",  "-i",  "~/.ssh/id_pc",  "espeak", "-v", "mb-de2", "-s", "110", "-p", "50");
		pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
		pb.redirectError(ProcessBuilder.Redirect.INHERIT);
		try {
			Process p = pb.start();
			writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void speak(AlarmObject ao) {
		gong();
		try {
			writer.write(ao.toTTSString());
			writer.newLine();
			writer.flush();
			
		} catch (IOException e) {
			e.getStackTrace();
		}
		
	}
	
	private void gong() {
		ProcessBuilder pb = new ProcessBuilder("wsl",  "ssh",  "192.168.178.175",  "-i",  "~/.ssh/id_pc",  "vlc", "--qt-start-minimized", "/home/daniel/Music/gong.wav", "vlc://quit");
		pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
		pb.redirectError(ProcessBuilder.Redirect.INHERIT);
		try {
			Process p = pb.start();
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}
}
