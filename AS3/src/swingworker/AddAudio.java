package swingworker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class AddAudio extends SwingWorker<Void, Void> {

	private int count = 1;
	
	private String oldVideoPath;
	private HashMap<String, String> audioToAdd;
	private String newVideoPath;
	private EmbeddedMediaPlayer video;
	private ArrayList<String> listOfAudio;
	private JProgressBar progressBar;
	private JFrame parentFrame;
	
	public AddAudio(String oldVideoPath, HashMap<String, String> audioToAdd, String newVideoPath,
			EmbeddedMediaPlayer video, ArrayList<String> listOfAudio, JProgressBar progressBar,
			JFrame parentFrame) {
		this.oldVideoPath = oldVideoPath;
		this.audioToAdd = audioToAdd;
		this.newVideoPath = newVideoPath;
		this.video = video;
		this.listOfAudio = listOfAudio;
		this.progressBar = progressBar;
		this.parentFrame = parentFrame;
	}
	
	@Override
	protected Void doInBackground() throws Exception {

		String cmd = "ffmpeg -i " + oldVideoPath;
		
		for (int i = 0; i < audioToAdd.size(); i++) {
			String filePath = listOfAudio.get(i);
			cmd += " -itsoffset " + audioToAdd.get(filePath) + " -i " + filePath;
			count++;
		}
		
		cmd += " -filter_complex amix=" + count + " -async 1 " + newVideoPath;
		
		System.out.println(cmd);
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);

		try {
			Process p = pb.start();
			p.waitFor();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	protected void done() {
		parentFrame.setVisible(false);
		progressBar.setIndeterminate(false);
		
		String message = "Successfully saved new video in " + newVideoPath +
					".\n Would you like to play the new video?";
		int result = JOptionPane.showConfirmDialog(parentFrame, message, "Video successfully saved!", JOptionPane.YES_NO_OPTION);
		
		if (result == JOptionPane.YES_OPTION) {
			video.startMedia(newVideoPath);
			audioToAdd.clear();
			listOfAudio.clear();
		}
	}
}
