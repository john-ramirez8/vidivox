package vidivox.actionlisteners.audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
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
	private VideoWindow videoFrame;
	private ProgressBar progressBar;
	
	public MergeAudioActionListener(MergePanel parentPanel, VideoWindow videoFrame) {
		this.parentPanel = parentPanel;
		this.videoFrame = videoFrame;
		
		videoOpener = new FileOpener (".avi", this.videoFrame);
		wm = new WindowManager(parentPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Prompts the user to name the file and also location to save.
		JOptionPane.showMessageDialog(videoFrame, "Choose the name and where to save the file");
		String newVideoPath = videoOpener.saveFile();
		
		if (newVideoPath != null) {
			
			String oldVideoPath = videoFrame.getVideoPath();
			EmbeddedMediaPlayer video = videoFrame.getVideo();
			HashMap<String, String> audioTimes = parentPanel.getAudioTimesHashMap();
			ArrayList<String> listOfAudio = parentPanel.getArrayList();
			DefaultTableModel table = parentPanel.getTableModel();
			HashMap<String, String> audioNames = parentPanel.getAudioNamesHashMap();
			
			//Creates a progress bar for the user
			progressBar = new ProgressBar(oldVideoPath, newVideoPath, video, audioTimes,
					listOfAudio, table, audioNames, videoFrame);
			wm.openWindow(progressBar);
		}
	}

}
