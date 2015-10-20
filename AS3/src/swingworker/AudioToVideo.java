package swingworker;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import gui.ProgressBar;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class AudioToVideo extends SwingWorker<Void, Void> {

	private String videoPath;
	private File mp3;
	private JPanel parentPanel;
	private String newVideoName;
	private ProgressBar progressFrame;
	private JProgressBar progressBar;
	private EmbeddedMediaPlayer video;
	
	// Constructor
	public AudioToVideo(String videoPath, File mp3, JPanel parentPanel,
			String newVideoName, ProgressBar parentFrame, JProgressBar progressBar, EmbeddedMediaPlayer video) {
		this.videoPath = videoPath;
		this.mp3 = mp3;
		this.parentPanel = parentPanel;
		this.newVideoName = newVideoName;
		this.progressFrame = parentFrame;
		this.progressBar = progressBar;
		this.video = video;
	}
	
	@Override
	public Void doInBackground() {
		
		String pathWithoutExtension = videoPath.substring(0,videoPath.length()-4);
		
		//Extracting audio from video, combining two audio, adding audio to a new video file, removing by-product files, renaming file
		String cmd1 = "ffmpeg -i " + videoPath + " " +  pathWithoutExtension + ".mp3";
		String cmd2 = "ffmpeg -i " + pathWithoutExtension + ".mp3 -i " + mp3.getAbsolutePath() + " -filter_complex amix=inputs=2 " + pathWithoutExtension + "_combined.mp3";		
		String cmd3 = "ffmpeg -i " + videoPath + " -i " + pathWithoutExtension + "_combined.mp3 -map 0:v -map 1:a " + pathWithoutExtension + "_with_" + mp3.getName().substring(0,mp3.getName().length()-4) + ".avi";
		String cmd4 = "rm " + pathWithoutExtension + ".mp3 " + pathWithoutExtension + "_combined.mp3";
		String cmd5 = "mv " + pathWithoutExtension + "_with_" + mp3.getName().substring(0,mp3.getName().length()-4) + ".avi " + newVideoName;

		
		ProcessBuilder pb1 = new ProcessBuilder("/bin/bash", "-c", cmd1);
		ProcessBuilder pb2 = new ProcessBuilder("/bin/bash", "-c", cmd2);
		ProcessBuilder pb3 = new ProcessBuilder("/bin/bash", "-c", cmd3);
		ProcessBuilder pb4 = new ProcessBuilder("/bin/bash", "-c", cmd4);
		ProcessBuilder pb5 = new ProcessBuilder("/bin/bash", "-c", cmd5);

		// Performs the previous commands in bash
		try {			
			Process p1 = pb1.start();
			p1.waitFor();
			Process p2 = pb2.start();
			p2.waitFor();
			Process p3 = pb3.start();
			p3.waitFor();
			Process p4 = pb4.start();
			p4.waitFor();
			Process p5 = pb5.start();
			p5.waitFor();
			
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
	
	// Ends the progress bar and shows successful message to user
	@Override
	protected void done() {
		progressFrame.setVisible(false);
		progressBar.setIndeterminate(false);
		
		String message = "Successfully saved new video in " + newVideoName +
					".\n Would you like to play the new video";
		int result = JOptionPane.showConfirmDialog(parentPanel, message, "Video successfully saved!", JOptionPane.YES_NO_OPTION);
		
		if (result == JOptionPane.YES_OPTION) {
			video.startMedia(newVideoName);
		}
	}
}
