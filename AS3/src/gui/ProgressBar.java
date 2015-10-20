package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import swingworker.AudioToVideo;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

@SuppressWarnings("serial")
public class ProgressBar extends JFrame {
	
	
	private JPanel contentPane;
	private JProgressBar progressBar;
	private AudioToVideo bgTask;
	
	public ProgressBar(String path, File mp3, String newVideoName, EmbeddedMediaPlayer video) {
		
		//Setting up the contentPanel
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocation(100, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(3, 1));
		setTitle("Processing...");
		setResizable(false);
			
		//Setting up the progress label
		JLabel text = new JLabel("Processing new video. Please be patient");
		text.setFont(new Font("Tahoma", Font.PLAIN, 15));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Adding the progress bar and title
		JPanel progressPanel = new JPanel();
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressPanel.add(progressBar);

		contentPane.add(text, BorderLayout.NORTH);
		contentPane.add(progressPanel, BorderLayout.SOUTH);
		
		pack();	
		
		// Creating the new video file in the background
		bgTask = new AudioToVideo(path, mp3, contentPane, newVideoName, this, progressBar, video);
		bgTask.execute();	
		
	}
}
