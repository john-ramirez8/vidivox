package gui;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helpers.FileOpener;
import helpers.Main;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

/**
 * This Menu class is the first thing the user sees when opening the program.
 * It allows the user to either open a video to play, or exit. 
 * @author John Ramirez (jram948)
 *
 */
@SuppressWarnings("serial")
public class Menu extends JFrame {
	
	private JPanel contentPane;
	
	public Menu() {
		//JFrames to open
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 100, 20, 100));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1,0 , 10));
		setTitle("Menu");
		setResizable(false);
		
		//Component declarations
		JLabel lblVidiVox = new JLabel();
		JButton btnExit = new JButton("EXIT");
		JButton btnSelectVideo = new JButton("Select Video");
		
		final FileOpener fo = new FileOpener(".avi", this);
		
		//Setting up the label
		ImageIcon img = new ImageIcon(Main.class.getResource("/images/logo.png"));

		lblVidiVox.setIcon(img);
		lblVidiVox.setPreferredSize(new Dimension(300, 80));
		
		//Setting up the exit button
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setPreferredSize(new Dimension(200, 50));
		
		//Setting up the selectVideo button
		btnSelectVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File vid = fo.openFile();
				if (vid != null){
					setVisible(false);
					
					try {
						VideoWindow vp;
						vp = new VideoWindow(vid.getAbsolutePath());
						vp.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}		
				}
			}
		});
		btnSelectVideo.setPreferredSize(new Dimension(200, 50));
		
		//Adding all components to the panel
		contentPane.add(lblVidiVox);
		contentPane.add(btnSelectVideo);
		contentPane.add(btnExit);
		pack();
	}
}
