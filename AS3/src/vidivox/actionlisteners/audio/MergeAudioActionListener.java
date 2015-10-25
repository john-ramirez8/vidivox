package vidivox.actionlisteners.audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import vidivox.gui.MergePanel;
import vidivox.gui.ProgressBar;
import vidivox.gui.VideoWindow;
import vidivox.helpers.FileOpener;
import vidivox.helpers.WindowManager;

/**
 * This class is used to invoke overlaying multiple audio over
 * the audio of the video (this will be done in a swingworker thread).
 * @author John Ramirez (jram948)
 *
 */
public class MergeAudioActionListener implements ActionListener {

	private final FileOpener videoOpener;
	private final WindowManager wm;
	private MergePanel parentPanel;
	private VideoWindow parentFrame;
	private ArrayList<String> listOfAudio;
	private ProgressBar progressBar;
	
	public MergeAudioActionListener(MergePanel parentPanel, VideoWindow parentFrame) {
		this.parentPanel = parentPanel;
		this.parentFrame = parentFrame;
		this.listOfAudio = parentPanel.getArrayList();
		
		videoOpener = new FileOpener (".avi", this.parentFrame);
		wm = new WindowManager(parentPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Prompts the user to name the file and also location to save.
		JOptionPane.showMessageDialog(parentFrame, "Choose the name and where to save the file");
		String newVideoPath = videoOpener.saveFile();
		
		if (newVideoPath != null) {
			
			//Creates a progress bar for the user
			progressBar = new ProgressBar(parentFrame.getVideoPath(), newVideoPath, parentFrame.getVideo(),
					parentPanel.getAudioTimesHashMap(), listOfAudio, parentPanel.getTableModel(),
					parentPanel.getAudioNamesHashMap());
			wm.openWindow(progressBar);
		}
	}

}
