package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class AudioPanel extends JPanel {
	
	private JTabbedPane audioPane = new JTabbedPane();
	
	public AudioPanel(VideoWindow parent) {
				
		setPreferredSize(new Dimension(250, 500));
		setLayout(new BorderLayout());
		JPanel festivalPanel = new CreateMP3Panel(parent);
		JPanel mergePanel = new MergePanel(parent);
		
		audioPane.addTab("Create MP3", festivalPanel);
		audioPane.addTab("Merge audio", mergePanel);
		add(audioPane, BorderLayout.CENTER);
		
	}
	
}
