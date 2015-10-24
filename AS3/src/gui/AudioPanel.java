package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class AudioPanel extends JPanel {
	
	private JTabbedPane audioPane = new JTabbedPane();
	
	public AudioPanel(JFrame parent) {
				
		setPreferredSize(new Dimension(250, 500));
		setLayout(new BorderLayout());
		JPanel festivalPanel = new CreateMP3Panel(parent);
		JPanel mergePanel = new JPanel();
		
		audioPane.addTab("Create MP3", festivalPanel);
		audioPane.addTab("Merge audio", mergePanel);
		add(audioPane, BorderLayout.CENTER);
		
	}
	
}
