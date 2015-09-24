package gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import swingworker.AudioToVideo;

public class ProgressBar extends JFrame {
	
	
	private JPanel contentPane;
	private JProgressBar progressBar;
	private AudioToVideo bgTask;
	
	public ProgressBar(String path, File mp3, String newVideoName) {
		
		// Setting up the contentPanel
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocation(100, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Processing...");
		setResizable(false);
			
		// Adding the progress bar
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		contentPane.add(progressBar, BorderLayout.CENTER);
		
		pack();	
		
		// Creating the new video file in the background
		bgTask = new AudioToVideo(path, mp3, contentPane, newVideoName, this, progressBar);
		bgTask.execute();	
		
	}
}
