package de.ff_hechtsheim.bftag.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class AlarmWindow extends JFrame {
	
	public AlarmWindow(AlarmObject ao, int minimalTextSize) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel alarmLabel = new JLabel("EINSATZ:");
		JTextArea informationArea = new JTextArea(ao.toDisplayString());
		JLabel shortKeywordLabel = new JLabel(ao.getShortKeyword());

		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.setBackground(new Color(0xbb1e10));
		northPanel.add(alarmLabel, BorderLayout.WEST);
		northPanel.add(shortKeywordLabel);

		Font northFont = new Font("Arial", 0, screenSize.height / 12);
		informationArea.setEditable(false);
		alarmLabel.setFont(northFont);
		shortKeywordLabel.setFont(northFont);

		northPanel.add(alarmLabel, BorderLayout.WEST);
		northPanel.add(shortKeywordLabel, BorderLayout.EAST);
		fitFontToScreenSize(informationArea, screenSize.width, screenSize.height - northPanel.getPreferredSize().height, minimalTextSize);
		getContentPane().add(northPanel, BorderLayout.NORTH);
		getContentPane().add(informationArea, BorderLayout.CENTER);
	}

	private static void fitFontToScreenSize(JTextArea area, int maxWidth, int maxHeight, int minTextSize) {
		int size = 200;
		Font f = new Font("Arial", 0, size); 
		area.setFont(f);
		while((area.getPreferredSize().width > maxWidth || area.getPreferredSize().height > maxHeight) && size >= minTextSize) {
			size -= 10;
			area.setFont(f.deriveFont((float)size));
		}
		if(size < minTextSize) {
			size = minTextSize;
			area.setFont(f.deriveFont((float)size));
			area.setLineWrap(true);
			area.setWrapStyleWord(true);
		}
	}
	
	public static void main(String[] args) {
		AlarmObject ao = new AlarmObject();
		ao.setKeyword("B2.4 Müllbrand");
		ao.setConcreteKeyword("LKW-Brand");
		ao.setCategory("Brand");
		ao.setStreet("Lion-Feuchtwanger-Straße 135");
		ao.setAdditionalInfo("Test");
		Map<String, String> vehiclesWithGroups = new HashMap<>();
		vehiclesWithGroups.put("HLF", "Gruppe 1");
		vehiclesWithGroups.put("LF KatS", "Gruppe 2");
		vehiclesWithGroups.put("Pool LF", "Gruppe 3");
		ao.setVehiclesWithGroups(vehiclesWithGroups);
		AlarmWindow aw = new AlarmWindow(ao, 10);
		aw.setVisible(true);
		AlarmTTS tts = new AlarmTTS();
		tts.alarm(ao);
	}
}
