package prototype;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VideoWindow extends JFrame {

	private WindowManager manager;
	private JPanel contentPane;

	public VideoWindow() {
		//Helper classes
		manager = new WindowManager(contentPane);
		
		//JFrames to open
		final AddAudio aA = new AddAudio();
		final AddVoice aV = new AddVoice();
		
		//Setting up the contentPane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		setSize(884, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("VidiVox Prototype");
		
		//Component declarations
		JPanel replaceMeWithVid = new JPanel();
		JButton btnAddAudio = new JButton("Add Audio");
		JButton btnAddVoice = new JButton("Add Voice");
		JButton btnPlay = new JButton("Play");
		JButton btnRewind = new JButton("<<<");
		JButton btnFastforward = new JButton(">>>");
		JButton btnVolUp = new JButton("Volume Up");
		JButton btnVolDown = new JButton("Volume Down");
		
		//Setting up the video player
		replaceMeWithVid.setBackground(new Color(135, 206, 235));
		replaceMeWithVid.setBounds(10,11, 864, 449);
		
		//Setting up the add audio button
		btnAddAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.openWindow(aA);
			}
		});
		btnAddAudio.setBounds(10, 471, 200, 30);
		
		//Setting up the add voice button
		btnAddVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.openWindow(aV);
			}
		});
		btnAddVoice.setBounds(10, 520, 200, 30);
		
		//Setting up the play button
		btnPlay.setBounds(402, 470, 80, 80);
		
		//Setting up the rewind button
		btnRewind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRewind.setBounds(303, 499, 89, 23);
		
		//Setting up the fast forward button
		btnFastforward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFastforward.setBounds(492, 499, 89, 23);
		
		//Setting up the volume up button
		btnVolUp.setBounds(674, 471, 200, 30);
		
		//Setting up the volume down button
		btnVolDown.setBounds(674, 520, 200, 30);
		
		//Adding all components to the panel
		contentPane.add(replaceMeWithVid);
		contentPane.add(btnAddAudio);
		contentPane.add(btnAddVoice);
		contentPane.add(btnPlay);
		contentPane.add(btnRewind);
		contentPane.add(btnFastforward);
		contentPane.add(btnVolUp);
		contentPane.add(btnVolDown);
	}
}
