package de.ff_hechtsheim.bftag.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class AlarmWindow extends JFrame {
	
	public AlarmWindow(String alarmInfo, String shortKeyword, int minimalTextSize) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel alarmLabel = new JLabel("EINSATZ:");
		JTextArea informationArea = new JTextArea(alarmInfo);
		JLabel shortKeywordLabel = new JLabel(shortKeyword);

		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.setBackground(new Color(0xbb1e10));
		northPanel.add(alarmLabel, BorderLayout.WEST);
		northPanel.add(shortKeywordLabel);

		Font northFont = new Font("Arial", 0, screenSize.height / 12);
		informationArea.setEditable(false);
		fitFontToScreenSize(informationArea, screenSize.width, screenSize.height, minimalTextSize);
		alarmLabel.setFont(northFont);
		shortKeywordLabel.setFont(northFont);

		northPanel.add(alarmLabel, BorderLayout.WEST);
		northPanel.add(shortKeywordLabel, BorderLayout.EAST);
		getContentPane().add(northPanel, BorderLayout.NORTH);
		getContentPane().add(informationArea, BorderLayout.CENTER);
	}

	private static void fitFontToScreenSize(JTextArea area, int maxWidth, int maxHeight, int minTextSize) {
		int size = 200;
		Font f = new Font("Arial", 0, size); 
		area.setFont(f);
		while(area.getPreferredSize().width > maxWidth && size >= minTextSize) {
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
}
