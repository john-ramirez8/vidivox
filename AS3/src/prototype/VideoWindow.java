package prototype;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VideoWindow extends JFrame {

	private WindowManager manager;
	private JPanel contentPane;

	public VideoWindow() {
		//JFrames to open
		final AddAudio aA = new AddAudio();
		final AddVoice aV = new AddVoice();
		
		//Setting up the contentPane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		setTitle("VidiVox Prototype");
		
		//Setting up WindowMananger
		manager = new WindowManager(contentPane);
		
		//Component declarations
		JPanel addPane = new JPanel();
		JPanel volumePane = new JPanel();
		JPanel playbackPane = new JPanel();
		JPanel buttonsPane = new JPanel();
		JLabel replaceMeWithVid = new JLabel();
		JButton btnAddAudio = new JButton("Add Audio");
		JButton btnAddVoice = new JButton("Add Voice");
		JButton btnPlay = new JButton();
		JButton btnRewind = new JButton("<<<");
		JButton btnFastforward = new JButton(">>>");
		JButton btnVolUp = new JButton("Volume Up");
		JButton btnVolDown = new JButton("Volume Down");
		JButton btnMute = new JButton("Mute");
		
		//Setting up the nested panels
		buttonsPane.setLayout(new GridLayout(1,3,20,0));
		buttonsPane.setBorder(new EmptyBorder(10,0,0,0));
		addPane.setLayout(new GridLayout(2,1,0,10));
		playbackPane.setLayout(new BorderLayout(5,0));
		volumePane.setLayout(new GridLayout(3,1,0,5));

		//Setting up the video player
		replaceMeWithVid.setIcon(new ImageIcon("Images/dummyImage.png"));
		
		//Setting up the add audio button
		btnAddAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.openWindow(aA);
			}
		});
		btnAddAudio.setPreferredSize(new Dimension(200, 30));
		
		//Setting up the add voice button
		btnAddVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.openWindow(aV);
			}
		});
		btnAddVoice.setPreferredSize(new Dimension(200, 30));
		
		//Setting up the play button
		btnPlay.setIcon(new ImageIcon("Images/play.png"));
		btnPlay.setPreferredSize(new Dimension(80, 80));
		
		//Setting up the rewind button
		btnRewind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		//Setting up the fast forward button
		btnFastforward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		//Setting up the volume up button
		btnVolUp.setPreferredSize(new Dimension(200, 30));
		
		//Setting up the volume down button
		btnVolDown.setPreferredSize(new Dimension(200, 30));
		
		//Setting up the mute button
		btnMute.setPreferredSize(new Dimension(200, 30));
		
		//Adding all components to the panel
		contentPane.add(replaceMeWithVid, BorderLayout.CENTER);
		addPane.add(btnAddVoice);
		addPane.add(btnAddAudio);
		playbackPane.add(btnRewind, BorderLayout.WEST);
		playbackPane.add(btnPlay, BorderLayout.CENTER);
		playbackPane.add(btnFastforward, BorderLayout.EAST);
		volumePane.add(btnVolUp);
		volumePane.add(btnVolDown);
		volumePane.add(btnMute);
		buttonsPane.add(addPane);
		buttonsPane.add(playbackPane);
		buttonsPane.add(volumePane);
		contentPane.add(buttonsPane, BorderLayout.SOUTH);
		pack();
	}
}
