package gui.actionlisteners.audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import gui.MergePanel;
import gui.ProgressBar;
import gui.VideoWindow;
import helpers.FileOpener;
import helpers.WindowManager;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 * This class is used to invoke overlaying multiple audio over
 * the audio of the video (this will be done in a swingworker thread).
 * @author John Ramirez (jram948)
 *
 */
public class MergeAudioActionListener implements ActionListener {

	private final FileOpener videoOpener;
	private final WindowManager wm;
	private VideoWindow parentFrame;
	private HashMap<String, String> audioToAdd;
	private EmbeddedMediaPlayer video;
	private ArrayList<String> listOfAudio;
	private JPanel contentPane;
	private ProgressBar progressBar;
	private String oldVideoPath;
	private DefaultTableModel table;
	
	public MergeAudioActionListener(MergePanel parentPanel, VideoWindow parentFrame) {
		this.parentFrame = parentFrame;
		this.audioToAdd = parentPanel.getHashMap();
		this.video = parentFrame.getVideo();
		this.listOfAudio = parentPanel.getArrayList();
		this.oldVideoPath = parentFrame.getVideoPath();
		this.table = parentPanel.getTableModel();
		
		videoOpener = new FileOpener (".avi", this.parentFrame);
		wm = new WindowManager(parentPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Prompts the user to name the file and also location to save.
		JOptionPane.showMessageDialog(contentPane, "Choose the name and where to save the file");
		String newVideoPath = videoOpener.saveFile();
		
		if (newVideoPath != null) {
			
			//Creates a progress bar for the user
			progressBar = new ProgressBar(oldVideoPath, newVideoPath, video,
					audioToAdd, listOfAudio, table);
			wm.openWindow(progressBar);
		}
	}

}
