package vidivox.swingworker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import vidivox.gui.VideoWindow;

/**
 * This AddAudio class is used to add multiple audio files
 * to the video file.
 * @author John Ramirez (jram948)
 * 
 */
public class AddAudio extends SwingWorker<Void, Void> {

	private int count = 1;
	
	private String oldVideoPath;
	private HashMap<String, String> audioTimes;
	private HashMap<String, String> audioNames;
	private String newVideoPath;
	private EmbeddedMediaPlayer video;
	private ArrayList<String> listOfAudio;
	private JFrame progressFrame;
	private DefaultTableModel table;
	private VideoWindow videoFrame;
	
	//Constructor
	public AddAudio(String oldVideoPath, HashMap<String, String> audioToAdd, String newVideoPath,
			EmbeddedMediaPlayer video, ArrayList<String> listOfAudio, JFrame progressFrame,
			DefaultTableModel table, HashMap<String, String> audioNames, VideoWindow videoFrame) {
		this.oldVideoPath = oldVideoPath;
		this.audioTimes = audioToAdd;
		this.newVideoPath = newVideoPath;
		this.video = video;
		this.listOfAudio = listOfAudio;
		this.progressFrame = progressFrame;
		this.table = table;
		this.audioNames = audioNames;
		this.videoFrame = videoFrame;
	}
	
	@Override
	protected Void doInBackground() throws Exception {

		//Creates the bash command that'll be used to overlay audio
		String cmd = "ffmpeg -i " + oldVideoPath;
		
		for (int i = 0; i < audioTimes.size(); i++) {
			String filePath = listOfAudio.get(i);
			cmd += " -itsoffset " + audioTimes.get(filePath) + " -i " + filePath;
			count++;
		}
		
		cmd += " -filter_complex amix=" + count + " -async 1 " + newVideoPath;
		
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);

		//Performs the command in bash
		try {
			Process p = pb.start();
			p.waitFor();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	//Removes the progress bar and asks if user would like to play the new video
	@Override
	protected void done() {
		progressFrame.setVisible(false);
		
		String message = "Successfully saved new video in " + newVideoPath +
					".\n Would you like to play the new video?";
		int result = JOptionPane.showConfirmDialog(videoFrame, message, "Video successfully saved!", JOptionPane.YES_NO_OPTION);
		
		if (result == JOptionPane.YES_OPTION) {
			//Clears the HashMaps, ArrayList and table for the new video
			audioTimes.clear();
			listOfAudio.clear();
			audioNames.clear();
			table.setRowCount(0);
			
			video.startMedia(newVideoPath); //Plays the new video
		}
	}
}
