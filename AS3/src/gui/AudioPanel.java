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
	private JFrame parent;
	
	public AudioPanel(JFrame parent) {
		
		this.parent = parent;
		
		setPreferredSize(new Dimension(250, 500));
		setLayout(new BorderLayout());
		JPanel festivalPanel = new JPanel();
		JPanel mergePanel = new JPanel();
		
		audioPane.addTab("Create MP3", festivalPanel);
		audioPane.addTab("Merge audio", mergePanel);
		add(audioPane, BorderLayout.CENTER);
		
	}

	protected JPanel createInnerPanel(String title) {
		JPanel panelToAdd = new JPanel();
		JLabel panelTitle = new JLabel(title);
		
		panelTitle.setHorizontalAlignment(JLabel.CENTER);
		panelToAdd.setLayout(new GridLayout(1,1));
		panelToAdd.add(panelTitle);
		
		return panelToAdd;
	}
}
