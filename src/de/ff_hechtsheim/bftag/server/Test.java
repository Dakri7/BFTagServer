package de.ff_hechtsheim.bftag.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Test {
	public static void main(String[] args) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(AlarmObject.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		AlarmObject ao = new AlarmObject();
		ao.setKeyword("H1.3");
		ao.setShortKeyword("H");
		ao.setStreet("hier");
		
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		
		m.marshal(ao, System.out);
		
//		ByteArrayInputStream bai = new ByteArrayInputStream(bao.toByteArray());
//		
//		Unmarshaller um = context.createUnmarshaller();
//		System.out.println(((AlarmObject)um.unmarshal(bai)).getShortKeyword());
	}
}
