package prototype;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VideoWindow extends JFrame {

	private String vidPath;
	private WindowManager manager;
	private JPanel contentPane;
	private FastForward ffTask;
	private Rewind rwTask;
	
	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private final EmbeddedMediaPlayer video;

	public VideoWindow(String path) {
		// JFrames to open
		final AddAudio aA = new AddAudio();
		final AddVoice aV = new AddVoice();

		// Setting up the contentPane & JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setTitle("VidiVox Prototype");
		setMinimumSize(new Dimension (500,421));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		// Setting up media components
		vidPath = path;
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		video = mediaPlayerComponent.getMediaPlayer();
		mediaPlayerComponent.setPreferredSize(new Dimension(854, 480));

		// Setting up WindowMananger
		manager = new WindowManager(contentPane);

		// Component declarations
		JPanel addPane = new JPanel();
		JPanel volumePane = new JPanel();
		JPanel volumeButtons = new JPanel();
		JPanel playbackPane = new JPanel();
		JPanel buttonsPane = new JPanel();
		JButton btnAddAudio = new JButton("Add Audio");
		JButton btnAddVoice = new JButton("Add Voice");
		final JButton btnPlay = new JButton();
		final JButton btnRewind = new JButton("<<<");
		final JButton btnFastForward = new JButton(">>>");
		final JButton btnVolUp = new JButton();
		final JButton btnVolDown = new JButton();
		final JButton btnMute = new JButton();
		final JSlider volSlider = new JSlider();

		// Setting up the nested panels
		buttonsPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		buttonsPane.setBorder(new EmptyBorder(10, 0, 0, 0));
		addPane.setLayout(new GridLayout(2, 1, 0, 10));
		playbackPane.setLayout(new BorderLayout(5, 0));
		volumePane.setLayout(new BorderLayout());

		// Setting up the add audio button
		btnAddAudio.setPreferredSize(new Dimension (140,29));
		btnAddAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.openWindow(aA);
			}
		});

		// Setting up the add voice button
		btnAddVoice.setPreferredSize(new Dimension (140,29));
		btnAddVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.openWindow(aV);
			}
		});

		// Setting up the play/pause button
		btnPlay.setIcon(new ImageIcon("Images/pause.png"));
		btnPlay.setPreferredSize(new Dimension(80, 80));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (video.isPlaying() == true){
					btnPlay.setIcon(new ImageIcon("Images/play.png"));
				} else {
					btnPlay.setIcon(new ImageIcon("Images/pause.png"));
				}
				video.pause();
			}
		});

		// Setting up the rewind button
		btnRewind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnRewind.getText().equals("<<<")) {
					rwTask = new Rewind(video, video.getRate());
					rwTask.execute();
					btnRewind.setText("Play");
				} else {
					rwTask.cancel(true);
					btnRewind.setText("<<<");
				}
			}
		});

		// Setting up the fast forward button
		btnFastForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnFastForward.getText().equals(">>>")) {
					ffTask = new FastForward(video, video.getRate());
					ffTask.execute();
					btnFastForward.setText("Play");
				} else {
					ffTask.cancel(true);
					btnFastForward.setText(">>>");
				}
			}
		});
		
		// Setting up the mute button
		btnMute.setPreferredSize(new Dimension(35, 35));
		btnMute.setIcon(new ImageIcon("Images/unmuted.png"));
		btnMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(video.isMute() == true){
					btnMute.setIcon(new ImageIcon("Images/unmuted.png"));
				} else {
					btnMute.setIcon(new ImageIcon("Images/muted.png"));
				}
				video.mute();
			}
		});
		
		// Setting up the volume up button
		btnVolUp.setIcon(new ImageIcon("Images/volUp.png"));
		btnVolUp.setPreferredSize(new Dimension(35, 35));
		btnVolUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (video.getVolume() < 100){
					volSlider.setValue(video.getVolume() + 10);
				}
			}
		});
		
		// Setting up the volume down button
		btnVolDown.setIcon(new ImageIcon("Images/volDown.png"));
		btnVolDown.setPreferredSize(new Dimension(35, 35));
		btnVolDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (video.getVolume() > 0){
					volSlider.setValue(video.getVolume() - 10);
				}
			}
		});


		// Setting up the volume slider
		
		volSlider.setValue(100);
		volSlider.setMaximum(100);
		volSlider.setMinimum(0);
		volSlider.setMinorTickSpacing(10);
		volSlider.setPaintTicks(true);
		volSlider.setSnapToTicks(true);
		volSlider.setPreferredSize(new Dimension(100,50));
		volSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				video.setVolume(volSlider.getValue());
			}
		});
		

		// Adding all components to the panel
		contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);
		addPane.add(btnAddVoice);
		addPane.add(btnAddAudio);
		playbackPane.add(btnRewind, BorderLayout.WEST);
		playbackPane.add(btnPlay, BorderLayout.CENTER);
		playbackPane.add(btnFastForward, BorderLayout.EAST);
		volumePane.add(volSlider, BorderLayout.NORTH);
		volumeButtons.add(btnVolUp);
		volumeButtons.add(btnVolDown);
		volumeButtons.add(btnMute);
		volumePane.add(volumeButtons, BorderLayout.CENTER);
		buttonsPane.add(addPane);
		buttonsPane.add(playbackPane);
		buttonsPane.add(volumePane);
		contentPane.add(buttonsPane, BorderLayout.SOUTH);
		pack();

		// Playing video specified
		video.playMedia(vidPath);
	}
}
