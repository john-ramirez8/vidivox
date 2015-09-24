package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helpers.FileOpener;
import helpers.WindowManager;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AddAudio extends JFrame {

	private JPanel contentPane;
	private final String path;
	private ProgressBar progressBar;
	
	public AddAudio(String videoPath) throws Exception {
		
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
		
		//Setting up window manager
		final WindowManager wm = new WindowManager(contentPane); 
		
		//Component declarations
		JLabel lblMessage = new JLabel("Choose an mp3 file to add");
		JButton btnSearch = new JButton("Search");
		JButton btnCancel = new JButton("Cancel");
		JPanel buttonPane = new JPanel();
		
		final FileOpener fo = new FileOpener(".mp3", this);
		final FileOpener videoOpener = new FileOpener(".avi", this);

		//Setting up the label
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Setting up the search button
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File mp3 = fo.openFile();
				if (mp3 != null){
					JOptionPane.showMessageDialog(contentPane, "Now choose the name and where to save the file");
					String newVideoName = videoOpener.saveFile();

					if (newVideoName != null) {
						progressBar = new ProgressBar(path, mp3, newVideoName);				
						wm.openWindow(progressBar);
					}
					setVisible(false);
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
