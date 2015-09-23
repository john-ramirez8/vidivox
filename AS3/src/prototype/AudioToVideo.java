package prototype;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class AudioToVideo extends SwingWorker<Void, Void> {

	private String videoPath;
	private File mp3;
	private JPanel parentPanel;
	
	public AudioToVideo(String videoPath, File mp3, JPanel parentPanel) {
		this.videoPath = videoPath;
		this.mp3 = mp3;
		this.parentPanel = parentPanel;
	}
	
	public Void doInBackground() {
		
		String pathWithoutExtension = videoPath.substring(0,videoPath.length()-4);
		
		//Extracting audio from video, combining two audio, adding audio to a new video file, removing by-product files
		String cmd1 = "ffmpeg -i " + videoPath + " " +  pathWithoutExtension + ".mp3";
		String cmd2 = "ffmpeg -i " + pathWithoutExtension + ".mp3 -i " + mp3.getAbsolutePath() + " -filter_complex amix=inputs=2 " + pathWithoutExtension + "_combined.mp3";
		String cmd3 = "ffmpeg -i " + videoPath + " -i " + pathWithoutExtension + "_combined.mp3 -map 0:v -map 1:a " + pathWithoutExtension + "_with_" + mp3.getName().substring(0,mp3.getName().length()-4) + ".avi";
		String cmd4 = "rm " + pathWithoutExtension + ".mp3 " + pathWithoutExtension + "_combined.mp3";
		
		ProcessBuilder pb1 = new ProcessBuilder("/bin/bash", "-c", cmd1);
		ProcessBuilder pb2 = new ProcessBuilder("/bin/bash", "-c", cmd2);
		ProcessBuilder pb3 = new ProcessBuilder("/bin/bash", "-c", cmd3);
		ProcessBuilder pb4 = new ProcessBuilder("/bin/bash", "-c", cmd4);

		try {			
			Process p1 = pb1.start();
			p1.waitFor();
			Process p2 = pb2.start();
			p2.waitFor();
			Process p3 = pb3.start();
			p3.waitFor();
			Process p4 = pb4.start();
			p4.waitFor();
			
			JOptionPane.showMessageDialog(parentPanel, "Successfully saved new video in " + pathWithoutExtension + "_new.avi");
		} catch (IOException | InterruptedException e1) {
			 //TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
}
