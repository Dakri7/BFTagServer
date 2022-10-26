package de.ff_hechtsheim.bftag.server;

import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class AlarmServer {

	private DatagramSocket socket;
	private int port;
	private AlarmHandler alarmHandler;

	public AlarmServer(int port) throws SocketException {
		this.port = port;
	}

	private void start() throws IOException, JAXBException{
		socket = new DatagramSocket(port);
		JAXBContext context = JAXBContext.newInstance(AlarmObject.class);

		while (true) {
			byte[] buff = new byte[512];
			DatagramPacket recv = new DatagramPacket(buff, buff.length);
			socket.receive(recv);
			
			String s = new String(buff, "UTF-8");
			s = s.trim();
			
			Unmarshaller um = context.createUnmarshaller();
			AlarmObject ao = (AlarmObject) um.unmarshal(new StringReader(s));
			
			alarmHandler = new AlarmHandler();
			
			alarmHandler.alarmReceived(ao);
			
			System.out.println(ao.toTTSString());
		}
	}
	
	public void setAlarmHandler(AlarmHandler alarmHandler) {
		this.alarmHandler = alarmHandler;

	}

	public static void main(String[] args) throws SocketException, IOException, JAXBException {
		new AlarmServer(1903).start();
	}
}
