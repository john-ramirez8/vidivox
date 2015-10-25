package vidivox.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * This AudioPanel class is the main panel that holds the
 * CreateMP3Panel and MergePanel classes, as they are in a tabbed pane.
 * @author John Ramirez (jram948)
 *
 */
@SuppressWarnings("serial")
public class AudioPanel extends JPanel {
	
	private JTabbedPane audioPane = new JTabbedPane();
	
	public AudioPanel(VideoWindow parent) {
				
		//Creates the AudioPanel
		setPreferredSize(new Dimension(250, 500));
		setLayout(new BorderLayout());
		JPanel festivalPanel = new CreateMP3Panel(parent);
		JPanel mergePanel = new MergePanel(parent);
		
		audioPane.addTab("Create MP3", festivalPanel);
		audioPane.addTab("Merge audio", mergePanel);
		add(audioPane, BorderLayout.CENTER);
		
	}
	
}
