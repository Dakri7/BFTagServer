package de.ff_hechtsheim.bftag.server;

import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"de.ff_hechtsheim.bftag.server"})
public class AlarmServer {

	private DatagramSocket socket;
	private int port;
	private AlarmHandler alarmHandler;

	public AlarmServer() throws SocketException {
	}
	
	public void setAlarmHandler(AlarmHandler alarmHandler) {
		this.alarmHandler = alarmHandler;

	}
	
	public static void main(String[] args) throws IOException{
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AlarmServer.class);
		builder.headless(false).run(args);
	}
}
