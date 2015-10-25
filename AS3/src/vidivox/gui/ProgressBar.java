package vidivox.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import vidivox.swingworker.AddAudio;

/**
 * This ProgressBar class is a JFrame that contains the progress bar
 * and is used to show the progress of the process to the user.
 * @author John Ramirez (jram948)
 * 
 */
@SuppressWarnings("serial")
public class ProgressBar extends JFrame {

	private JPanel contentPane;
	private JProgressBar progressBar;
	private AddAudio bgTask;
	
	public ProgressBar(String oldVideoPath, String newVideoPath, EmbeddedMediaPlayer video,
			HashMap<String, String> audioTimes, ArrayList<String> listOfAudio,
			DefaultTableModel table, HashMap<String, String> audioNames, VideoWindow videoFrame) {
		
		//Setting up the contentPane
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
		
		//Creating the new video file in the background
		bgTask = new AddAudio(oldVideoPath, audioTimes, newVideoPath,
				video, listOfAudio, this, table, audioNames, videoFrame);
		bgTask.execute();
		
	}
}
