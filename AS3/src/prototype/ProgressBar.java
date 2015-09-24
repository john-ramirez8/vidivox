package prototype;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class ProgressBar extends JFrame {
	
	
	private JPanel contentPane;
	private JProgressBar progressBar;
	private String path;
	private File mp3;
	private String newVideoName;
	private AudioToVideo bgTask;
	
	public ProgressBar(String path, File mp3, String newVideoName) {
		
		this.path = path;
		this.mp3 = mp3;
		this.newVideoName = newVideoName;
		
		//Setting up the contentPanel
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocation(100, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Processing...");
		setResizable(false);
		
		//Declaring component
		progressBar = new JProgressBar();
		
		//Adding the progress bar
		progressBar.setIndeterminate(true);
		contentPane.add(progressBar, BorderLayout.CENTER);
		
		pack();	
		
		bgTask = new AudioToVideo(path, mp3, contentPane, newVideoName, this, progressBar);
		bgTask.execute();	
		
	}
}
