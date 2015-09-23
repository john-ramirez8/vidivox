package prototype;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;

public class AddAudio extends JFrame {

	private JPanel contentPane;
	private final String path;
	
	public AddAudio(String videoPath) {
		
		this.path = videoPath;
		
		//Setting up the contentPanel
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocation(100, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2,1));
		setTitle("Add Audio");
		setResizable(false);
		
		//Component declarations
		JLabel lblMessage = new JLabel("Choose an mp3 f"
				+ "ile to add");
		JButton btnSearch = new JButton("Search");
		JButton btnCancel = new JButton("Cancel");
		JPanel buttonPane = new JPanel();
		
		final FileOpener fo = new FileOpener(".mp3", this);
		
		//Setting up the label
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Setting up the search button
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File mp3 = fo.openFile();
				if (mp3 != null){
					setVisible(false);
					
					//Extracting audio from video, combining two audio, adding audio to a new video file
					String cmd1 = "ffmpeg -i " + path + " " +  path.substring(0,path.length()-4) + ".mp3";
					String cmd2 = "ffmpeg -i " + path.substring(0,path.length()-4) + ".mp3 -i " + mp3.getAbsolutePath() + " -filter_complex amix=inputs=2 " + path.substring(0,path.length()-4) + "_combined.mp3";
					String cmd3 = "ffmpeg -i " + path + " -i " + path.substring(0,path.length()-4) + "_combined.mp3 -map 0:v -map 1:a " + path.substring(0,path.length()-4) + "_new.avi";
					
					ProcessBuilder pb1 = new ProcessBuilder("/bin/bash", "-c", cmd1);
					ProcessBuilder pb2 = new ProcessBuilder("/bin/bash", "-c", cmd2);
					ProcessBuilder pb3 = new ProcessBuilder("/bin/bash", "-c", cmd3);

					try {			
						Process p1 = pb1.start();
						p1.waitFor();
						Process p2 = pb2.start();
						p2.waitFor();
						Process p3 = pb3.start();
						p3.waitFor();
						JOptionPane.showMessageDialog(contentPane, "Successfully saved new mp4.");
					} catch (IOException | InterruptedException e1) {
						 //TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}		
			}
		});
		
		
		btnSearch.setPreferredSize(new Dimension(120,23));
		
		//Setting up the cancel button
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setPreferredSize(new Dimension(120,23));
		
		//Adding all components to the panel
		contentPane.add(lblMessage);
		buttonPane.add(btnSearch);
		buttonPane.add(btnCancel);
		contentPane.add(buttonPane);
		pack();
	}
}
